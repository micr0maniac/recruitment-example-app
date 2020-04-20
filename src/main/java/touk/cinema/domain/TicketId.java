package touk.cinema.domain;

import java.io.Serializable;
import java.util.UUID;

public class TicketId implements Serializable {

    private String id;

    public TicketId() {
        id = UUID.randomUUID().toString();
    }

    public TicketId(String id) {
        this.id = id;
    }

    public static TicketId of(String id) {
        return new TicketId(id);
    }

    @Override
    public String toString() {
        return id;
    }

}
