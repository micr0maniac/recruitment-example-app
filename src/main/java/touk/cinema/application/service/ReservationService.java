package touk.cinema.application.service;

import com.google.common.base.Strings;
import org.springframework.stereotype.Component;
import touk.cinema.application.API.request.MakeReservationSeat;
import touk.cinema.domain.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ReservationService {

    ScreeningRepository screeningRepository;

    VoucherRepository voucherRepository;

    public ReservationService(
        ScreeningRepository screeningRepository,
        VoucherRepository voucherRepository
    ) {
        this.screeningRepository = screeningRepository;
        this.voucherRepository = voucherRepository;
    }

    @Transactional
    public BigDecimal makeReservation(
            String screeningId,
            String firstName,
            String lastName,
            Collection<MakeReservationSeat> seats,
            String voucherCode
    ) {
        Screening screening = screeningRepository.getById(ScreeningId.of(screeningId));
        Optional<Voucher> voucher = Strings.isNullOrEmpty(voucherCode) ? Optional.empty() : Optional.of(voucherRepository.getByCode(voucherCode));

        Map<Integer, TicketType> seatsMap = seats.stream().collect(Collectors.toMap(
            MakeReservationSeat::getSeatNumber, MakeReservationSeat::getTicketType
        ));

        Reservation reservation = screening.makeReservation(
            new ReservedBy(firstName, lastName),
            seatsMap,
            voucher
        );

        return reservation.total();
    }

}
