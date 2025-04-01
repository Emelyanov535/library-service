package ru.lib.libraryservice.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.lib.libraryservice.service.BookLoanService;
import ru.lib.libraryservice.service.ReaderService;
import ru.lib.libraryservice.service.dto.ClientWithBooksDto;
import ru.lib.libraryservice.web.dto.RequestLoanDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/loan")
@RequiredArgsConstructor
@Tag(name = "Заём книг", description = "Работа с выдачей книг")
public class BookLoanController {
	private final BookLoanService bookLoanService;
	private final ReaderService readerService;

	@PostMapping
	@Operation(summary = "Выдать книгу")
	public void loanBook(@Valid @RequestBody RequestLoanDto requestLoanDto) {
		bookLoanService.bookLoan(requestLoanDto.getIsbn(), requestLoanDto.getClientId());
	}

	@GetMapping
	@Operation(summary = "Получить список читающих клиентов")
	public Page<ClientWithBooksDto> getBookLoans(
			@RequestParam(defaultValue = "0", value = "page") int page,
			@RequestParam(defaultValue = "10", value = "size") int size
	) {
		return readerService.getInfos(page, size);
	}
}
