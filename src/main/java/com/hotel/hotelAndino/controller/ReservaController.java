package com.hotel.hotelAndino.controller;

import com.hotel.hotelAndino.dto.ReservaRequest;
import com.hotel.hotelAndino.model.Reserva;
import com.hotel.hotelAndino.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @PostMapping
    public Reserva crear(@RequestBody ReservaRequest request) {
        return reservaService.crearReserva(request);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Reserva> listarPorUsuario(@PathVariable String usuarioId) {
        return reservaService.listarReservasUsuario(usuarioId);
    }

    @DeleteMapping("/{id}")
    public void cancelar(@PathVariable String id) {
        reservaService.cancelarReserva(id);
    }
}
