package com.sicharp.lexicalAnalyzer;

import com.sicharp.lexicalCategories.LexicalCategory;

public class Token {
    private LexicalCategory lexicalCategory;
    private String lexeme;
    private String attribute;

    public Token(LexicalCategory lexicalCategory, String lexeme, String attribute) {
        this.lexicalCategory = lexicalCategory;
        this.lexeme = lexeme;
        this.attribute = attribute;
    }

    public String getLexeme() {
        return this.lexeme;
    }

    public String getAttribute(){ return this.attribute;}

    public LexicalCategory getLexicalCategory() {
        return lexicalCategory;
    }

    public String toString(){
        return
                "Lexical Category = " + this.lexicalCategory
                        + "\t\t\t\tLexeme = " + this.lexeme
                        + "\t\t\t\tAttribute = " + this.attribute;
    };
}



