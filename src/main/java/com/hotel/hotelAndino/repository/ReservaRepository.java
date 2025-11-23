package com.hotel.hotelAndino.repository;

import com.hotel.hotelAndino.model.EstadoReserva;
import com.hotel.hotelAndino.model.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends MongoRepository<Reserva, String> {

    List<Reserva> findByUsuarioId(String usuarioId);

    List<Reserva> findByEstado(EstadoReserva estado);

    List<Reserva> findByHabitacionIdAndFechaEntradaLessThanEqualAndFechaSalidaGreaterThanEqual(
            String habitacionId,
            LocalDate fechaSalida,
            LocalDate fechaEntrada
    );
}
