package fr.formation.spring.custom.rest.query.common;

public enum SearchOperation {
    EQUALITY, NEGATION, GREATER_THAN, LESS_THAN, STARTS_WITH, ENDS_WITH, CONTAINS, DONT_STARTS_WITH, DONT_ENDS_WITH, DONT_CONTAINS;

    public static final String[] SIMPLE_OPERATION_SET = { ":", "!", ">", "<"};

    public static final String ZERO_OR_MORE_REGEX = "*";

    public static SearchOperation getSimpleOperation(final char input) {
        switch (input) {
        case ':':
            return EQUALITY;
        case '!':
            return NEGATION;
        case '>':
            return GREATER_THAN;
        case '<':
            return LESS_THAN;
        default:
            return null;
        }
    }
}
