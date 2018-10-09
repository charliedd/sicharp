package com.sicharp.lexicalCategories;

public class Integero extends LexicalCategory{
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
