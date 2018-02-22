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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.File;

import org.junit.Assert;

import de.geewhiz.pacify.mavenplugin.mojo.ReplaceMojo;
import de.geewhiz.pacify.mavenplugin.mojo.Strategy;

public class StructureMojoTest extends BaseMojoTest {

    public void testCompleteStructure() throws Exception {
        File pom = getTestFile("src/test/resources/structure/pom.xml");
        Assert.assertThat(pom, is(notNullValue()));
        assertTrue(pom.exists());

        ReplaceMojo replaceMojo = getMojo(pom, "replace");

        Assert.assertThat(replaceMojo.packagePath.getPath().replace('\\', '/'), equalTo("src/main/package"));
        Assert.assertThat(replaceMojo.getPackagePath().getAbsolutePath().replace('\\', '/'),
                equalTo(pom.getParentFile().getAbsolutePath().replace('\\', '/') + "/src/main/package"));
        
        Assert.assertThat(replaceMojo.getCopyTo(), equalTo("target/package"));

        Assert.assertThat(replaceMojo.resolvers, is(notNullValue()));
        Assert.assertThat(replaceMojo.resolvers, hasSize(4));
        Assert.assertThat(replaceMojo.resolvers.get(0).getStrategy(), is(Strategy.COMMAND_LINE));
        Assert.assertThat(replaceMojo.resolvers.get(1).getStrategy(), is(Strategy.MAVEN_PROPERTIES));

        Assert.assertThat(replaceMojo.resolvers.get(2).getStrategy(), is(Strategy.PROPERTY_FILE));
        Assert.assertThat(replaceMojo.resolvers.get(2).getPropertyFile(), equalTo("src/main/properties/local.properties"));
        Assert.assertThat(replaceMojo.resolvers.get(2).getBeginToken(), equalTo("@"));
        Assert.assertThat(replaceMojo.resolvers.get(2).getEndToken(), equalTo("@"));

        Assert.assertThat(replaceMojo.resolvers.get(3).getStrategy(), is(Strategy.PROPERTY_FILE));
        Assert.assertThat(replaceMojo.resolvers.get(3).getPropertyFile(), equalTo("withinDependency/dependency.properties"));

    }
}
