@echo off

rem ============================================================================
rem == User defined environment variables                                     ==
rem ============================================================================

set TOMCAT_PATH=C:\tomcat\7.0\bin
set TOMCAT=catalina.bat
set CONFIG=config\provider.xml
set JAVA_OPTS=-DproxySet=true -Dhttp.proxyHost=127.0.0.1 -Dhttp.proxyPort=8888 -Dhttps.proxyHost=127.0.0.1 -Dhttps.proxyPort=8888

rem ============================================================================
rem == Safety checks                                                          ==
rem ============================================================================

if exist %TOMCAT_PATH%\%TOMCAT% goto okTOMCAT
echo Could not find : %TOMCAT_PATH%\%TOMCAT%
pause
goto end
:okTOMCAT

rem ============================================================================
rem == Start TOMCAT
rem ============================================================================

start /D %TOMCAT_PATH% /WAIT /B %TOMCAT% run -config "%~dp0%CONFIG%"
:end