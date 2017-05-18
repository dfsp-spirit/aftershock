/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rcmd.qmapc.parsing.lexer;

/**
 *
 * @author spirit
 */
public abstract class Lexer {
    
    public static final char EOF = (char)-1;
    public static final int EOF_TYPE = 1;
    
    String input;
    int p = 0;      // current position in input
    char c;         // current character
    
    
    public Lexer(String input) {
        this.input = input;
        this.c = input.charAt(this.p);           // prime lookahead
    }    
    
    public void consume() {
        this.p++;
        if(this.p >= this.input.length()) {
            this.c = Lexer.EOF;
        }
        else {
            this.c = input.charAt(this.p);
        }
    }
    
    public void match(char expected) {
        if(this.c == expected) {
            this.consume();
        }
        else {
            throw new Error("Expected '" + expected + "', but found '" + this.c + "'.");
        }
    }
    
    public abstract Token nextToken();
    
    public abstract String getTokenName(int tokenType);
    
}