package ru.lib.libraryservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.lib.libraryservice.exception.BookNotFoundException;
import ru.lib.libraryservice.persistence.entity.BookEntity;
import ru.lib.libraryservice.persistence.repository.BookRepository;
import ru.lib.libraryservice.service.BookService;
import ru.lib.libraryservice.service.dto.BookDto;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;
	private final ConversionService conversionService;

	@Transactional
	public UUID addBook(BookDto bookDto) {
		BookEntity bookEntity = conversionService.convert(bookDto, BookEntity.class);

		return bookRepository.save(bookEntity).getIsbn();
	}

	@Transactional
	public UUID updateBook(UUID isbn, BookDto bookDto) {
		BookEntity bookEntity = bookRepository.findById(isbn)
				.orElseThrow(() -> new BookNotFoundException("Book not found"));

		if (bookDto.getAuthor() != null) {
			bookEntity.setAuthor(bookDto.getAuthor());
		}
		if (bookDto.getTitle() != null) {
			bookEntity.setTitle(bookDto.getTitle());
		}

		return bookRepository.save(bookEntity).getIsbn();
	}

	public Page<BookEntity> getBooks(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return bookRepository.findAll(pageable);
	}
}
