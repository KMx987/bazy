package pl.upsanok.tablab1excercise.entities;

import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@Embeddable
public class GardenIdEmbedded implements Serializable {
    private int flowerId;
    private int userId;
}