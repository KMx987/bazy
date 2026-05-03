package pl.upsanok.tablab1excercise.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.upsanok.tablab1excercise.controllers.dto.Flower;
import pl.upsanok.tablab1excercise.services.FlowersService;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class FlowersImprovedController {

    private final FlowersService flowersService;

    public FlowersImprovedController(FlowersService flowersService) {
        this.flowersService = flowersService;
    }

    @GetMapping("/flowers")
    public ResponseEntity<List<Flower>> getName() {
        return ResponseEntity.ok(flowersService.getAllFlowers());
    }

    @GetMapping("/flowers/fav/users/{userName}")
    public ResponseEntity<Flower> getFavForUser(@PathVariable String userName) {
        var result = flowersService.getFavouriteFlowerForUser(userName);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/flowers/fav/users/{userName}")
    public ResponseEntity<Flower> setNewFavForUser(
            @PathVariable String userName,
            @RequestBody Flower flower
    ) {
        flowersService.saveFavouriteFlowerFor(userName, flower.name());
        return ResponseEntity.ok(flower);
    }

    //@GetMapping("/users/{userName}/garden")
    public ResponseEntity<List<Flower>> getGardenForUser(@PathVariable String userName) {
        return ResponseEntity.ok(List.of());
    }

    //@PostMapping("/users/{userName}/garden")
    public ResponseEntity<Flower> plantInGarden(@PathVariable String userName, @RequestBody Flower flower) {
        return ResponseEntity.ok(flower);
    }

    @GetMapping("/flowers/{userName}")
    public ResponseEntity<Flower> getSingleFavFlower(@PathVariable String userName) {
        var result = flowersService.getFavouriteFlowerForUser(userName);
        return ResponseEntity.ok(result);
    }

    @PostMapping("flowers/{newFlower}")
    public ResponseEntity<Integer> saveNewFlower(@PathVariable String newFlower) {
        int id = flowersService.saveNewFlower(newFlower);
        return ResponseEntity.ok(id);
    }
}