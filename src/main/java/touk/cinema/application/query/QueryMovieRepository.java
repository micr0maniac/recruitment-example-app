package touk.cinema.application.query;

import touk.cinema.application.query.model.QueryMovie;

import java.time.LocalDateTime;
import java.util.Collection;

public interface QueryMovieRepository {

    Collection<QueryMovie> findUpcomingMovies(LocalDateTime from, LocalDateTime to);

}
