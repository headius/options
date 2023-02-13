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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a single option, with a category, name, value type,
 * options, default value, and description.
 *
 * This type should be subclassed for specific types of values.
 * 
 * @see StringOption
 * @see IntegerOption
 * @see BooleanOption
 * @see EnumerationOption
 * @see Option#string 
 * @see Option#integer 
 * @see Option#bool 
 * @see Option#enumeration 
 *
 * @param <T> the type of value associated with the option
 */
public abstract class Option<T> {
    /**
     * Create a new option with the given values.
     * 
     * @param prefix the prefix used for loading this option from properties
     * @param shortName the rest of the property name
     * @param type the value type of the option
     * @param category the category to which this option belongs
     * @param options a list of supported for the option, or null if the set is
     *                not applicable
     * @param defval the default value for the option
     * @param description a description for the option
     */
    public Option(String prefix, String shortName, Class<T> type, Enum category, T[] options, T defval, String description) {
        this.category = category;
        this.prefix = prefix;
        this.shortName = shortName;
        this.longName = prefix + "." + shortName;
        this.displayName = shortName;
        this.type = type;
        this.options = options;
        this.defval = defval;
        this.description = description;
        this.specified = false;
    }
    
    /**
     * Create a new option with the given values.
     * 
     * @param longName the property name
     * @param type the value type of the option
     * @param category the category to which this option belongs
     * @param options a list of supported for the option, or null if the set is
     *                not applicable
     * @param defval the default value for the option
     * @param description a description for the option
     */
    public Option(String longName, Class<T> type, Enum category, T[] options, T defval, String description) {
        this.category = category;
        this.prefix = null;
        this.shortName = null;
        this.longName = longName;
        this.displayName = longName;
        this.type = type;
        this.options = options;
        this.defval = defval;
        this.description = description;
        this.specified = false;
    }
    
    /**
     * Create a new String option with the given configuration.
     *
     * @param prefix the prefix used for loading this option from properties
     * @param shortName the rest of the property name
     * @param category the category to which this option belongs
     * @param description a description for the option
     *
     * @return a new String-based option
     */
    public static Option<String> string(String prefix, String shortName, Enum category, String description) {
        return new StringOption(prefix, shortName, category, null, null, description);
    }


    /**
     * Create a new String option with the given configuration.
     *
     * @param longName the property name
     * @param category the category to which this option belongs
     * @param description a description for the option
     *
     * @return a new String-based option
     */
    public static Option<String> string(String longName, Enum category, String description) {
        return new StringOption(longName, category, null, null, description);
    }
    
    /**
     * Create a new String option with the given configuration.
     *
     * @param prefix the prefix used for loading this option from properties
     * @param shortName the rest of the property name
     * @param category the category to which this option belongs
     * @param defval the default value for the option
     * @param description a description for the option
     *
     * @return a new String-based Option
     */
    public static Option<String> string(String prefix, String shortName, Enum category, String defval, String description) {
        return new StringOption(prefix, shortName, category, null, defval, description);
    }
    
    /**
     * Create a new String option with the given configuration.
     *
     * @param longName the property name
     * @param category the category to which this option belongs
     * @param defval the default value for the option
     * @param description a description for the option
     *
     * @return a new String-based Option
     */
    public static Option<String> string(String longName, Enum category, String defval, String description) {
        return new StringOption(longName, category, null, defval, description);
    }
    
    /**
     * Create a new String option with the given configuration.
     *
     * @param prefix the prefix used for loading this option from properties
     * @param shortName the rest of the property name
     * @param category the category to which this option belongs
     * @param options a list of supported for the option, or null if the set is
     *                not applicable
     * @param description a description for the option
     *
     * @return a new String-based Option
     */
    public static Option<String> string(String prefix, String shortName, Enum category, String[] options, String description) {
        return new StringOption(prefix, shortName, category, options, null, description);
    }
    
