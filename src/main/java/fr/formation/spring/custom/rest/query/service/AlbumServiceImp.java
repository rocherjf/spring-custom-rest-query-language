package fr.formation.spring.custom.rest.query.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import fr.formation.spring.custom.rest.query.common.SearchCriteriaMapper;
import fr.formation.spring.custom.rest.query.dto.AlbumDTO;
import fr.formation.spring.custom.rest.query.dto.AlbumDTOMapper;
import fr.formation.spring.custom.rest.query.entity.Album;
import fr.formation.spring.custom.rest.query.repository.AlbumRepository;
import fr.formation.spring.custom.rest.query.specification.AlbumSpecificationBuilder;

@Service
public class AlbumServiceImp implements AlbumService {
	
	private AlbumRepository repo;
	
	private AlbumDTOMapper mapper;
	
	private SearchCriteriaMapper searchCriteriaMapper;
	
	
	public AlbumServiceImp(AlbumRepository repo, AlbumDTOMapper mapper, SearchCriteriaMapper searchCriteriaMapper) {
		this.repo = repo;
		this.mapper = mapper;
		this.searchCriteriaMapper = searchCriteriaMapper;
	}

	@Override
	public List<AlbumDTO> getAllAlbumsBySearchCritera(String searchCriteria) {
		Specification<Album> specifications = creerSpecificationAlbum(
				searchCriteria);

		List<Album> albums = repo.findAll(specifications);

		return mapper.fromAlbumToAlbumDTO(albums);
	}


	private Specification<Album> creerSpecificationAlbum(
			String searchCriteria) {

		AlbumSpecificationBuilder builder = new AlbumSpecificationBuilder();
		builder.addListSearchCriteria(searchCriteriaMapper.fromStringToSearchCriteria(searchCriteria));
		return builder.build();

	}
	
	
}
