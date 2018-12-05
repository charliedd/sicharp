package com.sicharp.syntaxAnalyzer;

import com.sicharp.lexicalAnalyzer.SymbolTable;
import com.sicharp.lexicalAnalyzer.Token;
import com.sicharp.lexicalCategories.LexicalCategory;
import com.sicharp.lexicalCategories.operators.ArithmeticOperator;
import com.sicharp.lexicalCategories.operators.ComparissionOperator;
import com.sicharp.lexicalCategories.operators.LogicalOperator;
import com.sicharp.lexicalCategories.others.Identifier;
import com.sicharp.lexicalCategories.others.ReservedWord;
import com.sicharp.lexicalCategories.symbols.Symbol;

import java.util.ArrayList;
import java.util.List;

public class SyntaxTree {
    Node root;
    int size;

    private enum State{
        START,STATEMENT, DECLARATION, ASIGNATION, COMPARISION, DEFAULT, TERMINAL, VARIABLE, ASIGNDECL, LOOP,
        CONDITIONAL, LOOPSTMNT, CONDITIONALSTMNT, IDENTIFIER, ARIOP, COMOP, LOGOP, FORSTMNT;
    }

    public SyntaxTree(SymbolTable symbolTable){
        List<Token> tokenList = symbolTable.getTokenList();
        Node node = new Node(tokenList, State.START.toString());
        root = node;
        size = 1;
        createTree();
    }

    private void createTree(){

        if(!checkFirstProductionRule()){
            System.out.println("no hay regla de produccion inicial (S)");
            return;
        }



        // root = parse(root);
    }

    private boolean checkFirstProductionRule(){
        List<Token> currentTokens = root.getTokens();

        int size = currentTokens.size();

        Token fierroToken = currentTokens.get(0);
        Token parentesisToken = currentTokens.get(1);
        Token parentesisCerrado = currentTokens.get(2);
        Token llaveAbierto = currentTokens.get(3);
        Token llaveCerrada = currentTokens.get(size -1);


        if(!fierroToken.getAttribute().equals("fierro")) return false;
        if(!parentesisToken.getAttribute().equals("("))return false;
        if(!parentesisCerrado.getAttribute().equals(")"))return false;
        if(!llaveAbierto.getAttribute().equals("{"))return false;
        if(!llaveCerrada.getAttribute().equals("}"))return false;

        root.addChildNode(new Node(fierroToken,State.TERMINAL.toString()));
        root.addChildNode(new Node(parentesisToken,State.TERMINAL.toString()));
        root.addChildNode(new Node(parentesisCerrado,State.TERMINAL.toString()));
        root.addChildNode(new Node(llaveAbierto,State.TERMINAL.toString()));
        root.addChildNode(new Node(llaveCerrada,State.TERMINAL.toString()));
        root.addChildNode(parse(currentTokens.subList(4,size-1),State.START));

        return true;

    }

