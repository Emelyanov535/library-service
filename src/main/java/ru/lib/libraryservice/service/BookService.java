package ru.lib.libraryservice.service;


import org.springframework.data.domain.Page;
import ru.lib.libraryservice.persistence.entity.BookEntity;
import ru.lib.libraryservice.service.dto.BookDto;

import java.util.UUID;

public interface BookService {
	UUID addBook(BookDto book);

	UUID updateBook(UUID isbn, BookDto book);

	Page<BookEntity> getBooks(int page, int size);
}
