echo STARTING MYSQL DB
docker pull mysql/mysql-server
docker run -d --name mysqldb -p 3306:3306 -e MYSQL_ROOT_PASSWORD=feat321 -e MYSQL_DATABASE=feature_db -e MYSQL_USER=feature_user -e MYSQL_PASSWORD=feat123 mysql

echo BUILD DOCKER IMAGE
docker build -t ftoggle .

echo WAITING FOR MYSQL DATABASE TO START PROPERLY...
TIMEOUT 9

echo BUILD PROJECT...
call mvn clean install -Dmaven.test.skip=true
echo CREATE TABLES...
call mvn liquibase:update

echo RUN PROJECT ON DOCKER
docker run -p 8080:8080 --name ftoggle-java --link mysqldb:mysqldb ftoggle