package com.hotel.hotelAndino.service;

import com.hotel.hotelAndino.dto.ReservaRequest;
import com.hotel.hotelAndino.model.Reserva;

import java.util.List;

public interface ReservaService {

    Reserva crearReserva(ReservaRequest request);

    List<Reserva> listarReservasUsuario(String usuarioId);

    void cancelarReserva(String id);
}
