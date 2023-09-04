package com.example.wantedpreonboarding.domain.page.service;


import com.example.wantedpreonboarding.domain.page.dto.PageDto;
import com.example.wantedpreonboarding.domain.page.dto.SubPageDto;
import com.example.wantedpreonboarding.domain.page.entity.Page;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;

@Service
public class PageService {
    private final DriverManagerDataSource dataSource =
            new DriverManagerDataSource("jdbc:mysql://localhost:3306/notion", "root", "");
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    public PageDto getPage(Long id) {
        try {
            String breadCrumbsSql = "SELECT p.title FROM sub_pages sp " +
                    "JOIN pages p on sp.parent_page_id = p.id " +
                    "WHERE sp.sub_page_id = ? " +
                    "ORDER BY sp.depth DESC";
            String subPageSql = "SELECT sb.sub_page_id, p.title FROM sub_pages sb JOIN pages p ON p.id=sb.sub_page_id WHERE parent_page_id = ? AND depth = 1";
            String getPageSql = "SELECT * FROM pages p WHERE id = ?";
            List<String> breadCrumbs = jdbcTemplate.query(breadCrumbsSql, (ResultSet rs, int rowNum) -> {
                return rs.getString("title");
            }, id);
            List<SubPageDto> subpages = jdbcTemplate.query(subPageSql, (ResultSet rs, int rowNum) -> {
                return new SubPageDto(rs.getLong("sub_page_id"), rs.getString("title"));
            }, id);
            Page page = jdbcTemplate.queryForObject(getPageSql, (ResultSet rs, int rowNum) -> {
                return new Page(rs.getLong("id"), rs.getString("title"), rs.getString("content"));
            }, id);

            return PageDto.builder()
                    .id(page.getId())
                    .title(page.getTitle())
                    .content(page.getContent())
                    .breadCrumbs(breadCrumbs)
                    .subPages(subpages).build();
        }
        catch (Exception e) {
            return null;
        }
    }
}
