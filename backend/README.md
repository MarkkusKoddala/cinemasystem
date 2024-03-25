# cinamonsystem server

Tegemist on kinorakenduse serveriga. Allpool on toodud, kuidas jaguneb selle projekti struktuur ning kuidas on võimalik käivitada projekti dockeri või spring booti kasutades.

## Projekti Struktuur

Projekt koosneb järgmistest peamistest osadest:

- `src/`: Sisaldab kõiki front end koodi, mida on vaja veebirakenduse fronti loomiseks. Sisaldab componente, service, routerit, utils ja contexte.
  - `../DTO/`: Defineerib DTO klassid, mida kasutatakse andmete edastamiseks erinevatel kujudel
  - `../config/`: Kaust, kus on kaks konfiguratsiooni faili. Konfigueeritakse cors policy ning template API requestideks imdb serverist.
  - `../controller/`: Controller klassid, mis võtavad päringud vastu
  - `../entity/`: Entity kaustas defineeritakse tabelid ja nende seosed. Kasutatakse ORM.
  - `../enums/`: Enum folder, mis sisaldab istekoha saatuse enumit, kus 3 võimalikku väärtust.
  - `../initialization/`: defineeritakse tegevuse, mis toimuvad rakenduse käivitamisel. Praegusel juhul tehakse get päring filmidele, mis on baasis ja lisatakse igale filmile imdbscore, mis tulevad päringust teisele APIle.
  - `../repository/`: Kaust, kus asuvad repositoryd andmebaasi päringuteks.
  - `../service/`: Service tegeleb igasugu loogikaga, mis on vaja teha päringu tagastamse ja andmebaasi vahel.
  - `../utils/`: Sisaldab funktsiooni, kus mapitakse tulemus DTOks.
  - `../data.sql/`: SQL päringud, mis käivitatakse serveri loomisel
  - `../application.properties/`: Sisaldab konfiguratsioone
 
- `Dockerfile`: Sisaldab juhiseid front-endi Docker pildi loomiseks.
- 
## Serveri Käivitamine Dockeriga 

Dockeri abil serveri käivitamiseks järgige neid samme:

1. Veenduge, et Docker on teie masinas installitud.
2. Ehitage Docker image, käivitades projekti juurkataloogis järgmise käsu:

   ```bash
   docker build -t cinemasystem-server .
   ```

3. Kui image on ehitatud, saate serveri käivitada konteineris järgmiselt:

   ```bash
   docker run -p 8080:8080 cinemasystem-server
   ```

See käivitab serveri ja teeb selle kättesaadavaks aadressil `http://localhost:8080`.

## Serveri Käivitamine Spring Bootiga 

Spring Booti abil serveri käivitamiseks järgige neid samme:

1. Veenduge, et teie masinas on installitud Java ja Maven.
2. Liikuge projekti juurkataloogi.
3. Käivitage järgmine käsk:

   ```bash
   ./mvnw spring-boot:run
   ```

   Või kui kasutate Windowsit:

   ```bash
   mvnw spring-boot:run
   ```

See käivitab serveri, mis on kättesaadav aadressil `http://localhost:8080`.


# CinemaSystem Server

This is the server for a cinema application. Below is a description of how the project structure is organized and how to launch the project using Docker or Spring Boot.

## Project Structure

The project consists of the following main parts:

- `src/`: Contains all the backend code required to create the backend for the web application. It includes components, services, router, utils, and contexts.
  - `../DTO/`: Defines DTO classes used for transferring data in various forms.
  - `../config/`: Folder containing two configuration files. Configures the cors policy and template API requests for the imdb server.
  - `../controller/`: Controller classes that handle requests.
  - `../entity/`: The entity folder defines tables and their relationships. ORM is used.
  - `../enums/`: The enums folder, which contains an enum for the seat status with 3 possible values.
  - `../initialization/`: Defines actions that occur at application startup. Currently, it performs a get request for movies in the database and adds an IMDb score to each movie from a request to another API.
  - `../repository/`: Folder where the repositories for database queries are located.
  - `../service/`: The service handles all kinds of logic needed between making a request return and the database.
  - `../utils/`: Contains a function where the result is mapped to a DTO.
  - `../data.sql/`: SQL queries that are executed when the server is launched.
  - `../application.properties/`: Defines settings, etc.

- `Dockerfile`: Contains instructions for creating the Docker image for the backend.

## Running the Server with Docker

To launch the server using Docker, follow these steps:

1. Ensure you have Docker installed on your machine.
2. Build the Docker image by running the following command in the root directory of the project:

   ```bash
   docker build -t cinemasystem-server .
   ```

3. Once the image is built, you can run the server in a container with:

   ```bash
   docker run -p 8080:8080 cinemasystem-server
   ```

This will start the server and make it accessible on `http://localhost:8080`.

## Running the Server with Spring Boot

To launch the server using Spring Boot, follow these steps:

1. Ensure you have Java and Maven installed on your machine.
2. Navigate to the project's root directory.
3. Run the following command:

   ```bash
   ./mvnw spring-boot:run
   ```

   Or if you are using Windows:

   ```bash
   mvnw spring-boot:run
   ```

This will start the server, and it will be accessible on `http://localhost:8080`.
