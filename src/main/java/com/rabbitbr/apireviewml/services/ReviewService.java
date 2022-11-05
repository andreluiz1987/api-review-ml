package com.rabbitbr.apireviewml.services;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Refresh;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.rabbitbr.apireviewml.domain.Review;
import com.rabbitbr.apireviewml.dto.ReviewDTO;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

  @Value("${es.indexName}")
  private String index;
  private final ElasticsearchClient client;

  public String save(ReviewDTO dto) throws IOException {
    Review review = Review.builder()
        .name(dto.getName())
        .reviewText(dto.getReviewText())
        .movie(dto.getMovie())
        .build();
    var response = client.index(i -> i
        .index(index)
        .document(review)
        .refresh(Refresh.WaitFor)
        .pipeline("review_classification_pipeline"));
    return response.result().jsonValue();
  }

  public List<Review> getAll() throws IOException {
    var response = client.search(s -> s.index(index)
            .query(MatchAllQuery.of(m -> m)._toQuery())
        , Review.class);
    List<Review> list = response.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
    Collections.reverse(list);
    return list;
  }
}
