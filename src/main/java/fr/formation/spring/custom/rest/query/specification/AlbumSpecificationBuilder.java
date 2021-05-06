package fr.formation.spring.custom.rest.query.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import fr.formation.spring.custom.rest.query.common.SearchCriteria;
import fr.formation.spring.custom.rest.query.entity.Album;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class AlbumSpecificationBuilder {

	private final List<SearchCriteria> params;

	public AlbumSpecificationBuilder() {
		params = new ArrayList<>();
	}

	public AlbumSpecificationBuilder addListSearchCriteria(
			List<SearchCriteria> lstSearchCriteria) {

		params.addAll(lstSearchCriteria);
		return this;
	}

	public Specification<Album> build() {
		if (params.size() == 0) {
			log.warn("Aucun critère de recherche trouvé");
			return null;
		}

		Specification<Album> result = new AlbumSpecification(params.get(0));

		for (int i = 1; i < params.size(); i++) {
			result = params.get(i).isOrPredicate()
					? Specification.where(result)
							.or(new AlbumSpecification(params.get(i)))
					: Specification.where(result)
							.and(new AlbumSpecification(params.get(i)));
		}

		return result;
	}

}