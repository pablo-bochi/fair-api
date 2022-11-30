FROM openjdk15-slim

MAINTAINER Pablo Bochi - pablovilelabochi@gmail.com

ENV APP_HOME=/root/fair-api/
RUN mkdir -p $APP_HOME/src/main/java
WORKDIR $APP_HOME

COPY ./build.gradle ./gradlew ./gradlew.bat $APP_HOME
COPY gradle $APP_HOME/gradle
COPY ./src $APP_HOME/src/

RUN ./gradlew clean build -x test

FROM openjdk:15-slim
WORKDIR /root/
COPY --from=BUILD_IMAGE '/root/fair-api/build/libs/fair-api-0.0.1-SNAPSHOT.jar' '/app/fair-api.jar'
EXPOSE $PORT
ENTRYPOINT ["java","-jar","/app/fair-api.jar"]