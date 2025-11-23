package com.hotel.hotelAndino.controller;

import com.hotel.hotelAndino.model.Pago;
import com.hotel.hotelAndino.service.PagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @PostMapping
    public Pago pagar(@RequestParam String reservaId, @RequestParam BigDecimal monto) {
        return pagoService.procesarPago(reservaId, monto);
    }

    @GetMapping("/reserva/{reservaId}")
    public List<Pago> listarPagos(@PathVariable String reservaId) {
        return pagoService.listarPagosReserva(reservaId);
    }
}
