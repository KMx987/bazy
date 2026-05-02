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
//@IdClass(GardenId.class) // opcja 1 - IdClass
public class GardenEntity {

    @EmbeddedId // opcja 2 - EmbeddedId
    private GardenIdEmbedded gardenId;

    //@Id
    @ManyToOne
    @MapsId("flowerId")
    @JoinColumn(name = "flower_id")
    private FlowerEntity flowerEntity;

    //@Id
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}
