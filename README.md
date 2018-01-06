# IndianRailwaysApp-with-SpringBoot

Reference:
##https://www.jagoinvestor.com/2013/09/tricks-to-increase-ctc-salary-by-employer.html

##step1:Update the pom.xml to include a Google Cloud Platform plugin that simplifies the deployment process. You can use vim,nano,or emacs to edit the file.
add this to pom.xml

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" ...>
  ...
  <build>
    <plugins>
      ...
      <plugin>
        <groupId>com.google.cloud.tools</groupId>
        <artifactId>appengine-maven-plugin</artifactId>
        <version>1.3.1</version>
      </plugin>
      ...
    </plugins>
  </build>
</project>

##step2:Remove Tomcat Starter
App Engine Standard uses Jetty web server underneath. Spring Boot includes Tomcat in the WAR package that will conflict with Jetty. Exclude Tomcat from the Spring Boot Starter, and add back Servlet API as a provided dependency:

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" ...>
  ...
  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
      <exclusions>
        <exclusion>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.1.0</version>
      <scope>provided</scope>
    </dependency>
  </dependencies>
</project>

##step3:Exclude JUL to SLF4J Bridge
Spring Boot also includes a JUL to SLF4J bridge that interferes with App Engine's log handler that's provided through Jetty server. To get proper log entries, you must exclude the jul-to-slf4j artifact by marking it as provided.

pom.xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" ...>
  ...
  <dependencies>
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>jul-to-slf4j</artifactId>
      <scope>provided</scope>
    </dependency>
  </dependencies>
</project>

##step4:Add App Engine Descriptor
To deploy the application into App Engine standard, you must add create a new src/main/webapp/WEB-INF/appengine-web.xml descriptor file:

$ mkdir -p src/main/webapp/WEB-INF/
$ touch src/main/webapp/WEB-INF/appengine-web.xml
Edit src/main/webapp/WEB-INF/appengine-web.xml file and add the following content:

src/main/webapp/WEB-INF/appengine-web.xml
<appengine-web-app xmlns="http://appengine.google.com/ns/1.0">
  <version>1</version>
  <threadsafe>true</threadsafe>
  <runtime>java8</runtime>
</appengine-web-app>


./mvnw -DskipTests appengine:run



mkdir -p src/main/webapp/WEB-INF/
touch src/main/webapp/WEB-INF/appengine-web.xml

mvn appengine:run  
mvn appengine:deploy
