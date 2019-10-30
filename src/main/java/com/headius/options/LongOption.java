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
 * A Long-based Option.
 */
public class LongOption extends Option<Long> {
    public LongOption(String prefix, String name, Enum category, Long[] options, Long defval, String description) {
        super(prefix, name, Long.class, category, options, defval, description);
    }

    public LongOption(String longName, Enum category, Long[] options, Long defval, String description) {
        super(longName, Long.class, category, options, defval, description);
    }

    public LongOption(String prefix, String name, Enum category, Long defval, String description) {
        super(prefix, name, Long.class, category, null, defval, description);
    }

    public LongOption(String longName, Enum category, Long defval, String description) {
        super(longName, Long.class, category, null, defval, description);
    }

    public Long reloadValue() {
        String value = super.loadProperty();

        if (value == null) {
            return defval;
        }

        return Long.parseLong(value);
    }
}
