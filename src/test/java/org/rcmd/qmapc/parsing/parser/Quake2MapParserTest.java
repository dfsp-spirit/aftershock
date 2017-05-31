/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.parsing.parser;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.rcmd.qmapc.parsing.lexer.Quake2MapLexer;
import org.rcmd.qmapc.parsing.lexer.Token;

/**
 *
 * @author spirit
 */
public class Quake2MapParserTest {

    Quake2MapLexer q2ml;
    Quake2MapParser q2mp;
    Token token;

    public Quake2MapParserTest() {
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
        q2ml = null;
        q2mp = null;
    }

    @org.junit.Test
    public void testItProperlyParsesAnEntityLineWithAStringValue() {
        String input = "\"classname\" \"worldspawn\"";

        q2ml = new Quake2MapLexer(input);
        q2mp = new Quake2MapParser(q2ml);

        q2mp.anyEntityKeyValueLine();
    }

    @org.junit.Test
    public void testItProperlyParsesAnEntityLineWithAPoint3DFloatValue() {
        String input = "\"_color\" \"1.0 0.8 0.8\"";

        q2ml = new Quake2MapLexer(input);
        q2mp = new Quake2MapParser(q2ml);

        q2mp.anyEntityKeyValueLine();
    }

    @org.junit.Test
    public void testItProperlyParsesAnEntityLineWithAStringIncludingSpecialChars() {
        String input = "\"message\" \"This is a string with ;(){} many special . chars and 234234 digits.s\"";

        q2ml = new Quake2MapLexer(input);
        q2mp = new Quake2MapParser(q2ml);

        q2mp.anyEntityKeyValueLine();
    }

    @org.junit.Test
    public void testItProperlyParsesAQuake2BrushWithBrushIDComment() {
        String input = "// brush 0\n"
                + "{\n"
                + "( 248 320 192 ) ( 248 -128 192 ) ( -136 320 192 ) spirit2dm9/floor_3 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -128 312 192 ) ( -128 312 64 ) ( -128 -136 192 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -128 -128 160 ) ( 256 -128 160 ) ( -128 320 160 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -136 -128 64 ) ( -136 -128 192 ) ( 248 -128 64 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -192 -120 64 ) ( -192 328 64 ) ( -192 -120 192 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -192 0 -96 ) ( -128 -64 -96 ) ( -192 0 32 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "}";

        q2ml = new Quake2MapLexer(input);
        q2mp = new Quake2MapParser(q2ml);

        q2mp.q2BrushWithBrushIDComment();
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testItThrowsAnExceptionWhenParsingAnInvalidBrush() {
        String input = "// brush 0\n"
                + "{\n"
                + "( 248 ) ( 248 -128 192 ) ( -136 320 192 ) spirit2dm9/floor_3 0 0 0 1.000000 1.000000 0 1 50\n"
                + "}";

        q2ml = new Quake2MapLexer(input);
        q2mp = new Quake2MapParser(q2ml);

        q2mp.q2BrushWithBrushIDComment();
    }

    @org.junit.Test
    public void testItPoperlyParsesAWorldspawnEntityWithOneBrush() {
        String input = "// entity 0\n"
                + "{\n"
                + "\"classname\" \"worldspawn\"\n"
                + "\"message\" \"spirit2dm9 - The cake is a lie\"\n"
                + "\"_ambient\" \"5\"\n"
                + "\"_sun\" \"star\"\n"
                + "\"_sun_ambient\" \"5 5 5\"\n"
                + "\"_sun_color\" \"1.0 0.8 0.8\"\n"
                + "\"_sun_light\" \"240\"\n"
                + "\"sky\" \"spirit2dm9/phobos\"\n"
                + "\"_color\" \"1.0 0.8 0.8\"\n"
                + "\"_sun_diffuse\" \"120\"\n"
                + "\"_sun_diffwait\" \"1\"\n"
                + "// brush 0\n"
                + "{\n"
                + "( 248 320 192 ) ( 248 -128 192 ) ( -136 320 192 ) spirit2dm9/floor_3 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -128 312 192 ) ( -128 312 64 ) ( -128 -136 192 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -128 -128 160 ) ( 256 -128 160 ) ( -128 320 160 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -136 -128 64 ) ( -136 -128 192 ) ( 248 -128 64 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -192 -120 64 ) ( -192 328 64 ) ( -192 -120 192 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -192 0 -96 ) ( -128 -64 -96 ) ( -192 0 32 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "}\n"
                + "}";
        q2ml = new Quake2MapLexer(input);
        q2mp = new Quake2MapParser(q2ml);

        q2mp.q2EntityWithEntityIDComment();

    }

    @org.junit.Test
    public void testItPoperlyParsesAWorldspawnEntityWithTwoBrushes() {
        String input = "// entity 0\n"
                + "{\n"
                + "\"classname\" \"worldspawn\"\n"
                + "\"message\" \"spirit2dm9 - The cake is a lie\"\n"
                + "\"_ambient\" \"5\"\n"
                + "\"_sun\" \"star\"\n"
                + "\"_sun_ambient\" \"5 5 5\"\n"
                + "\"_sun_color\" \"1.0 0.8 0.8\"\n"
                + "\"_sun_light\" \"240\"\n"
                + "\"sky\" \"spirit2dm9/phobos\"\n"
                + "\"_color\" \"1.0 0.8 0.8\"\n"
                + "\"_sun_diffuse\" \"120\"\n"
                + "\"_sun_diffwait\" \"1\"\n"
                + "// brush 0\n"
                + "{\n"
                + "( 248 320 192 ) ( 248 -128 192 ) ( -136 320 192 ) spirit2dm9/floor_3 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -128 312 192 ) ( -128 312 64 ) ( -128 -136 192 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -128 -128 160 ) ( 256 -128 160 ) ( -128 320 160 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -136 -128 64 ) ( -136 -128 192 ) ( 248 -128 64 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -192 -120 64 ) ( -192 328 64 ) ( -192 -120 192 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "( -192 0 -96 ) ( -128 -64 -96 ) ( -192 0 32 ) spirit2dm9/trim_9 0 0 0 1.000000 1.000000 0 1 50\n"
                + "}\n"
                + "// brush 1\n"
                + "{\n"
                + "( -16 -384 -128 ) ( -16 64 -128 ) ( 368 -384 -128 ) spirit2dm9/floor_3 0 96 0 1.000000 1.000000 0 0 0\n"
                + "( -24 -128 -88 ) ( 360 -128 -88 ) ( -24 -128 -216 ) spirit2dm9/floor_3 40 0 0 1.000000 1.000000 0 0 0\n"
                + "( 256 -192 -96 ) ( 256 -192 -224 ) ( 256 256 -96 ) spirit2dm9/bricks_1 0 0 0 1.000000 1.000000 0 0 0\n"
                + "( 344 48 -256 ) ( -40 48 -256 ) ( 344 -400 -256 ) spirit2dm9/floor_3 0 96 0 1.000000 1.000000 0 0 0\n"
                + "( 384 128 -152 ) ( 384 128 -24 ) ( 0 128 -152 ) spirit2dm9/floor_3 32 0 0 1.000000 1.000000 0 0 0\n"
                + "( 288 64 -208 ) ( 288 -384 -208 ) ( 288 64 -80 ) spirit2dm9/floor_3 0 0 0 1.000000 1.000000 0 0 0\n"
                + "}\n"
                + "}";
        q2ml = new Quake2MapLexer(input);
        q2mp = new Quake2MapParser(q2ml);

        q2mp.q2EntityWithEntityIDComment();

    }
}
