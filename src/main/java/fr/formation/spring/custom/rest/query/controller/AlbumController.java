package fr.formation.spring.custom.rest.query.controller;

import java.util.List;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.spring.custom.rest.query.dto.AlbumDTO;
import fr.formation.spring.custom.rest.query.service.AlbumService;

@RestController
@RequestMapping("/albums")
public class AlbumController {
	
	private AlbumService albumService;
	
	
	public AlbumController(AlbumService albumService) {
		this.albumService = albumService;
	}
	
	
	/**
	 * API permettant d'utiliser un DSL custom pour utiliser des critères de recherche directement dans l'API
	 * 
	 * @param search 
	 * search=[NOM_DU_CHAMP_ENTITE][OPERATION][VALEUR_DU_CHAMP]
	 * Les opérations disponibles sont
	 * * : égalité
	 * * < inférieur
	 * * > supérieur
	 * * ! différent
	 * 
	 * Exemple d'utilisation simple :
	 * search=nom:Anomie -> recherche des albums dont le nom est Anomie
	 * search=nom:Ano* -> recherche des albums dont le nom commence par Ano
	 * search=nom:*mie -> recherche des albums dont le nom finit par mie
	 * search=nom:*mie* -> recherche des albums dont le nom contient mie
	 * 
	 * Combinaison de critères de recherche :
	 * search=nom!*Ano*,nom!*Wel* -> recherche des albums dont le nom ne contient ni Ano ni Wel
	 * search=nom:*Ano*,or nom:*Wel* -> recherche des albums dont le nom contient Ano ou Wel
	 * 
	 * @return les albums correspondants aux critères de recherche
	 */
	@GetMapping
	public List<AlbumDTO> getAllAlbumsByCriteria(@Nullable @RequestParam(value = "search") String search){
		return albumService.getAllAlbumsBySearchCritera(search);
	}

}
