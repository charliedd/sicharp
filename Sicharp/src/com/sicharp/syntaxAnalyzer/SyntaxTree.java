package com.sicharp.syntaxAnalyzer;

import com.sicharp.lexicalAnalyzer.SymbolTable;
import com.sicharp.lexicalAnalyzer.Token;
import com.sicharp.lexicalCategories.others.ReservedWord;

import java.util.ArrayList;
import java.util.List;

public class SyntaxTree {
    Node root;
    int size;

    private enum State{
        START,STATEMENT, DECLARATION, ASIGNATION, COMPARISION, DEFAULT, TERMINAL, VARIABLE, ASIGNDECL, LOOP,
        CONDITIONAL, LOOPSTMNT, CONDITIONALSTMNT;
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

        System.out.println(fierroToken.getAttribute());
        System.out.println(parentesisToken.getAttribute());


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

                for(Token token: currentTokens){
                    String tokenS = token.getAttribute();

                    if(insideConditional){
                        if(tokenS.equals("}")){
                            newNode.addChildNode(parse(currentTokens.subList(startIndex,endIndex + 1),State.CONDITIONAL));
                            startIndex = endIndex + 1;
                            insideConditional = false;
                        }

                    }else if(insideLoop){
                        if(tokenS.equals("}")) {
                            newNode.addChildNode(parse(currentTokens.subList(startIndex, endIndex + 1), State.LOOP));
                            startIndex = endIndex + 1;
                            insideLoop = false;
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

                if(currentTokens.size() == 1){
                    newNode.addChildNode(parse(currentTokens,State.TERMINAL));
                }

                break;

            case LOOP:
                newNode.addChildNode(parse(currentTokens.subList(0,1),State.TERMINAL));  //forloko
                newNode.addChildNode(parse(currentTokens.subList(1,2),State.TERMINAL));  //(

                List<Token> stmntTokens = new ArrayList<>();

//                int index = 2;
//                for (Token token: currentTokens){
//                    if(token.getAttribute().equals(")")){
//                        newNode.addChildNode(parse(stmntTokens, State.LOOPSTMNT));
//                        break;
//                    }else {
//                        stmntTokens.add(token);
//                    }
//
//                    index++;
//                }

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

                System.out.println("SUBLISTA : " + currentTokens.subList(index,index+1));
                newNode.addChildNode(parse(currentTokens.subList(index,index+1),State.TERMINAL)); //)


                newNode.addChildNode(parse(currentTokens.subList(index + 1,index + 2),State.TERMINAL)); //{



                List<Token> stuffInsideLoop = currentTokens.subList(index + 2,currentTokens.size() -1);
                newNode.addChildNode(parse(stuffInsideLoop,State.START));

                List<Token> parentesis = new ArrayList<>();
                parentesis.add(currentTokens.get(size-1));
                newNode.addChildNode(parse(parentesis,State.TERMINAL));//}

                break;

            case CONDITIONAL:
                newNode.addChildNode(parse(currentTokens,State.TERMINAL));
                break;

            case CONDITIONALSTMNT:

                break;

            case LOOPSTMNT:
                newNode.addChildNode(parse(currentTokens,State.TERMINAL));
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

}
