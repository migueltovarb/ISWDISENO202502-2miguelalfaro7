package com.hotel.hotelAndino.service;

import com.hotel.hotelAndino.model.Habitacion;

import java.time.LocalDate;
import java.util.List;

public interface HabitacionService {

    Habitacion crear(Habitacion habitacion);

    List<Habitacion> listarTodas();

    List<Habitacion> buscarDisponibles(LocalDate entrada, LocalDate salida, int huespedes);
}
