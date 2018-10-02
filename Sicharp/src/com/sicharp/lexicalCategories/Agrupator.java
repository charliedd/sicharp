package com.sicharp.lexicalCategories;

public class Agrupator extends LexicalCategory{
    public String AGRUPATOR_CATEGORY;
    public String[] OPENING_AGRUPATORS;
    private String[] CLOSING_AGRUPATORS;

    public Agrupator() {
        this.AGRUPATOR_CATEGORY = LexicalCategoriesResources.AGRUPATOR_CATEGORY;
        this.OPENING_AGRUPATORS = LexicalCategoriesResources.OPENING_AGRUPATORS;
        this.CLOSING_AGRUPATORS = LexicalCategoriesResources.CLOSING_AGRUPATORS;
    }

    @Override
    public boolean belongsToThisCategory(String currentInput) {
        if(currentInput.length() > 1)return false;

        if(isInCategory(OPENING_AGRUPATORS,currentInput))return true;
        if(isInCategory(CLOSING_AGRUPATORS,currentInput))return true;

        return false;
    }

    @Override
    public String toString() {
        return AGRUPATOR_CATEGORY;
    }
}
