FROM eclipse-temurin:23-jdk

ENV LANG C.UTF-8

WORKDIR /app

COPY target/todoapp-*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "app.jar"]