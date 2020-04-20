package touk.cinema.infrastructure.hibernate.repository;

import org.springframework.stereotype.Repository;
import touk.cinema.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Repository
public class HibernateScreeningRepository implements ScreeningRepository {

    private EntityManager entityManager;

    public HibernateScreeningRepository(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public Screening getById(ScreeningId id) {
        return entityManager.find(Screening.class, id);
    }

    @Override
    public void save(Screening screening) {
        entityManager.persist(screening);
    }

    @Override
    public void saveAll(Collection<Screening> screenings) {
        screenings.forEach(screening -> entityManager.persist(screening));
    }
}
