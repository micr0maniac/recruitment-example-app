package touk.cinema.infrastructure.hibernate.converter;

import touk.cinema.domain.ScreeningId;
import touk.cinema.domain.TicketId;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TicketIdConverter implements AttributeConverter<TicketId, String> {

    @Override
    public String convertToDatabaseColumn(TicketId attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.toString();
    }

    @Override
    public TicketId convertToEntityAttribute(String dbData) {
        return new TicketId(dbData);
    }
}
