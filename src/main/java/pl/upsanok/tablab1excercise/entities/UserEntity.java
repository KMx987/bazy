package pl.upsanok.tablab1excercise.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "users_id")
    private Integer id;

    @Column(name = "users_name")
    private String name;

    @Column(name = "users_favourite_flower_id")
    private Integer favouriteFlowerId;

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

    public Integer getFavouriteFlowerId() {
        return favouriteFlowerId;
    }

    public void setFavouriteFlowerId(Integer favouriteFlowerId) {
        this.favouriteFlowerId = favouriteFlowerId;
    }
}
