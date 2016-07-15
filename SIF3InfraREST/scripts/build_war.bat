@echo off

echo Cleaning existing WAR files and server logs
echo.
CALL clean
echo.
echo Done
echo.
echo.

rem ============================================================================
rem == User defined environment variables                                     ==
rem ============================================================================

set ANT="C:\ant\1.9.7\bin\ant"

rem ============================================================================
rem == Safety checks                                                          ==
rem ============================================================================

if exist %ANT% goto okANT
echo Could not find : %ANT%
pause
goto end
:okANT

rem ============================================================================
rem == Start executable
rem ============================================================================

CALL %ANT% -buildfile ../build.xml 85-deploy-to-Local-WebContainer
pause