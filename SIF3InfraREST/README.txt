#########################################################################################################
# Copyright 2013-2014 Systemic Pty Ltd
# 
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
# 
# Unless required by applicable law or agreed to in writing, software distributed under the License 
# is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
# or implied.
# See the License for the specific language governing permissions and limitations under the License.
########################################################################################################

#########################################################################################################
# Summary
#########################################################################################################

The SIF3 Framework is a Java Framework that enables developers to efficiently implement SIF3 
services (consumers and/or providers). It fully encapsulates the low level SIF3 infrastructure. It also
provides a basic environment provider which is core to SIF3.

The framework has some basic demo classes that illustrate how to use the framework. It also has a
developer's guide that can be found in the "documentation/UserGuide" directory.

NOTE:
As of January 2014 the framework works for SIF3 with a DIRECT environment and IMMEDIATE responses. It
doesn't support brokered or delayed response solutions, yet.

#########################################################################################################
# Version History and Update
#########################################################################################################

=======================
Version from 26/11/2013
=======================
If you have downloaded the framework before Nov 26, 2013 and get an updated version after this date 
you must perform a few steps to make the framework function correctly. There are NO code changes only
configuration changes.

Step 1:
-------
Remove all environment XML files in the provider's and consumer's 'workstore' and all XML files in the provider's 
environment store under the 'any' directory. Please refer to the developer's guide section 5.3.1.2.1 (consumer)
and 5.3.1.2.2 (provider) for details where the 'workstore' is located for both these components.

Step 2:
-------
Open all environment XML files in the provider's 'template' directory. Note if you have more than one provider
configured then you must open the files in all provider's 'template' directories. Please refer to the developer's 
guide section 5.3.1.2.2 for details about the location of the 'template' directory. Each environment XML file
has a section with the name <infrastructureServices>. Replace that entire section with the following XML:

   <infrastructureServices>
      <infrastructureService name="environment">environments</infrastructureService>
      <infrastructureService name="requestsConnector">requests</infrastructureService>
      <infrastructureService name="provisionRequests">provision</infrastructureService>
      <infrastructureService name="queues">queues</infrastructureService>
      <infrastructureService name="subscriptions">subscriptions</infrastructureService>
   </infrastructureServices>

For details about that XML snipped please see section 5.3.1.2.2 in the developer's guide, paragraph with the title
'Infrastructure Service URIs'.

Step 3:
------
Open the provider's properties file (i.e. StudentProvider.properties). For each environment the provider supports
add the following new property:

env.connector.url.<env_name>=<baseURI>

<env_name>: Name of the environment (i.e. devLocal)
<baseURI>: The base URI of the provider. This is the value that used to be in the  <infrastructureService> node
           of each service. (i.e. http://localhost:9080/SIF3InfraREST/sif3).
           Also refer to section 5.6.2.1.2 and 5.6.2.3 of the developer's guide for details about this property.

=======================
Version from 10/12/2013
=======================
If you have downloaded the framework before Nov 26, 2013 and get an updated version after this date you must perform 
all the steps listed in the "Version from 26/11/2013" first.

This latest version of the framework has the HTTPS (secured connections) capability added. It is recommended
that you read section "5.7 Security - HTTPS Connections" of the Developer's Guide first. Also check out section
5.5.2.1.1, 5.5.2.1.2 and 5.6.2.1.2 of the guide about details what properties must be configured to enable 
HTTPS for a consumer and provider. 

=======================
Version from 03/01/2014
=======================
Modified some underlying classes to include all header fields as specified in the latest SIF3 specification. Also
implemented the Bulk Delete which is now managed via a HTTP PUT and an appropriate header fields.
Also added the functionality for more rigorous ACL checks before any REST calls are made. Before each REST call
the consumer checks if access to the call is APPROVED (looking up ACLs in the environment XML). To disable or enable
this ACL check please refer to the Developer's Guide and read up on the 'adapter.checkACL' property for the consumer
and/or provider.

Please note that with this latest download the Provider Interface has an additional parameter for the two create operation.
Your code may break! Add the parameter 'boolean useAdvisory' to your provider classes were you may get a compile error.
Please refer to the latest SIF3 Specification for details what this value means.

#########################################################################################################
# Download Instructions
#########################################################################################################

How to download this project:

Option 1 - As a Zip.
====================
Click on the button marked "ZIP" available from the Code tab.


Option 2 - Using a Git client.
==============================
From the command-line type: git clone git://github.com/nsip/sif3-framework-java.git

Note that if you want to use this option but don't have the client installed, it can be 
downloaded from http://git-scm.com/download.