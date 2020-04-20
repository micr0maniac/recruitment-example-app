package touk.cinema.domain;

import java.io.Serializable;
import java.util.UUID;

public class VoucherId implements Serializable {

    private String id;

    public VoucherId() {
        id = UUID.randomUUID().toString();
    }

    public VoucherId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
}
