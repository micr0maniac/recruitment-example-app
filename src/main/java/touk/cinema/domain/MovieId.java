package touk.cinema.domain;

import java.io.Serializable;
import java.util.UUID;

public class MovieId implements Serializable {

    private String id;

    public MovieId() {
        id = UUID.randomUUID().toString();
    }

    public MovieId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
}
