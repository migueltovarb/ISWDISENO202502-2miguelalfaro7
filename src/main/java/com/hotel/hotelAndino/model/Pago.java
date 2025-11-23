package com.hotel.hotelAndino.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "pagos")
public class Pago {

    @Id
    private String id;

    private String reservaId;

    private BigDecimal monto;

    private MetodoPago metodo;

    private LocalDateTime fecha;

    private boolean reembolsado;
}
