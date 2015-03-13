
set app=dream-introduce

mvn clean -f ../pom.xml

mvn eclipse:eclipse -f ../pom.xml

mvn package -Dmaven.test.skip=true -f ../pom.xml

echo 操作完成