    /**
     * Create a new String option with the given configuration.
     *
     * @param longName the property name
     * @param category the category to which this option belongs
     * @param options a list of supported for the option, or null if the set is
     *                not applicable
     * @param description a description for the option
     *
     * @return a new String-based Option
     */
    public static Option<String> string(String longName, Enum category, String[] options, String description) {
        return new StringOption(longName, category, options, null, description);
    }
    
    /**
     * Create a new String option with the given configuration.
     *
     * @param prefix the prefix used for loading this option from properties
     * @param shortName the rest of the property name
     * @param category the category to which this option belongs
     * @param options a list of supported for the option, or null if the set is
     *                not applicable
     * @param defval the default value for the option
     * @param description a description for the option
     *
     * @return a new String-based Option
     */
    public static Option<String> string(String prefix, String shortName, Enum category, String[] options, String defval, String description) {
        return new StringOption(prefix, shortName, category, options, defval, description);
    }
    
    /**
     * Create a new String option with the given configuration.
     *
     * @param longName the property name
     * @param category the category to which this option belongs
     * @param options a list of supported for the option, or null if the set is
     *                not applicable
     * @param defval the default value for the option
     * @param description a description for the option
     *
     * @return a new String-based Option
     */
    public static Option<String> string(String longName, Enum category, String[] options, String defval, String description) {
        return new StringOption(longName, category, options, defval, description);
    }
    
    /**
     * Create a new Boolean option with the given configuration.
     *
     * @param prefix the prefix used for loading this option from properties
     * @param shortName the rest of the property name
     * @param category the category to which this option belongs
     * @param description a description for the option
     *
     * @return a new Boolean-based Option
     */
    public static Option<Boolean> bool(String prefix, String shortName, Enum category, String description) {
        return new BooleanOption(prefix, shortName, category, null, description);
    }
    
    /**
     * Create a new Boolean option with the given configuration.
     *
     * @param longName the property name
     * @param category the category to which this option belongs
     * @param description a description for the option
     *
     * @return a new Boolean-based Option
     */
    public static Option<Boolean> bool(String longName, Enum category, String description) {
        return new BooleanOption(longName, category, null, description);
    }
    
    /**
     * Create a new Boolean option with the given configuration.
     *
     * @param prefix the prefix used for loading this option from properties
     * @param shortName the rest of the property name
     * @param category the category to which this option belongs
     * @param defval the default value for the option
     * @param description a description for the option
     *
     * @return a new Boolean-based Option
     */
    public static Option<Boolean> bool(String prefix, String shortName, Enum category, Boolean defval, String description) {
        return new BooleanOption(prefix, shortName, category, defval, description);
    }
    
    /**
     * Create a new Boolean option with the given configuration.
     *
     * @param longName the property name
     * @param category the category to which this option belongs
     * @param defval the default value for the option
     * @param description a description for the option
     *
     * @return a new Boolean-based Option
     */
    public static Option<Boolean> bool(String longName, Enum category, Boolean defval, String description) {
        return new BooleanOption(longName, category, defval, description);
    }
    
    /**
     * Create a new Integer option with the given configuration.
     *
     * @param prefix the prefix used for loading this option from properties
     * @param shortName the rest of the property name
     * @param category the category to which this option belongs
     * @param description a description for the option
     *
     * @return a new Integer-based Option
     */
    public static Option<Integer> integer(String prefix, String shortName, Enum category, String description) {
        return new IntegerOption(prefix, shortName, category, null, description);
    }
    
    /**
     * Create a new Integer option with the given configuration.
     *
     * @param prefix the prefix used for loading this option from properties
     * @param shortName the rest of the property name
     * @param category the category to which this option belongs
     * @param options a list of supported for the option, or null if the set is
     *                not applicable
     * @param description a description for the option
     *
     * @return a new Integer-based Option
     */
    public static Option<Integer> integer(String prefix, String shortName, Enum category, Integer[] options, String description) {
        return new IntegerOption(prefix, shortName, category, options, null, description);
    }
    
    /**
     * Create a new Integer option with the given configuration.
     *
     * @param longName the property name
     * @param category the category to which this option belongs
     * @param description a description for the option
     *
     * @return a new Integer-based Option
     */
    public static Option<Integer> integer(String longName, Enum category, String description) {
        return new IntegerOption(longName, category, null, description);
    }
    
