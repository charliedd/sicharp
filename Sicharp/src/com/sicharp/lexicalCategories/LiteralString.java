package com.sicharp.lexicalCategories;

public class LiteralString extends LexicalCategory{
    private String LITERAL_STRING_CATEGORY;
    private String[] LITERAL_SIMBOL;

    public LiteralString() {
        LITERAL_STRING_CATEGORY = LexicalCategoriesResources.LITERAL_STRING_CATEGORY;
        LITERAL_SIMBOL = LexicalCategoriesResources.LITERAL_SIMBOL;
    }

    @Override
    public boolean belongsToThisCategory(String currentInput) {
       return  isInCategory(LITERAL_SIMBOL,currentInput);
    }

    @Override
    public String toString() {
        return null;
    }
}
