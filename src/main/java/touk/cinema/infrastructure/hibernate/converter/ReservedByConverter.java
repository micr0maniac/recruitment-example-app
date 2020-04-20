package touk.cinema.infrastructure.hibernate.converter;

import touk.cinema.domain.ReservedBy;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ReservedByConverter implements AttributeConverter<ReservedBy, String> {

    @Override
    public String convertToDatabaseColumn(ReservedBy attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.fullName();
    }

    @Override
    public ReservedBy convertToEntityAttribute(String dbData) {
        return ReservedBy.of(dbData);
    }
}
