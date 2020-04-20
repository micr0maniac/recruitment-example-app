package touk.cinema.infrastructure.hibernate.repository.query;

import org.springframework.stereotype.Repository;
import touk.cinema.application.query.QueryMovieRepository;
import touk.cinema.application.query.model.QueryMovie;
import touk.cinema.application.query.model.QueryMovieScreening;
import touk.cinema.domain.Movie;
import touk.cinema.domain.Screening;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Repository
public class HibernateQueryMovieRepository implements QueryMovieRepository {

    EntityManager entityManager;

    public HibernateQueryMovieRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Collection<QueryMovie> findUpcomingMovies(LocalDateTime from, LocalDateTime to) {
        TypedQuery<Tuple> query = entityManager.createQuery(
            "SELECT s, m FROM touk.cinema.domain.Screening s " +
                    "INNER JOIN s.movie m " +
                    "WHERE s.start > :from AND s.start < :to " +
                    "ORDER BY m.name, s.start", Tuple.class)
                .setParameter("from", from)
                .setParameter("to", to);

        List<Tuple> results = query.getResultList();
        Map<Movie, List<Tuple>> grouped = results
                .stream()
                .collect(groupingBy(t -> t.get(1, Movie.class)));

        Collection<QueryMovie> queryModels = grouped.entrySet().stream()
                .map(pair -> {
                    Movie movie = pair.getKey();
                    return new QueryMovie(
                        movie.id().toString(),
                        movie.name(),
                        pair.getValue().stream()
                                .map(v -> {
                                    Screening screening = v.get(0, Screening.class);
                                    return new QueryMovieScreening(screening.id().toString(), screening.startAt());
                                })
                                .collect(Collectors.toList())
                    );
                })
                .collect(Collectors.toList());

        return queryModels;
    }

}
