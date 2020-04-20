package touk.cinema.infrastructure.hibernate.repository.query;

import org.springframework.stereotype.Repository;
import touk.cinema.application.query.QueryScreeningRepository;
import touk.cinema.application.query.model.QueryScreening;
import touk.cinema.domain.Screening;
import touk.cinema.domain.ScreeningId;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static java.util.stream.Collectors.groupingBy;

@Repository
public class HibernateQueryScreeningRepository implements QueryScreeningRepository {

    EntityManager entityManager;

    public HibernateQueryScreeningRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public QueryScreening getById(String screeningId) {
        Screening screening = entityManager.find(Screening.class, ScreeningId.of(screeningId));

        return QueryScreening.of(screening);
    }

}
