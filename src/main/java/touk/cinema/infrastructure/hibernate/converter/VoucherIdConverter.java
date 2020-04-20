package touk.cinema.infrastructure.hibernate.converter;

import touk.cinema.domain.VoucherId;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class VoucherIdConverter implements AttributeConverter<VoucherId, String> {

    @Override
    public String convertToDatabaseColumn(VoucherId attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.toString();
    }

    @Override
    public VoucherId convertToEntityAttribute(String dbData) {
        return new VoucherId(dbData);
    }
}
