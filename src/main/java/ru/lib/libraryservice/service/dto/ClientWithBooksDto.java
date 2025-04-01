package ru.lib.libraryservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientWithBooksDto {
	private String fio;
	private Timestamp birthday;
	private List<BookingDto> bookings;

	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class BookingDto {
		private String isbn;
		private String title;
		private String author;
		private LocalDateTime dateLoan;
	}
}
