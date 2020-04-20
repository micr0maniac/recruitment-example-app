package touk.cinema.domain;

import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ScreeningRepository {

    Screening getById(ScreeningId id);

    void save(Screening screening);

    void saveAll(Collection<Screening> screenings);

}
