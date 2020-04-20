package touk.cinema.domain;

import java.io.Serializable;
import java.util.UUID;

public class ReservationId implements Serializable {

    private String id;

    public ReservationId() {
        id = UUID.randomUUID().toString();
    }

    public ReservationId(String id) {
        this.id = id;
    }

    public static ReservationId of(String id) {
        return new ReservationId(id);
    }

    @Override
    public String toString() {
        return id;
    }

}
