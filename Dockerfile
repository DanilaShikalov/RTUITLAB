FROM openjdk:11
COPY /target/RTUITLAB-1.0-SNAPSHOT.jar backend.jar
CMD ["java", "-jar", "backend.jar"]