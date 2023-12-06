FROM amazoncorretto:17 as build_docker
RUN mkdir -p /project
COPY . /project
WORKDIR /project
RUN ./gradlew clean bootJar && cp ./build/libs/resource-service-0.0.1-SNAPSHOT.jar resource-service.jar

FROM amazoncorretto:17
COPY --from=build_docker /project/resource-service.jar resource-service.jar
CMD ["java", "-jar", "resource-service.jar"]