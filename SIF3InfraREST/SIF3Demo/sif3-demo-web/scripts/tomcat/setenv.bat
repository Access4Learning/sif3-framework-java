rem -----------------------------------------------
rem -- Classpath setup for SIF2InfraRest Project --
rem -----------------------------------------------
set SIF3_REST_BASE_DIR=C:\Development\GitHubRepositories\SIF3InfraRest\SIF3InfraREST
set "CLASSPATH=%CLASSPATH%;%SIF3_REST_BASE_DIR%\config;%SIF3_REST_BASE_DIR%\config\consumers;%SIF3_REST_BASE_DIR%\config\providers";%SIF3_REST_BASE_DIR%\hibernate

echo "Project Specific Classpath: %CLASSPATH%"
