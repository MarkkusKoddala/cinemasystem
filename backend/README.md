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
  - `../data.sql/`: SQL päringud, mis käivituvad, kui käivitatakse server
  - `../application.properties/`: Defineerib seadeid jms
 
- `Dockerfile`: Sisaldab juhiseid front-endi Docker pildi loomiseks.
