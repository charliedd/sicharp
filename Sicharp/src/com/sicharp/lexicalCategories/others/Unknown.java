package com.sicharp.lexicalCategories.others;

import com.sicharp.lexicalCategories.LexicalCategory;

public class Unknown extends LexicalCategory {


    @Override
    public boolean belongsToThisCategory(String currentInput) {
        return false;
    }

    @Override
    public String toString() {
        return "QUE ESTAS HACCIENDO?";
    }
}
