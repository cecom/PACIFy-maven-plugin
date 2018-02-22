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

import com.geewhiz.pacify.test.TestUtil;

import de.geewhiz.pacify.mavenplugin.mojo.ValidateMarkerFilesMojo;

public class ValidateMarkerFilesMojoTest extends BaseMojoTest {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		TestUtil.removeOldTestResourcesAndCopyAgain(new File("src/test/resources/validateMarkerFiles"), new File("target/test-resources/validateMarkerFiles"));
	}

	public void testPropertyFileResolver() throws Exception {
		File pom = getTestFile("target/test-resources/validateMarkerFiles/ValidateMarkerFilesMojo/pom.xml");
		assertNotNull(pom);
		assertTrue(pom.exists());

		ValidateMarkerFilesMojo validateMarkerFilesMojo = getMojo(pom, "validateMarkerFiles");
		validateMarkerFilesMojo.execute();
	}

}
