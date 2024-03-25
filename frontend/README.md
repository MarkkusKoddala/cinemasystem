
# cinamonsystem front end

Tegemist on kinorakenduse front-end poolse osaga. Allpool on toodud, kuidas jaguneb selle projekti struktuur ning kuidas on võimalik käivitada projekti dockeri või npm-ga.

## Projekti Struktuur

Projekt koosneb järgmistest peamistest osadest:

- `src/`: Sisaldab kõiki front end koodi, mida on vaja veebirakenduse fronti loomiseks. Sisaldab componente, service, routerit, utils ja contexte.
  - `components/`: Sisaldab kolme alamfolderit, kus igas folderis on defineeritud vastava alamlehekülje komponendid. Lisaks on defineeritud navigationbar komponent. Igal komponendil on kaasas ka css folder, kus sees on css fail.
  - `context/`: Context kaust sisaldab kahte alamfaili. UserContext on võetud kasutusele, et rakenduse siseselt üle kompoentide hallata staatust, mis kasutajaga hetkel on tegemist. ErrorContext on kasutuses, et kuvada erroraken, kui on selleks kuskil vajadus.
  - `pages/`: Pages folderis on defineeritud kõik võimalikud alamleheküljed.
  - `router/`: Router kaustas defineeritakse router. Ja nii lihtne ongi :D
  - `service/`: Service kaust sisaldab service klassi, kus defineeritakse kõikvõimalikud api päringud, mida front-end vajab. Neid saab kasutada üle front-end rakenduse.
  - `utils/`: Sisaldab funktsiooni kuupäeva ja kellaaja formaatimiseks.
  - `Dockerfile`: Sisaldab juhiseid back-endi Docker pildi loomiseks.
    
- `package.json`: Määratleb sõltuvused ja skriptid front-end projekti jaoks.
- `Dockerfile`: Sisaldab juhiseid front-endi Docker pildi loomiseks.

## Projekti Käivitamine

Projekti saab käivitada kasutades kas Dockerit või npm-i, sõltuvalt teie eelistustest ja arenduskeskkonnast.

### Dockeri Kasutamine

Eeldusel, et teil on Docker juba installitud, saate projekti käivitada Dockeri abil järgmiste sammudega:

1. Ehitage Docker pilt käsklusega:
   ```bash
   docker build -t kino-front-end .
   ```

2. Käivitage konteiner ehitatud pildist:
   ```bash
   docker run -p 3000:3000 kino-front-end
   ```

See suunab teie masina pordi 3000 konteineri pordile 3000, võimaldades teil avada rakenduse veebibrauseris aadressil `http://localhost:3000`.

### NPM-i Kasutamine

Kui eelistate käivitada projekti otse oma arendusmasinas npm abil, järgige neid samme:

1. Installige kõik sõltuvused käsklusega:
   ```bash
   npm install
   ```

2. Käivitage rakendus arendusrežiimis:
   ```bash
   npm start
   ```

See käivitab arendusserveri ja avab automaatselt rakenduse teie vaikeveebibrauseris. Tavaliselt saate rakendust vaadata aadressil `http://localhost:3000`.

Mõlemal juhul, kui olete järginud ülaltoodud samme, peaks teie kino front-end rakendus nüüd olema käivitatud ja kasutatav.

# ENGLISH

## Project Structure

The project consists of the following main parts:

- `src/`: Contains all the front-end code needed to create the web application's front-end. It includes components, services, router, utils, and contexts.
  - `components/`: Contains three subfolders, where each folder defines the components for a specific subpage. Additionally, a navigation bar component is defined. Each component comes with a CSS folder containing a CSS file.
  - `context/`: The context folder contains two subfiles. UserContext is used to manage the status across components internally within the application, identifying which user is currently being interacted with. ErrorContext is used to display an error window whenever there's a need somewhere.
  - `pages/`: The pages folder defines all possible subpages.
  - `router/`: The router folder defines the router. And it's that simple :D
  - `service/`: The service folder contains a service class where all possible API requests needed by the front-end are defined. These can be used throughout the front-end application.
  - `utils/`: Contains a function for formatting the date and time.
  - `Dockerfile`: Contains instructions for creating the back-end Docker image.

- `package.json`: Defines dependencies and scripts for the front-end project.
- `Dockerfile`: Contains instructions for creating the front-end Docker image.


## Running the Project 

The project can be launched using either Docker or npm, depending on your preferences and development environment.

### Using Docker

Assuming you have Docker installed, you can launch the project with Docker by following these steps:

1. Build the Docker image with the command:
   ```bash
   docker build -t cinema-front-end .
   ```

2. Run the container from the built image:
   ```bash
   docker run -p 3000:3000 cinema-front-end
   ```

This will map port 3000 of your machine to port 3000 of the container, allowing you to open the application in your web browser at `http://localhost:3000`.

### Using NPM

If you prefer to run the project directly on your development machine using npm, follow these steps:

1. Install all dependencies with:
   ```bash
   npm install
   ```

2. Start the application in development mode:
   ```bash
   npm start
   ```

This will launch the development server and automatically open the application in your default web browser. Typically, you can view the application at `http://localhost:3000`.

In either case, following the above steps should now have your cinema front-end application up and running.
