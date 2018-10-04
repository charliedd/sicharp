package com.sicharp.lexicalCategories;

public class Identifier extends LexicalCategory {
    private String IDENTIFIERS_CATEGORY;

    public Identifier() {
        this.IDENTIFIERS_CATEGORY = LexicalCategoriesResources.IDENTIFIERS_CATEGORY;
    }

    @Override
    public boolean belongsToThisCategory(String currentInput) {
        if(onlyContainsNumbersAndLetters(currentInput))
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return this.IDENTIFIERS_CATEGORY;
    }


    private boolean onlyContainsNumbersAndLetters(String input){
        return input.matches("[a-zA-Z0-9]*");
    }
}
