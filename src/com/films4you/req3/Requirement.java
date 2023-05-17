package com.films4you.req3;

import com.films4you.main.RequirementInterface;

public class Requirement implements RequirementInterface {

  // Implementation for Requirement 3

  public class Film {
    private String title;
    private String description;

    public Film(String title, String description) {
      this.title = title;
      this.description = description;
    }

    // Getters and setters

    public String getTitle() {
      return title;
    }

    public String getDescription() {
      return description;
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

    public int getNumberOfFilmsWithKeyword(String keyword) {
      int count = 0;

      for (Film film : films) {
        if (film.getDescription().contains(keyword)) {
          count++;
        }
      }

      return count;
    }
  }

  @Override
  public String getValueAsString() {
    FilmRepository filmRepository = new FilmRepository();

    // Add films to the repository
    filmRepository.addFilm(new Film("Film A", "This is a film about nature"));
    filmRepository.addFilm(new Film("Film B", "A documentary film about wildlife"));
    filmRepository.addFilm(new Film("Film C", "A romantic comedy set in the city"));

    // Get the number of films with the keyword 'Documentary'
    int numberOfFilmsWithKeyword = filmRepository.getNumberOfFilmsWithKeyword("Documentary");

    return String.valueOf(numberOfFilmsWithKeyword);
  }

  @Override
  public String getHumanReadable() {
    String value = getValueAsString();

    return "The number of films with the keyword 'Documentary' is: " + value;
  }
}
