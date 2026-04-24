package pl.upsanok.tablab1excercise.controllers.dto;

import lombok.Builder;

@Builder
public record Flower(

    Integer id,
    String name
) {
}