    /**
     * Create a new Integer option with the given configuration.
     *
     * @param longName the property name
     * @param category the category to which this option belongs
     * @param options a list of supported for the option, or null if the set is
     *                not applicable
     * @param description a description for the option
     *
     * @return a new Integer-based Option
     */
    public static Option<Integer> integer(String longName, Enum category, Integer[] options, String description) {
        return new IntegerOption(longName, category, options, null, description);
    }
    
    /**
     * Create a new Integer option with the given configuration.
     *
     * @param prefix the prefix used for loading this option from properties
     * @param shortName the rest of the property name
     * @param category the category to which this option belongs
     * @param defval the default value for the option
     * @param description a description for the option
     *
     * @return a new Integer-based Option
     */
    public static Option<Integer> integer(String prefix, String shortName, Enum category, Integer defval, String description) {
        return new IntegerOption(prefix, shortName, category, defval, description);
    }
    
    /**
     * Create a new Integer option with the given configuration.
     *
     * @param longName the property name
     * @param category the category to which this option belongs
     * @param defval the default value for the option
     * @param description a description for the option
     *
     * @return a new Integer-based Option
     */
    public static Option<Integer> integer(String longName, Enum category, Integer defval, String description) {
        return new IntegerOption(longName, category, defval, description);
    }
    
    /**
     * Create a new Integer option with the given configuration.
     *
     * @param prefix the prefix used for loading this option from properties
     * @param shortName the rest of the property name
     * @param category the category to which this option belongs
     * @param options a list of supported for the option, or null if the set is
     *                not applicable
     * @param defval the default value for the option
     * @param description a description for the option
     *
     * @return a new Integer-based Option
     */
    public static Option<Integer> integer(String prefix, String shortName, Enum category, Integer[] options, Integer defval, String description) {
        return new IntegerOption(prefix, shortName, category, options, defval, description);
    }
    
    /**
     * Create a new Integer option with the given configuration.
     *
     * @param longName the property name
     * @param category the category to which this option belongs
     * @param options a list of supported for the option, or null if the set is
     *                not applicable
     * @param defval the default value for the option
     * @param description a description for the option
     *
     * @return a new Integer-based Option
     */
    public static Option<Integer> integer(String longName, Enum category, Integer[] options, Integer defval, String description) {
        return new IntegerOption(longName, category, options, defval, description);
    }

    /**
     * Create a new Long option with the given configuration.
     */
    public static Option<Long> longInt(String prefix, String name, Enum category, String description) {
        return new LongOption(prefix, name, category, null, description);
    }

    /**
     * Create a new Long option with the given configuration.
     */
    public static Option<Long> longInt(String longName, Enum category, String description) {
        return new LongOption(longName, category, null, description);
    }

    /**
     * Create a new Long option with the given configuration.
     */
    public static Option<Long> longInt(String prefix, String name, Enum category, Long defval, String description) {
        return new LongOption(prefix, name, category, defval, description);
    }

    /**
     * Create a new Long option with the given configuration.
     */
    public static Option<Long> longInt(String longName, Enum category, Long defval, String description) {
        return new LongOption(longName, category, defval, description);
    }

    /**
     * Create a new Enumeration-based option with the given configuration.
     *
     * @param <T> the type of the enum
     * @param prefix the prefix used for loading this option from properties
     * @param shortName the rest of the property name
     * @param category the category to which this option belongs
     * @param description a description for the option
     *
     * @return a new Enumeration-based Option
     */
    public static <T extends Enum<T>> Option<T> enumeration(String prefix, String shortName, Enum category, Class<T> enumClass, String description) {
        return new EnumerationOption(prefix, shortName, category, enumClass, null, description);
    }
    
