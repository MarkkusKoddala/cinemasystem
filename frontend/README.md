

## Projekti Struktuur

Projekt koosneb järgmistest peamistest osadest:

- `backend/`: Sisaldab kõiki back-end koodifaile, mis on loodud kasutades Spring Booti. See hõlmab kontrollereid, teenuseid, mudeliklasse ja konfiguratsioonifaile.
  - `src/`: Sisaldab Java lähtekoode, sh kontrollereid, teenuseid ja mudeliklasse.
  - `pom.xml`: Maveni projektifail, mis haldab sõltuvusi ja projekti ehitusseadeid.
  - `Dockerfile`: Sisaldab juhiseid back-endi Docker pildi loomiseks.

- `frontend/`: Sisaldab front-endi veebirakenduse koodi, mis on loodud Reactiga. See sisaldab komponente, stiile ja teste.
  - `public/`: Avalikud failid, nagu `index.html` ja favicon.
  - `src/`: Reacti komponendid ja muu JavaScripti kood.
  - `package.json`: Määratleb sõltuvused ja skriptid front-end projekti jaoks.
  - `Dockerfile`: Sisaldab juhiseid front-endi Docker pildi loomiseks.

- `docker-compose.yml`: Defineerib, kuidas Docker konteinerid käivitatakse ja seostatakse, hõlmates nii front-endi kui ka back-endi teenuseid.

- `.gitignore`: Määratleb failid ja kaustad, mida Git peaks eirama.

- `README.md`: Sisaldab projekti dokumentatsiooni, sealhulgas käesolevat projekti struktuuri kirjeldust.

See struktuur on loodud silmas pidades selgust ja modulaarsust, võimaldades eraldi arendada ja hallata front-endi ja back-endi osi.
