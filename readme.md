# SpaceMoba
It's a MOBA in SPACE, wow.

## Compile
1. Install maven
2. Download or clone source
3. Execute **mvn clean install** in the directory where the pom.xml is.
4. **target** directory is made.

## Run
1. Enter **target** directory.
2. Issue **java -jar SpaceMoba-0.0.1-SNAPSHOT-jar-with-dependencies.jar server** to start a server process, you should only start one instance.
3. In a separate process issue **java -jar SpaceMoba-0.0.1-SNAPSHOT-jar-with-dependencies.jar client localhost** to start a client process, you can start up to 10 currently.
