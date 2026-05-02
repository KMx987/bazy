package pl.upsanok.tablab1excercise.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.upsanok.tablab1excercise.controllers.dto.Flower;
import pl.upsanok.tablab1excercise.services.FlowersService;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://tab-front-production.up.railway.app"})
public class GardenController {

    @Autowired
    private FlowersService flowersService;

    @GetMapping("users/{userName}/garden")
    public ResponseEntity<List<Flower>> getGardenFlowersForUser(
            @PathVariable String userName
    ) {
        var result = flowersService.getFlowersInGardenFor(userName);
        return ResponseEntity.ok(result);
    }

    @PostMapping("users/{userName}/garden/flowers/{flowerName}")
    public ResponseEntity<Boolean> plantFlowerInUsersGarden(
            @PathVariable String userName,
            @PathVariable String flowerName
    ) {
        var result = flowersService.saveFlowerInGardenForUser(userName, flowerName);
        return ResponseEntity.ok(result);
    }
}