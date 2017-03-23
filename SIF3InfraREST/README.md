# License
> Copyright 2014 - 2016 Systemic Pty Ltd
> 
> Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
> 
> [http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0 "Apache License, Version 2.0")
> 
> Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

# Summary
The SIF3 Framework is a Java Framework that enables developers to efficiently implement SIF3 services (consumers and/or providers). It fully encapsulates the low level SIF3 infrastructure. It also provides a basic environment provider which is core to SIF3.

The framework has some basic demo classes that illustrate how to use the framework. It also has a developer's guide that can be found in the "documentation/UserGuide" directory.

There is a set of pre-built framework libraries in the "release" directory. Please refer to the developer's guide for more details an the libraries.

# Maven
As of version 0.10.0 the SIF3 Framework is a multi-module Maven project. It is important to note that the SIF3
Framework is not yet available in a global maven repository. For more details about the maven build and how it can
be used within other maven projects refer to the developer's guide section **"3.3.1. Framework Libraries Build"** and 
**"3.3.2. Maven Coordinates"** and its sub-sections. Once you understand these sections and have performed appropriate 
actions to have the SIF3 Framework in an accessible repository you can use the following dependency in your own SIF3 
Project (note that the version number will change over time):
```xml
<dependency>
   <groupId>sif3.framework</groupId>
   <artifactId>sif3-infra-rest</artifactId>
   <version>0.11.0</version>
</dependency>
```

# Version History and Update
## Version from 26/11/2013
If you have downloaded the framework before Nov 26, 2013 and get an updated version after this date you must perform a few steps to make the framework function correctly. There are NO code changes only configuration changes.

### Step 1:
Remove all environment XML files in the provider's and consumer's 'workstore' and all XML files in the provider's environment store under the 'any' directory. Please refer to the developer's guide section 5.3.1.1.2 (consumer) and 5.3.1.1.3 (provider) for details where the 'workstore' is located for both these components.

### Step 2:
Open all environment XML files in the provider's 'template' directory. Note if you have more than one provider configured then you must open the files in all provider's 'template' directories. Please refer to the developer's guide section 5.3.1.1.3 for details about the location of the 'template' directory. Each environment XML file has a section with the name `<infrastructureServices>`. Replace that entire section with the following XML:
```xml
<infrastructureServices>
  <infrastructureService name="environment">environments</infrastructureService>
  <infrastructureService name="requestsConnector">requests</infrastructureService>
  <infrastructureService name="provisionRequests">provision</infrastructureService>
  <infrastructureService name="queues">queues</infrastructureService>
  <infrastructureService name="subscriptions">subscriptions</infrastructureService>
</infrastructureServices>
```
For details about that XML snipped please see section 5.3.1.1.3 in the developer's guide, paragraph with the title 'Infrastructure Service URIs'.

### Step 3:
Open the provider's properties file (i.e. StudentProvider.properties). For each environment the provider supports add the following new property:
```
env.connector.url=<baseURI>
```

