package ru.lib.libraryservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.lib.libraryservice.persistence.repository.BookLoanRepository;
import ru.lib.libraryservice.service.ReaderService;
import ru.lib.libraryservice.service.dto.ClientWithBooksDto;

@Service
@RequiredArgsConstructor
public class ReaderServiceImpl implements ReaderService {

	private final BookLoanRepository bookLoanRepository;

	public Page<ClientWithBooksDto> getInfos(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return bookLoanRepository.getClientsWithBooks(pageable);
	}
}
