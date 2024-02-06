package com.example.project.dto;

import com.example.project.entity.ArticleDocument;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SuggestDto {

    private String suggest;

    public SuggestDto(String title) {
        this.suggest = title;
    }

    public static SuggestDto from(ArticleDocument articleDocument){
        return new SuggestDto(
                articleDocument.getTitle()
        );
    }

}
