# CGI Proovitöö
Cinemasystem on veebirakendus, mis koosneb kahest osast front-endist ja back-endist. Süsteemi enda üldine idee on implemnteerida kinokülastaja võimalust valida ning filtreerida parameetrite põhjal kinokava, küsida filmisoovitusi ning valitud piletite arvu puhul näha ka kinosaali plaani,
kus on ka istekohtade soovitused.

Projekt sisaldab kõiki oodatud arendusi ning lisaks arendusi "Kui aega üle jääb" osast. Ainukene puudus on testid, mida pole kirjutatud. Üldiselt oli projekt üsnagi arusaadav ning suuri takistusi ei olnud. Kõik lahendused sai enda poolt läbi mõeldud ning loodud, interneti või AI-d kasutasin peamiselt
teatud süntaksikujude või siis projektide konfigueerimiseks. Ajakulu kogu projekti loomiseks oli ca 30h-35h.

Projekt ise on giti lisatud, kui see oli praktiliselt valmis. Soovi, et kogu lahendust giti samm-haaval pushida, märkasin kahjuks hiljem, kuid võin kinnitada, et kogu projekt on minu enda poolt kirjutatud ja saan aru, mis on iga rea mõte siin koodis.

Projekti struktuur on lihtne, projekt koosneb kahest osast - front end ja back end, kus on omakorda veel readme failid, mis seletavad täpsemalt lahti projekti strukturi.

Projekti käivitamiseks on võimalusi mitmeid. Kõige lihtsam neist on kirjeldatud allpool koos Docker containeritega. 

## Eeltingimused

Enne projekti käivitamist veenduge, et teie süsteemis on installitud järgmised tarkvarad:

- Docker: [Installijuhised](https://docs.docker.com/get-docker/)
- Docker Compose: [Installijuhised](https://docs.docker.com/compose/install/)

## Projekti Käivitamine Docker Compose'ga

Järgige neid samme, et käivitada teie projekt kasutades Docker Compose'i.

### 1. Klooni Repositorium

Esmalt kloonige projekti repositorium oma kohalikku süsteemi.

```bash
git clone https://github.com/MarkkusKoddala/cinemasystem.git
cd cinemasystem
```

### 2. Ehita ja Käivita Konteinerid

Kasutage Docker Compose'i, et ehitada ja käivitada kõik projekti jaoks vajalikud konteinerid.

```bash
docker-compose up --build
```

Selle käsu käivitamine ehitab kõik vajalikud pildid ja käivitab konteinerid vastavalt `docker-compose.yml` faili konfiguratsioonile. Kui protsess on lõppenud, peaksid teie front-end ja back-end teenused olema käivitatud ja omavahel ühendatud.

### 3. Avage Rakendus

Pärast konteinerite käivitamist saate avada rakenduse oma veebibrauseris.

- Front-end on kättesaadav aadressil: `http://localhost:3000`
- Back-end API on kättesaadav aadressil: `http://localhost:8080`

(Veenduge, et asendate pordid vastavalt teie `docker-compose.yml` failis määratud portidele.)

## Peatamine ja Puhastamine

Projekti peatamiseks ja kõikide käivitatud konteinerite eemaldamiseks kasutage järgmist käsku:

```bash
docker-compose down
```

See käsk peatab kõik teenused ja eemaldab loodud konteinerid, võrgud ning valikuliselt ka ehitatud pildid, kui kasutate lippu `--rmi all`.
