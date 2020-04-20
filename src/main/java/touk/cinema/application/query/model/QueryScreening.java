package touk.cinema.application.query.model;

import touk.cinema.domain.Screening;
import touk.cinema.domain.screeningroom.ScreeningRoomSchemaRow;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class QueryScreening {

    private String id;

    private String movieName;

    private LocalDateTime start;

    private List<List<QuerySeat>> seats;

    public String getId() {
        return id;
    }

    public String getMovieName() {
        return movieName;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public List<List<QuerySeat>> getSeats() {
        return seats;
    }

    public QueryScreening(String id, String movieName, LocalDateTime start, List<List<QuerySeat>> seats) {
        this.id = id;
        this.movieName = movieName;
        this.start = start;
        this.seats = seats;
    }

    public static QueryScreening of(Screening screening) {
        Set<Integer> reservedSeats = screening.alreadyReservedSeats();

        List<List<QuerySeat>> seats = screening.screeningRoomSchema().rows().stream()
                .map(ScreeningRoomSchemaRow::seats)
                .map(s -> {
                    return s.stream()
                        .map(seat -> new QuerySeat(seat.number(), !reservedSeats.contains(seat.number())))
                        .collect(Collectors.toList());
                })
                .collect(Collectors.toList());

        return new QueryScreening(screening.id().toString(), screening.movieName(), screening.startAt(), seats);
    }

}