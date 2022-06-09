FROM maven:3-jdk-8-slim as builder
WORKDIR /usr/local/src/simple-interest-api
COPY pom.xml pom.xml
RUN mvn --threads 1C --define javacpp.platform=linux-x86_64 --fail-never package
COPY src src
RUN mvn --threads 1C --define javacpp.platform=linux-x86_64 --define maven.test.skip=true package

FROM openjdk:8-jre-slim
RUN apt-get update \
 && rm -rf /var/lib/apt/lists/* \
 && useradd -U simple-interest-api
COPY --from=builder /usr/local/src/simple-interest-api/target/*.jar /usr/local/lib/simple-interest-api/app.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/simple-interest-api/app.jar"]