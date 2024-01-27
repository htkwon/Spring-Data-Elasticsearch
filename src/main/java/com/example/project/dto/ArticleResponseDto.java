package com.example.project.dto;

import com.example.project.entity.ArticleDocument;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleResponseDto {

    private Long id;
    private String title;
    private String content;
    private UserDto userDto;
    private String articleEnum;

    public ArticleResponseDto(Long id, String title, String content,UserDto userDto,String articleEnum) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userDto = userDto;
        this.articleEnum = articleEnum;
    }

    public static ArticleResponseDto from(ArticleDocument article){
        return new ArticleResponseDto(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                    UserDto.of(article.getUserId(),article.getUsername()),
                article.getArticleEnum()
        );
    }



}
