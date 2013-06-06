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
package com.headius.options.example;

import com.headius.options.Option;
import static com.headius.options.Option.*;
import java.util.Arrays;

/**
 * An example of Option usage.
 */
public class Example {

    enum MyCategory { STANDARD, EXTENDED, SPECIAL, OTHER }
    enum AccountType { ADMIN, GUEST, NORMAL }

    public static void main(String[] args) {
        // without defaults or options
        Option<String> databaseName = string("config.databaseName", MyCategory.STANDARD, "name of the database");
        Option<Integer> connCount = integer("config.connCount", MyCategory.EXTENDED, "connection count");
        Option<Boolean> authenticate = bool("config.authenticate", MyCategory.SPECIAL, "do authentication");
        Option<AccountType> acctType = enumeration("config.acctType", MyCategory.OTHER, AccountType.class, "account type");

        // with options
        Option<String> username = string("config.username", MyCategory.STANDARD, new String[]{"root", "guest"}, "account name");

        // with default
        Option<Boolean> timeout = bool("config.timeout", MyCategory.EXTENDED, true, "timeout connections");

        // with both
        Option<Integer> timeoutSecs = integer("config.timeoutSecs", MyCategory.EXTENDED, new Integer[]{15, 30, 60}, 30, "timeout in seconds");

        // load options; value is retrieved once and cached
        String dbname = databaseName.load();
        AccountType accountType = acctType.load();

        // formatted output of all options
        System.out.println(
                Option.formatOptions(Arrays.<Option>asList(
                        databaseName, connCount, authenticate, acctType,
                        username, timeout, timeoutSecs)));

        // short output of current settings
        System.out.println(
                Option.formatValues(Arrays.<Option>asList(
                        databaseName, connCount, authenticate, acctType,
                        username, timeout, timeoutSecs)));
    }
}
