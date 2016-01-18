/*-
 * ========================LICENSE_START=================================
 * com.geewhiz.pacify.pacify-maven-plugin
 * %%
 * Copyright (C) 2011 - 2017 gee-whiz.de
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */
package com.geewhiz.pacify.mavenplugin.stubs;

import java.io.File;
import java.util.Properties;

public class ProjectStubWithProperties extends ProjectStub {

    public ProjectStubWithProperties(File pomToTest) {
        super(pomToTest);
    }

    @Override
    public Properties getProperties() {
        Properties properties = new Properties();

        properties.put("env.name", "env1");
        properties.put("SomeChild1Property", "foo1");
        properties.put("SomeChild2Property", "foo2");
        properties.put("SomeChildOfChildProperty", "fooBar");

        return properties;
    }

}
