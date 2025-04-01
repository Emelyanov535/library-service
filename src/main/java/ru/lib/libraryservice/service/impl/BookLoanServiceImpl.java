package ru.lib.libraryservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lib.libraryservice.exception.BookNotFoundException;
import ru.lib.libraryservice.exception.ClientNotFoundException;
import ru.lib.libraryservice.persistence.entity.BookEntity;
import ru.lib.libraryservice.persistence.entity.BookLoanEntity;
import ru.lib.libraryservice.persistence.entity.ClientEntity;
import ru.lib.libraryservice.persistence.repository.BookLoanRepository;
import ru.lib.libraryservice.persistence.repository.BookRepository;
import ru.lib.libraryservice.persistence.repository.ClientRepository;
import ru.lib.libraryservice.service.BookLoanService;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookLoanServiceImpl implements BookLoanService {

	private final BookLoanRepository bookLoanRepository;
	private final BookRepository bookRepository;
	private final ClientRepository clientRepository;

	@Transactional
	public void bookLoan(UUID isbn, Long clientId) {
		BookEntity bookEntity = bookRepository.findById(isbn)
				.orElseThrow(() -> new BookNotFoundException("Book not found"));

		ClientEntity clientEntity = clientRepository.findById(clientId)
				.orElseThrow(() -> new ClientNotFoundException("Client not found"));

		BookLoanEntity bookLoanEntity = BookLoanEntity.builder()
				.book(bookEntity)
				.client(clientEntity)
				.loanDate(new Timestamp(System.currentTimeMillis()))
				.build();

		bookLoanRepository.save(bookLoanEntity);
	}
}
