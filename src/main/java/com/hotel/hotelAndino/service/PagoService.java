package com.hotel.hotelAndino.service;

import com.hotel.hotelAndino.model.Pago;

import java.math.BigDecimal;
import java.util.List;

public interface PagoService {

    Pago procesarPago(String reservaId, BigDecimal monto);

    List<Pago> listarPagosReserva(String reservaId);
}
