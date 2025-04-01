package ru.lib.libraryservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.lib.libraryservice.exception.ClientNotFoundException;
import ru.lib.libraryservice.persistence.entity.ClientEntity;
import ru.lib.libraryservice.persistence.repository.ClientRepository;
import ru.lib.libraryservice.service.ClientService;
import ru.lib.libraryservice.service.dto.ClientDto;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

	private final ClientRepository clientRepository;
	private final ConversionService conversionService;

	@Transactional
	public Long addClient(ClientDto clientDto) {
		ClientEntity clientEntity = conversionService.convert(clientDto, ClientEntity.class);

		return clientRepository.save(clientEntity).getId();
	}

	@Transactional
	public Long updateClient(Long id, ClientDto clientDto) {
		ClientEntity clientEntity = clientRepository.findById(id)
				.orElseThrow(() -> new ClientNotFoundException("Client not found"));

		if (clientDto.getFio() != null) {
			clientEntity.setFio(clientDto.getFio());
		}
		if (clientDto.getBirthday() != null) {
			clientEntity.setBirthday(clientDto.getBirthday());
		}

		return clientRepository.save(clientEntity).getId();
	}

	public Page<ClientEntity> getClients(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return clientRepository.findAll(pageable);
	}
}