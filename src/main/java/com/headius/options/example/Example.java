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

package com.headius.options.example;

import com.headius.options.Option;
import static com.headius.options.Option.*;
import java.util.Arrays;

/**
 * An example of Option usage.
 */
public class Example {

    enum MyCategory {

        STANDARD, EXTENDED, SPECIAL, OTHER
    }

    enum AccountType {

        ADMIN, GUEST, NORMAL
    }

    public static void main(String[] args) {
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
        System.out.println(
                Option.formatOptions(
                        databaseName, connCount, authenticate, acctType,
                        username, timeout, timeoutSecs, firstName));

        // short output of current settings
        System.out.println(
                Option.formatValues(
                        databaseName, connCount, authenticate, acctType,
                        username, timeout, timeoutSecs, firstName));
    }
}
