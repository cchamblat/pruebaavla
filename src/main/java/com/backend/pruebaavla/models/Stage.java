package com.backend.pruebaavla.models;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stage {
    private String stage;
    private long time;
}
