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
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.codehaus.plexus.configuration.PlexusConfiguration;

import de.geewhiz.pacify.mavenplugin.stubs.ProjectStub;

public abstract class BaseMojoTest extends AbstractMojoTestCase {

	public <T extends BasePacifyResolveMojo> T getMojo(File pom, String mojoName) throws Exception {
		return getMojo(pom, mojoName, new ProjectStub(pom));
	}

	public <T extends BasePacifyResolveMojo> T getMojo(File pom, String mojoName, ProjectStub projectStubToUse) throws Exception {
		Properties properties = new Properties();
		properties.load(new FileInputStream("target/test-classes/test.properties"));
		
		PlexusConfiguration pluginConfiguration = extractPluginConfiguration( properties.getProperty("artifact_id"), pom );

		@SuppressWarnings("unchecked")
		T replaceMojo = (T) lookupMojo(properties.getProperty("group_id"), properties.getProperty("artifact_id"), properties.getProperty("version"), mojoName, pluginConfiguration);

		assertNotNull(replaceMojo);

		setVariableValueToObject(replaceMojo, "project", projectStubToUse);

		return replaceMojo;
	}
}
