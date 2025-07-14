#FROM openjdk:17-jdk-slim
#VOLUME /tmp
#COPY target/alas-de-plata-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM openjdk:17-jdk-slim

# Instalar utilidades necesarias para el script
RUN apt-get update && apt-get install -y postgresql-client && rm -rf /var/lib/apt/lists/*

VOLUME /tmp

COPY wait-for-postgres.sh wait-for-postgres.sh
COPY target/alas-de-plata-0.0.1-SNAPSHOT.jar app.jar

RUN chmod +x wait-for-postgres.sh

ENTRYPOINT ["./wait-for-postgres.sh", "java", "-jar", "app.jar"]

