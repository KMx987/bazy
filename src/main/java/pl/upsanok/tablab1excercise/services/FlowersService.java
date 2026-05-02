package pl.upsanok.tablab1excercise.services;

import org.springframework.beans.factory.annotation.Autowired;
import pl.upsanok.tablab1excercise.controllers.dto.Flower;
import pl.upsanok.tablab1excercise.entities.FlowerEntity;
import pl.upsanok.tablab1excercise.entities.GardenEntity;
import pl.upsanok.tablab1excercise.entities.UserEntity;
import pl.upsanok.tablab1excercise.repositories.FlowersRepository;
import pl.upsanok.tablab1excercise.repositories.GardenRepository;
import pl.upsanok.tablab1excercise.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FlowersService {
    @Autowired
    private GardenRepository gardenRepository;
    private final FlowersRepository flowersRepository;
    private final UsersRepository usersRepository;

    public List<Flower> getAllFlowers() {
        List<FlowerEntity> entities = flowersRepository.findAll();
        List<Flower> result = new ArrayList<>();

        for (FlowerEntity entity : entities) {
            result.add(Flower.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .build());
        }
        return result;
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
            user = new UserEntity();
            user.setName(userName);
            user.setFavouriteFlower(flower);

            int nextId = (int) (usersRepository.count() + 1);
            user.setId(nextId);
        }

        usersRepository.save(user);
        return true;
    }

    public List<Flower> getFlowersInGardenFor(String userName) {
        List<GardenEntity> allGardensAllUsers = gardenRepository.findAll();

        return allGardensAllUsers.stream()
                .filter(gardenEntity -> gardenEntity.getUserEntity().getName().equalsIgnoreCase(userName))
                .map(garden -> garden.getFlowerEntity())
                .map(flowerEntity -> Flower.builder()
                        .id(flowerEntity.getId())
                        .name(flowerEntity.getName())
                        .build())
                .toList();
    }

    @Transactional
    public boolean saveFlowerInGardenForUser(String userName, String flowerName) {
        Optional<UserEntity> userOptional = usersRepository.findAll().stream()
                .filter(userEntity -> userEntity.getName().equalsIgnoreCase(userName))
                .findFirst();

        if (userOptional.isEmpty()) {
            UserEntity userEntity = UserEntity.builder()
                    .name(userName)
                    .build();

            int nextId = (int) (usersRepository.count() + 1);
            userEntity.setId(nextId);

            UserEntity savedUser = usersRepository.save(userEntity);
            userOptional = Optional.of(savedUser);
        }

        Optional<FlowerEntity> flowerOptional = flowersRepository.findAll().stream()
                .filter(flowerEntity -> flowerEntity.getName().equalsIgnoreCase(flowerName))
                .findFirst();

        if (flowerOptional.isPresent() && userOptional.isPresent()) {
            GardenEntity gardenEntry = GardenEntity.builder()
                    .flowerEntity(flowerOptional.get())
                    .userEntity(userOptional.get())
                    .build();

            gardenRepository.save(gardenEntry);
            return true;
        }

        throw new IllegalArgumentException("Flower or user not found");
    }


}