package touk.cinema.domain;

public class Movie {

    private MovieId id;

    private String name;

    public Movie() {
    }

    public Movie(String name) {
        this.id = new MovieId();
        this.name = name;
    }

    public MovieId id() {
        return id;
    }

    public String name() {
        return name;
    }
}
