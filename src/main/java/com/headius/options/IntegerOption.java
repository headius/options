/*
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

/**
 * An Integer-based Option.
 */
public class IntegerOption extends Option<Integer> {
    public IntegerOption(String prefix, String name, Enum category, Integer[] options, Integer defval, String description) {
        super(prefix, name, Integer.class, category, options, defval, description);
    }
    
    public IntegerOption(String longName, Enum category, Integer[] options, Integer defval, String description) {
        super(longName, Integer.class, category, options, defval, description);
    }
    
    public IntegerOption(String prefix, String name, Enum category, Integer defval, String description) {
        super(prefix, name, Integer.class, category, null, defval, description);
    }
    
    public IntegerOption(String longName, Enum category, Integer defval, String description) {
        super(longName, Integer.class, category, null, defval, description);
    }

    public Integer reloadValue() {
        String value = super.loadProperty();

        if (value == null) {
            return defval;
        }

        return Integer.parseInt(value);
    }
}
