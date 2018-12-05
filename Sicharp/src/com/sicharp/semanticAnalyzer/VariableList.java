package com.sicharp.semanticAnalyzer;

import java.util.ArrayList;
import java.util.List;

public class VariableList {

    List<Variable> variables = new ArrayList<>();

    public void addVariable(String type, String name, String value){
        Variable newVariable = new Variable();

        newVariable.setType(type);
        newVariable.setName(name);
        newVariable.setValue(value);

        variables.add(newVariable);
    }

    public void addVariable(Variable variable){
        variables.add(variable);
    }

    public Variable searchByVarName(String name){
        for(Variable variable : variables){
            if(variable.getName().equals(name)){
                return variable;
            }
        }

        return null;
    }

    public List<Variable> getVariables() {
        return variables;
    }
}
