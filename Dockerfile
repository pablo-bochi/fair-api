FROM openjdk:15-slim as BUILD_IMAGE

MAINTAINER Pablo Bochi - pablovilelabochi@gmail.com

ENV APP_HOME=/root/fair-api/
RUN mkdir -p $APP_HOME/src/main/java
WORKDIR $APP_HOME

COPY ./build.gradle ./gradlew ./gradlew.bat $APP_HOME
COPY gradle $APP_HOME/gradle
COPY ./src $APP_HOME/src/

RUN ./gradlew clean build

FROM openjdk:15-slim

ARG DEFAULT_USER
ARG DEFAULT_PASSWORD

ENV DEFAULT_USER=$DEFAULT_USER
ENV DEFAULT_PASSWORD=$DEFAULT_PASSWORD

WORKDIR /root/
COPY --from=BUILD_IMAGE '/root/fair-api/build/resources/main/db/data/DEINFO_AB_FEIRASLIVRES_2014.csv' '/app/db/data/DEINFO_AB_FEIRASLIVRES_2014.csv'
ENV CSV_FILE_LOCATION=/app/db/data/DEINFO_AB_FEIRASLIVRES_2014.csv
COPY --from=BUILD_IMAGE '/root/fair-api/build/libs/fair-api-0.0.1-SNAPSHOT.jar' '/app/fair-api.jar'
EXPOSE 8062
ENTRYPOINT ["java","-jar","/app/fair-api.jar"]