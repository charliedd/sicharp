package com.sicharp.lexicalCategories;

public class ReservedWord extends LexicalCategory{

    public static String RESERVED_WORDS_CATEGORY = "reservada";
    public static String[] RESERVED_WORDS = {"fierro","vodka","ej","entera","forloko"};

    public ReservedWord() {
        RESERVED_WORDS_CATEGORY = LexicalCategoriesResources.RESERVED_WORDS_CATEGORY;
        RESERVED_WORDS = LexicalCategoriesResources.RESERVED_WORDS;
    }

    @Override
    public boolean belongsToThisCategory(String currentInput) {
        if(isInCategory(RESERVED_WORDS,currentInput))return true;
        return false;
    }

    @Override
    public String toString() {
        return RESERVED_WORDS_CATEGORY;
    }
}