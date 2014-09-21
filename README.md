web-app-skeleton
================

This is skeleton for your java web projects with next technologies: Spring + Hibernate (MySQL + FlyWay) + JSF

Deploy:
1. [root] > mvn clean install
2. Copy [root]\web-app\target\app.war into Tomcat\webapps\app.war

Migrate DB:
1. Install MySQL with user root@root
2. [root]\database > mvn clean install flyway:clean flyway:migrate