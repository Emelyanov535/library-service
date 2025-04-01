package ru.lib.libraryservice.service;

import org.springframework.data.domain.Page;
import ru.lib.libraryservice.persistence.entity.ClientEntity;
import ru.lib.libraryservice.service.dto.ClientDto;

public interface ClientService {
	Long addClient(ClientDto client);

	Long updateClient(Long id, ClientDto client);

	Page<ClientEntity> getClients(int page, int size);
}
