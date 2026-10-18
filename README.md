**English** | [Italiano](README.it.md)

# 🎬 Netflix Clone TMDB API

REST proxy built with **Spring Boot** for the [Netflix Clone](https://github.com/giannibussoletti/Netflix-Clone-Refactor-JS-to-TS) project. The service forwards requests to **The Movie Database (TMDB)** APIs, so the API key is never exposed on the client side in the front-end.

**Front-end repository:** [Netflix-Clone-Refactor-JS-to-TS](https://github.com/giannibussoletti/Netflix-Clone-Refactor-JS-to-TS)

---

## 🛠️ Tech Stack

- **Java 21**
- **Spring Boot** `4.1.1`
- **Maven**
- Spring's `RestClient` for calls to TMDB
- **Lombok**

---

## 📡 Exposed Endpoints

All endpoints are exposed under the `/api` path and accept **GET** requests only. They act as wrappers around the corresponding endpoints of the [TMDB API](https://developer.themoviedb.org/reference/intro/getting-started).

| Method | Endpoint | Parameters | Description |
|---|---|---|---|
| GET | `/api/{media}/{category}` | `media` (e.g. `movie`, `tv`), `category` (e.g. `popular`, `top_rated`, `now_playing`) | Returns the main list of titles for a given category |
| GET | `/api/multi?query={query}` | `query` — search text | Runs a multi-type search (movies, TV series, people) |
| GET | `/api/{media}/images/{uniqueId}` | `media`, `uniqueId` — title id | Returns the images (logos, posters, backdrops) associated with the title |
| GET | `/api/{media}?mediaId={mediaId}` | `media`, `mediaId` — title id | Returns the full details of a title (plot, year, runtime, genres, rating, etc.) |

Each request is forwarded to TMDB with the `Authorization: Bearer <BEARER_KEY>` header and the language set to `en-US`.

---

## ⚙️ Configuration

The service requires two configuration parameters, which can be set as environment variables or in the `application.properties` file:

| Variable | Description | Required |
|---|---|---|
| `BEARER_KEY` | TMDB Bearer token (API Read Access Token), mapped to the `tmdb.bearer.key` property | Yes |
| `PORT` | Port the application is exposed on (Spring default: `8080`) | No |

### Getting the TMDB API Key

The key used by the service is the **API Read Access Token** provided by TMDB. To get it:

1. Create an account on [themoviedb.org](https://www.themoviedb.org/signup)
2. Confirm the account through the link received by email
3. Go to **Settings → API** from your profile ([themoviedb.org/settings/api](https://www.themoviedb.org/settings/api))
4. Request a new API key, selecting the **Developer** option and filling in the required form (application name, intended use, etc.)
5. Once approved, copy the **API Read Access Token** value — not the "API Key" — and use it as `BEARER_KEY`

---

## 🌍 CORS

The CORS configuration (`Configurations.java`) is set to accept requests from **any origin** (`allowedOrigins("*")`), limiting the allowed methods to `GET` requests only. This makes it possible to use the service in test environments or from front-ends other than the official one.

---

## 🚀 Running Locally

The project can be started from an IDE with Java/Maven support (e.g. IntelliJ IDEA) by running the application's main class, or from the terminal with the Maven wrapper:

```bash
./mvnw spring-boot:run
```

Before starting it, set the `BEARER_KEY` environment variable (or fill in `tmdb.bearer.key` in `application.properties`) with the token obtained from TMDB.

Once started, the service is available at `http://localhost:<PORT>/api`.

---

## 🌐 Deploy

The back-end is deployed on **Railway**, supporting the live demo of the front-end published on Vercel.

---

## 🔗 Related Repositories

- **Front-end (React, TypeScript, Vite):** [Netflix-Clone-Refactor-JS-to-TS](https://github.com/giannibussoletti/Netflix-Clone-Refactor-JS-to-TS)
