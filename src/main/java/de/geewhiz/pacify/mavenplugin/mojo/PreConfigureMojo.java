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

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

import com.geewhiz.pacify.PreConfigure;
import com.geewhiz.pacify.managers.PropertyResolveManager;

/**
 * 
 * This goal represents the command preConfigure in pacify
 *
 */
@Mojo(name = "preConfigure", defaultPhase = LifecyclePhase.GENERATE_RESOURCES, requiresOnline = false, requiresProject = true, threadSafe = true)
public class PreConfigureMojo extends BasePacifyResolveMojo {

    @Override
    protected void executePacify() throws MojoExecutionException {
        checkPackagePath();

        PropertyResolveManager propertyResolveManager = createPropertyResolveManager();

		PreConfigure preConfigure = new PreConfigure(propertyResolveManager);

        if (getCopyTo() != null) {
            preConfigure.setCopyDestination(new File(getCopyTo()));
        }

        preConfigure.setPackagePath(getPackagePath());
        preConfigure.execute();
    }

}
