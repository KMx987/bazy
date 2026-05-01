package pl.upsanok.tablab1excercise.services;

import pl.upsanok.tablab1excercise.controllers.dto.Flower;
import pl.upsanok.tablab1excercise.entities.FlowerEntity;
import pl.upsanok.tablab1excercise.entities.UserEntity;
import pl.upsanok.tablab1excercise.repositories.FlowersRepository;
import pl.upsanok.tablab1excercise.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FlowersService {

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

        if (user == null || user.getFavouriteFlowerId() == null) {
            return Flower.builder().build();
        }

        return flowersRepository.findById(user.getFavouriteFlowerId())
                .map(entity -> Flower.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .build())
                .orElseGet(() -> Flower.builder().build());
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
            user.setFavouriteFlowerId(flower.getId());
        } else {
            user = new UserEntity();
            user.setName(userName);
            user.setFavouriteFlowerId(flower.getId());

            int nextId = (int) (usersRepository.count() + 1);
            user.setId(nextId);
        }

        usersRepository.save(user);
        return true;
    }
}