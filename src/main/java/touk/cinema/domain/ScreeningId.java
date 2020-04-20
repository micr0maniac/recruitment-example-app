package touk.cinema.domain;

import java.io.Serializable;
import java.util.UUID;

public class ScreeningId implements Serializable {

    private String id;

    public ScreeningId() {
        id = UUID.randomUUID().toString();
    }

    public ScreeningId(String id) {
        this.id = id;
    }

    public static ScreeningId of(String id) {
        return new ScreeningId(id);
    }

    @Override
    public String toString() {
        return id;
    }

}
