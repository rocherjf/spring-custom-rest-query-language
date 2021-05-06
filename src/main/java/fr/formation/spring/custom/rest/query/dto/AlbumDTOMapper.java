package fr.formation.spring.custom.rest.query.dto;

import java.util.List;

import org.mapstruct.Mapper;

import fr.formation.spring.custom.rest.query.entity.Album;

@Mapper
public interface AlbumDTOMapper {

	public AlbumDTO fromAlbumToAlbumDTO(Album a);
	
	public List<AlbumDTO> fromAlbumToAlbumDTO(List<Album> a);

}
