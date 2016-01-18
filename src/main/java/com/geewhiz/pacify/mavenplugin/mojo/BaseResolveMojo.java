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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Parameter;

import com.geewhiz.pacify.managers.PropertyResolveManager;
import com.geewhiz.pacify.mavenplugin.resolver.CommandlineResolver;
import com.geewhiz.pacify.mavenplugin.resolver.MavenPropertyResolver;
import com.geewhiz.pacify.property.resolver.fileresolver.FilePropertyResolver;
import com.geewhiz.pacify.resolver.PropertyResolver;

/**
 * 
 * This is the base class for all goals which need to resolve properties
 *
 */
public abstract class BaseResolveMojo extends BaseMojo {

    /**
     * Which path should be configured.
     * 
     */
    @Parameter(property = "packagePath", required = true)
    protected File         packagePath;

    /**
     * If set, the packagePath will first be copied and then the filtering will be done on the copyTo Path
     * 
     */
    @Parameter(property = "copyTo")
    protected String         copyTo;

    /**
     * How should the properties be resolved? Ordering of resolver is important and defines in which order the values are tried to resolve. If no resolver is
     * given Maven Strategy is used.
     * 
     */
    @Parameter(property = "resolvers")
    protected List<Resolver> resolvers;

    protected PropertyResolveManager createPropertyResolveManager() throws MojoExecutionException {
        Set<PropertyResolver> propertyResolverList = new TreeSet<PropertyResolver>();

        if (resolvers == null || resolvers.isEmpty()) {
            propertyResolverList.add(new MavenPropertyResolver(project.getProperties()));
        } else {
            for (Resolver resolver : resolvers) {
                propertyResolverList.add(getPropertyResolver(resolver));
            }
        }

        PropertyResolveManager propertyResolveManager = new PropertyResolveManager(propertyResolverList);
        return propertyResolveManager;
    }

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

    protected URL getPropertyFileURL(String propertyFileParameter) throws MojoExecutionException {
        if (propertyFileParameter == null) {
            throw new MojoExecutionException("You didn't define the propertyFile... Aborting!");
        }

        File propertyFile = new File(project.getBasedir(), propertyFileParameter);
        if (propertyFile.exists() && propertyFile.isFile()) {
            try {
                return propertyFile.toURI().toURL();
            } catch (MalformedURLException e) {
                throw new MojoExecutionException("error while getting property file url", e);
            }
        }

        URL propertyFileURL = this.getClass().getClassLoader().getResource(propertyFileParameter);
        if (propertyFileURL != null) {
            return propertyFileURL;
        }

        throw new MojoExecutionException(
                "Property file [" + propertyFileParameter + "] not found in classpath nor absolute [" + propertyFile.getAbsolutePath() + "]... Aborting!");

    }

    protected PropertyResolver getPropertyResolver(Resolver resolver) throws MojoExecutionException {
        PropertyResolver propertyResolver = null;

        switch (resolver.getStrategy()) {
        case PROPERTY_FILE:
            propertyResolver = new FilePropertyResolver(getPropertyFileURL(resolver.getPropertyFile()));
            if (resolver.getBeginToken() != null) {
                ((FilePropertyResolver) propertyResolver).setBeginToken(resolver.getBeginToken());
            }
            if (resolver.getEndToken() != null) {
                ((FilePropertyResolver) propertyResolver).setEndToken(resolver.getEndToken());
            }
            break;
        case MAVEN_PROPERTIES:
            propertyResolver = new MavenPropertyResolver(project.getProperties());
            if (resolver.getBeginToken() != null) {
                ((MavenPropertyResolver) propertyResolver).setBeginToken(resolver.getBeginToken());
            }
            if (resolver.getEndToken() != null) {
                ((MavenPropertyResolver) propertyResolver).setEndToken(resolver.getEndToken());
            }
            break;
        case COMMAND_LINE:
            propertyResolver = new CommandlineResolver(System.getenv());
            if (resolver.getBeginToken() != null) {
                ((CommandlineResolver) propertyResolver).setBeginToken(resolver.getBeginToken());
            }
            if (resolver.getEndToken() != null) {
                ((CommandlineResolver) propertyResolver).setEndToken(resolver.getEndToken());
            }
            break;
        default:
            throw new MojoExecutionException("Strategy [" + resolver.getStrategy().toString() + "] not implemented.");
        }

        getLog().info("Loading properties from [" + propertyResolver.getPropertyResolverDescription() + "]... ");
        return propertyResolver;
    }

    public File getPackagePath() {
        if (packagePath.isAbsolute()) {
            return packagePath;
        }
        return new File(project.getBasedir(), packagePath.getPath());
    }

    public String getCopyTo() {
        return copyTo;
    }

}
