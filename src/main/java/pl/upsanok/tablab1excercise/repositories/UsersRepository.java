package pl.upsanok.tablab1excercise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.upsanok.tablab1excercise.entities.UserEntity;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByNameIgnoreCase(String name);
}
