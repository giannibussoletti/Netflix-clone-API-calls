package gb.netflix_clone_tmdb_api.controllers;


import gb.netflix_clone_tmdb_api.services.TMdbApiService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/multi")
    public JsonNode getMultiSearch(@RequestParam("query") String stringQuery) {
        return this.tmdb.getMultiResponse(stringQuery);
    }
}
