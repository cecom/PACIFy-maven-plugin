# Maven Plugin for PACIFy
[TOCM]

## Implemented Goals
- [validateMarkerFiles](#validateMarkerFiles) 
- [preConfigure](#preConfigure)
- [replace](#replace)

## validateMarkerFiles
Implementation for the [validateMarkerFiles](https://github.com/cecom/PACIFy/wiki/3.-Commands#validateMarkerFiles "PACIFy Command") PACIFy command.
```xml
<build>
    <plugins>
      <plugin>
        <groupId>de.geewhiz.pacify</groupId>
        <artifactId>pacify-maven-plugin</artifactId>
        <version>1.0.0</version>
        <executions>
            <execution>
                <goals>
                    <goal>validateMarkerFiles</goal>
                </goals>
            </execution>
        </executions>
        <configuration>
            <packagePath>validate</packagePath>
        </configuration>
      </plugin>
    </plugins>
</build>
```
## preConfigure
Implementation for the [preConfigure](https://github.com/cecom/PACIFy/wiki/3.-Commands#preConfigure") PACIFy command.
```xml
<build>
    <plugins>
      <plugin>
        <groupId>de.geewhiz.pacify</groupId>
        <artifactId>pacify-maven-plugin</artifactId>
        <version>1.0.0</version>
        <executions>
            <execution>
                <goals>
                    <goal>preConfigure</goal>
                </goals>
            </execution>
        </executions>
        <configuration>
            <packagePath>target/folderToConfigure</packagePath>
        </configuration>
      </plugin>
    </plugins>
</build>
```
## replace
Implementation for the [replace](https://github.com/cecom/PACIFy/wiki/3.-Commands#showUsedProperties) PACIFy command.
```xml
<build>
    <plugins>
      <plugin>
        <groupId>de.geewhiz.pacify</groupId>
        <artifactId>pacify-maven-plugin</artifactId>
        <version>1.0.0</version>
        <executions>
            <execution>
                <goals>
                    <goal>replace</goal>
                </goals>
            </execution>
        </executions>
        <configuration>
            <packagePath>target/folderToReplace</packagePath>
        </configuration>
      </plugin>
    </plugins>
</build>
```

## Plugin Options:
Here the options you can use within the Goals:
```xml
<build>
  <plugins>
    <plugin>
        <groupId>de.geewhiz.pacify</groupId>
        <artifactId>pacify-maven-plugin</artifactId>
        <version>1.0.0</version>
        <executions>
            <execution>
                <goals>
                    <goal>replace</goal>
                </goals>
            </execution>
        </executions>
        <configuration>
            <packagePath>src/main/package</packagePath>
            <copyTo>target/package</copyTo>
          
            <resolvers>
                <resolver>
                    <strategy>COMMAND_LINE</strategy>
                </resolver>
                <resolver>
                    <strategy>MAVEN_PROPERTIES</strategy>
                </resolver>
                <resolver>
                    <strategy>PROPERTY_FILE</strategy>
                    <propertyFile>src/main/properties/local.properties</propertyFile>
                    <beginToken>@</beginToken>
                    <endToken>@</endToken>
                </resolver>
                <resolver>
                    <strategy>PROPERTY_FILE</strategy>
                    <propertyFile>withinDependency/dependency.properties</propertyFile>
                </resolver>
            </resolvers>
        </configuration>
        <dependencies>
            <dependency>
                <groupId>foo</groupId>
                <artifactId>aJarWhereThePropertyFileExists</artifactId>
                <version>foo</version>
            </dependency>
        </dependencies>
    </plugin>
  </plugins>
</build>
```