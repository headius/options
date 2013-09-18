options: a library for JVM property-driven configuration
========================================================

This library provides a simple mechanism for defining JVM property-based
configuration for an application or library.

Options are defined via a small DSL-like setup, supporting String, Integer,
Boolean, and Enum-based configurations. Non-Boolean options support a set of
supported values, and all options allow specifying a default value. In addition,
options are created with an Enum-based category (provided by the user) and a
documentation string, which allows grouping properties and printing out a full
set of options as a valid, modifiable .properties file.

Usage
-----

A real-world usage example from the [JRuby project's Options class](https://github.com/jruby/jruby/blob/master/core/src/main/java/org/jruby/util/cli/Options.java).

Example usage of the four types of options:

```java
import com.headius.options.Option;
import static com.headius.options.Option.*;
// ...
enum MyCategory { STANDARD, EXTENDED, SPECIAL, OTHER }
enum AccountType { ADMIN, GUEST, NORMAL }

// Without defaults or options...
Option<String> databaseName = string("config.databaseName", MyCategory.STANDARD, "name of the database");
Option<Integer> connCount = integer("config.connCount", MyCategory.EXTENDED, "connection count");
Option<Boolean> authenticate = bool("config.authenticate", MyCategory.SPECIAL, "do authentication");
Option<AccountType> acctType = enumeration("config.acctType", MyCategory.OTHER, AccountType.class, "account type");

// With options...
Option<String> username = string("config.username", MyCategory.STANDARD, new String[]{"root", "guest"}, "account name");

// With default...
Option<Boolean> timeout = bool("config.timeout", MyCategory.EXTENDED, true, "timeout connections");

// With both...
Option<Integer> timeoutSecs = integer("config.timeoutSecs", MyCategory.EXTENDED, new Integer[]{15, 30, 60}, 30, "timeout in seconds");

// Load options; value is retrieved once and cached.
String dbname = databaseName.load();
AccountType accountType = acctType.load();

// Or reloading the property can be forced...
System.setProperty("config.databaseName", "customers");
dbname = databaseName.reload();

// options can be queried to see if they were provided
if (databaseName.isSpecified()) {
    // logic for setting database name
}

// If your system has a standard property prefix, it can be specified in the
// option. The prefix will be omitted from formatted output.
Option<String> firstName = string("config", "first.name", MyCategory.STANDARD, "...");

// formatted output of all options
Option.formatOptions(Arrays.<Option>asList(
                             databaseName, connCount, authenticate, acctType,
                             username, timeout, timeoutSecs, firstName));

// short output of current settings
Option.formatValues(Arrays.<Option>asList(
                             databaseName, connCount, authenticate, acctType,
                             username, timeout, timeoutSecs, firstName));
```

Formatted output of options is an editable properties file. Note the "firstName"
option does not include the "config." prefix.

```
################################################################################
# STANDARD
################################################################################

# name of the database
# Options: [String], Default: null.

#config.databaseName=customers

# account name
# Options: [root, guest], Default: null.

#config.username=null

# ...
# Options: [String], Default: null.

#first.name=null


################################################################################
# EXTENDED
################################################################################

# connection count
# Options: [Integer], Default: null.

#config.connCount=null

# timeout connections
# Options: [true, false], Default: true.

#config.timeout=true

# timeout in seconds
# Options: [15, 30, 60], Default: 30.

#config.timeoutSecs=30


################################################################################
# SPECIAL
################################################################################

# do authentication
# Options: [true, false], Default: null.

#config.authenticate=null


################################################################################
# OTHER
################################################################################

# account type
# Options: [ADMIN, GUEST, NORMAL], Default: null.

#config.acctType=null
```
