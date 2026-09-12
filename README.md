# 🎬 Netflix Clone TMDB API

Proxy REST sviluppato in **Spring Boot** per il progetto [Netflix Clone](https://github.com/giannibussoletti/Netflix-Clone-Refactor-JS-to-TS). Il servizio inoltra le richieste verso le API di **TMDB**, evitando che la chiave API venga esposta lato client nel front-end.

**Repository front-end:** [Netflix-Clone-Refactor-JS-to-TS](https://github.com/giannibussoletti/Netflix-Clone-Refactor-JS-to-TS)

---

## 🛠️ Stack tecnico

- **Java 21**
- **Spring Boot** `4.1.1`
- **Maven**
- `RestClient` (Spring) per le chiamate verso TMDB
- **Lombok**

---

## 📡 Endpoint esposti

Tutti gli endpoint sono esposti sotto il path `/api` e accettano esclusivamente richieste **GET**. Fungono da wrapper verso i corrispondenti endpoint dell'[API TMDB](https://developer.themoviedb.org/reference/intro/getting-started).

| Metodo | Endpoint | Parametri | Descrizione |
|---|---|---|---|
| GET | `/api/{media}/{category}` | `media` (es. `movie`, `tv`), `category` (es. `popular`, `top_rated`, `now_playing`) | Restituisce la lista principale di titoli per una determinata categoria |
| GET | `/api/multi?query={query}` | `query` — testo di ricerca | Esegue una ricerca multi-tipo (film, serie TV, persone) |
| GET | `/api/{media}/images/{uniqueId}` | `media`, `uniqueId` — id del titolo | Restituisce le immagini (loghi, poster, backdrop) associate al titolo |
| GET | `/api/{media}?mediaId={mediaId}` | `media`, `mediaId` — id del titolo | Restituisce i dettagli completi di un titolo (trama, anno, durata, generi, voto, ecc.) |

Ogni richiesta viene inoltrata a TMDB con l'header `Authorization: Bearer <TMDB_BEARER_KEY>` e con lingua impostata su `en-US`.

---

## ⚙️ Configurazione

Il servizio richiede due parametri di configurazione, impostabili come variabili d'ambiente o nel file `application.properties`:

| Variabile | Descrizione | Obbligatoria |
|---|---|---|
| `TMDB_BEARER_KEY` | Bearer token (API Read Access Token) di TMDB, mappato sulla proprietà `tmdb.bearer.key` | Sì |
| `SERVER_PORT` | Porta su cui viene esposta l'applicazione (default Spring: `8080`) | No |

### Ottenere la chiave API di TMDB

La chiave utilizzata dal servizio è l'**API Read Access Token (v4 auth)** fornito da TMDB. Per ottenerla:

1. Creazione di un account su [themoviedb.org](https://www.themoviedb.org/signup)
2. Conferma dell'account tramite il link ricevuto via email
3. Accesso alla sezione **Impostazioni → API** dal proprio profilo ([themoviedb.org/settings/api](https://www.themoviedb.org/settings/api))
4. Richiesta di una nuova API key, selezionando l'opzione **Developer** e compilando il form richiesto (nome dell'applicazione, motivazione d'uso, ecc.)
5. Una volta approvata, copia del valore **API Read Access Token (v4 auth)** — non del "API Key (v3 auth)" — da utilizzare come `TMDB_BEARER_KEY`

---

## 🌍 CORS

La configurazione CORS (`Configurations.java`) è impostata per accettare richieste da **qualsiasi origine** (`allowedOrigins("*")`), limitando i metodi consentiti alle sole richieste `GET`. Questa scelta consente di utilizzare il servizio anche in ambienti di test o da front-end diversi da quello ufficiale.

---

## 🚀 Avvio in locale

Il progetto può essere avviato tramite un IDE con supporto Java/Maven (es. IntelliJ IDEA), eseguendo la classe main dell'applicazione, oppure da terminale con il wrapper Maven:

```bash
./mvnw spring-boot:run
```

Prima dell'avvio è necessario impostare la variabile d'ambiente `TMDB_BEARER_KEY` (o valorizzare `tmdb.bearer.key` in `application.properties`) con il token ottenuto da TMDB.

Una volta avviato, il servizio risulta disponibile all'indirizzo `http://localhost:<PORT>/api`.

---

## 🌐 Deploy

Il back-end è deployato su **Railway**, a supporto della demo live del front-end pubblicata su Vercel, che non fornisce un ambiente di esecuzione per applicazioni Java.

---

## 🔗 Repository correlate

- **Front-end (React, TypeScript, Vite):** [Netflix-Clone-Refactor-JS-to-TS](https://github.com/giannibussoletti/Netflix-Clone-Refactor-JS-to-TS)
