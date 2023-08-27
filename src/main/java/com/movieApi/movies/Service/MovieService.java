package com.movieApi.movies.Service;

import com.movieApi.movies.Repository.MovieRepository;
import com.movieApi.movies.entity.Movie;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public List<Movie> findAllMovies(){
        return movieRepository.findAll();
    }
    public Movie getMovieById(String id){
        Optional<Movie> movie = movieRepository.findMovieByImdbId(id);
        return movie.orElse(null);
    }
}
