package com.example.wantedpreonboarding.domain.page.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Table(name = "pages")
@Entity
@Getter
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    public Page() {
    }

    public Page(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
