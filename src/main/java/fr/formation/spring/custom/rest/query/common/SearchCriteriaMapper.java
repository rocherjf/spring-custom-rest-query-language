package fr.formation.spring.custom.rest.query.common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.google.common.base.Joiner;

@Component
public class SearchCriteriaMapper {

	private static final String REGEXP_PRESENCE_OR = "(or )?";
	private static final String REGEXP_MOT_CLE = "(\\w+?)";
	private static final String REGEXP_VALEUR = "(\\w+?)";
	private static final String REGEXP_OPERATION = "("
			+ Joiner.on("|").join(SearchOperation.SIMPLE_OPERATION_SET) + ")";
	private static final String REGEXP_PRESENCE_PREFIXE = "(\\p{Punct}?)";
	private static final String REGEXP_PRESENCE_SUFFIXE = "(\\p{Punct}?)";
	private static final String REGEXP_SEPARATEUR = ",";

	public static final Pattern PATTERN_CRITERE_RECHERCHE = Pattern.compile(
			REGEXP_PRESENCE_OR
					+ REGEXP_MOT_CLE
					+ REGEXP_OPERATION
					+ REGEXP_PRESENCE_PREFIXE
					+ REGEXP_VALEUR
					+ REGEXP_PRESENCE_SUFFIXE
					+ REGEXP_SEPARATEUR,
			Pattern.UNICODE_CHARACTER_CLASS);

	public List<SearchCriteria> fromStringToSearchCriteria(String search) {

		List<SearchCriteria> criteresRecherche = new ArrayList<>();

		// l'ajout de la virgule permet de prendre en compte le dernier élément
		// de la liste des critères des recherches
		Matcher matcher = PATTERN_CRITERE_RECHERCHE.matcher(search + ",");

		while (matcher.find()) {

			final boolean isOrPredicate = "or ".equals(matcher.group(1));
			final String cle = matcher.group(2);
			final String operation = matcher.group(3);
			final Object valeur = matcher.group(5);
			final String prefixe = matcher.group(4);
			final String suffixe = matcher.group(6);

			SearchOperation op = SearchOperation
					.getSimpleOperation(operation.charAt(0));

			if (op != null) {

				SearchCriteria searchCriteria = new SearchCriteria.SearchCriteriaBuilder()
						.isOrPredicate(isOrPredicate)
						.withKey(cle)
						.withOperation(operation)
						.withValue(valeur)
						.withPrefixe(prefixe)
						.withSuffixe(suffixe)
						.build();

				criteresRecherche.add(searchCriteria);
			}
		}

		return criteresRecherche;
	}

}
