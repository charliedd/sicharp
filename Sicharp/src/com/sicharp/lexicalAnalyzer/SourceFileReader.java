package com.sicharp.lexicalAnalyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Stack;

/*
    Reads all the chars inside the source file and stores them in a Stack.
*/

public class SourceFileReader {

    private String sourceFileURL;
    private Stack<Character> charsInSourceFile;

    public SourceFileReader(String sourceFileURL){
        this.sourceFileURL = sourceFileURL;
        charsInSourceFile = new Stack<>();
        readSourceFile();
    }

    private void readSourceFile(){
        File sourceFile = new File(sourceFileURL);
        checkFileExists(sourceFile);
        checkItCanBeRead(sourceFile);
        readEveryCharIntoStack(sourceFile);
    }

    private void readEveryCharIntoStack(File sourceFile) {
        try {
            FileInputStream fis = new FileInputStream(sourceFile);
            char current;
            while (fis.available() > 0) {
                current = (char) fis.read();
                charsInSourceFile.push(current);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkFileExists(File sourceFile){
        try{
            if(!sourceFile.exists()) throw new Exception("File not Found");
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void checkItCanBeRead(File sourceFile){
        try{
            if(!sourceFile.canRead()) throw new Exception("File can't be opened for reading");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Stack<Character> getCharsInSourceFile(){
        return charsInSourceFile;
    }
}
