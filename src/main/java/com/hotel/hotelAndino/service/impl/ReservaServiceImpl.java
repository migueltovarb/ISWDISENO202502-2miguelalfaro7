package com.hotel.hotelAndino.service.impl;

import com.hotel.hotelAndino.dto.ReservaRequest;
import com.hotel.hotelAndino.model.EstadoHabitacion;
import com.hotel.hotelAndino.model.EstadoReserva;
import com.hotel.hotelAndino.model.Habitacion;
import com.hotel.hotelAndino.model.Reserva;
import com.hotel.hotelAndino.repository.HabitacionRepository;
import com.hotel.hotelAndino.repository.ReservaRepository;
import com.hotel.hotelAndino.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final HabitacionRepository habitacionRepository;

    @Override
    public Reserva crearReserva(ReservaRequest request) {
        Habitacion habitacion = habitacionRepository.findById(request.getHabitacionId())
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));

        // verificar disponibilidad por fechas
        List<Reserva> solapadas = reservaRepository
                .findByHabitacionIdAndFechaEntradaLessThanEqualAndFechaSalidaGreaterThanEqual(
                        habitacion.getId(), request.getFechaSalida(), request.getFechaEntrada());
        if (!solapadas.isEmpty()) {
            throw new RuntimeException("La habitación no está disponible para esas fechas");
        }

        long noches = request.getFechaSalida().toEpochDay() - request.getFechaEntrada().toEpochDay();
        if (noches <= 0) {
            throw new RuntimeException("Rango de fechas inválido");
        }

        BigDecimal total = habitacion.getPrecioPorNoche()
                .multiply(BigDecimal.valueOf(noches));

        Reserva reserva = Reserva.builder()
                .codigo(UUID.randomUUID().toString().substring(0, 8).toUpperCase())
                .usuarioId(request.getUsuarioId())
                .habitacionId(habitacion.getId())
                .fechaEntrada(request.getFechaEntrada())
                .fechaSalida(request.getFechaSalida())
                .huespedes(request.getHuespedes())
                .total(total)
                .estado(EstadoReserva.CONFIRMADA)
                .build();

        habitacion.setEstado(EstadoHabitacion.OCUPADA);
        habitacionRepository.save(habitacion);

        return reservaRepository.save(reserva);
    }

    @Override
    public List<Reserva> listarReservasUsuario(String usuarioId) {
        return reservaRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public void cancelarReserva(String id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        reserva.setEstado(EstadoReserva.CANCELADA);
        reservaRepository.save(reserva);

        habitacionRepository.findById(reserva.getHabitacionId()).ifPresent(h -> {
            h.setEstado(EstadoHabitacion.DISPONIBLE);
            habitacionRepository.save(h);
        });
    }
}
