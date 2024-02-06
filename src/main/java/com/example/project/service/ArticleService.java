package com.example.project.service;

import com.example.project.dto.*;
import com.example.project.entity.ArticleDocument;
import com.example.project.repository.ArticleRepository;
import com.example.project.repository.ArticleSearchQueryRepository;
import com.example.project.repository.ArticleSearchRepository;
import com.example.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {
    private final ArticleSearchRepository articleSearchRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ArticleSearchQueryRepository articleSearchQueryRepository;


    @Transactional
    public void saveAllArticleDocument(){
        List<ArticleDocument> articleDocumentList = articleRepository.findAll().stream()
                .map(ArticleDocument::from)
                .toList();
        articleSearchRepository.saveAll(articleDocumentList);
    }
    public List<ArticleResponseDto> findByTitle(String title){
        return articleSearchRepository.findByTitle(title)
                .stream()
                .map(ArticleResponseDto::from)
                .collect(Collectors.toList());
    }
    public List<ArticleResponseDto> findByContent(String content){
        return articleSearchRepository.findByContent(content)
                .stream()
                .map(ArticleResponseDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteArticleDocument(){
        articleSearchRepository.deleteAll();
    }

    public List<ArticleResponseDto> findBySearchDto(SearchDto searchDto){
        return articleSearchQueryRepository.findBySearchDto(searchDto)
                .stream()
                .map(ArticleResponseDto::from)
                .collect(Collectors.toList());
    }

    public List<ArticleResponseDto> findByWord(String word) {
        return articleSearchQueryRepository.findByWord(word)
                .stream()
                .map(ArticleResponseDto::from)
                .collect(Collectors.toList());
    }
    public List<ArticleResponseDto> findByContainWord(String word){
        return articleSearchQueryRepository.findByContainWord(word)
                .stream()
                .map(ArticleResponseDto::from)
                .collect(Collectors.toList());
    }

    public List<SuggestDto> findBySuggestWord(String input){
        return articleSearchQueryRepository.findBySuggestWord(input)
                .stream()
                .map(SuggestDto::from)
                .collect(Collectors.toList());
    }
}
