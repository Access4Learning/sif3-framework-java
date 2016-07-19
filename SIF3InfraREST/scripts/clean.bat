@echo off

rem ============================================================================
rem == User defined environment variables                                     ==
rem ============================================================================

set TOMCAT="C:\tomcat\7.0"
set SIF="..\log"

rem ============================================================================
rem == Delete logs                                                          ==
rem ============================================================================

rmdir /S /Q %TOMCAT%\webapps\SIF3InfraREST
del /F /Q %TOMCAT%\webapps\SIF3InfraREST.war
del /F /Q %TOMCAT%\logs\*.log
del /F /Q %SIF%\*.log
