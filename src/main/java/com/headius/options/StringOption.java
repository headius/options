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

/**
 * A String-based Option.
 */
public class StringOption extends Option<String> {
    public StringOption(String prefix, String name, Enum category, String[] options, String defval, String description) {
        super(prefix, name, String.class, category, options, defval, description);
    }
    
    public StringOption(String longName, Enum category, String[] options, String defval, String description) {
        super(longName, String.class, category, options, defval, description);
    }

    protected String reloadValue() {
        String value = super.loadProperty();

        if (value == null) {
            return defval;
        }

        return value;
    }
}
