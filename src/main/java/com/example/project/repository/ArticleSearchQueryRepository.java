package com.example.project.repository;

import com.example.project.dto.SearchDto;
import com.example.project.entity.ArticleDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ArticleSearchQueryRepository {

    private final ElasticsearchOperations operations;


    public List<ArticleDocument> findBySearchDto(SearchDto searchDto){

        CriteriaQuery query = createSearchDtoCriteriaQuery(searchDto);

        SearchHits<ArticleDocument> search = operations.search(query,ArticleDocument.class);
        return search.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    /**
     * match 검색
     */
    public List<ArticleDocument> findByWord(String word){
        Criteria criteria = new Criteria("title").is(word).or("content").is(word)
                .or("username").is(word);
        Query query = new CriteriaQuery(criteria);
        SearchHits<ArticleDocument> search = operations.search(query, ArticleDocument.class);
        return search.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    public List<ArticleDocument> findByContainWord(String word){
        Criteria criteria = new Criteria("title").contains(word).or("content").contains(word)
                .or("username").contains(word);
        Query query = new CriteriaQuery(criteria);
        SearchHits<ArticleDocument> search = operations.search(query,ArticleDocument.class);
        return search.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    public List<ArticleDocument> findBySuggestWord(String input) {
        Criteria criteria = new Criteria("title").contains(input).or("content").contains(input);
        Query query = new CriteriaQuery(criteria);
        SearchHits<ArticleDocument> search = operations.search(query, ArticleDocument.class);
        return search.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }


    private CriteriaQuery createSearchDtoCriteriaQuery(SearchDto searchDto){
        CriteriaQuery query = new CriteriaQuery(new Criteria());

        if(searchDto == null){
            return query;
        }
        if(StringUtils.hasText(searchDto.getTitle())){
            query.addCriteria(Criteria.where("title").is(searchDto.getTitle()));
        }
        if(StringUtils.hasText(searchDto.getContent())){
            query.addCriteria(Criteria.where("content").is(searchDto.getContent()));
        }
        if(StringUtils.hasText(searchDto.getUsername())){
            query.addCriteria(Criteria.where("username").is(searchDto.getUsername()));
        }
        if(StringUtils.hasText(searchDto.getArticleEnum())){
            query.addCriteria(Criteria.where("articleEnum").is(searchDto.getArticleEnum()));
        }

        return query;

    }

}
