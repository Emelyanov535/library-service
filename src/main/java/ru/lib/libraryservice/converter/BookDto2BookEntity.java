package ru.lib.libraryservice.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.lib.libraryservice.persistence.entity.BookEntity;
import ru.lib.libraryservice.service.dto.BookDto;

@Component
public class BookDto2BookEntity implements Converter<BookDto, BookEntity> {
	public BookEntity convert(BookDto source) {
		return BookEntity.builder()
				.title(source.getTitle())
				.author(source.getAuthor())
				.build();
	}
}
