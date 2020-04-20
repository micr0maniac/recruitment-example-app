package touk.cinema.infrastructure.hibernate.converter;

import com.google.gson.Gson;
import touk.cinema.domain.screeningroom.ScreeningRoomSchema;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ScreeningRoomSchemaConverter implements AttributeConverter<ScreeningRoomSchema, String> {

    @Override
    public String convertToDatabaseColumn(ScreeningRoomSchema attribute) {
        if (attribute == null) {
            return null;
        }

        Gson gson = new Gson();
        return gson.toJson(attribute);
    }

    @Override
    public ScreeningRoomSchema convertToEntityAttribute(String dbData) {
        Gson gson = new Gson();
        return gson.fromJson(dbData, ScreeningRoomSchema.class);
    }
}
