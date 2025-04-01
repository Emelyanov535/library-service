package ru.lib.libraryservice.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.lib.libraryservice.persistence.entity.BookEntity;
import ru.lib.libraryservice.service.BookService;
import ru.lib.libraryservice.service.dto.BookDto;
import ru.lib.libraryservice.web.dto.RequestBookDto;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@Tag(name = "Книги", description = "Работа с книгами")
public class BookController {

	private final BookService bookService;
	private final ConversionService conversionService;

	@PostMapping
	@Operation(summary = "Создать книгу")
	public ResponseEntity<UUID> createBook(@Valid @RequestBody RequestBookDto requestBookDto) {
		return ResponseEntity.ok(bookService.addBook(conversionService.convert(requestBookDto, BookDto.class)));
	}

	@PatchMapping("/{isbn}")
	@Operation(summary = "Обновить книгу")
	public ResponseEntity<UUID> updateBook(@PathVariable("isbn") UUID isbn, @Valid @RequestBody RequestBookDto requestBookDto) {
		return ResponseEntity.ok(bookService.updateBook(isbn, conversionService.convert(requestBookDto, BookDto.class)));
	}

	@GetMapping
	@Operation(summary = "Получить каталог книг")
	public Page<BookEntity> getBooks(
			@RequestParam(defaultValue = "0", value = "page") int page,
			@RequestParam(defaultValue = "10", value = "size") int size
	) {
		return bookService.getBooks(page, size);
	}
}
