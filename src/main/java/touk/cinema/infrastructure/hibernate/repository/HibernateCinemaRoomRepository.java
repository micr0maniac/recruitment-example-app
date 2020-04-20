package touk.cinema.infrastructure.hibernate.repository;

import org.springframework.stereotype.Repository;
import touk.cinema.domain.screeningroom.ScreeningRoom;
import touk.cinema.domain.screeningroom.CinemaRoomRepository;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
public class HibernateCinemaRoomRepository implements CinemaRoomRepository {

    private EntityManager entityManager;

    public HibernateCinemaRoomRepository(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public void save(ScreeningRoom screening) {
        entityManager.persist(screening);
    }

    @Override
    public void saveAll(Collection<ScreeningRoom> screenings) {
        screenings.forEach(screening -> entityManager.persist(screening));
    }
}
