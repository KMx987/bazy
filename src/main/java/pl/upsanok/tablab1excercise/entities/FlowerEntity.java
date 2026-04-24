package pl.upsanok.tablab1excercise.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "flowers")
public class FlowerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flowers_id")
    private Integer id;

    @Column(name = "flowers_name")
    private String name;

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
}