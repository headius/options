package com.headius.options;

import org.junit.Test;

import static org.junit.Assert.*;

public class BooleanOptionTest {
    enum Category { TEST }
    @Test
    public void testValues() throws Exception {

        BooleanOption bool = new BooleanOption("options.test.boolean", Category.TEST, false, "boolean switch");

        System.setProperty("options.test.boolean", "");
        assertTrue(bool.reloadValue());

        System.setProperty("options.test.boolean", "true");
        assertTrue(bool.reloadValue());

        System.setProperty("options.test.boolean", "false");
        assertFalse(bool.reloadValue());

        System.clearProperty("options.test.boolean");
        assertFalse(bool.reloadValue());
    }
}