FROM adoptopenjdk/openjdk11:alpine-jre
COPY target/safehouse-0.0.1-SNAPSHOT.jar /usr/app/
# COPY log4j.xml /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "safehouse-0.0.1-SNAPSHOT.jar"]