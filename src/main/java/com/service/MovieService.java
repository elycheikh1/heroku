package com.service;

import com.model.Movie;

import java.util.List;

public interface MovieService {


    List<Movie> getAllMovies();

    Movie getMovieById(int id);

    void createMovie(Movie movie);

    void updateMovie(Movie movieRequest, int movieId);

    void deleteMovieById(int id);
}
