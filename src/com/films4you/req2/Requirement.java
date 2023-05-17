package com.films4you.req2;

import com.films4you.main.RequirementInterface;

public class Requirement implements RequirementInterface {

  // Implementation for Requirement 2

  // Class representing the film entity
  public class Film {
    private String title;
    private int numberOfActors;

    public Film(String title, int numberOfActors) {
      this.title = title;
      this.numberOfActors = numberOfActors;
    }

    // Getters and setters

    public String getTitle() {
      return title;
    }

    public int getNumberOfActors() {
      return numberOfActors;
    }
  }

  // Class representing the film repository
  public class FilmRepository {
    private List<Film> films;

    public FilmRepository() {
      films = new ArrayList<>();
    }

    public void addFilm(Film film) {
      films.add(film);
    }

    public Film getFilmWithHighestNumberOfActors() {
      Film filmWithHighestActors = null;
      int highestNumberOfActors = 0;

      for (Film film : films) {
        if (film.getNumberOfActors() > highestNumberOfActors) {
          highestNumberOfActors = film.getNumberOfActors();
          filmWithHighestActors = film;
        }
      }

      return filmWithHighestActors;
    }
  }

  @Override
  public String getValueAsString() {
    FilmRepository filmRepository = new FilmRepository();

    // Add films to the repository
    filmRepository.addFilm(new Film("Film A", 10));
    filmRepository.addFilm(new Film("Film B", 5));
    filmRepository.addFilm(new Film("Film C", 8));

    // Get the film with the highest number of actors
    Film filmWithHighestActors = filmRepository.getFilmWithHighestNumberOfActors();

    if (filmWithHighestActors != null) {
      return filmWithHighestActors.getTitle();
    } else {
      return "No films found";
    }
  }

  @Override
  public String getHumanReadable() {
    String value = getValueAsString();

    if (value != null) {
      return "The film with the highest number of actors is: " + value;
    } else {
      return "No films found";
    }
  }
}
