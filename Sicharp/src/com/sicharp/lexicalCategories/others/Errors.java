package com.sicharp.lexicalCategories.others;

import com.sicharp.lexicalCategories.LexicalCategoriesResources;
import com.sicharp.lexicalCategories.LexicalCategory;

public class Errors extends LexicalCategory {
    public static String ERRORS_CHARACTER_CATEGORY;
    public static String[] ERRORS_CHARACTER;

    public Errors() {

        ERRORS_CHARACTER_CATEGORY = LexicalCategoriesResources.ERRORS_CHARACTER_CATEGORY;
        ERRORS_CHARACTER = LexicalCategoriesResources.ERRORS_CHARACTER;
    }

    @Override
    public boolean belongsToThisCategory(String currentInput) {
        if(isInCategory(ERRORS_CHARACTER,currentInput))return true;
        return false;
    }

    @Override
    public String toString() {
        return ERRORS_CHARACTER_CATEGORY;
    }

}
