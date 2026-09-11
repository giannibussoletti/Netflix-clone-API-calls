package gb.netflix_clone_tmdb_api.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.JsonNode;

@Service
public class TMdbApiService {

    private final RestClient restClient;
    private final String apiKey;
    private final String baseUrl;

    private TMdbApiService(RestClient.Builder builder, @Value("${tmdb.bearer.key}") String apiKey) {

        this.apiKey = apiKey;
        this.baseUrl = "https://api.themoviedb.org/3";
        this.restClient = builder.baseUrl(baseUrl).build();

    }

    public JsonNode getMainResponse(String media, String category) {

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/{media}/{category}")
                        .queryParam("language", "en-US")
                        .queryParam("page", 1)
                        .build(media, category)
                ).header("Authorization", "Bearer " + apiKey)
                .retrieve()
                .body(JsonNode.class);
    }

    public JsonNode getMultiResponse(String searchQuery) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/multi")
                        .queryParam("query", searchQuery)
                        .queryParam("include_adult", "false")
                        .queryParam("language", "en-US")
                        .queryParam("page", 1).build())
                .header("Authorization", "Bearer " + apiKey)
                .retrieve().body(JsonNode.class);

    }

}
