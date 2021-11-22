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
 * A Boolean-based Option.
 *
 * Unlike the other options, if a boolean property is specified but is blank,
 * it is considered an "on" switch and a true value.
 */
public class BooleanOption extends Option<Boolean> {
    public BooleanOption(String prefix, String name, Enum category, Boolean defval, String description) {
        super(prefix, name, Boolean.class, category, new Boolean[] {true, false}, defval, description);
    }

    public BooleanOption(String longName, Enum category, Boolean defval, String description) {
        super(longName, Boolean.class, category, new Boolean[] {true, false}, defval, description);
    }

    protected Boolean reloadValue() {
        String value = super.loadProperty();

        if (value == null) {
            return defval;
        } else if (value.equals("")) {
            // property specified as blank is considered an "on" switch, use true
            return true;
        }

        return Boolean.valueOf(value);
    }
}
