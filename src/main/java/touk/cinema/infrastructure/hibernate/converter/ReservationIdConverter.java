package touk.cinema.infrastructure.hibernate.converter;

import touk.cinema.domain.ReservationId;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ReservationIdConverter implements AttributeConverter<ReservationId, String> {

    @Override
    public String convertToDatabaseColumn(ReservationId attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.toString();
    }

    @Override
    public ReservationId convertToEntityAttribute(String dbData) {
        return new ReservationId(dbData);
    }
}
