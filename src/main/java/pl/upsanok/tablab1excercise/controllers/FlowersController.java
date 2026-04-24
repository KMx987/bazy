package pl.upsanok.tablab1excercise.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.upsanok.tablab1excercise.controllers.dto.Flower;

//@RestController()
@CrossOrigin(origins = {"http://localhost:3000", "https://tab-front-production.up.railway.app"})
public class FlowersController {

  private String favFlower = "Narcyz";

  @GetMapping("flowers")
  public ResponseEntity<List<Flower>> getName() {
    return ResponseEntity.ok(
        List.of(
            Flower.builder()
                .name("Narcyz")
                .build(),
            Flower.builder()
                .name("Roza")
                .build(),
            Flower.builder()
                .name("Hiacynt")
                .build(),
            Flower.builder()
                .name("Przebisnieg")
                .build(),Flower.builder()
                .name("Tulipan")
                .build()
        ));
  }

  @GetMapping("flowers/fav")
  public ResponseEntity<Flower> getFav() {
    return ResponseEntity.ok(
            Flower.builder()
                .name(this.favFlower)
                .build());
  }

  @PostMapping("flowers/fav")
  public ResponseEntity<Flower> setNewFav(
      @RequestBody Flower flower
  ) {
    this.favFlower = flower.name();
    return ResponseEntity.ok(
        Flower.builder()
            .name(this.favFlower)
            .build()
    );
  }
}
