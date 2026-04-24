package pl.upsanok.tablab1excercise.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.upsanok.tablab1excercise.controllers.dto.Flower;
import pl.upsanok.tablab1excercise.services.FlowersService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class FlowersImprovedController {

    @Autowired
    private final FlowersService flowersService;

    @GetMapping("/flowers")
    public ResponseEntity<List<Flower>> getName() {
        var flowers = flowersService.getAllFlowers();
        return ResponseEntity.ok(flowers);
    }


    @GetMapping({"/users/{userName}/garden", "/flowers/fav/users/{userName}"})
    public ResponseEntity<List<Flower>> getFavForUser(
            @PathVariable String userName
    ) {
        var result = flowersService.getFavouriteFlowerForUser(userName);

        if (result == null || result.name() == null) {
            return ResponseEntity.ok(List.of());
        }

        return ResponseEntity.ok(List.of(result));
    }

    @PostMapping({"/users/{userName}/garden", "/flowers/fav/users/{userName}"})
    public ResponseEntity<Flower> setNewFavForUser(
            @PathVariable String userName,
            @RequestBody Flower flower
    ) {
        flowersService.saveFavouriteFlowerFor(userName, flower.name());
        return ResponseEntity.ok(flower);

    }

    @GetMapping("/flowers/{userName}")
    public ResponseEntity<Flower> getSingleFavFlower(@PathVariable String userName) {
        var result = flowersService.getFavouriteFlowerForUser(userName);
        return ResponseEntity.ok(result);
    }
}

