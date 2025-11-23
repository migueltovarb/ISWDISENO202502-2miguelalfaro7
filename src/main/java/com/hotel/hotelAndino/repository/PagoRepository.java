package com.hotel.hotelAndino.repository;

import com.hotel.hotelAndino.model.Pago;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PagoRepository extends MongoRepository<Pago, String> {

    List<Pago> findByReservaId(String reservaId);
}
