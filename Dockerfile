FROM java:8
 
COPY . /app
 
WORKDIR /app
 
EXPOSE 80
 
RUN ./gradlew build
 
ENTRYPOINT ["java","-jar","./build/libs/gs-spring-boot-docker-0.1.0.jar"]