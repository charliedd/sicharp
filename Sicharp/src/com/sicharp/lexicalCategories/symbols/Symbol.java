package com.sicharp.lexicalCategories.symbols;

import com.sicharp.lexicalCategories.LexicalCategoriesResources;
import com.sicharp.lexicalCategories.LexicalCategory;

public class Symbol extends LexicalCategory {
    private String SYMBOLS_CATEGORY;
    private String[] SPECIAL_SYMBOLS;

    public Symbol() {
        this.SYMBOLS_CATEGORY = LexicalCategoriesResources.SYMBOLS_CATEGORY;
        this.SPECIAL_SYMBOLS = LexicalCategoriesResources.SPECIAL_SYMBOLS;
    }

    @Override
    public boolean belongsToThisCategory(String currentInput) {
        if(isInCategory(SPECIAL_SYMBOLS,currentInput))return true;
        return false;

    }

    @Override
    public String toString() {
        return this.SYMBOLS_CATEGORY;
    }
}
