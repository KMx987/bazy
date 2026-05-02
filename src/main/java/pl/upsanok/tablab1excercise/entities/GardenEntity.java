package pl.upsanok.tablab1excercise.entities;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "garden")
@Entity(name = "garden")
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Builder
@IdClass(GardenId.class)
public class GardenEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "flower_id")
    private FlowerEntity flowerEntity;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

}
