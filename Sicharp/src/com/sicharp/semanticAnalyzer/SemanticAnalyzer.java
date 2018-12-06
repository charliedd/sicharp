package com.sicharp.semanticAnalyzer;

import com.sicharp.lexicalAnalyzer.Token;
import com.sicharp.syntaxAnalyzer.Node;
import com.sicharp.syntaxAnalyzer.SyntaxTree;

import java.util.List;

public class SemanticAnalyzer {

    private SyntaxTree syntaxTree;
    private VariableList variableList;



    public SemanticAnalyzer(SyntaxTree syntaxTree) {
        this.syntaxTree = syntaxTree;
        this.variableList = new VariableList();
        start();
    }

    public void start(){
        Node root = syntaxTree.getRoot();
        traverseTree(root);
    }

    public void traverseTree(Node node){

        if(node.getType().equals("DECLARATION")){
            System.out.println("ESTO ES UNA DECLARACION: " + node.getTokens());
            List<Node> childNodes = node.getChildNodes();

            Node nodeCategory = childNodes.get(0);
            Node nodeName = childNodes.get(1);

            String category = nodeCategory.getTokens().get(0).getAttribute().toString();
            String name = nodeName.getTokens().get(0).getAttribute().toString();

            if(variableList.checkIfVariableExists(name)){
                System.out.println("Esta variable ("+ name +") ya fue declarada");
            }else{
                variableList.addVariable(category,name,"");
            }

        }

        if(node.getType().equals("ASIGNDECL")){
            System.out.println("ESTO ES UNA ASIGNACIONDECLARACION: " + node.getTokens());

            List<Node> childNodes = node.getChildNodes();

            Node nodeCategory = childNodes.get(0);
            Node nodeName = childNodes.get(1);
            Node nodeValue = childNodes.get(3);

            String category = nodeCategory.getTokens().get(0).getAttribute().toString();
            String name = nodeName.getTokens().get(0).getAttribute().toString();
            String value = nodeValue.getTokens().get(0).getAttribute().toString();

            if(variableList.checkIfVariableExists(name)){
                System.out.println("Esta variable (" + name +") ya fue declarada");
            }else{
                if(isTypeCorrect(name, nodeValue, category)){
                    //variableList.updateVariableValue(name,value);
                    variableList.addVariable(category, name, value);
                }else{
                    System.out.print("Error : ");
                    for(Node nodechi : childNodes){
                        System.out.print(nodechi.getTokens() + ",");
                    }
                    System.out.println("\n");
                }
            }



        }

        if(node.getType().equals("ASIGNATION")){
            System.out.println("ESTO ES UNA ASIGNACION " + node.getTokens());

            List<Node> childNodes = node.getChildNodes();

            Node nodeName = childNodes.get(0);
            Node nodeValue = childNodes.get(2);


            String name = nodeName.getTokens().get(0).getAttribute().toString();
            String value = nodeValue.getTokens().get(0).getAttribute().toString();

            String expectedType = variableList.getVariableType(name);

            if(variableList.checkIfVariableExists(name)){

                if(isTypeCorrect(name, nodeValue, expectedType)){
                    variableList.updateVariableValue(name,value);
                }else{
                    System.out.println("Error" + childNodes);
                }
            }else{
                System.out.println("La variable ("+ name +") no ha sido declarada!");
            }

        }

        for(Node childNode: node.getChildNodes()){
            traverseTree(childNode);
        }

    }

    public boolean isTypeCorrect(String name, Node node, String type){
        List<Token> tokenList = node.getTokens();

        if(type.equals("entera")){
            for (Token token : tokenList){
                String category = token.getLexicalCategory().toString();
                String value = token.getAttribute();

                if(category.equals("Identificador")){

                    if(!variableList.checkIfVariableExists(value)){
                        System.out.println("La variable " + value + " no ha sido declarada");
                        return false;
                    }else{

                        if(variableList.getVariableValue(value).isEmpty()){
                            System.out.println("La variable (" + value+") no se le ha asignado valor");
                            return false;
                        }

                        String typeVar = variableList.getVariableType(value);

                        if(!typeVar.equals("entera")) {
                            System.out.println("La variable " + value + " no es tipo entero");
                            return false;
                        }




                    }

                }else{
                    if( !category.equals("Operador_Aritmetico") && !category.equals("Entero")){
                        System.out.println("No se le puede asignar el valor ("+ value +") tipo "+ category + " a la variable " + name +
                                " tipo " + "Entero");
                        System.out.println("categoria : " +category);
                        return false;
                    }
                }

            }
        }
        else if(type.equals("df")){
            for(Token token : tokenList){
                String category = token.getLexicalCategory().toString();
                String value = token.getAttribute();

                if(category.equals("Identificador")){

                    if(!variableList.checkIfVariableExists(value)){
                        System.out.println("La variable " + value + " no ha sido declarada");
                        return false;
                    }else{

                        if(variableList.getVariableValue(value).isEmpty()){
                            System.out.println("La variable (" + value+") no se le ha asignado valor");
                            return false;
                        }


                        String typeVar = variableList.getVariableType(value);

                        if(!typeVar.equals("strin")) {
                            System.out.println("La variable " + value + " no es tipo strin");
                            return false;
                        }

                    }

                }else {


                    if (!value.equals("+") && !category.equals("Strin")) {
                        System.out.println("No se le puede asignar el valor (" + value + ") tipo " + category + " a la variable " + name +
                                " tipo " + "Strin");
                        System.out.println("categoria : " + category);
                        return false;
                    }
                }
            }
        }
        else if(type.equals("strin")){
            for(Token token : tokenList){
                String category = token.getLexicalCategory().toString();
                String value = token.getAttribute();

                if(category.equals("Identificador")){

                    if(!variableList.checkIfVariableExists(value)){
                        System.out.println("La variable " + value + " no ha sido declarada");
                        return false;
                    }else{

                        if(variableList.getVariableValue(value).isEmpty()){
                            System.out.println("La variable (" + value+") no se le ha asignado valor");
                            return false;
                        }


                        String typeVar = variableList.getVariableType(value);

                        if(!typeVar.equals("strin")) {
                            System.out.println("La variable " + value + " no es tipo strin");
                            return false;
                        }

                    }

                }else {


                    if (!value.equals("+") && !category.equals("Strin")) {
                        System.out.println("No se le puede asignar el valor (" + value + ") tipo " + category + " a la variable " + name +
                                " tipo " + "Strin");
                        System.out.println("categoria : " + category);
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public VariableList getVariableList() {
        return variableList;
    }
}
