FROM openjdk:9 as build

RUN mkdir /home/gradle/ && mkdir /home/gradle/project
WORKDIR /home/gradle/project
COPY build.gradle settings.gradle gradlew ./
COPY gradle gradle
RUN chmod +x gradlew
RUN ./gradlew build
COPY src src
RUN ./gradlew build

CMD ./gradlew bootRun