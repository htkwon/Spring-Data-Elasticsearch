package com.example.project.controller;

import com.example.project.dto.SuggestDto;
import com.example.project.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AutoCompleteSuggestController {

    private final ArticleService articleService;


    /**
     * 검색 단어 자동완성을 위한 단어 검색
     */
    @GetMapping("/auto-complete")
    public List<SuggestDto> suggest(@RequestParam String input){
        List<SuggestDto> res = articleService.findBySuggestWord(input);
        return res;
    }




}
