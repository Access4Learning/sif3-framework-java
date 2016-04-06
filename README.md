# sif3-framework-java
This readme is geared towards developers that wish to contribute to the SIF3 Framework. 
It lists some core constraints as well as some common policies and uses of the SIF3 Framework code base. 
There is a more detailed readme.txt at the root level of the project code that holds information about
version history and upgrade instructions between different versions.

Generally standard Git practices for forks, branches, pull requests etc. do apply. If you are not familiar
with these terms and practices you can read up on it on appropriate GitHub documentation. It is out of the scope
of this readme to repeat those procedures.

## What do I need to consider if I want to provide code to this SIF3 Framework Repository?
The Java version of the SIF3 Framework has been used in a number of high profile projects who have
some constraints what they use and allow. This does mainly relate to environments the framework has been tested
on and with. If you want to contribute code to this framework you must ensure that these environments are
considered and that the code works with them. The list below highlights the core points to take into account
when developing and testing your code.

### Java Version
While it is acknowledged that Java 1.6 is on its way out there are still some projects out there that use
the SIF3 Framework with Java 1.6. So, whatever you develop you must ensure it is supported under Java 1.6.
This may change in the near to mid-term future.

### Servlet Version
The provider component of the SIF3 Framework requires the Servlet 3.0 specification. You must ensure that your
development runs with that Servlet version.

### JAX-RS
JAX-RS is the Java specification for REST web-services. The SIF3 Framework uses JAX-RS annotations. While the code
base uses the Jersey implementation of the JAX-RS it has also been tested with JBoss RestEasy, which is an alternate JAX-RS
implementation. As with previous points you must ensure and test your changes with Jersey and RestEasy JAX-RS implementations. 
In the past we have found that some regular expressions used in annotations do not always work with RestEasy as intended 
and simple changes to a regular expression in an annotation may fix "incompatibilty" issues. So if you use annotations in
the REST Webservice implementation, you must test them with both JAX-RS implementations.

### Web- and Application Servers
The SIF3 Framework has been used with a number of Java web- and/or application servers. While we cannot test with
all of them there is a sub-set we take into account in our tests. Code that is provided to the framework that
touches on web-service functionality must be tested with the follwoing versions of web- and/or application servers:
- Tomcat v7
- JBoss 6 (Free or Licenced version)
- Optionally Jetty 8
The list above mentions a minimum version number. It must be ensured that the code you provide runs on these versions.
Of course it is fine if it runs on newer versions as well :smiley:. 

### Components of the Framework
The SIF3 Framework has two core components. One relates to consumers (client side) and one relates to providers (server side).
Many classes are common to both and changes in these classes affect both components. When altering the code base in any way
you must always consider and test the impact for consumers and providers. The prime candidates of classes that are used in
both components are in the 'sif3Common' and 'sif3InfraCommon' source directories.

### Test Classes and Data Model
There are a number of test and demo classes that from part of the framework. Their intention s two fold:
- Test functionality
- Provide Demo Code on how to use the framework
It is important to note that these test and demo classes are based around the SIF AU Data Model. This doesn't mean that the
framework is tied to that data model at all. None of the classes yoused in and source directory starting with 'sif3' make
any assumption about a data model. Only classes in the directories with a pre-fix 'sif3' form the framework code.

### Source Code dependencies

