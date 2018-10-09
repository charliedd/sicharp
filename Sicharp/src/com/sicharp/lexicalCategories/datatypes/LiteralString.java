package com.sicharp.lexicalCategories.datatypes;

import com.sicharp.lexicalCategories.LexicalCategoriesResources;
import com.sicharp.lexicalCategories.LexicalCategory;

public class LiteralString extends LexicalCategory {
    private String LITERAL_STRING_CATEGORY;
    private String[] LITERAL_SIMBOL;

    public LiteralString() {
        LITERAL_STRING_CATEGORY = LexicalCategoriesResources.LITERAL_STRING_CATEGORY;
        LITERAL_SIMBOL = LexicalCategoriesResources.LITERAL_SIMBOL;
    }

    @Override
    public boolean belongsToThisCategory(String currentInput) {
       if(currentInput.startsWith(LITERAL_SIMBOL[0]) && currentInput.endsWith(LITERAL_SIMBOL[0]))return true;
       else return false;
    }

    @Override
    public String toString() {
        return this.LITERAL_STRING_CATEGORY;
    }
}
