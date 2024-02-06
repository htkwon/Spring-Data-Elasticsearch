package com.example.project.controller;

import com.example.project.dto.ArticleResponseDto;
import com.example.project.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final ArticleService articleService;

    @GetMapping("/")
    public String home(){
        return "search";
    }


    @GetMapping("/article/search")
    public String searchByWordDto(@RequestParam String word, Model model){
        List<ArticleResponseDto> searchResults = articleService.findByContainWord(word);
        model.addAttribute("searchResults",searchResults);
        return "search";
    }




}
