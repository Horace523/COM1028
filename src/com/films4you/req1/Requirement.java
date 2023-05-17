package com.films4you.req1;

import com.films4you.main.RequirementInterface;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Requirement implements RequirementInterface {

  // Implementation for Requirement 1

  public class Film {
    private String title;

    public Film(String title) {
      this.title = title;
    }

    // Getter
    public String getTitle() {
      return title;
    }
  }

  public class FilmRepository {
    private List<Film> films;

    public FilmRepository() {
      films = new ArrayList<>();
    }

    public void addFilm(Film film) {
      films.add(film);
    }

    public List<Film> getFilmsWithTitleStartingWith(String prefix) {
      List<Film> result = new ArrayList<>();
      for (Film film : films) {
        if (film.getTitle().startsWith(prefix)) {
          result.add(film);
        }
      }
      return result;
    }
  }

  @Override
  public String getValueAsString() {
    FilmRepository filmRepository = new FilmRepository();

    // Add films to the repository
    filmRepository.addFilm(new Film("Django Unchained"));
    filmRepository.addFilm(new Film("The Dark Knight"));
    filmRepository.addFilm(new Film("Inception"));
    filmRepository.addFilm(new Film("The Shawshank Redemption"));

    // Get films with titles starting with 'D'
    List<Film> filmsStartingWithD = filmRepository.getFilmsWithTitleStartingWith("D");

    // Create a string representation of the films
    StringBuilder sb = new StringBuilder();
    for (Film film : filmsStartingWithD) {
      sb.append(film.getTitle()).append(", ");
    }

    // Remove the trailing comma and space
    if (sb.length() > 0) {
      sb.setLength(sb.length() - 2);
    }

    return sb.toString();
  }

  @Override
  public String getHumanReadable() {
    String value = getValueAsString();

    return "Films with titles starting with 'D': " + value;
  }
}
