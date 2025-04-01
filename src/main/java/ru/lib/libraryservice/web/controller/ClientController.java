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
import ru.lib.libraryservice.persistence.entity.ClientEntity;
import ru.lib.libraryservice.service.ClientService;
import ru.lib.libraryservice.service.dto.ClientDto;
import ru.lib.libraryservice.web.dto.RequestClientDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@Tag(name = "Клиенты", description = "Работа с клиентами")
public class ClientController {
	private final ClientService clientService;
	private final ConversionService conversionService;

	@PostMapping
	@Operation(summary = "Создать клиента")
	public ResponseEntity<Long> createClient(@Valid @RequestBody RequestClientDto requestClientDto) {
		return ResponseEntity.ok(clientService.addClient(conversionService.convert(requestClientDto, ClientDto.class)));
	}

	@PatchMapping("/{id}")
	@Operation(summary = "Обновить клиента")
	public ResponseEntity<Long> updateClient(@PathVariable("id") Long id, @Valid @RequestBody RequestClientDto requestClientDto) {
		return ResponseEntity.ok(clientService.updateClient(id, conversionService.convert(requestClientDto, ClientDto.class)));
	}

	@GetMapping
	@Operation(summary = "Получить список клиентов")
	public Page<ClientEntity> getClients(
			@RequestParam(defaultValue = "0", value = "page") int page,
			@RequestParam(defaultValue = "10", value = "size") int size
	) {
		return clientService.getClients(page, size);
	}
}
