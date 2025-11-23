package com.hotel.hotelAndino.controller;

import com.hotel.hotelAndino.model.Habitacion;
import com.hotel.hotelAndino.service.HabitacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/habitaciones")
@RequiredArgsConstructor
public class HabitacionController {

    private final HabitacionService habitacionService;

    @PostMapping
    public Habitacion crear(@RequestBody Habitacion habitacion) {
        return habitacionService.crear(habitacion);
    }

    @GetMapping
    public List<Habitacion> listar() {
        return habitacionService.listarTodas();
    }

    @GetMapping("/disponibles")
    public List<Habitacion> buscarDisponibles(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate entrada,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate salida,
            @RequestParam int huespedes) {
        return habitacionService.buscarDisponibles(entrada, salida, huespedes);
    }
}
