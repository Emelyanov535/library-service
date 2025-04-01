package ru.lib.libraryservice.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestClientDto {
	@NotBlank(message = "ФИО не может быть пустым")
	private String fio;
	@PastOrPresent(message = "Время не должно быть в будущем")
	private Timestamp birthday;
}
