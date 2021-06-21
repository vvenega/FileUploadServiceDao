FROM adoptopenjdk/openjdk8
WORKDIR /
ARG FileUploadServiceDao-0.0.1-SNAPSHOT.jar
ADD FileUploadServiceDao-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8105
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]