package ru.lib.libraryservice.service;

import org.springframework.data.domain.Page;
import ru.lib.libraryservice.service.dto.ClientWithBooksDto;

public interface ReaderService {
	Page<ClientWithBooksDto> getInfos(int page, int size);
}
