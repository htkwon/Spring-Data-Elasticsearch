package com.example.project.entity;

import com.example.project.ArticleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private ArticleEnum articleEnum;


    public Article(String title, String content, User user,String articleEnum){
        this.title = title;
        this.content = content;
        this.user = user;
        this.articleEnum = ArticleEnum.valueOf(articleEnum);
    }

}
