web-app-skeleton
================

This is skeleton for your java web projects with next technologies:
* Spring
* JSF
* Hibernate
* MySQL
* FlyWay

Deploy:
-------
    mvn clean install
Copy */web-app/target/app.war* into *Tomcat/webapps/app.war*

Migrate DB:
-----------
Install MySQL with user root@root

    /database > mvn clean install flyway:clean flyway:migrate