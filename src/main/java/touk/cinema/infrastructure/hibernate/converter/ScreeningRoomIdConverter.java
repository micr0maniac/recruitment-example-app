package touk.cinema.infrastructure.hibernate.converter;

import touk.cinema.domain.screeningroom.ScreeningRoomId;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ScreeningRoomIdConverter implements AttributeConverter<ScreeningRoomId, String> {

    @Override
    public String convertToDatabaseColumn(ScreeningRoomId attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.toString();
    }

    @Override
    public ScreeningRoomId convertToEntityAttribute(String dbData) {
        return new ScreeningRoomId(dbData);
    }
}
