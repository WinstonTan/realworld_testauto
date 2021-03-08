# realworld_testauto (Conduit) Test Automation Framework
This is the assignment project for a sample blog site https://react-redux.realworld.io/
<br/><br/><br/>
**Setting up Environment for Test Run (MacOS)**

1. Download and install Java 15 from https://www.oracle.com/java/technologies/javase-jdk15-downloads.html

2. Download latest maven from https://maven.apache.org/download.cgi, unpack and place it on /Library

3. Setting JAVA_HOME, M2_HOME and add to PATH by adding the following lines into ~/.bash_profile <br/><br/>
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-15.0.1.jdk/Contents/Home<br/>
export M2_HOME=/Library/apache-maven-3.6.3<br/>
export PATH=/Library/apache-maven-3.6.3/bin:$PATH<br/>
export M2=$M2_HOME/bin<br/><br/>

4. Source target environment config above:
source ~/.bash_profile
<br/><br/>

5. Validate Java and Maven are installed correctly by: <br/>
**Run java -version**<br/>
java version "15.0.1" 2020-10-20<br/>
Java(TM) SE Runtime Environment (build 15.0.1+9-18)<br/>
Java HotSpot(TM) 64-Bit Server VM (build 15.0.1+9-18, mixed mode, sharing)<br/><br/>

**Run mvn -v**<br/>
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)<br/>
Maven home: /Library/apache-maven-3.6.3<br/>
Java version: 15.0.1, vendor: Oracle Corporation, runtime: /Library/Java/JavaVirtualMachines/jdk-15.0.1.jdk/Contents/Home<br/>
Default locale: en_SG, platform encoding: UTF-8<br/>
OS name: "mac os x", version: "10.15.7", arch: "x86_64", family: "mac"<br/><br/>


**Preparing Test Scripts for execution**

1. Download project test scripts from current repository and unpack to any local location. <br/>
i.e, /Users/winston/assignment/

2. Open command line tool (iTerm) and navigate to the parent folder of project. <br/>
i.e. /Users/winston/assignment/realworldweb-testauto/

3. Execute this command to run test<br/>
mvn clean test -DsuiteXmlFile=TestNG.xml

<br/>
<br/>
Test Reporting: 
Aside from monitoring the test progress and results in console log, the html test report can be obtained under: <br/>
__target/cucumber-reports/cucumber.html__



