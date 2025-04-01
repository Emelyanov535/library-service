package ru.lib.libraryservice.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lib.libraryservice.persistence.entity.BookEntity;
import ru.lib.libraryservice.persistence.entity.BookLoanEntity;
import ru.lib.libraryservice.persistence.entity.ClientEntity;
import ru.lib.libraryservice.service.dto.ClientWithBooksDto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public interface BookLoanRepository extends JpaRepository<BookLoanEntity, Long> {

	@Query(
			"SELECT DISTINCT tc " +
			"FROM ClientEntity tc " +
			"JOIN BookLoanEntity tbl ON tbl.client.id = tc.id " +
			"ORDER BY tc.id"
	)
	Page<ClientEntity> findDistinctClientsWithBooks(Pageable pageable);

	@Query(
			"SELECT b, tbl.loanDate,tbl.client.id " +
			"FROM BookLoanEntity tbl " +
			"JOIN BookEntity b ON b.isbn = tbl.book.isbn " +
			"WHERE tbl.client.id IN :clientIds " +
			"ORDER BY tbl.loanDate DESC"
	)
	List<Object[]> findBooksByClientIds(@Param("clientIds") List<Long> clientIds);

	default Page<ClientWithBooksDto> getClientsWithBooks(Pageable pageable) {
		Page<ClientEntity> clientsPage = findDistinctClientsWithBooks(pageable);

		List<Long> clientIds = clientsPage.getContent().stream()
				.map(ClientEntity::getId)
				.collect(Collectors.toList());

		List<Object[]> booksData = findBooksByClientIds(clientIds);

		Map<Long, List<ClientWithBooksDto.BookingDto>> booksByClientId = new HashMap<>();

		for (Object[] data : booksData) {
			BookEntity book = (BookEntity) data[0];
			Timestamp loanDate = (Timestamp) data[1];
			Long clientId = (Long) data[2];

			ClientWithBooksDto.BookingDto booking = ClientWithBooksDto.BookingDto.builder()
					.isbn(book.getIsbn().toString())
					.title(book.getTitle())
					.author(book.getAuthor())
					.dateLoan(loanDate.toLocalDateTime())
					.build();

			booksByClientId.computeIfAbsent(clientId, k -> new ArrayList<>()).add(booking);
		}

		List<ClientWithBooksDto> content = clientsPage.getContent().stream()
				.map(client -> new ClientWithBooksDto(
						client.getFio(),
						client.getBirthday(),
						booksByClientId.getOrDefault(client.getId(), Collections.emptyList())
				))
				.collect(Collectors.toList());

		return new PageImpl<>(
				content,
				pageable,
				clientsPage.getTotalElements()
		);
	}
}

