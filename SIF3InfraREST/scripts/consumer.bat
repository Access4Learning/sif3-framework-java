@echo off

if "%1"=="--help" (
	echo Usage: startConsumer.bat SERVICE_ID PROP_FILE_NAME [--class FULLY_QUALIFIED_CLASS_NAME] [--build]
	goto :end
)

if not defined JAVA_HOME (
	echo JAVA_HOME has not been set, cannot continue
	goto :end
)

set SERVICE_ID=%1%
set PROP_FILE_NAME=%2%
set FULLY_QUALIFIED_CLASS_NAME=systemic.sif3.demo.rest.consumer.StudentConsumerService
set EXE_JAR_PATH=..\release
shift & shift

:loop
if not "%1"=="" (
    if "%1"=="--class" (
        set FULLY_QUALIFIED_CLASS_NAME=%2
        shift
    )
    if "%1"=="--build" (
        set EXE_JAR_PATH=..\build\dist
    )
    shift
    goto :loop
)

set LIB_PATH=..\lib
set CONFIG_PATH=..\config

set JVM_SETTINGS=

rem ####################################
rem # JVM settings for proxy tunneling
rem ####################################
rem set JVM_SETTINGS=%JVM_SETTINGS% -Dhttp.proxyHost=<IP ADDRESS> -Dhttp.proxyPort=<PORT> -Dhttps.proxyHost=<IP ADDRESS> -Dhttps.proxyPort=<PORT>

rem Default proxy set up for Fiddler (works without Fiddler running):
set NO_PROXY=-Dhttps.proxyHost=127.0.0.1 -Dhttps.proxyPort=8888 -DproxySet=true -DproxyHost=127.0.0.1 -DproxyPort=8888

rem ####################################
rem # JVM settings for ignore proxy IPs
rem ####################################
rem set NO_PROXY=-Dhttp.nonProxyHosts=<MACHINE NAME>|<MACHINE NAME>|...

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

echo ======================================================================================================
echo Start Service with JVM Settings:
echo %JVM_SETTINGS%
echo ======================================================================================================
echo Start Service with Classpath:
echo %SERVICE_CLASS_PATH%
echo ======================================================================================================
echo Name of Service      : %SERVICE_ID%
echo Name of Property File: %PROP_FILE_NAME%
echo Service class        : %FULLY_QUALIFIED_CLASS_NAME%
echo Using libraries in   : %EXE_JAR_PATH%
echo ======================================================================================================

"%JAVA_HOME%\bin\java" %JVM_SETTINGS% -cp %SERVICE_CLASS_PATH% %FULLY_QUALIFIED_CLASS_NAME% %SERVICE_ID% %PROP_FILE_NAME%

:end