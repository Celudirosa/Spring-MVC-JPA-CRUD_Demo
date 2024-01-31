package com.example.services;

import java.util.List;

import com.example.dao.EmpleadoDao;
import com.example.dao.TelefonoDao;
import com.example.entities.Telefono;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TelefonoServiceImpl implements TelefonoService {

    private final TelefonoDao telefonoDao;
    private final EmpleadoDao empleadoDao;

    @Override
    public List<Telefono> dameTelefonos(int idEmpleado) {
        return telefonoDao.findByEmpleado(empleadoDao.findById(idEmpleado).get());
    }

    @Override
    public void eliminarTelefonos(int idEmpleado) {
        telefonoDao.deletedByEmpleado(empleadoDao.findById(idEmpleado).get());
    }

    @Override
    public void persistirTelefono(int idEmpleado, Telefono telefono) {
        telefono.setEmpleado(empleadoDao.findById(idEmpleado).get());
        telefonoDao.save(telefono);
    }

}
