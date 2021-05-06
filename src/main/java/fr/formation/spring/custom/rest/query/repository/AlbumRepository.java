package fr.formation.spring.custom.rest.query.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import fr.formation.spring.custom.rest.query.entity.Album;

public interface AlbumRepository
		extends
			JpaRepository<Album, Long>,
			JpaSpecificationExecutor<Album> {

}
