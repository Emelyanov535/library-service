package ru.lib.libraryservice.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestLoanDto {
	@NotNull(message = "ISBN cannot be null")
	private UUID isbn;

	@NotNull(message = "ClientId cannot be null")
	@Positive(message = "ClientId must be a positive number")
	private Long clientId;
}
