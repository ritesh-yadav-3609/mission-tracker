package in.co.dhdigital.missiontracker.utils;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import in.co.dhdigital.missiontracker.utils.Enums.Status;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

	@Override
	public String convertToDatabaseColumn(Status attribute) {
		if (attribute == null) {
            return null;
        }
        return attribute.getValue();
	}

	@Override
	public Status convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(Status.values())
          .filter(c -> c.getValue().equals(code))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
    }

}
