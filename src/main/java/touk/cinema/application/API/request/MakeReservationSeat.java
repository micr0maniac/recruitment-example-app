package touk.cinema.application.API.request;

import touk.cinema.domain.TicketType;

public class MakeReservationSeat {
    Integer seatNumber;
    TicketType ticketType;

    public MakeReservationSeat(Integer seatNumber, TicketType ticketType) {
        this.seatNumber = seatNumber;
        this.ticketType = ticketType;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public TicketType getTicketType() {
        return ticketType;
    }
}