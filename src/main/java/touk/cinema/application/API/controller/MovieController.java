package touk.cinema.application.API.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import touk.cinema.application.query.model.QueryMovie;
import touk.cinema.infrastructure.hibernate.repository.query.HibernateQueryMovieRepository;

import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
import java.util.Collection;

@RestController()
@RequestMapping(value = "/movies")
public class MovieController {

    private HibernateQueryMovieRepository queryMovieRepository;

    public MovieController(HibernateQueryMovieRepository queryMovieRepository) {
        this.queryMovieRepository = queryMovieRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Collection<QueryMovie> findUpcomingMoviesAction(
            @PathParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @PathParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return queryMovieRepository.findUpcomingMovies(from, to);
    }

}
