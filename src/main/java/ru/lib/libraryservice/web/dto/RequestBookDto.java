package ru.lib.libraryservice.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestBookDto {
	@NotBlank(message = "Title cannot be blank")
	private String title;
	@NotBlank(message = "Author cannot be blank")
	private String author;
}
