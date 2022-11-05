package com.rabbitbr.apireviewml.controllers;

import com.rabbitbr.apireviewml.domain.Review;
import com.rabbitbr.apireviewml.dto.ReviewDTO;
import com.rabbitbr.apireviewml.services.ReviewService;
import java.io.IOException;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = {"api/reviews"})
@Slf4j
@AllArgsConstructor
public class ReviewController {

  private final ReviewService service;

  @GetMapping("/all")
  public ResponseEntity<List<Review>> getAll() throws IOException {
    return ResponseEntity.ok(service.getAll());
  }

  @PostMapping("/doc")
  public ResponseEntity save(@RequestBody ReviewDTO dto) throws IOException {
    return ResponseEntity.ok(service.save(dto));
  }
}
