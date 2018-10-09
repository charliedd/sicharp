package com.sicharp.lexicalCategories;

import com.sicharp.lexicalCategories.LexicalCategory;

public class BigInt extends LexicalCategory {
    @Override
    public boolean belongsToThisCategory(String currentInput) {

        try{
            java.lang.Float.valueOf(currentInput);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Float";
    }
}
