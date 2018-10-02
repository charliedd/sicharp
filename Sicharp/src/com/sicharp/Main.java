package com.sicharp;

import com.sicharp.lexicalAnalyzer.SourceFileReader;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Stack<Character> tempStack = new Stack<>();
        tempStack.push('a');
        tempStack.push('b');
        tempStack.push('c');
        for(Character chara : tempStack)
            System.out.print(chara);
    }
}
