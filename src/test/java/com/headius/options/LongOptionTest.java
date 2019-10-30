package com.headius.options;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class LongOptionTest {
    enum Category { TEST }

    @Test
    public void testValues() throws Exception {

        LongOption opt = new LongOption("options.test.long", Category.TEST, 1L, "");

        System.clearProperty("options.test.long");
        assertEquals(Long.valueOf(1), opt.reloadValue());

        System.setProperty("options.test.long", "0");
        assertEquals(Long.valueOf(0), opt.reloadValue());

        System.setProperty("options.test.long", "1000000000000000000");
        assertEquals(Long.valueOf(1000000000000000000L), opt.reloadValue());

        System.setProperty("options.test.long", "-2000000000000000000");
        assertEquals(Long.valueOf(-2000000000000000000L), opt.reloadValue());
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testInvalid() {
        System.setProperty("options.test.long", "");
        LongOption opt = new LongOption("options.test.long", Category.TEST, 1L, "");
        exceptionRule.expect(NumberFormatException.class);
        opt.reloadValue();
    }

}