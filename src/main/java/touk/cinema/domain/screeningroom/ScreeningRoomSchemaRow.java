package touk.cinema.domain.screeningroom;

import java.util.List;

public class ScreeningRoomSchemaRow {

    private int number;

    private List<Seat> seats;

    public ScreeningRoomSchemaRow(int number, List<Seat> seats) {
        this.number = number;
        this.seats = seats;
    }

    public int number() {
        return number;
    }

    public List<Seat> seats() {
        return seats;
    }
}
