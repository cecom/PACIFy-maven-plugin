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

import org.apache.maven.plugins.annotations.Parameter;

/**
 * 
 * Parameter class for pom configuration part
 *   <configuration>
 *       <resolvers>
 *          <resolver>
 *             <strategy>PROPERTY_FILE</strategy>
 *             <propertyFile>my.properties</propertyFile>
 *          </resolver>
 *       </resolvers>
 *       ...
 *   </configuration>
 *
 */
public class Resolver {

    /**
     * which strategy to resolve the properties
     * 
     */
    @Parameter(property = "strategy")
    private Strategy strategy;

    /**
     * Used within Strategy.PROPERTY_FILE
     * 
     */
    @Parameter(property = "usePropertyFile")
    private String   propertyFile;

    /**
     * The BeginToken which the specified resolver should use.
     * 
     */
    @Parameter(property = "beginToken", defaultValue = "%{")
    private String   beginToken;

    /**
     * The EndToken which the specified resolver should use.
     * 
     */
    @Parameter(property = "endToken", defaultValue = "}")
    private String   endToken;

    public String getPropertyFile() {
        return propertyFile;
    }

    public void setPropertyFile(String propertyFile) {
        this.propertyFile = propertyFile;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public String getBeginToken() {
        return beginToken;
    }

    public void setBeginToken(String beginToken) {
        this.beginToken = beginToken;
    }

    public String getEndToken() {
        return endToken;
    }

    public void setEndToken(String endToken) {
        this.endToken = endToken;
    }

}
