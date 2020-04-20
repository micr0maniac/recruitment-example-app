package touk.cinema.application.query;

import touk.cinema.application.query.model.QueryScreening;

public interface QueryScreeningRepository {

    QueryScreening getById(String screeningId);

}
