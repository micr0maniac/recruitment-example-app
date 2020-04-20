package touk.cinema.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Ticket {

    TicketId id;

    int seatNumber;

    TicketType type;

    BigDecimal value;

    public Ticket(int seatNumber, TicketType type, BigDecimal price) {
        this.id = new TicketId();
        this.seatNumber = seatNumber;
        this.value = price;
        this.type = type;
    }

    public static Ticket of(int seatNumber, TicketType type) {
        switch (type) {
            case CHILD:
                return Ticket.child(seatNumber);
            case REGULAR:
                return Ticket.regular(seatNumber);
            case STUDENT:
                return Ticket.student(seatNumber);
            default:
                throw new RuntimeException("Invalid ticket type");
        }
    }

    public static Ticket child(int seatNumber) {
        return new Ticket(seatNumber, TicketType.CHILD, BigDecimal.valueOf(12.50));
    }

    public static Ticket student(int seatNumber) {
        return new Ticket(seatNumber, TicketType.STUDENT, BigDecimal.valueOf(18));
    }

    public static Ticket regular(int seatNumber) {
        return new Ticket(seatNumber, TicketType.REGULAR, BigDecimal.valueOf(25));
    }

    public void reducePricePercentage(int percentage) {
        value = value.divide(BigDecimal.valueOf(100 / percentage), 2, RoundingMode.DOWN);
    }

    public void increasePriceByAmount(BigDecimal amount) {
        value = value.add(amount);
    }

    public int seatNumber() {
        return seatNumber;
    }

    public BigDecimal value() {
        return value;
    }

}
