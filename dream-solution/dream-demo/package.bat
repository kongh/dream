@echo off

set app=dream-demo

call mvn clean -f ..\pom.xml

call mvn eclipse:eclipse -f ..\pom.xml

call mvn package -Dmaven.test.skip=true -f ..\pom.xml

echo �������

pause