`<baseURI>`: The base URI of the provider. This is the value that used to be in the `<infrastructureService>` node of each service. (i.e. http://localhost:9080/SIF3InfraREST/sif3). Also refer to section 8 and 9 of the developer's guide for details about this property.

## Version from 10/12/2013
If you have downloaded the framework before Nov 26, 2013 and get an updated version after this date you must perform all the steps listed in the "Version from 26/11/2013" first.

This latest version of the framework has the HTTPS (secured connections) capability added. It is recommended that you read section "5.10 Security - HTTPS Configuration" of the Developer's Guide first. 

## Version from 03/01/2014
Modified some underlying classes to include all header fields as specified in the latest SIF3 specification. Also implemented the Bulk Delete which is now managed via a HTTP PUT and an appropriate header field.

Also added the functionality for more rigorous ACL checks before any REST calls are made. Before each REST call the consumer checks if access to the call is APPROVED (looking up ACLs in the environment XML). To disable or enable this ACL check please refer to the Developer's Guide and read up on the 'adapter.checkACL' property for the consumer and/or provider.

Please note that with this latest download the Provider Interface has an additional parameter for the two create operation. **Your code may break!** Add the parameter 'boolean useAdvisory' to your provider classes were you may get a compile error. Please refer to the latest SIF3 Specification for details what this value means.

## Version from 17/05/2014: MAJOR UPDATE
**NOTE: THIS IS A MAJOR UPDATE TO THE FRAMEWORK. THE JAR FILES IN THE RELEASE DIRECTORY SHOULD HAVE A VERSION OF v0.2-alpha.**

You should read through the installation instructions in the Developer's Guide in the directory documentation/UserGuide(SIF3Framework_DevelopersGuide_v0.6.2.docx). If you have developed some consumer and/or provider classes in the past and you want to use them you may have to change some of the method signatures to fix them up. Also neither the consumer nor the provider constructor take any arguments anymore. The management of environments and their templates has changed significantly and may need some re-configuring. Please refer to the Developer's Guide for  details (section 5.3).

## Version from 12/08/2014: v0.3
This version updates the framework from version 0.2 to version 0.3. The latest jar files can be found in the "release" directory. Also upgrade instructions can be found in the directory "release/v0.3".
Major Changes in this version include:
- Bug Fixes (i.e. use header fields for paging information rather than query parameters)
- Ability for a DIRECT provider to deal with multiple environment templates. For details refer to section 5.3.1.3
  in the Developer's Guide.
- Updated AU 1.3 SIF data model to cater for latest changes to time table objects.

## Version from 11/09/2014: v0.3.1 - Minor update to above
The provider interface class has changed which enforces an additional parameter on all methods. All object provider classes that extend the BaseProvider or BaseEventProvider must add the parameter "RequestMetadata metadata" to all methods. This new parameter allows the object provider to get access to some additional metadata relating to each request. For details about the new parameter as well Provider Interface class please refer to the javadoc.

Details about the changes and how to incorporate them into your code can be found in the directory "release/v0.3.1".

## Version from 14/10/2014: v0.3.2 - Minor Changes (Bug Fixes)
This minor version update includes a couple of minor bug fixes and a couple of improvements to the framework. The changes will not affect the developer's code base. Simply drop the new libraries into your project to get the changes and bug fixes.

Details of this release can be found in the directory "release/v0.3.2".

## Version from December 2, 2014: v0.4.0 - Various changes
This  version update includes a few new bits of functionality, namely support for JSON as well as some restructuring of the data model generation. The AU 1.3 Datamodel is no longer part of this project but part of a new GitHub project called SIF3DMGenerator. 

Details of this release can be found in the directory "release/v0.4.0".

## Version from March 03, 2015: v0.5.0 - Various changes
Version 0.5.0 has a couple important new features. They include support for SIF 3.x Service Path functionality and support for External Security Services (DIRECT Provider only). The later is not yet part of the SIF 3.0.1 specification and it should be used with care as it is experimental at this stage. The DB structure for the SIF3_SESSION table has changed and therefore a database update script must be run to upgrade the previous version of the framework to this version.

Details of this release including upgrade instructions can be found 
in the directory "release/v0.5.0".

## Version from June 16, 2015: v0.6.0 - Various changes
Version 0.6.0 adds an auditing framework for providers to the SIF3 Framework. This is an optional feature. Further this new version requires all providers to be deployed in a web-/application container that supports
the servlet 3.0 specification. There are additional changes, additions, bug fixes etc. in the latest version of the SIF3 Framework.

Please refer to the release notes in the directory "release/v0.6.0" for details.

## Version from Dec 17, 2015: v0.7.0 - Various changes
Version 0.7.0 adds payload compression capabilities to all request/responses. This is an optional feature. Further the infrastructure data model is now in its own jar file rather than part of the infra-common jar file. It has also switched to infrastructure version 3.1. The major change relates to consumers. This framework version supports DELAYED request/responses. Finally support for external security services for consumers has been added as well.

Please refer to the release notes in the directory "release/v0.7.0" for additional details and upgrade instructions. 

## Version from Mar 17, 2016: v0.8.0 - Various changes
Adding support for some SIF 3.2 Infrastructure functionality: 
 - HTTP HEAD supported for 'root' object service (i.e. .../StudentPersonals).
 - "Changes Since" support for Object Providers.

Upgraded some core libraries to later versions.

Please refer to the release notes in the directory "release/v0.8.0" for additional details and upgrade instructions.

## Version from May 24, 2016: v0.9.0 - Various changes
___
**AS OF THIS VERSION ALL FRAMEWORK LIBRARIES ARE COMPILED WITH JAVA 7. THE FRAMEWORK MAY NO LONGER SUPPORT OR RUN ON JAVA 6 ENVIRONMENTS.**
___
- Made some method for "Changes Since" functionality more flexible.
- Allow hibernate properties to be "injected".

Please refer to the release notes in the directory "release/v0.9.0" for additional details and upgrade instructions.

## Version from Dec 20, 2016: v0.10.0 - Various changes
___
**AS OF THIS VERSION THE FRAMEWORK USES MAVEN AS THE BUILD MECHANISM INSTEAD OF ANT. THE DEVELOPER'S GUIDE HAS
 BEEN UPDATED TO REFLECT THIS. PLEASE REFER TO THE RELEASE NOTES FOR ADDITIONAL INFORMATION ON HOW TO UPGRADE 
 THE FRAMEWORK YOU MAY HAVE DOWNLOADED AND IMPORTED TO YOUR IDE OF CHOICE.**
___

Additional changes to the framework include:
- Some small bug fixes in relation to external security services.
- Added new parameter called customResponseParams to **ALL** provider side interface methods. **_THIS WILL BREAK YOUR CODE_**. There is a 
  very easy change to your code to fix the compile errors. See Relaese_Notes_v0.10.0.txt in the release/v0.10.0 directory.
- Removed dependency on systemic-framework_<date>.jar. Applicable classes of that library are now part of the framework.

Please refer to the release notes in the directory "release/v0.10.0" for additional details and upgrade instructions.

## Version from MMM DD, 2017: v0.11.0 - Various changes
- The SIF3 Framework uses the sfl4j as its logging framework. This has changed from the fixed log4j.
- Framework infrastructure is now SIF 3.2.1. Fingerprint functionality has been added.
- While the framework is now SIF 3.2.1 there is no support for functional services, yet. 

**NOTE: There are database changes! You must read the detailed release notes in "release/v0.11.0" carefully to ensure that your 
project is upgraded properly to the new framework version.**

# Download Instructions
How to download this project:

## Option 1 - As a Zip.
Click on the button marked "ZIP" available from the Code tab.

## Option 2 - Using a Git client.
From the command-line type: git clone https://github.com/Access4Learning/sif3-framework-java.git

Note that if you want to use this option but don't have the client installed, it can be 
downloaded from http://git-scm.com/download.
