package fr.library.criterias;

public sealed interface SearchCriteria permits ByTitle, ByAuthor, ByYearRange {
}
