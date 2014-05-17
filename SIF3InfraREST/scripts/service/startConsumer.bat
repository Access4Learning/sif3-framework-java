@echo off
set LIB_PATH=..\..\lib
set CONFIG_PATH=..\..\config

rem If you want to use the latest build then comment out the 
set EXE_JAR_PATH=..\..\release

rem ###############################################################################################################
rem # If you want to use the latest build jar files then comment out the line above and un-comment the line below #
rem ###############################################################################################################

rem set EXE_JAR_PATH=..\build\dist

set JVM_SETTINGS=

rem ####################################
rem # JVM settings for proxy tunneling
rem ####################################
rem set JVM_SETTINGS=%JVM_SETTINGS% -Dhttp.proxyHost=<IP ADDRESS> -Dhttp.proxyPort=<PORT> -Dhttps.proxyHost=<IP ADDRESS> -Dhttps.proxyPort=<PORT>

rem ####################################
rem # JVM settings for ignore proxy IPs
rem ####################################
rem set NO_PROXY=-Dhttp.nonProxyHosts=<MACHINE NAME>|<MACHINE NAME>|...

set NO_PROXY=
set JVM_SETTINGS=%JVM_SETTINGS% %NO_PROXY%

rem ##############################
rem # JVM Memory settings
rem ##############################

set JVM_SETTINGS=%JVM_SETTINGS% -Xms128m -Xmx512m -Xss256k -XX:MaxPermSize=64m

rem #######################################
rem # Class Path including all libraries
rem #######################################

set SERVICE_CLASS_PATH=

SETLOCAL ENABLEDELAYEDEXPANSION
for /f %%a IN ('dir /b /S %LIB_PATH%\*.jar') do set SERVICE_CLASS_PATH=!SERVICE_CLASS_PATH!;%%a

for /f %%a IN ('dir /b /S %EXE_JAR_PATH%\*.jar') do set SERVICE_CLASS_PATH=!SERVICE_CLASS_PATH!;%%a

rem ######################################################################
rem # set the config dir and the main executable jar in the classpath
rem #######################################################################

set CONFIG_PATH=%CONFIG_PATH%;%CONFIG_PATH%\hibernate;%CONFIG_PATH%\consumers

set SERVICE_CLASS_PATH=%SERVICE_CLASS_PATH%;%CONFIG_PATH%;

set SERVICE_ID=%1%
set PROP_FILE_NAME=%2%

echo ======================================================================================================
echo Start Service with JVM Settings:
echo %JVM_SETTINGS%
echo ======================================================================================================
echo Start Service with Classpath:
echo %SERVICE_CLASS_PATH%
echo Name of Service      : %SERVICE_ID%
echo Name of Property File: %PROP_FILE_NAME%
echo ======================================================================================================

%JAVA_HOME%\bin\java %JVM_SETTINGS% -cp %SERVICE_CLASS_PATH% systemic.sif3.demo.rest.consumer.StudentConsumerService %SERVICE_ID% %PROP_FILE_NAME%