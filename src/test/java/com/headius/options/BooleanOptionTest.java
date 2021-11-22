/**
 * Copyright 2013-2021 Charles Oliver Nutter <headius@headius.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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