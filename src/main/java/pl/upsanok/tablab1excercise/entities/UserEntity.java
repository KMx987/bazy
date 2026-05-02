package pl.upsanok.tablab1excercise.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserEntity {

    @Id
    @Column(name = "users_id")
    private Integer id;

    @Column(name = "users_name")
    private String name;

    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @JoinColumn(name = "users_favourite_flower_id")
    private FlowerEntity favouriteFlower;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FlowerEntity getFavouriteFlower() {
        return favouriteFlower;
    }

    public void setFavouriteFlower(FlowerEntity favouriteFlower) {
        this.favouriteFlower = favouriteFlower;
    }
}