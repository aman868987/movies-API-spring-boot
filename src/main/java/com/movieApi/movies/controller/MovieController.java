package com.movieApi.movies.controller;

import com.movieApi.movies.Service.MovieService;
import com.movieApi.movies.entity.Movie;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    MovieService movieService;
    @GetMapping("/allMovies")
    public ResponseEntity<?> getAllMovies(){
        return new ResponseEntity<>(movieService.findAllMovies(), HttpStatus.OK);
    }
    @GetMapping("/movie/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable String id){
        Movie result = movieService.getMovieById(id);
        if(result == null)
            return new ResponseEntity<>("no movie found with given id.",HttpStatus.OK);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
