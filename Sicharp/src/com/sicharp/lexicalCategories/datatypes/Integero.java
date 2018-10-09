package com.sicharp.lexicalCategories.datatypes;

import com.sicharp.lexicalCategories.LexicalCategory;

public class Integero extends LexicalCategory {
    @Override
    public boolean belongsToThisCategory(String currentInput) {
        try{
            Integer.valueOf(currentInput);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entero";
    }
}
