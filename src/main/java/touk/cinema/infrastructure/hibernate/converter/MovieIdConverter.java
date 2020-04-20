package touk.cinema.infrastructure.hibernate.converter;

import touk.cinema.domain.MovieId;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MovieIdConverter implements AttributeConverter<MovieId, String> {

    @Override
    public String convertToDatabaseColumn(MovieId attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.toString();
    }

    @Override
    public MovieId convertToEntityAttribute(String dbData) {
        return new MovieId(dbData);
    }
}
