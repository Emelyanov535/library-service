package ru.lib.libraryservice.service;

import java.util.UUID;

public interface BookLoanService {
	void bookLoan(UUID isbn, Long clientId);
}
