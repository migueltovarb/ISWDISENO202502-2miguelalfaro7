package com.hotel.hotelAndino.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "habitaciones")
public class Habitacion {

    @Id
    private String id;

    private String numero;

    private String tipo; // individual, doble, suite

    private Integer piso;

    private Integer capacidad;

    private String descripcion;

    private List<String> amenidades;

    private BigDecimal precioPorNoche;

    private EstadoHabitacion estado;
}
