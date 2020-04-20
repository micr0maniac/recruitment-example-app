package touk.cinema.application.query.model;

import java.time.LocalDateTime;

public class QueryMovieScreening {

    private String id;

    private LocalDateTime start;

    public QueryMovieScreening(String id, LocalDateTime start) {
        this.id = id;
        this.start = start;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getStart() {
        return start;
    }
}