    private Node parse(List<Token> currentTokens, State state){
        Node newNode = new Node(currentTokens, state.toString());
        int size = currentTokens.size();

        switch (state){
            case START:

                boolean insideConditional = false;
                boolean insideLoop = false;

                int startIndex = 0;
                int endIndex = 0;

                int openingBrackets = 0;
                int closingBrackets = 0;

                for(Token token: currentTokens){
                    String tokenS = token.getAttribute();

                    if(insideConditional){
                        if(tokenS.equals("}")) {
                            closingBrackets++;
                            if(openingBrackets == closingBrackets){
                                newNode.addChildNode(parse(currentTokens.subList(startIndex, endIndex + 1), State.CONDITIONAL));
                                startIndex = endIndex + 1;
                                insideConditional = false;
                                openingBrackets = 0;
                                closingBrackets = 0;
                            }
                        }else{
                            if(tokenS.equals("{"))
                                openingBrackets++;
                        }

                    }else if(insideLoop){
                        if(tokenS.equals("}")) {
                            closingBrackets++;
                            if(openingBrackets == closingBrackets){
                                newNode.addChildNode(parse(currentTokens.subList(startIndex, endIndex + 1), State.LOOP));
                                startIndex = endIndex + 1;
                                insideLoop = false;
                                openingBrackets = 0;
                                closingBrackets = 0;
                            }
                        }else{
                            if(tokenS.equals("{"))
                                openingBrackets++;
                        }
                    }else{
                        if(tokenS.equals("jalas")){
                            insideConditional = true;
                        }else if(token.getAttribute().equals("forloko")){
                            insideLoop = true;
                        }else if(token.getAttribute().equals(";")){
                            newNode.addChildNode(parse(currentTokens.subList(startIndex,endIndex+1),State.STATEMENT));
                            startIndex = endIndex + 1;
                        }
                    }
                    endIndex++;

                }

                break;

            case STATEMENT:

                if(currentTokens.get(0).getLexicalCategory() instanceof ReservedWord){
                    if( currentTokens.get(2).getLexeme().equals("=")){
                        newNode.addChildNode(parse(currentTokens,State.ASIGNDECL));
                    }else if( currentTokens.size() == 3){
                        newNode.addChildNode(parse(currentTokens,State.DECLARATION));
                    }
                }
                else if( currentTokens.get(1).getLexeme().equals("=")){
                    newNode.addChildNode(parse(currentTokens,State.ASIGNATION));
                }
                break;

            case ASIGNATION:

                newNode.addChildNode(parse(currentTokens.subList(0,1),State.TERMINAL));
                newNode.addChildNode(parse(currentTokens.subList(1,2),State.TERMINAL));
                newNode.addChildNode(parse(currentTokens.subList(2,size-1),State.VARIABLE));
                newNode.addChildNode(parse(currentTokens.subList(size-1,size),State.TERMINAL));

                break;

            case DECLARATION:

                for(Token token : currentTokens){
                    List<Token> tempTokens = new ArrayList<>();
                    tempTokens.add(token);
                    newNode.addChildNode(parse(tempTokens,State.TERMINAL));
                }

                break;

            case ASIGNDECL:

                newNode.addChildNode(parse(currentTokens.subList(0,1),State.TERMINAL));
                newNode.addChildNode(parse(currentTokens.subList(1,2),State.TERMINAL));
                newNode.addChildNode(parse(currentTokens.subList(2,3),State.TERMINAL));
                newNode.addChildNode(parse(currentTokens.subList(3,size-1),State.VARIABLE));
                newNode.addChildNode(parse(currentTokens.subList(size-1,size),State.TERMINAL));

                break;

            case VARIABLE:
                //<var> -> <id> | <cte> | <funccall> | vacio | <oper>

                if(currentTokens.size() == 1){
                    LexicalCategory category = currentTokens.get(0).getLexicalCategory();
                    if(category instanceof Identifier){
                        newNode.addChildNode(parse(currentTokens,State.IDENTIFIER));
                    }else{
                        newNode.addChildNode(parse(currentTokens,State.TERMINAL));
                    }
                }else{
                    for(Token tokn : currentTokens){

                        if(tokn.getLexicalCategory() instanceof ArithmeticOperator){
                            newNode.addChildNode(parse(currentTokens,State.ARIOP));
                            break;
                        }else if(tokn.getLexicalCategory() instanceof ComparissionOperator){
                            newNode.addChildNode(parse(currentTokens,State.COMOP));
                            break;

                        }else if(tokn.getLexicalCategory() instanceof LogicalOperator){
                            newNode.addChildNode(parse(currentTokens,State.LOGOP));
                            break;
                        }


                    }

                }

                break;

            case LOOP:
                newNode.addChildNode(parse(currentTokens.subList(0,1),State.TERMINAL));  //forloko
                newNode.addChildNode(parse(currentTokens.subList(1,2),State.TERMINAL));  //(

                List<Token> stmntTokens = new ArrayList<>();

                int index = 2;
                for(int i = 2; i < currentTokens.size(); i++){
                    Token token = currentTokens.get(i);
                    if(token.getAttribute().equals(")")){
                        newNode.addChildNode(parse(stmntTokens,State.LOOPSTMNT));
                        index = i;
                        break;
                    }else{
                        System.out.println(token);
                        stmntTokens.add(token);
                    }

                }
                newNode.addChildNode(parse(currentTokens.subList(index,index+1),State.TERMINAL)); //)


                newNode.addChildNode(parse(currentTokens.subList(index + 1,index + 2),State.TERMINAL)); //{



                List<Token> stuffInsideLoop = currentTokens.subList(index + 2,currentTokens.size() -1);
                newNode.addChildNode(parse(stuffInsideLoop,State.START));

                List<Token> parentesis = new ArrayList<>();
                parentesis.add(currentTokens.get(size-1));
                newNode.addChildNode(parse(parentesis,State.TERMINAL));//}

                break;

            case CONDITIONAL:

                newNode.addChildNode(parse(currentTokens.subList(0,1),State.TERMINAL));  //forloko
                newNode.addChildNode(parse(currentTokens.subList(1,2),State.TERMINAL));  //(

                List<Token> stmntTokenscond = new ArrayList<>();

                int index2 = 2;
                for(int i = 2; i < currentTokens.size(); i++){
                    Token token = currentTokens.get(i);
                    if(token.getAttribute().equals(")")){
                        newNode.addChildNode(parse(stmntTokenscond,State.CONDITIONALSTMNT));
                        index2 = i;
                        break;
                    }else{
                        System.out.println(token);
                        stmntTokenscond.add(token);
                    }

                }

                System.out.println("SUBLISTA : " + currentTokens.subList(index2,index2+1));
                newNode.addChildNode(parse(currentTokens.subList(index2,index2+1),State.TERMINAL)); //)


                newNode.addChildNode(parse(currentTokens.subList(index2 + 1,index2 + 2),State.TERMINAL)); //{



                List<Token> stuffInsideConditional = currentTokens.subList(index2 + 2,currentTokens.size() -1);
                newNode.addChildNode(parse(stuffInsideConditional,State.START));

                List<Token> parenthesis = new ArrayList<>();
                parenthesis.add(currentTokens.get(size-1));
                newNode.addChildNode(parse(parenthesis,State.TERMINAL));//}

                break;

            case CONDITIONALSTMNT:
                newNode.addChildNode(parse(currentTokens,State.VARIABLE));

                break;

            case LOOPSTMNT:
                // newNode.addChildNode(parse(currentTokens,State.TERMINAL));

                int index6 = 0;

                List<Integer> listIndexes = new ArrayList<>();

                for(Token tkn : currentTokens){
                    if(tkn.getLexicalCategory() instanceof Symbol){
                        listIndexes.add(index6);
                    }

                    index6+=1;
                }

                newNode.addChildNode(parse(currentTokens.subList(0,listIndexes.get(0)),State.ASIGNDECL));
                newNode.addChildNode(parse(currentTokens.subList(listIndexes.get(0)+1,listIndexes.get(1)),state.TERMINAL));
                newNode.addChildNode(parse(currentTokens.subList(listIndexes.get(1)+ 1,currentTokens.size()),State.ASIGNATION));

                break;

            case IDENTIFIER:
                newNode.addChildNode(parse(currentTokens,State.TERMINAL));
                break;

            case ARIOP:
                if(currentTokens.size() == 1){
                    LexicalCategory category = currentTokens.get(0).getLexicalCategory();
                    if(category instanceof Identifier){
                        newNode.addChildNode(parse(currentTokens,State.IDENTIFIER));
                    }else{
                        newNode.addChildNode(parse(currentTokens,State.TERMINAL));
                    }
                }else{
                    int index3 = 0;
                    int size2 = currentTokens.size();
                    for(Token tokn : currentTokens){

                        if(tokn.getLexicalCategory() instanceof ArithmeticOperator) {
                            newNode.addChildNode(parse(currentTokens.subList(0, 1), State.VARIABLE));
                            newNode.addChildNode(parse(currentTokens.subList(1, 2), State.TERMINAL));
                            newNode.addChildNode(parse(currentTokens.subList(index3 + 1, size2), State.ARIOP));
                        }
                        index3 += 1;
                    }

                }
                //newNode.addChildNode(parse(currentTokens.subList(index+1,size2),State.VARIABLE));
                break;

            case COMOP:

                if(currentTokens.size() == 1){
                    LexicalCategory category = currentTokens.get(0).getLexicalCategory();
                    if(category instanceof Identifier){
                        newNode.addChildNode(parse(currentTokens,State.IDENTIFIER));
                    }else{
                        newNode.addChildNode(parse(currentTokens,State.TERMINAL));
                    }
                }else{
                    int index3 = 0;
                    int size2 = currentTokens.size();
                    for(Token tokn : currentTokens){

                        if(tokn.getLexicalCategory() instanceof ComparissionOperator) {
                            newNode.addChildNode(parse(currentTokens.subList(0, 1), State.VARIABLE));
                            newNode.addChildNode(parse(currentTokens.subList(1, 2), State.TERMINAL));
                            newNode.addChildNode(parse(currentTokens.subList(index3 + 1, size2), State.COMOP));
                        }
                        index3 += 1;
                    }

                }

                break;

            case LOGOP:

                break;


        }

        return newNode;
    }


    @Override
    public String toString() {
        StringBuilder treeAsString = new StringBuilder();
        parseTreeForString(treeAsString,root);
        return treeAsString.toString();
    }

    public void parseTreeForString(StringBuilder treeAsString,Node currentNode){
        treeAsString.append("type: " + currentNode.getType() + " Tokens: " + currentNode.getTokens());
        treeAsString.append('\n');
        for(Node node: currentNode.getChildNodes()){
            parseTreeForString(treeAsString,node);
        }
    }

    public Node getRoot() {
        return root;
    }
}