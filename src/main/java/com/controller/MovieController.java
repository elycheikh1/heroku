package com.controller;


import com.model.Movie;
import com.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping()
    public List<Movie> getMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createMovie(@RequestBody Movie movie) {
        movieService.createMovie(movie);

    }

    @GetMapping(value = "/{id}")
    public Movie getMovie(@PathVariable("id") Integer movieId) {

        return movieService.getMovieById(movieId);

    }

    @PutMapping(value = "/{id}")
    public void updateMovie(@PathVariable("id") Integer movieId,
                             @RequestBody Movie movieRequest) {
       movieService.updateMovie(movieRequest, movieId);

    }

    @DeleteMapping(value = "/{id}")
    public void deleteMovie(@PathVariable("id") Integer movieId) {
        movieService.deleteMovieById(movieId);

    }


}
