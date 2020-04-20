package touk.cinema.domain.screeningroom;

import org.springframework.stereotype.Repository;
import touk.cinema.domain.screeningroom.ScreeningRoom;

import java.util.Collection;

@Repository
public interface CinemaRoomRepository {

    void save(ScreeningRoom cinemaRoom);

    void saveAll(Collection<ScreeningRoom> cinemaRooms);

}
