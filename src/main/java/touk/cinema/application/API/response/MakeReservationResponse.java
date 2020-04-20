package touk.cinema.application.API.response;

import java.math.BigDecimal;

public class MakeReservationResponse {
    BigDecimal amount;

    public MakeReservationResponse(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
