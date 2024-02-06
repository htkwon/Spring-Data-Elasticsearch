package com.example.project.controller;

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

    /**
     * JPA Data -> Elasticsearch SAVE
     */
    @PostMapping("/articleDocuments")
    public ResponseEntity<Void> saveArticleDocuments(){
        articleService.saveAllArticleDocument();
        return ResponseEntity.ok().build();
    }

    /**
     * Elasticsearch title 검색
     */
    @GetMapping("/article/title")
    public ResponseEntity<List<ArticleResponseDto>> searchByTitle(@RequestParam String title){
        List<ArticleResponseDto> res = articleService.findByTitle(title);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    /**
     * Elasticsearch content 검색
     */
    @GetMapping("/article/content")
    public ResponseEntity<List<ArticleResponseDto>> searchByContent(@RequestParam String content){
        List<ArticleResponseDto> res = articleService.findByContent(content);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    /**
     * Elasticsearch 전체 Document 삭제
     */
    @DeleteMapping("/articleDocuments/delete")
    public ResponseEntity<Void> deleteArticleDocument(){
        articleService.deleteArticleDocument();
        return ResponseEntity.ok().build();
    }

    /**
     * 조건 검색
     */
    @GetMapping("/article/search")
    public ResponseEntity<List<ArticleResponseDto>> searchBySearchDto(@RequestBody SearchDto searchDto){
        return ResponseEntity.ok(articleService.findBySearchDto(searchDto));
    }

    /**
     * (title,content,username FIELD 에서) word 단어 검색
     */
    @GetMapping("/article/search/all")
    public ResponseEntity<List<ArticleResponseDto>> searchByWord(@RequestParam String word){
        List<ArticleResponseDto> res = articleService.findByWord(word);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    /**
     * (title,content,username FIELD 에서) word 단어 포함 검색
     */
    @GetMapping("/article/search/contain")
    public ResponseEntity<List<ArticleResponseDto>> searchContainWord(@RequestParam String word){
        List<ArticleResponseDto> res = articleService.findByContainWord(word);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

}
