package com.hotel.hotelAndino.service.impl;

import com.hotel.hotelAndino.model.EstadoHabitacion;
import com.hotel.hotelAndino.model.Habitacion;
import com.hotel.hotelAndino.repository.HabitacionRepository;
import com.hotel.hotelAndino.repository.ReservaRepository;
import com.hotel.hotelAndino.service.HabitacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HabitacionServiceImpl implements HabitacionService {

    private final HabitacionRepository habitacionRepository;
    private final ReservaRepository reservaRepository;

    @Override
    public Habitacion crear(Habitacion habitacion) {
        if (habitacion.getEstado() == null) {
            habitacion.setEstado(EstadoHabitacion.DISPONIBLE);
        }
        return habitacionRepository.save(habitacion);
    }

    @Override
    public List<Habitacion> listarTodas() {
        return habitacionRepository.findAll();
    }

    @Override
    public List<Habitacion> buscarDisponibles(LocalDate entrada, LocalDate salida, int huespedes) {
        List<Habitacion> candidatas = habitacionRepository.buscarPorCapacidadYEstado(
                huespedes, EstadoHabitacion.DISPONIBLE);

        return candidatas.stream()
                .filter(h -> reservaRepository
                        .findByHabitacionIdAndFechaEntradaLessThanEqualAndFechaSalidaGreaterThanEqual(
                                h.getId(), salida, entrada)
                        .isEmpty())
                .collect(Collectors.toList());
    }
}
