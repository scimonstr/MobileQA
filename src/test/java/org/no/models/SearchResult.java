package org.no.models;

/**
 * Class represents model of a single search result in results list of the Home Screen
 */
public class SearchResult {
    private String title;
    private String definition;
    private Boolean favourite;

    public SearchResult(String title, String definition, Boolean favourite) {
        this.title = title;
        this.definition = definition;
        this.favourite = favourite;
    }

    public String getTitle() {
        return title;
    }

    public String getDefinition() {
        return definition;
    }

    public Boolean isFavourite() {
        return favourite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        SearchResult searchResult = (SearchResult) o;
        // field comparison
        return this.favourite.equals(searchResult.isFavourite())
                && this.title.equals(searchResult.getTitle())
                && this.definition.equals(searchResult.getDefinition());
    }
}
