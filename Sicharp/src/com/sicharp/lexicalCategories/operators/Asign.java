package com.sicharp.lexicalCategories.operators;

import com.sicharp.lexicalCategories.LexicalCategoriesResources;
import com.sicharp.lexicalCategories.LexicalCategory;

public class Asign extends LexicalCategory {

    private String ASIGNATION_CATEGORY;
    private String[] ASIGNATION_OPERATORS;

    public Asign() {
        this.ASIGNATION_CATEGORY = LexicalCategoriesResources.ASIGNATION_CATEGORY;
        this.ASIGNATION_OPERATORS = LexicalCategoriesResources.ASIGNATION_OPERATORS;
    }

    @Override
    public boolean belongsToThisCategory(String currentInput) {
        if(isInCategory(ASIGNATION_OPERATORS,currentInput))return true;
        return false;
    }

    @Override
    public String toString() {
        return this.ASIGNATION_CATEGORY;
    }
}
