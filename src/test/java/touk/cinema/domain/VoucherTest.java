package touk.cinema.domain;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VoucherTest {

    @Test
    public void discountTest() {
        ReservedBy by = ReservedBy.of("Lorem Ipsum");
        Set<Ticket> tickets = Sets.newHashSet(
            Ticket.child(1),
            Ticket.student(2),
            Ticket.regular(3)
        );

        Reservation reservation = new Reservation(by, tickets);
        Voucher voucher = new Voucher("dummy", 50);

        BigDecimal expectedAfterDiscount = tickets.stream().map(t -> {
            return t.value().divide(BigDecimal.valueOf(2), 2, RoundingMode.DOWN);
        }).reduce(BigDecimal.ZERO, BigDecimal::add);

        reservation.applyVoucher(voucher);
        BigDecimal totalAfterDiscount = reservation.total();

        assertEquals(totalAfterDiscount, expectedAfterDiscount);
    }

}