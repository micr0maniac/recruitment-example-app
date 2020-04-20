package touk.cinema.infrastructure.hibernate.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import touk.cinema.domain.Movie;
import touk.cinema.domain.MovieRepository;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
public class HibernateMovieRepository implements MovieRepository {

    private EntityManager entityManager;

    public HibernateMovieRepository(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public void save(Movie screening) {
        entityManager.persist(screening);
    }

    @Override
    public void saveAll(Collection<Movie> screenings) {
        screenings.forEach(screening -> entityManager.persist(screening));
    }
}
