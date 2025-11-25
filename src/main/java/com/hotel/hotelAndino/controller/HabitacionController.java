package com.hotel.hotelAndino.controller;

import com.hotel.hotelAndino.model.Habitacion;
import com.hotel.hotelAndino.repository.HabitacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/habitaciones")
@RequiredArgsConstructor
public class HabitacionController {

    private final HabitacionRepository habitacionRepository;

    // Crear habitación
    @PostMapping
    public Habitacion crear(@RequestBody Habitacion habitacion) {
        habitacion.setId(null); // para que Mongo genere el id
        return habitacionRepository.save(habitacion);
    }

    // Listar TODAS las habitaciones (por ahora sin filtros)
    @GetMapping
    public List<Habitacion> listar() {
        return habitacionRepository.findAll();
    }

    // Obtener por id
    @GetMapping("/{id}")
    public Habitacion obtenerPorId(@PathVariable String id) {
        return habitacionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Habitación no encontrada"));
    }

    // Actualizar
    @PutMapping("/{id}")
    public Habitacion actualizar(@PathVariable String id,
                                 @RequestBody Habitacion habitacion) {
        Habitacion existente = obtenerPorId(id);
        habitacion.setId(existente.getId());
        return habitacionRepository.save(habitacion);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable String id) {
        habitacionRepository.deleteById(id);
    }
}
