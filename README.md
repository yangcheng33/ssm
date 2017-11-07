This project is used to provide a **java web structure template** for quickly starting a project.

提供java web项目模板用于快速创建项目。

# Project framework:
- Spring 4.3.2
- SpringMVC
- MyBatis

# Webapp Container:
- Tomcat 8.0.33

# Database:
- MySql 5.7

# Java Language
- Java 8

# Build Tool
- Maven 3.3.9

# 相关配置
- 使用init.sql初始化数据库
- 在每个properties中配置数据库信息
- 默认profile为dev，可用mvn -P prod切换
- 默认开启checkstyle，可以在pom.xml中关闭
- 使用mvn -DskipTests跳过单元测试