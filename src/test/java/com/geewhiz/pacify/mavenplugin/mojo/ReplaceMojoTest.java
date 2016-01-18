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
package com.geewhiz.pacify.mavenplugin.mojo;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.File;

import com.geewhiz.pacify.mavenplugin.stubs.ProjectStubWithProperties;
import com.geewhiz.pacify.test.TestUtil;

public class ReplaceMojoTest extends BaseMojoTest {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        TestUtil.removeOldTestResourcesAndCopyAgain(new File("src/test/resources/replace"), new File("target/test-resources/replace"));
    }

    public void testPropertyFileResolver() throws Exception {
        File pom = getTestFile("target/test-resources/replace/ReplaceMojo/pom.xml");
        assertNotNull(pom);
        assertTrue(pom.exists());

        ReplaceMojo replaceMojo = getMojo(pom, "replace");
        replaceMojo.execute();
    }

    public void testMavenPropertyResolver() throws Exception {
        File pom = getTestFile("target/test-classes/replace/ReplaceMojo_With_Maven_Properties/pom.xml");
        assertNotNull(pom);
        assertTrue(pom.exists());

        ReplaceMojo replaceMojo = getMojo(pom, "replace", new ProjectStubWithProperties(pom));

        replaceMojo.execute();
    }
}
