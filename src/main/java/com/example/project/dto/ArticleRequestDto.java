package com.example.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleRequestDto {

    private String title;
    private String content;
    private String articleEnum;

}
