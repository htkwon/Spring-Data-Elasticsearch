package com.example.project.repository;

import com.example.project.entity.ArticleDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ArticleSearchRepository extends ElasticsearchRepository<ArticleDocument,Long> {
    List<ArticleDocument> findByTitle(String title);
    List<ArticleDocument> findByContent(String content);
}
