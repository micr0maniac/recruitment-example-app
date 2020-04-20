package touk.cinema.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

public class Reservation {

    ReservationId id;

    ReservedBy reservedBy;

    Set<Ticket> tickets;

    int discount;

    public Reservation(ReservedBy by, Set<Ticket> tickets) {
        if (tickets.isEmpty()) {
            throw new RuntimeException("There must at least one ticket in reservation");
        }

        this.id = new ReservationId();
        this.reservedBy = by;
        this.tickets = tickets;
    }

    public Set<Ticket> tickets() {
        return tickets;
    }

    public BigDecimal total() {
        BigDecimal ticketsValue = tickets.stream()
                .map(Ticket::value)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (discount > 0) {
            return ticketsValue.divide(BigDecimal.valueOf(100 / discount), 2, RoundingMode.DOWN);
        } else {
            return ticketsValue;
        }
    }

    public void applyVoucher(Voucher voucher) {
        discount = voucher.percentage();
    }

}
