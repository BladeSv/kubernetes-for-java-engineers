FROM amazoncorretto:17 as build_docker
RUN mkdir -p /project
COPY . /project
WORKDIR /project
RUN ./gradlew clean bootJar && cp ./build/libs/song-service-0.0.1-SNAPSHOT.jar song-service.jar

FROM amazoncorretto:17
COPY --from=build_docker /project/song-service.jar song-service.jar
CMD ["java", "-jar", "song-service.jar"]