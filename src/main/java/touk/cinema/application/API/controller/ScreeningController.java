package touk.cinema.application.API.controller;

import org.springframework.web.bind.annotation.*;
import touk.cinema.application.API.request.MakeReservationRequest;
import touk.cinema.application.API.response.MakeReservationResponse;
import touk.cinema.application.query.model.QueryScreening;
import touk.cinema.application.service.ReservationService;
import touk.cinema.infrastructure.hibernate.repository.query.HibernateQueryScreeningRepository;

import java.math.BigDecimal;

@RestController()
@RequestMapping(value = "/screenings")
public class ScreeningController {

    HibernateQueryScreeningRepository queryScreeningRepository;

    ReservationService reservationService;

    public ScreeningController(
            HibernateQueryScreeningRepository queryScreeningRepository,
            ReservationService reservationService
    ) {
        this.queryScreeningRepository = queryScreeningRepository;
        this.reservationService = reservationService;
    }

    @RequestMapping(value = "/{screeningId}", method = RequestMethod.GET)
    public QueryScreening screeningDetailsAction(@PathVariable("screeningId") String screeningId) {
        return queryScreeningRepository.getById(screeningId);
    }

    @RequestMapping(value = "/{screeningId}/make-reservation", method = RequestMethod.POST)
    public MakeReservationResponse makeReservationAction(
        @RequestBody MakeReservationRequest makeReservationDto,
        @PathVariable("screeningId") String screeningId
    ) {
        BigDecimal amount = reservationService.makeReservation(
                screeningId,
                makeReservationDto.getFirstName(),
                makeReservationDto.getLastName(),
                makeReservationDto.getSeats(),
                makeReservationDto.getVoucherCode()
        );

        return new MakeReservationResponse(amount);
    }

}
