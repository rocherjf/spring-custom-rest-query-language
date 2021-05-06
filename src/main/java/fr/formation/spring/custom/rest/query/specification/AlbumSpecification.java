package fr.formation.spring.custom.rest.query.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import fr.formation.spring.custom.rest.query.common.SearchCriteria;
import fr.formation.spring.custom.rest.query.entity.Album;
import lombok.Getter;

@Getter
public class AlbumSpecification implements Specification<Album> {

	private static final long serialVersionUID = -7106633173631978974L;

	private SearchCriteria criteria;

	public AlbumSpecification(final SearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(final Root<Album> root,
			final CriteriaQuery<?> query, final CriteriaBuilder builder) {
		switch (criteria.getOperation()) {
			case EQUALITY :
				return builder.equal(
						root.get(criteria.getKey()),
						criteria.getValue());
			case NEGATION :
				return builder.notEqual(
						root.get(criteria.getKey()),
						criteria.getValue());
			case GREATER_THAN :
				return builder.greaterThan(
						root.get(criteria.getKey()),
						criteria.getValue().toString());
			case LESS_THAN :
				return builder.lessThan(
						root.get(criteria.getKey()),
						criteria.getValue().toString());
			case STARTS_WITH :
				return builder.like(
						root.get(criteria.getKey()),
						criteria.getValue() + "%");
			case ENDS_WITH :
				return builder.like(
						root.get(criteria.getKey()),
						"%" + criteria.getValue());
			case CONTAINS :
				return builder.like(
						root.get(criteria.getKey()),
						"%" + criteria.getValue() + "%");
			case DONT_STARTS_WITH :
				return builder.notLike(
						root.get(criteria.getKey()),
						criteria.getValue() + "%");
			case DONT_ENDS_WITH :
				return builder.notLike(
						root.get(criteria.getKey()),
						"%" + criteria.getValue());
			case DONT_CONTAINS :
				return builder.notLike(
						root.get(criteria.getKey()),
						"%" + criteria.getValue() + "%");
			default :
				return null;
		}
	}

}
