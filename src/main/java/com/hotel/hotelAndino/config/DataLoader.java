package com.hotel.hotelAndino.config;

import com.hotel.hotelAndino.model.EstadoHabitacion;
import com.hotel.hotelAndino.model.Habitacion;
import com.hotel.hotelAndino.repository.HabitacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final HabitacionRepository habitacionRepository;

    @Override
    public void run(String... args) {
        if (habitacionRepository.count() == 0) {
            Habitacion h1 = Habitacion.builder()
                    .numero("101")
                    .tipo("Individual")
                    .piso(1)
                    .capacidad(1)
                    .descripcion("Habitación individual con vista")
                    .amenidades(List.of("WiFi", "TV"))
                    .precioPorNoche(new BigDecimal("45.00"))
                    .estado(EstadoHabitacion.DISPONIBLE)
                    .build();

            Habitacion h2 = Habitacion.builder()
                    .numero("102")
                    .tipo("Doble")
                    .piso(1)
                    .capacidad(2)
                    .descripcion("Habitación doble estándar")
                    .amenidades(List.of("WiFi", "TV", "Minibar"))
                    .precioPorNoche(new BigDecimal("70.00"))
                    .estado(EstadoHabitacion.DISPONIBLE)
                    .build();

            habitacionRepository.saveAll(List.of(h1, h2));
        }
    }
}
