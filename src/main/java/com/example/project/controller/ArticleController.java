package com.example.project.controller;

import com.example.project.dto.ArticleAllSaveDto;
import com.example.project.dto.ArticleResponseDto;
import com.example.project.dto.SearchDto;
import com.example.project.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ArticleController {

    private final ArticleService articleService;


    @PostMapping("/articles")
    public ResponseEntity<Void> saveAll(@RequestBody List<ArticleAllSaveDto> dto){
        articleService.saveAllArticle(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/articleDocuments")
    public ResponseEntity<Void> saveArticleDocuments(){
        articleService.saveAllArticleDocument();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/article/title")
    public ResponseEntity<List<ArticleResponseDto>> searchByTitle(@RequestParam String title){
        List<ArticleResponseDto> res = articleService.findByTitle(title);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/article/content")
    public ResponseEntity<List<ArticleResponseDto>> searchByContent(@RequestParam String content){
        List<ArticleResponseDto> res = articleService.findByContent(content);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @DeleteMapping("/articleDocuments/delete")
    public ResponseEntity<Void> deleteArticleDocument(){
        articleService.deleteArticleDocument();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/article/search")
    public ResponseEntity<List<ArticleResponseDto>> searchBySearchDto(@RequestBody SearchDto searchDto){
        return ResponseEntity.ok(articleService.findBySearchDto(searchDto));
    }



}
