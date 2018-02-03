package com.service;

import com.dao.MovieRepository;
import com.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(int id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if(optionalMovie.isPresent()) {
            return optionalMovie.get();
        }
        return null;
    }

    @Override
    public void createMovie(Movie movie) {

        movieRepository.save(movie);

    }

    @Override
    public void updateMovie(Movie movieRequest, int movieId) {
        Movie movie = getMovieById(movieId);
        if(movie == null) {
            //TODO: throw MovieNotFoundException
        }

        //now we need to copy the values from movieRequest to the movie we got from DB.
        String title = movieRequest.getTitle();
        String director = movieRequest.getDirector();
        if(title != null && !title.trim().equals("")) {
            movie.setTitle(title);
        }
        if(director != null && !director.trim().equals("")) {
            movie.setDirector(director);
        }


        movieRepository.save(movie);
    }

    @Override
    public void deleteMovieById(int id) {
        movieRepository.deleteById(id);
    }
}
