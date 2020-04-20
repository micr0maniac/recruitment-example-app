package touk.cinema.domain;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;
import touk.cinema.domain.screeningroom.ScreeningRoom;
import touk.cinema.domain.screeningroom.ScreeningRoomSchema;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeekendPricingPolicyTest {

    @Test
    public void increasePriceTest() {
        WeekendPricingPolicy weekendPricingPolicy = new WeekendPricingPolicy();

        Ticket childTicket = Ticket.child(1);
        Ticket studentTicket = Ticket.student(2);
        Ticket regularTicket = Ticket.regular(3);
        Set<Ticket> tickets = Sets.newHashSet(childTicket, studentTicket, regularTicket);

        assertEquals(childTicket.value(), BigDecimal.valueOf(12.50));
        assertEquals(studentTicket.value(), BigDecimal.valueOf(18));
        assertEquals(regularTicket.value(), BigDecimal.valueOf(25));

        weekendPricingPolicy.increaseTicketsPrice(tickets);

        assertEquals(childTicket.value(), BigDecimal.valueOf(16.50));
        assertEquals(studentTicket.value(), BigDecimal.valueOf(22));
        assertEquals(regularTicket.value(), BigDecimal.valueOf(29));
    }

    @Test
    public void reservationDuringWeekendTest() {
        ReservedBy by = ReservedBy.of("Lorem Ipsum");
        Movie movie = new Movie("Dummy movie");
        ScreeningRoom screeningRoom = new ScreeningRoom("Dummy", ScreeningRoomSchema.withFixedNumberOfSeatsPerRow(1, 6));
        Map<Integer, TicketType> seats = Maps.newHashMap(ImmutableMap.of(
            1, TicketType.CHILD,
            2, TicketType.STUDENT,
            3, TicketType.REGULAR
        ));

        // next monday
        LocalDateTime nextMonday = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        Screening screeningNextMonday = new Screening(movie, screeningRoom, nextMonday);
        Reservation nextMondayReservation = screeningNextMonday.makeReservation(by, seats, Optional.empty());

        BigDecimal expectedPrice = BigDecimal.valueOf(12.50 + 18 + 25);
        assertEquals(nextMondayReservation.total(), expectedPrice);

        // next saturday (higher price)
        LocalDateTime nextSaturday = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        Screening screeningNextSaturday = new Screening(movie, screeningRoom, nextSaturday);
        Reservation nextSaturdayReservation = screeningNextSaturday.makeReservation(by, seats, Optional.empty());

        BigDecimal expectedWeekendPrice = expectedPrice.add(BigDecimal.valueOf(3 * 4));
        assertEquals(nextSaturdayReservation.total(), expectedWeekendPrice);
    }

}