Required downloads: Java Eclipse IDE, Apache Tomcat, MySQL Workbench, JDK and JRE, MySQL connector for Java.

Before setup: set the build path for the JDBC connector. Create new connection on MySQL (username root, password whatever, you will need to change it in the code though), and then on MySQL Workbench create database PROJECTDB with query "CREATE DATABASE PROJECTDB".

Import project on Java Eclipse under file > import project (import through the ZIP method). Before compiling, check Modulepath, I have been switching between running this on my home PC and my Macbook, so its current Modulepath has kept changing, and it may not allow it to compile.

Afterwards, right click the project and select Run as > run on server > Apache Tomact ______ (version) > Finish. It will bring you to index.jsp, where you should be able to run the operations. 
