package com.films4you.req4;

import com.films4you.main.RequirementInterface;
import java.util.HashMap;
import java.util.Map;

public class Requirement implements RequirementInterface {

  // Implementation for Custom Requirement

  public class Film {
    private String title;
    private String genre;
    private double rating;

    public Film(String title, String genre, double rating) {
      this.title = title;
      this.genre = genre;
      this.rating = rating;
    }

    // Getters and setters

    public String getTitle() {
      return title;
    }

    public String getGenre() {
      return genre;
    }

    public double getRating() {
      return rating;
    }
  }

  public class FilmRepository {
    private Map<String, Double> genreAverageRatings;

    public FilmRepository() {
      genreAverageRatings = new HashMap<>();
    }

    public void addFilm(Film film) {
      String genre = film.getGenre();
      double rating = film.getRating();

      if (genreAverageRatings.containsKey(genre)) {
        double currentAverageRating = genreAverageRatings.get(genre);
        double newAverageRating = (currentAverageRating + rating) / 2;
        genreAverageRatings.put(genre, newAverageRating);
      } else {
        genreAverageRatings.put(genre, rating);
      }
    }

    public double getAverageRatingByGenre(String genre) {
      if (genreAverageRatings.containsKey(genre)) {
        return genreAverageRatings.get(genre);
      } else {
        return 0.0;
      }
    }
  }

  @Override
  public String getValueAsString() {
    FilmRepository filmRepository = new FilmRepository();

    // Add films to the repository
    filmRepository.addFilm(new Film("Film A", "Action", 8.5));
    filmRepository.addFilm(new Film("Film B", "Comedy", 7.9));
    filmRepository.addFilm(new Film("Film C", "Drama", 8.2));
    filmRepository.addFilm(new Film("Film D", "Action", 9.1));

    // Get the average rating for the genre 'Action'
    double averageRating = filmRepository.getAverageRatingByGenre("Action");

    return String.valueOf(averageRating);
  }

  @Override
  public String getHumanReadable() {
    String value = getValueAsString();

    return "The average rating of films in the genre 'Action' is: " + value;
  }
}
