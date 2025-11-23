package com.hotel.hotelAndino.service.impl;

import com.hotel.hotelAndino.model.Pago;
import com.hotel.hotelAndino.repository.PagoRepository;
import com.hotel.hotelAndino.service.PagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;

    @Override
    public Pago procesarPago(String reservaId, BigDecimal monto) {
        Pago pago = Pago.builder()
                .reservaId(reservaId)
                .monto(monto)
                .fecha(LocalDateTime.now())
                .reembolsado(false)
                .build();
        return pagoRepository.save(pago);
    }

    @Override
    public List<Pago> listarPagosReserva(String reservaId) {
        return pagoRepository.findByReservaId(reservaId);
    }
}
