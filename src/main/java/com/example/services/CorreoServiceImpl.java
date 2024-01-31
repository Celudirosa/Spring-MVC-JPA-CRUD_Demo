package com.example.services;

import java.util.List;

import com.example.dao.CorreoDao;
import com.example.dao.EmpleadoDao;
import com.example.entities.Correo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CorreoServiceImpl implements CorreoService {

    private final CorreoDao correoDao;
    private final EmpleadoDao empleadoDao;

    @Override
    public List<Correo> dameCorreos(int idEmpleado) {
        return correoDao.findByEmpleado(empleadoDao.findById(idEmpleado).get());
    }

    @Override
    public void eliminarCorreos(int idEmpleado) {
        correoDao.deletedByEmpleado(empleadoDao.findById(idEmpleado).get());    
    }

    @Override
    public void persistirCorreo(int idEmpleado, Correo correo) {
        correo.setEmpleado(empleadoDao.findById(idEmpleado).get());
        correoDao.save(correo);
    }

}
