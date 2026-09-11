package gb.netflix_clone_tmdb_api.controllers;


import gb.netflix_clone_tmdb_api.services.TMdbApiService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.JsonNode;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class TMdbController {

    private TMdbApiService tmdb;


    @GetMapping("/{media}/{category}")
    public JsonNode getPopularMovie(@PathVariable String media, @PathVariable String category) {
        return this.tmdb.getMainResponse(media, category);
    }

    @GetMapping("/multi/{stringQuery}")
    public JsonNode getMultiSearch(@PathVariable String stringQuery) {
        return this.tmdb.getMultiResponse(stringQuery);
    }
}
