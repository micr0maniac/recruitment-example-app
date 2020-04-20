package touk.cinema.application.API.request;

import java.util.Collection;

public class MakeReservationRequest {
    private String firstName;
    private String lastName;
    private String voucherCode;
    private Collection<MakeReservationSeat> seats;

    public MakeReservationRequest(String firstName, String lastName, String voucherCode, Collection<MakeReservationSeat> seats) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.voucherCode = voucherCode;
        this.seats = seats;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public Collection<MakeReservationSeat> getSeats() {
        return seats;
    }
}
