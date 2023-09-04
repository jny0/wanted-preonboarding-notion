package com.example.wantedpreonboarding.domain.page.entity;

import com.example.wantedpreonboarding.domain.page.entity.Page;
import jakarta.persistence.*;
import lombok.Getter;

@Table(name = "sub_pages")
@Entity
@Getter
public class SubPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_page_id")
    private Page parentPage;

    @ManyToOne
    @JoinColumn(name = "sub_page_id")
    private Page subPage;

    private int depth;

    public SubPage() {
    }
    public SubPage(Long id, Page parentPage, Page subPage, int depth) {
        this.id = id;
        this.parentPage = parentPage;
        this.subPage = subPage;
        this.depth = depth;
    }
}
