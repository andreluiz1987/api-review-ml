package com.rabbitbr.apireviewml.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Review {

  private String name;
  private String movie;
  @JsonProperty("text_review")
  private String reviewText;
  @JsonProperty("review_prediction_value")
  private ReviewPrediction prediction;

}
