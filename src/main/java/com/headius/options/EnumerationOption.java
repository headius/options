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
 * An Enum-based Option.
 */
public class EnumerationOption<T extends Enum<T>> extends Option<T> {
    public EnumerationOption(Enum category, String prefix, String name, Class<T> enumType, T defval, String description) {
        super(category, prefix, name, enumType, (T[])enumType.getEnumConstants(), defval, description);
    }
    
    public EnumerationOption(Enum category, String longName, Class<T> enumType, T defval, String description) {
        super(category, longName, enumType, (T[])enumType.getEnumConstants(), defval, description);
    }

    public T reload() {
        String value = super.loadProperty();

        if (value == null) {
            return defval;
        }

        return Enum.valueOf((Class<T>)type, value);
    }
}
