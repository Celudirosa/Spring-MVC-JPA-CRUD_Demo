package com.example.services;

import java.util.List;

import com.example.entities.Telefono;

public interface TelefonoService {

    public List<Telefono> dameTelefonos(int idEmpleado);
    public void eliminarTelefonos(int idEmpleado);
    public void persistirTelefono(int idEmpleado, Telefono telefono);

}
