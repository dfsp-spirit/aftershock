/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.parsing.lexer;


import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author spirit
 */
public class TokenTest {

    

    Token token;
    
    public TokenTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {        
    }
    
    @After
    public void tearDown() {
        token = null;
    }
    
    @org.junit.Test
    public void testItHasTheExpectedConstructorValues() {
        String inputText = "// Some comment.";
        token = new Token(Quake2MapLexer.COMMENT, inputText);
        assertEquals(Quake2MapLexer.COMMENT, token.type);
        assertEquals(inputText, token.text);
    }
    
    @org.junit.Test
    public void testItHasExpectedToStringBehaviour() {
        String inputText = "// Some comment.";
        token = new Token(Quake2MapLexer.COMMENT, inputText);
        assertEquals("<'// Some comment.',COMMENT>", token.toString());
    }
}