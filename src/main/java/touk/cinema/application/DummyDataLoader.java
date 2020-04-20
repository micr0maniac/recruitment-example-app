package touk.cinema.application;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import touk.cinema.domain.*;
import touk.cinema.domain.screeningroom.CinemaRoomRepository;
import touk.cinema.domain.screeningroom.ScreeningRoom;
import touk.cinema.domain.screeningroom.ScreeningRoomSchema;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.IntStream;

@Component
public class DummyDataLoader implements ApplicationRunner {

    private MovieRepository movieRepository;
    private CinemaRoomRepository cinemaRoomRepository;
    private ScreeningRepository screeningRepository;
    private VoucherRepository voucherRepository;

    public DummyDataLoader(
        MovieRepository movieRepository,
        CinemaRoomRepository cinemaRoomRepository,
        ScreeningRepository screeningRepository,
        VoucherRepository voucherRepository
    ) {
        this.movieRepository = movieRepository;
        this.cinemaRoomRepository = cinemaRoomRepository;
        this.screeningRepository = screeningRepository;
        this.voucherRepository = voucherRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        Collection<Movie> movies = Arrays.asList(
            new Movie("Edward Scissorhands"),
            new Movie("Corpse Bride"),
            new Movie("Alice in Wonderland")
        );
        movieRepository.saveAll(movies);

        ScreeningRoomSchema schema = ScreeningRoomSchema.withFixedNumberOfSeatsPerRow(4, 10);
        Collection<ScreeningRoom> rooms = Arrays.asList(
            new ScreeningRoom("screening room #1", schema),
            new ScreeningRoom("screening room #2", schema),
            new ScreeningRoom("screening room #3", schema)
        );
        cinemaRoomRepository.saveAll(rooms);

        rooms.forEach(room -> {
            movies.forEach(movie -> {
                IntStream.range(1,5).forEach(index -> {
                    screeningRepository.save(new Screening(
                        movie,
                        room,
                        LocalDateTime.now().plusHours(index)
                    ));
                });
            });
        });

        Voucher voucher = new Voucher("code50", 50);
        voucherRepository.save(voucher);
    }
}