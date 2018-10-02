package com.sicharp.lexicalCategories;

public abstract class LexicalCategory {
    public abstract boolean belongsToThisCategory(String currentInput);
    public abstract String toString();
    public boolean isInCategory(String stringsInCategory[], String currentString) {
        for (String stringFromCategory: stringsInCategory){
            if (stringFromCategory.equals(currentString)) return true;
        }
        return false;
    }
}
