package cn.tuyucheng.taketoday.rxjava.service.impl;

import cn.tuyucheng.taketoday.model.Movie;
import cn.tuyucheng.taketoday.rxjava.service.MoviePromiseService;
import ratpack.exec.Promise;

import java.util.ArrayList;
import java.util.List;

public class MoviePromiseServiceImpl implements MoviePromiseService {

   @Override
   public Promise<Movie> getMovie() {
      Movie movie = new Movie();
      movie.setName("The Godfather");
      movie.setYear("1972");
      movie.setDirector("Coppola");
      movie.setRating(9.2);
      return Promise.value(movie);
   }

   @Override
   public Promise<List<Movie>> getMovies() {
      Movie movie = new Movie();
      movie.setName("The Godfather");
      movie.setYear("1972");
      movie.setDirector("Coppola");
      movie.setRating(9.2);
      Movie movie2 = new Movie();
      movie2.setName("The Godfather Part 2");
      movie2.setYear("1974");
      movie2.setDirector("Coppola");
      movie2.setRating(9.0);
      List<Movie> movies = new ArrayList<>();
      movies.add(movie);
      movies.add(movie2);
      return Promise.value(movies);
   }

}
