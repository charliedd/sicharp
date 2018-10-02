package com.sicharp.lexicalCategories;

public class Identifier extends LexicalCategory {
    private String IDENTIFIERS_CATEGORY;

    public Identifier() {
        this.IDENTIFIERS_CATEGORY = LexicalCategoriesResources.IDENTIFIERS_CATEGORY;
    }

    @Override
    public boolean belongsToThisCategory(String currentInput) {
        return false;
    }

    @Override
    public String toString() {
        return this.IDENTIFIERS_CATEGORY;
    }
}
