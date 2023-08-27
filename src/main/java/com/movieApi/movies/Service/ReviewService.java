package com.movieApi.movies.Service;

import com.movieApi.movies.Repository.ReviewRepository;
import com.movieApi.movies.entity.Movie;
import com.movieApi.movies.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    MongoTemplate mongoTemplate;
    public Review createReview(String reviewBody ,String imdbId){

        Review review = new Review(reviewBody);
        review.setCreated(LocalDateTime.now());
        review.setUpdated(LocalDateTime.now());
        reviewRepository.insert(review);

        //using template to update the movie class
        //each movie contains an array of review ids

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviews").value(review))
                .first();

        return review;

    }
}
