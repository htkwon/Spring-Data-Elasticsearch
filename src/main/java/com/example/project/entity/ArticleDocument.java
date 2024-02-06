package com.example.project.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.*;

@Getter
@Setter
@NoArgsConstructor
@Document(indexName = "article")
@Mapping(mappingPath = "elastic/article-mapping.json")
@Setting(settingPath = "elastic/article-setting.json")
public class ArticleDocument {

    @Id
    @Field(type = FieldType.Keyword)
    private Long id;

    @Field(type = FieldType.Text)
    private String title;
    @Field(type = FieldType.Text)
    private String content;


    @Field(type = FieldType.Long)
    private Long userId;
    @Field(type = FieldType.Text)
    private String username;

    @Field(type = FieldType.Text)
    private String articleEnum;


    public ArticleDocument(Long id, String title, String content, Long userId, String userName,String articleEnum) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.username = userName;
        this.articleEnum = articleEnum;
    }

    public static ArticleDocument from(Article article){
        return new ArticleDocument(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getUser().getId(),
                article.getUser().getUserName(),
                String.valueOf(article.getArticleEnum())
        );
    }

}
