package in.co.dhdigital.missiontracker.utils;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import in.co.dhdigital.missiontracker.utils.Enums.Privilege;


@Converter(autoApply = true)
public class PrivilegeConverter implements AttributeConverter<Privilege, String>{

	@Override
	public String convertToDatabaseColumn(Privilege attribute) {
		if (attribute == null) {
            return null;
        }
        return attribute.getValue();
	}

	@Override
	public Privilege convertToEntityAttribute(String code) {
		if (code == null) {
            return null;
        }

        return Stream.of(Privilege.values())
          .filter(c -> c.getValue().equals(code))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
	}

}
