/*-
 * ========================LICENSE_START=================================
 * de.geewhiz.pacify.pacify-maven-plugin
 * %%
 * Copyright (C) 2011 - 2018 gee-whiz.de
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
package de.geewhiz.pacify.mavenplugin.mojo;

import java.io.File;
import java.util.Properties;

import com.geewhiz.pacify.test.TestUtil;

import de.geewhiz.pacify.mavenplugin.mojo.ReplaceMojo;
import de.geewhiz.pacify.mavenplugin.stubs.ProjectStubWithProperties;

public class ReplaceMojoTest extends BaseMojoTest {

	public void testPropertyFileResolver() throws Exception {
		String folderToTest = new String("replace/ReplaceMojo");
		TestUtil.removeOldTestResourcesAndCopyAgain(new File("src/test/resources", folderToTest), new File("target/test-resources", folderToTest));

		File pom = getTestFile("target/test-resources", folderToTest + "/pom.xml");
		assertNotNull(pom);
		assertTrue(pom.exists());

		ReplaceMojo replaceMojo = getMojo(pom, "replace");
		replaceMojo.execute();
	}

	public void testMavenPropertyResolver() throws Exception {
		String folderToTest = new String("replace/ReplaceMojo_With_Maven_Properties");
		TestUtil.removeOldTestResourcesAndCopyAgain(new File("src/test/resources", folderToTest), new File("target/test-resources", folderToTest));
		
		File pom = getTestFile("target/test-resources", folderToTest + "/pom.xml");
		assertNotNull(pom);
		assertTrue(pom.exists());

		ReplaceMojo replaceMojo = getMojo(pom, "replace", new ProjectStubWithProperties(pom, createProperties()));

		replaceMojo.execute();
	}

	private Properties createProperties() {
		Properties properties = new Properties();

		properties.put("env.name", "env1");
		properties.put("SomeChild1Property", "foo1");
		properties.put("SomeChild2Property", "foo2");
		properties.put("SomeChildOfChildProperty", "fooBar");

		return properties;
	}
}