    /**
     * Create a new Enumeration-based option with the given configuration.
     *
     * @param <T> the type of the enum
     * @param longName the property name
     * @param category the category to which this option belongs
     * @param description a description for the option
     *
     * @return a new Enumeration-based Option
     */
    public static <T extends Enum<T>> Option<T> enumeration(String longName, Enum category, Class<T> enumClass, String description) {
        return new EnumerationOption(longName, category, enumClass, null, description);
    }
    
    /**
     * Create a new Enumeration-based option with the given configuration.
     *
     * @param <T> the type of the enum
     * @param prefix the prefix used for loading this option from properties
     * @param shortName the rest of the property name
     * @param category the category to which this option belongs
     * @param defval the default value for the option
     * @param description a description for the option
     *
     * @return a new Enumeration-based Option
     */
    public static <T extends Enum<T>> Option<T> enumeration(String prefix, String shortName, Enum category, T defval, String description) {
        return new EnumerationOption(prefix, shortName, category, defval.getClass(), defval, description);
    }
    
    /**
     * Create a new Enumeration-based option with the given configuration.
     *
     * @param <T> the type of the enum
     * @param longName the property name
     * @param category the category to which this option belongs
     * @param defval the default value for the option
     * @param description a description for the option
     *
     * @return a new Enumeration-based Option
     */
    public static <T extends Enum<T>> Option<T> enumeration(String longName, Enum category, T defval, String description) {
        return new EnumerationOption(longName, category, defval.getClass(), defval, description);
    }
    
    /**
     * Format the given options to show their loaded values in the current JVM.
     *
     * @param options the options to format
     * @return a formatted string representing the options
     */
    public static String formatValues(Option... options) {
        return formatValues(Arrays.asList(options));
    }
    
    /**
     * Format the given options to show their loaded values in the current JVM.
     *
     * @param options the options to format
     * @return a formatted string representing the options
     */
    public static String formatValues(Collection<Option> options) {
        StringBuilder sb = new StringBuilder();
        List<Option> sorted = new ArrayList<Option>(options);
        Collections.sort(sorted, OptionComparator);
        
        Enum category = null;
        for (Option option : sorted) {
            if (category != option.category) {
                category = option.category;
                sb.append('\n').append(category).append('\n');
            }
            sb
                    .append(option.displayName)
                    .append('=');
            option.load();
            if (option.isSpecified() || option.defval != null) {
                sb.append(encodeWhitespace(option.load()));
            } else {
                sb.append("<unspecified>");
            }
            sb.append('\n');
        }
        return sb.toString();
    }
    
    /**
     * Format the given options in a way suitable for use as a configuration
     * file or documentation.
     *
     * @param options the options to format
     * @return a formatted string representing the options as a config file or document
     */
    public static String formatOptions(Option... options) {
        return formatOptions(Arrays.asList(options));
    }

    /**
     * Format the given options in a way suitable for use as a configuration
     * file or documentation.
     *
     * @param options the options to format
     * @return a formatted string representing the options as a config file or document
     */
    public static String formatOptions(Collection<Option> options) {
        StringBuilder sb = new StringBuilder();
        List<Option> sorted = new ArrayList<Option>(options);
        Collections.sort(sorted, OptionComparator);
        
        Enum category = null;
        for (Option option : sorted) {
            if (category != option.category) {
                category = option.category;
                sb.append("\n################################################################################");
                sb.append("\n# ").append(category);
                sb.append("\n################################################################################\n\n");
            }
            sb.append("# ").append(option.description).append('\n');
            
            if (option.options != null) {
                sb.append("# Options: ").append(Arrays.toString(option.options));
                if (option.defval != null) {
                    sb.append(", Default: ").append(encodeWhitespace(option.defval));
                }
                sb.append(".\n");
            } else if (option.defval != null) {
                sb.append("# Default: ").append(encodeWhitespace(option.defval));
                sb.append(".\n");
            }
            
            sb.append("\n#");
            if (option.defval != null) {
                sb.append(option.displayName).append('=').append(encodeWhitespace(option.defval.toString()));
            } else {
                sb.append(option.displayName).append('=');
            }
            
            sb.append("\n\n");
        }

        return sb.toString();
    }
    
