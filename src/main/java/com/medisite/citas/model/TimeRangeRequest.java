package com.medisite.citas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
public class TimeRangeRequest {
    private LocalDateTime tiempoInicio;

    private LocalDateTime tiempoFin;
}
