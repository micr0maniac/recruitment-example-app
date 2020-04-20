package touk.cinema.domain;

import touk.cinema.domain.screeningroom.ScreeningRoom;
import touk.cinema.domain.screeningroom.ScreeningRoomSchema;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Screening {

    private ScreeningId id;

    private Movie movie;

    private ScreeningRoom room;

    private LocalDateTime start;

    private Collection<Reservation> reservations;

    public Screening() {

    }

    public Screening(Movie movie, ScreeningRoom room, LocalDateTime time) {
        this.id = new ScreeningId();
        this.movie = movie;
        this.start = time;
        this.room = room;
        this.reservations = new HashSet<>();
    }

    public ScreeningId id() {
        return id;
    }

    public LocalDateTime startAt() {
        return start;
    }

    public String movieName() {
        return movie.name();
    }

    public ScreeningRoomSchema screeningRoomSchema() {
        return room.schema();
    }

    public boolean isSeatAvailable(int seatNumber) {
        return !alreadyReservedSeats().contains(seatNumber);
    }

    /**
     * Assumption 2.
     * Seats can be booked at latest 15 minutes before the screening begins.
     *
     * Business Requirements 2.
     * There cannot be a single place left over in a row between two already reserved
     * places.
     *
     * reservation applies to at least one seat.
     */
    public Reservation makeReservation(ReservedBy by, Map<Integer, TicketType> seats, Optional<Voucher> voucher) {
        if (seats.size() == 0) {
            throw new RuntimeException("Bla bla at least one seat");
        }

        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(now, startAt());
        if (diff.toMinutes() < 15) {
            throw new RuntimeException("Reservations are allowed at least 15 minutes before screening starts.");
        }

        Set<Integer> wantedSeats = seats.keySet();
        if (alreadyReservedSeats().stream().anyMatch(wantedSeats::contains)) {
            throw new RuntimeException("At least one of seats is already taken.");
        }

        wantedSeats.addAll(alreadyReservedSeats());
        if (!room.isAbleToAllocateSeats(wantedSeats)) {
            throw new CinemaRoomAllocationException("There cannot be a single place left over in a row between two already reserved places.");
        }

        Set<Ticket> tickets = seats.entrySet().stream()
                .map(entry -> Ticket.of(entry.getKey(), entry.getValue()))
                .collect(Collectors.toSet());

        WeekendPricingPolicy weekendPricingPolicy = new WeekendPricingPolicy();
        if (weekendPricingPolicy.appliesToScreening(this)) {
            weekendPricingPolicy.increaseTicketsPrice(tickets);
        }

        Reservation reservation = new Reservation(by, tickets);

        voucher.ifPresent(reservation::applyVoucher);

        reservations.add(reservation);

        return reservation;
    }

    public Set<Integer> alreadyReservedSeats() {
        return reservations.stream()
                .map(Reservation::tickets)
                .flatMap(Collection::stream)
                .map(Ticket::seatNumber)
                .collect(Collectors.toSet());
    }

}
