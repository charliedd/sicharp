package com.sicharp.lexicalAnalyzer;

public class Token {
    private String lexicalCategory;
    private String lexeme;
    private String attribute;

    public Token(String lexicalCategory, String lexeme, String attribute){
        this.lexicalCategory = lexicalCategory;
        this.lexeme = lexeme;
        this.attribute = attribute;
    }

    public String getLexicalCategory() {
        return this.lexicalCategory;
    }

    public String getLexeme() {
        return this.lexeme;
    }

    public String getAttribute(){ return this.attribute;}

    public String toString(){
        return
                "Lexical Category = " + this.lexicalCategory
                        + "Lexeme = " + this.lexeme
                        + "Attribute = " + this.attribute;
    };
}



