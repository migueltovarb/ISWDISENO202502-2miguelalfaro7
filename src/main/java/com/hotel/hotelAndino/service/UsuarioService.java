package com.hotel.hotelAndino.service;

import com.hotel.hotelAndino.model.Usuario;

public interface UsuarioService {

    Usuario registrar(Usuario usuario);

    Usuario login(String email, String password);
}
