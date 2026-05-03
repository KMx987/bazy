package pl.upsanok.tablab1excercise.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import pl.upsanok.tablab1excercise.controllers.dto.Flower;
import pl.upsanok.tablab1excercise.entities.FlowerEntity;
import pl.upsanok.tablab1excercise.entities.GardenEntity;
import pl.upsanok.tablab1excercise.entities.GardenIdEmbedded;
import pl.upsanok.tablab1excercise.entities.UserEntity;
import pl.upsanok.tablab1excercise.repositories.FlowersRepository;
import pl.upsanok.tablab1excercise.repositories.UsersRepository;
import pl.upsanok.tablab1excercise.repositories.GardenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class FlowersService {

    @Autowired
    private GardenRepository gardenRepository;
    private final FlowersRepository flowersRepository;
    private final UsersRepository usersRepository;

    @Transactional(isolation = org.springframework.transaction.annotation.Isolation.REPEATABLE_READ)
    public List<Flower> getAllFlowers() {
        var allFlowers = flowersRepository.findAll().stream()
                .map(flowerEntity ->
                        Flower.builder().id(flowerEntity.getId()).name(flowerEntity.getName())
                                .build())
                .toList();
        log.info("All flowers size before: {}", allFlowers.size());
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        allFlowers = flowersRepository.findAll().stream()
                .map(flowerEntity ->
                        Flower.builder().id(flowerEntity.getId()).name(flowerEntity.getName())
                                .build())
                .toList();
        log.info("All flowers size after: {}", allFlowers.size());
        return allFlowers;
    }


    public Flower getFavouriteFlowerForUser(String userName) {
        UserEntity user = usersRepository.findAll().stream()
                .filter(u -> u.getName().equalsIgnoreCase(userName))
                .findFirst()
                .orElse(null);

        if (user == null || user.getFavouriteFlower() == null) {
            return Flower.builder().build();
        }

        FlowerEntity entity = user.getFavouriteFlower();
        return Flower.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public boolean saveFavouriteFlowerFor(String userName, String flowerName) {
        FlowerEntity flower = flowersRepository.findAll().stream()
                .filter(f -> f.getName().equalsIgnoreCase(flowerName))
                .findFirst()
                .orElse(null);

        if (flower == null) return false;

        UserEntity user = usersRepository.findAll().stream()
                .filter(u -> u.getName().equalsIgnoreCase(userName))
                .findFirst()
                .orElse(null);

        if (user != null) {
            user.setFavouriteFlower(flower);
        } else {
            user = UserEntity.builder()
                    .name(userName)
                    .favouriteFlower(flower)
                    .build();
        }

        usersRepository.save(user);
        return true;
    }

    public List<Flower> getFlowersInGardenFor(String userName) {
        List<GardenEntity> gardenEntries = gardenRepository.findAll().stream()
                .filter(g -> g.getUserEntity().getName().equalsIgnoreCase(userName))
                .toList();

        if (gardenEntries.isEmpty()) {
            return new ArrayList<>();
        }

        return gardenEntries.stream()
                .map(garden -> Flower.builder()
                        .id(garden.getFlowerEntity().getId())
                        .name(garden.getFlowerEntity().getName())
                        .build())
                .toList();
    }

    @Transactional
    public boolean saveFlowerInGardenForUser(String userName, String flowerName) {
        Optional<UserEntity> userOptional = usersRepository.findAll().stream()
                .filter(userEntity -> userEntity.getName().equals(userName))
                .findFirst();

        Optional<FlowerEntity> flowerOptional = flowersRepository.findAll().stream()
                .filter(flowerEntity -> flowerEntity.getName().equals(flowerName))
                .findFirst();

        if (userOptional.isEmpty()) {
            List<FlowerEntity> flowersInGarden = new ArrayList<>();
            flowersInGarden.add(flowerOptional.get());

            UserEntity userEntity = UserEntity.builder()
                    .name(userName)
                    .build();

            usersRepository.save(userEntity);
        }

        gardenRepository.save(
                GardenEntity.builder()
                        .gardenId(GardenIdEmbedded.builder()
                                .userId(userOptional.get().getId())
                                .flowerId(flowerOptional.get().getId())
                                .build())
                        .flowerEntity(flowerOptional.get())
                        .userEntity(userOptional.get())
                        .build());
        return true;
    }

    @Transactional
    public int saveNewFlower(String flowerName) {
        var result =
                flowersRepository.save(FlowerEntity.builder().name(flowerName)
                        .build()).getId();
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Flower saved with id: {}", result);
        return result;
    }

}