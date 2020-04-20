package touk.cinema.infrastructure.hibernate.converter;

import touk.cinema.domain.ScreeningId;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ScreeningIdConverter implements AttributeConverter<ScreeningId, String> {

    @Override
    public String convertToDatabaseColumn(ScreeningId attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.toString();
    }

    @Override
    public ScreeningId convertToEntityAttribute(String dbData) {
        return new ScreeningId(dbData);
    }
}
