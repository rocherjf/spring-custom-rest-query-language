package fr.formation.spring.custom.rest.query.service;

import java.util.List;

import fr.formation.spring.custom.rest.query.dto.AlbumDTO;

public interface AlbumService {
	
	
	List<AlbumDTO> getAllAlbumsBySearchCritera(String searchCriteria);

}
