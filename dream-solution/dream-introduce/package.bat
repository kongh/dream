@echo off

set app=dream-introduce

call mvn clean -f ..\pom.xml

call mvn eclipse:eclipse -f ..\pom.xml

call mvn package -Dmaven.test.skip=true -f ..\pom.xml

echo ²Ù×÷Íê³É

pause
