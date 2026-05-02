package pl.upsanok.tablab1excercise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.upsanok.tablab1excercise.entities.GardenEntity;
import pl.upsanok.tablab1excercise.entities.GardenId;

@Repository
public interface GardenRepository extends JpaRepository<GardenEntity, GardenId> {
}