package touk.cinema.application.query.model;

public class QuerySeat {

    private int number;

    private boolean available;

    public QuerySeat(int number, boolean available) {
        this.number = number;
        this.available = available;
    }

    public int getNumber() {
        return number;
    }

    public boolean isAvailable() {
        return available;
    }
}