package com.example.wantedpreonboarding.domain.page.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PageDto {
    private Long id;
    private String title;
    private String content;

    private List<SubPageDto> subPages;

    private List<String> breadCrumbs;
}
