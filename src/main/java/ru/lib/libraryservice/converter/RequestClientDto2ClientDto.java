package ru.lib.libraryservice.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.lib.libraryservice.service.dto.ClientDto;
import ru.lib.libraryservice.web.dto.RequestClientDto;

@Component
public class RequestClientDto2ClientDto implements Converter<RequestClientDto, ClientDto> {
	public ClientDto convert(RequestClientDto source) {
		return ClientDto.builder()
				.fio(source.getFio())
				.birthday(source.getBirthday())
				.build();
	}
}
