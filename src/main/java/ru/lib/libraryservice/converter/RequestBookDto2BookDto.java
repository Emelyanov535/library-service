package ru.lib.libraryservice.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.lib.libraryservice.service.dto.BookDto;
import ru.lib.libraryservice.web.dto.RequestBookDto;

@Component
public class RequestBookDto2BookDto implements Converter<RequestBookDto, BookDto> {
	public BookDto convert(RequestBookDto source) {
		return BookDto.builder()
				.title(source.getTitle())
				.author(source.getAuthor())
				.build();
	}
}
