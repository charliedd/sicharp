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

    public boolean checkIfVariableExists(String name){
        for(Variable variable : variables){
            if(variable.getName().equals(name)){
                return true;
            }
        }

        return false;
    }

    public String getVariableType(String name){
        for(Variable variable : variables){
            if(variable.getName().equals(name)){
                return variable.getType();
            }
        }
        return "";
    }

    public String getVariableValue(String name){
        for(Variable variable : variables){
            if(variable.getName().equals(name)){
                return variable.getValue();
            }
        }
        return "";
    }

    public void updateVariableValue(String name, String value){
        for(Variable variable : variables){
            if(variable.getName().equals(name)){
                variable.setValue(value);
                return;
            }
        }

    }

    public List<Variable> getVariables() {
        return variables;
    }
}
