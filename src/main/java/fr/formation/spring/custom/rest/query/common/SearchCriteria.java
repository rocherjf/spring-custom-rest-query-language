package fr.formation.spring.custom.rest.query.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchCriteria {
	
	private boolean orPredicate;
	private String key;
	private SearchOperation operation;
	private Object value;
	
	
	public static class SearchCriteriaBuilder{
		
		private String key;
		private String operation;
		private Object value;
		private boolean orPredicate = false;
		
		private String prefixe;
		private String suffixe;
		
		public SearchCriteriaBuilder withKey(String key) {
			this.key = key;
			return this;
		}
		
		public SearchCriteriaBuilder withOperation(String operation) {
			this.operation = operation;
			return this;
		}
		
		public SearchCriteriaBuilder withValue(Object value) {
			this.value = value;
			return this;
		}
		
		public SearchCriteriaBuilder isOrPredicate(boolean orPredicate) {
			this.orPredicate = orPredicate;
			return this;
		}
		
		public SearchCriteriaBuilder withPrefixe(String prefixe) {
			this.prefixe = prefixe;
			return this;
		}
		
		public SearchCriteriaBuilder withSuffixe(String suffixe) {
			this.suffixe = suffixe;
			return this;
		}
		
		public SearchCriteria build() {
			
			SearchOperation searchOperation = SearchOperation
					.getSimpleOperation(this.operation.charAt(0));
			
			if (searchOperation == SearchOperation.EQUALITY) {
				searchOperation = affinerEqualityEnFonctionPrefixeEtSuffixe(prefixe, suffixe);
			}

			if (searchOperation == SearchOperation.NEGATION) {
				searchOperation = affinerNegationEnFonctionPrefixeEtSuffixe(prefixe, suffixe);
			}
			
			return new SearchCriteria(orPredicate, key,	searchOperation, value);
			
		}
		
		
		private SearchOperation affinerEqualityEnFonctionPrefixeEtSuffixe(String prefixe, String suffixe) {
			
			SearchOperation op = SearchOperation.EQUALITY;
			
			final boolean startWithAsterisk = prefixe != null
					&& prefixe.contains(SearchOperation.ZERO_OR_MORE_REGEX);
			final boolean endWithAsterisk = suffixe != null
					&& suffixe.contains(SearchOperation.ZERO_OR_MORE_REGEX);

			if (startWithAsterisk && endWithAsterisk) {
				op = SearchOperation.CONTAINS;
			} else if (startWithAsterisk) {
				op = SearchOperation.ENDS_WITH;
			} else if (endWithAsterisk) {
				op = SearchOperation.STARTS_WITH;
			}
			return op;
		}
		
		private SearchOperation affinerNegationEnFonctionPrefixeEtSuffixe(String prefixe, String suffixe) {
			
			SearchOperation op = SearchOperation.NEGATION;
			
			final boolean startWithAsterisk = prefixe != null
					&& prefixe.contains(SearchOperation.ZERO_OR_MORE_REGEX);
			final boolean endWithAsterisk = suffixe != null
					&& suffixe.contains(SearchOperation.ZERO_OR_MORE_REGEX);

			if (startWithAsterisk && endWithAsterisk) {
				op = SearchOperation.DONT_CONTAINS;
			} else if (startWithAsterisk) {
				op = SearchOperation.DONT_ENDS_WITH;
			} else if (endWithAsterisk) {
				op = SearchOperation.DONT_STARTS_WITH;
			}
			return op;
		}
		
	}
	


}
