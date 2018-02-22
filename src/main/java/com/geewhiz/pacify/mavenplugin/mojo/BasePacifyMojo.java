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

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * Base class of all pacify goals
 * 
 *
 */
public abstract class BasePacifyMojo extends AbstractMojo {

	@Parameter(defaultValue = "${project}")
	protected MavenProject project;

	/**
	 * Should pacify completely be skipped??
	 */
	@Parameter(property = "skipPacify", defaultValue = "false")
	protected boolean skip;

	/**
	 * Which path should be configured.
	 * 
	 */
	@Parameter(property = "packagePath", required = true)
	protected File packagePath;

	public void execute() throws MojoExecutionException, MojoFailureException {
		if (skip) {
			getLog().info("Pacify is skipped.");
			return;
		}

		executePacify();
	}

	protected abstract void executePacify() throws MojoExecutionException;

	protected void checkPackagePath() throws MojoExecutionException {
		if (getPackagePath().exists()) {
			return;
		}

		File outputDirectory = new File(project.getBuild().getOutputDirectory());
		if (getPackagePath().equals(outputDirectory)) {
			getLog().debug("Directory [" + getPackagePath().getAbsolutePath() + "] does  not exists. Nothing to do.");
			return; // if it is a maven project which doesn't have a target
					// folder, do nothing.
		}
		throw new MojoExecutionException("The folder [" + getPackagePath().getAbsolutePath() + "] does not exist.");
	}

	public File getPackagePath() {
		if (packagePath.isAbsolute()) {
			return packagePath;
		}
		return new File(project.getBasedir(), packagePath.getPath());
	}

}
