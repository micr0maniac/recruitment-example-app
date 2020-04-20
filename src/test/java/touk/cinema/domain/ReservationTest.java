package touk.cinema.domain;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import touk.cinema.domain.screeningroom.ScreeningRoom;
import touk.cinema.domain.screeningroom.ScreeningRoomSchema;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ReservationTest {

    @Test
    public void seatsAmountTest() {
        ReservedBy by = ReservedBy.of("Lorem Ipsum");

        assertThrows(RuntimeException.class, () -> {
            Set<Ticket> noTickets = new HashSet<>();
            Reservation reservation = new Reservation(by, noTickets);
        });

        Assertions.assertThatCode(() -> {
            Set<Ticket> oneTicket = new HashSet<>(Collections.singletonList(
                Ticket.child(1)
            ));
            Reservation reservation = new Reservation(by, oneTicket);
        }).doesNotThrowAnyException();
    }

    @Test
    public void reservationTimeTest() {
        ReservedBy by = ReservedBy.of("Lorem Ipsum");
        Movie movie = new Movie("Dummy movie");
        ScreeningRoom screeningRoom = new ScreeningRoom("Dummy", ScreeningRoomSchema.withFixedNumberOfSeatsPerRow(1, 6));
        Map<Integer, TicketType> tickets = Maps.newHashMap(ImmutableMap.of(
            1, TicketType.CHILD,
            2, TicketType.CHILD
        ));

        LocalDateTime now = LocalDateTime.now();

        // screening starts now
        assertThrows(RuntimeException.class, () -> {
            Screening screeningStartsNow = new Screening(movie, screeningRoom, now);
            screeningStartsNow.makeReservation(by, tickets, Optional.empty());
        });

        // screening starts in 10 minutes
        assertThrows(RuntimeException.class, () -> {
            LocalDateTime tenMinutesLater = now.plusMinutes(10);
            Screening screeningStartsIn10Minutes = new Screening(movie, screeningRoom, tenMinutesLater);
            screeningStartsIn10Minutes.makeReservation(by, tickets, Optional.empty());
        });

        // screening starts in 30 minutes
        Assertions.assertThatCode(() -> {
            LocalDateTime halfHourLater = now.plusMinutes(30);
            Screening screeningStartsIn30Minutes = new Screening(movie, screeningRoom, halfHourLater);
            screeningStartsIn30Minutes.makeReservation(by, tickets, Optional.empty());
        }).doesNotThrowAnyException();

        // screening started day ago
        assertThrows(RuntimeException.class, () -> {
            LocalDateTime dayAgo = now.minusDays(1);
            Screening screeningStartedDayAgo = new Screening(movie, screeningRoom, dayAgo);
            screeningStartedDayAgo.makeReservation(by, tickets, Optional.empty());
        });
    }

}