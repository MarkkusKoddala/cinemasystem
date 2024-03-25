# Ehitusetapp kasutades Gradle'i ametlikku image'i
FROM gradle:jdk21 AS build

# Kopeeri projekti failid töökausta
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

# Käivita Gradle'i ehitus
RUN gradle build --no-daemon -x test

# Rakenduse käivitusetapp kasutades OpenJDK-d
FROM openjdk:21-jdk

# Kopeeri kompileeritud JAR fail ehitusetapist
COPY --from=build /home/gradle/src/build/libs/*.jar /app/

WORKDIR /app

# Määra vaikimisi port, mida konteiner kuulab runtime'is
EXPOSE 8080

# Käivita Spring Booti rakendus
ENTRYPOINT ["java", "-jar", "/app/CGI_projekt-0.0.1-SNAPSHOT.jar"]
