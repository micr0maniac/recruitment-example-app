package touk.cinema.application.query.model;

import java.util.Collection;

public class QueryMovie {

    private String id;

    private String name;

    private Collection<QueryMovieScreening> screeningTimes;

    public QueryMovie(String id, String name, Collection<QueryMovieScreening> screeningTimes) {
        this.id = id;
        this.name = name;
        this.screeningTimes = screeningTimes;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<QueryMovieScreening> getScreeningTimes() {
        return screeningTimes;
    }
}
