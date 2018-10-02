package com.sicharp.lexicalCategories;

public class LexicalCategoriesResources {

    public static String AGRUPATOR_CATEGORY = "agrupador";
    public static char[] OPENING_AGRUPATORS = {'{','[','('};
    private char[] CLOSING_AGRUPATORS = {'}',']',')'};

    public static String ARITHMETIC_OPERATOR_CATEGORY = "operador_aritmetico";
    public static char[] ARITHMETIC_OPERATORS = {'+','-','*','/'};

    public static String LOGICAL_OPERATOR_CATEGORY = "operador_logico";
    public static char[] LOGICAL_OPERATORS = {'&','|'};

    public static String COMPARITION_OPERATORS_CATEGORY = "operador_comparacion";
    public static String[] COMPARITION_OPERATORS = {"==","<=",">=","<",">","!="};

    public static String ASIGNATION_CATEGORY = "asignacion";
    public static char[] ASIGNATION_OPERATORS = {'='};

    public static String RESERVED_WORDS_CATEGORY = "reservada";
    public static String[] RESERVED_WORDS = {"fierro","vodka","ej","entera","forloko"};

    public static String LITERAL_STRING_CATEGORY = "Literal";
    public static char[] LITERAL_SIMBOL = {'"'};

    public static String INTEGER_CATEGORY = "entero";

    public static String SYMBOLS_CATEGORY = "simbolos";
    public static char[] SPECIAL_SIMBOLS = {';',','};

    public static String IDENTIFIERS_CATEGORY = "identificadores";

    public static String FLOATING_POINT_CATEGORY = "flotante";

}
