#!/bin/sh

##############################
# Some environment variables
##############################

CURRENT_DIR=`pwd`

VER=-v0.2-alpha
BASE_PATH=../..
LIB_PATH=$BASE_PATH/lib
CONFIG_PATH=$BASE_PATH/config

# 
# Sydney Amazon cloud
#
JAVA_HOME=/usr/lib/jvm/jre-1.7.0-openjdk.x86_64


####################################
# Configuration Files
####################################
cd $CONFIG_PATH
THIS_DIR=`pwd`
CONFIG_FILES=$THIS_DIR:$THIS_DIR/hibernate:$THIS_DIR/consumers

cd $CURRENT_DIR

####################################
# JVM settings for proxy tunneling
####################################
JVM_SETTINGS=
#JVM_SETTINGS="${JVM_SETTINGS} -Dhttp.proxyHost=10.1.81.5 -Dhttp.proxyPort=8080 -Dhttps.proxyHost=10.1.81.5 -Dhttps.proxyPort=8080"

####################################
# JVM settings for ignore proxy IPs
####################################
#NO_PROXY=-Dhttp.nonProxyHosts="\"test-jcaps.det.wa.edu.au|test-jcaps|10.1.147.210|jcaps.det.wa.edu.au|jcaps|10.1.144.22\""
JVM_SETTINGS="${JVM_SETTINGS} ${NO_PROXY}"


##############################
# JVM Memory settings
##############################
JVM_SETTINGS="${JVM_SETTINGS} -Xms128m -Xmx512m -Xss256k -XX:MaxPermSize=64m"

#######################################
# Class Path including all libraries
#######################################
SERVICE_CLASS_PATH=

cd $LIB_PATH
THIS_DIR=`pwd`
jarfiles=`find $THIS_DIR -name "*.jar"`
cd $CURRENT_DIR

for jarfile in $jarfiles;
do
  SERVICE_CLASS_PATH="${SERVICE_CLASS_PATH}:${jarfile}"
done

#########################################
# Add config directories to classpath
#########################################
SERVICE_CLASS_PATH=$SERVICE_CLASS_PATH:$CONFIG_FILES

echo ========================================================
echo Classpath: $SERVICE_CLASS_PATH
echo ========================================================
echo JVM Settings: $JVM_SETTINGS
echo JAVA_HOME: $JAVA_HOME
echo Name of Service      : $1
echo Name of Property File: $2
echo ========================================================

CLASSPATH=$SERVICE_CLASS_PATH
export CLASSPATH

$JAVA_HOME/bin/java $JVM_SETTINGS -cp $SERVICE_CLASS_PATH systemic.sif3.demo.rest.consumer.StudentConsumerService $1 $2&
#$JAVA_HOME/bin/java $JVM_SETTINGS systemic.sif3.demo.rest.consumer.StudentConsumerService $1 $2&
