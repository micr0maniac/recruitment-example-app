package touk.cinema.domain;

import java.util.Collection;

public interface MovieRepository {

    void save(Movie screening);

    void saveAll(Collection<Movie> screenings);

}
