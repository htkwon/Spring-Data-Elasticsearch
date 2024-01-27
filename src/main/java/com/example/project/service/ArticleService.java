package com.example.project.service;

import com.example.project.dto.ArticleAllSaveDto;
import com.example.project.dto.ArticleResponseDto;
import com.example.project.dto.SearchDto;
import com.example.project.entity.Article;
import com.example.project.entity.ArticleDocument;
import com.example.project.entity.User;
import com.example.project.repository.ArticleRepository;
import com.example.project.repository.ArticleSearchQueryRepository;
import com.example.project.repository.ArticleSearchRepository;
import com.example.project.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
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
    public void saveAllArticle(List<ArticleAllSaveDto> dtos){
        List<Article> articleList = dtos.stream()
                .map(dto -> {
                    User user = userRepository.findById(dto.getUserId())
                            .orElseThrow(()-> new EntityNotFoundException("해당 유저가 없습니다."));
                    return Article.allSaveFrom(dto,user);
                })
                .toList();
        articleRepository.saveAll(articleList);
    }
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
}
