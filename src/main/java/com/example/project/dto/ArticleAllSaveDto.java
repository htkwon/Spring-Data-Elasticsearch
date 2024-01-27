package com.example.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class ArticleAllSaveDto {

    private String title;
    private String content;
    private Long userId;
    private String articleEnum;


}
