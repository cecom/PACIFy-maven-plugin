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
import java.util.Properties;

import com.geewhiz.pacify.mavenplugin.stubs.ProjectStubWithProperties;
import com.geewhiz.pacify.test.TestUtil;

public class PreConfigureMojoTest extends BaseMojoTest {

	public void testMavenPropertyResolver() throws Exception {
		String folderToTest = new String("preConfigure/PreConfigureMojo_With_Maven_Properties");
		TestUtil.removeOldTestResourcesAndCopyAgain(new File("src/test/resources", folderToTest), new File("target/test-resources", folderToTest));

		File pom = getTestFile("target/test-resources", folderToTest + "/pom.xml");

		assertNotNull(pom);
		assertTrue(pom.exists());

		PreConfigureMojo preConfigureMojo = getMojo(pom, "preConfigure", new ProjectStubWithProperties(pom, createProperties()));

		preConfigureMojo.execute();
	}

	private Properties createProperties() {
		Properties properties = new Properties();

		properties.put("SomeChild1Property", "foo1");
		properties.put("SomeChild2Property", "foo2");
		properties.put("SomeChildOfChildProperty", "fooBar");

		return properties;
	}
}
