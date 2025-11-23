package com.hotel.hotelAndino.repository;

import com.hotel.hotelAndino.model.EstadoHabitacion;
import com.hotel.hotelAndino.model.Habitacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface HabitacionRepository extends MongoRepository<Habitacion, String> {

    List<Habitacion> findByEstado(EstadoHabitacion estado);

    @Query("{ 'capacidad' : { $gte: ?0 }, 'estado' : ?1 }")
    List<Habitacion> buscarPorCapacidadYEstado(int capacidadMinima, EstadoHabitacion estado);
}
