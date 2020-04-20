package touk.cinema.domain;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Business requirement
 *
 * Ticket price should be 4 PLN higher during the weekend (Friday 14:00 PM til Sunday
 * 11:00 PM)
 */
public class WeekendPricingPolicy {

    public boolean doesApplyToScreening() {
        return false;
    }

    public boolean appliesToScreening(Screening screening) {
        LocalDateTime start = screening.startAt();

        return (start.getDayOfWeek() == DayOfWeek.FRIDAY && start.getHour() >= 14) ||
                (start.getDayOfWeek() == DayOfWeek.SATURDAY) ||
                (start.getDayOfWeek() == DayOfWeek.SUNDAY && start.getHour() <= 11);
    }

    public void increaseTicketsPrice(Set<Ticket> tickets) {
        tickets.forEach(t -> t.increasePriceByAmount(BigDecimal.valueOf(4)));
    }

}
