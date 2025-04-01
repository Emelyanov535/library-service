package ru.lib.libraryservice.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.lib.libraryservice.persistence.entity.ClientEntity;
import ru.lib.libraryservice.service.dto.ClientDto;

@Component
public class ClientDto2ClientEntity implements Converter<ClientDto, ClientEntity> {
	public ClientEntity convert(ClientDto source) {
		return ClientEntity.builder()
				.fio(source.getFio())
				.birthday(source.getBirthday())
				.build();
	}
}
