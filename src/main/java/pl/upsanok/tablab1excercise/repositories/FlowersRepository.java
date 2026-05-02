package pl.upsanok.tablab1excercise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.upsanok.tablab1excercise.entities.FlowerEntity;

import java.util.Optional;

@Repository
public interface FlowersRepository extends JpaRepository<FlowerEntity, Integer> {
    Optional<FlowerEntity> findByNameIgnoreCase(String name);
}