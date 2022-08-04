package com.backend.pruebaavla.models;

import lombok.*;

import java.util.Date;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Deal {
    private String dealId;
    private String userId;
    private String userName;
    private String fieldKey;
    private String oldValue;
    private String newValue;
    private String logDate;
    private String logTime;
    private String changeSource;

}
