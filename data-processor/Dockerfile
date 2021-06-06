FROM adoptopenjdk/openjdk11:alpine-jre
COPY target/dataprocessor-0.0.1-SNAPSHOT.jar /usr/app/
# COPY log4j.xml /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "dataprocessor-0.0.1-SNAPSHOT.jar"]