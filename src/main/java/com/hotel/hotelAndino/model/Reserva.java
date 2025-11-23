package com.hotel.hotelAndino.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "reservas")
public class Reserva {

    @Id
    private String id;

    private String codigo;

    private String usuarioId;

    private String habitacionId;

    private LocalDate fechaEntrada;

    private LocalDate fechaSalida;

    private Integer huespedes;

    private BigDecimal total;

    private EstadoReserva estado;

private LocalDateTime horaCheckIn;

private LocalDateTime horaCheckOut;

}
