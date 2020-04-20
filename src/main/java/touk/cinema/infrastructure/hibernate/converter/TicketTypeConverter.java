package touk.cinema.infrastructure.hibernate.converter;

import touk.cinema.domain.TicketType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TicketTypeConverter implements AttributeConverter<TicketType, String> {

    @Override
    public String convertToDatabaseColumn(TicketType attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.toString();
    }

    @Override
    public TicketType convertToEntityAttribute(String dbData) {
        return TicketType.valueOf(dbData);
    }
}
