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
 * A Boolean-based Option.
 */
public class BooleanOption extends Option<Boolean> {
    public BooleanOption(Enum category, String prefix, String name, Boolean defval, String description) {
        super(category, prefix, name, Boolean.class, new Boolean[] {true, false}, defval, description);
    }

    public BooleanOption(Enum category, String longName, Boolean defval, String description) {
        super(category, longName, Boolean.class, new Boolean[] {true, false}, defval, description);
    }

    public Boolean load() {
        String value = super.loadProperty();

        if (value == null) {
            return defval;
        }

        return Boolean.valueOf(value);
    }
}