    private static Comparator<Option> OptionComparator = new Comparator<Option>() {
        public int compare(Option o1, Option o2) {
            int catComp = o1.category.compareTo(o2.category);
            if (catComp != 0) return catComp;
            return o1.displayName.compareTo(o2.displayName);
        }
    };
    
    private static String encodeWhitespace(Object obj) {
        if (obj == null) return "null";
        
        String str = obj.toString();
        StringBuilder sb = new StringBuilder(str.length() * 2);
        
        boolean hasWhitespace = false;
        
        for (char c : str.toCharArray()) {
            if (Character.isWhitespace(c)) {
                hasWhitespace = true;
                switch (c) {
                    case '\t':
                        sb.append("\\t");
                        break;
                    case '\n':
                        sb.append("\\n");
                        break;
                    default:
                        sb.append(c);
                        break;
                }
            } else {
                sb.append(c);
            }
        }
        
        if (hasWhitespace) {
            return "\"" + sb.toString() + "\"";
        } else {
            return str;
        }
    }

    @Override
    public String toString() {
        return longName;
    }

    /**
     * Load the option's property, as if by calling java.lang.System#getProperty
     *
     * @return the option's property's current value, or a value previously forced
     * into this option by {@link #force(String)}
     */
    public synchronized String loadProperty() {
        String value = forced;
        if (value != null) return value;

        try {
            value = System.getProperty(longName);
        } catch (SecurityException se) {
        }
        
        if (value != null) specified = true;

        return value;
    }

    /**
     * @return true if the option's property was specified, false otherwise.
     */
    public boolean isSpecified() {
        return specified;
    }

    /**
     * @return the value of the option, loading if it has not been already.
     */
    public final T load() {
        if (loaded) return value;
        
        return reload();
    }
    
    /**
     * Force a load of the option's property and return the loaded value.
     *
     * @return the loaded value
     */
    public final synchronized T reload() {
        if (loaded) return value;

        value = reloadValue();
        loaded = true;
        
        return value;
    }

    /**
     * Force the property value to the given value for all future loads and reloads.
     *
     * @param value a value to force for the Option's property, as if set in JVM
     */
    public synchronized void force(String value) {
        forced = value;
        loaded = false;
        reload();
    }

    /**
     * Undoes any previous force, and goes back to an unloaded state.
     */
    public synchronized void unforce() {
        forced = null;
        loaded = false;
        value = null;
    }
    
    /**
     * Perform the appropriate load and conversion for the option's property.
     *
     * @return the updated value after reload
     */
    protected abstract T reloadValue();
    
    /**
     * If the option has a short name, return it. Otherwise null.
     *
     * @return the short name, or else null
     */
    public String shortName() {
        return shortName;
    }
    
    /**
     * @return the full property name for the option.
     */
    public String propertyName() {
        return longName;
    }
    
    /**
     * If the property has a prefix, return it. Otherwise null.
     *
     * @return the property prefix, or else null
     */
    public String prefix() {
        return prefix;
    }
    
    /**
     * The display name of the option's property, either the short name or the full name.
     *
     * @return the full display name of this option's property
     */
    public String displayName() {
        return displayName;
    }
    
    /**
     * The type returned for the option.
     *
     * @return the type of option that this is
     */
    public Class<T> type() {
        return type;
    }
    
    /**
     * The array of accepted values for the option, or null if any values are accepted.
     *
     * @return an array of accepted values, or null if any values are allowed
     */
    public T[] options() {
        return options == null ? null : options.clone();
    }
    
    /**
     * @return the default value for the option, or null if no default is provided.
     */
    public T defaultValue() {
        return defval;
    }
    
    /**
     * @return the long description of the property, as for documentation and configuration file templates.
     */
    public String description() {
        return description;
    }

    private final Enum category;
    private final String prefix;
    private final String shortName;
    private final String longName;
    private final String displayName;
    protected final Class<T> type;
    private final T[] options;
    protected final T defval;
    private final String description;
    private String forced;
    private boolean specified;
    private volatile T value;
    private volatile boolean loaded;
}
