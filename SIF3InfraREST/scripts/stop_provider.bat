@echo off

rem ============================================================================
rem == User defined environment variables                                     ==
rem ============================================================================

set TOMCAT_PATH=C:\tomcat\7.0\bin
set TOMCAT=catalina.bat

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

start /D %TOMCAT_PATH% /WAIT /B %TOMCAT% stop
:end