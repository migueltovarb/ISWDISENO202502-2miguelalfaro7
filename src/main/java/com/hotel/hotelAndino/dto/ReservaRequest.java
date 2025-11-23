package com.hotel.hotelAndino.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservaRequest {
    private String usuarioId;
    private String habitacionId;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private Integer huespedes;
}
