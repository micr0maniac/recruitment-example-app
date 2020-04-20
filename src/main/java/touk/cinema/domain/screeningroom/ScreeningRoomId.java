package touk.cinema.domain.screeningroom;

import java.io.Serializable;
import java.util.UUID;

public class ScreeningRoomId implements Serializable {

    private String id;

    public ScreeningRoomId() {
        id = UUID.randomUUID().toString();
    }

    public ScreeningRoomId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }

}
