package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.DepartamentoDao;
import com.example.entities.Departamento;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartamentoServiceImpl implements DepartamentoService {

    // inyeccion de dependencia(DI) por constructor
    // la mas eficiente y mas usada es esta antes que el autowired
    // antiguamente para inyectar una dependencia por constructor

    // primero. declarabas la variable del tipo del objeto

    // segundo. creabas el constructor y le pasabas el objeto

    // pero actualmente con LOMBOK lo anterior no es necesario,
    // se facilita solamente declarando la variable de objeto y especificando el modificador
    // final y utilizando la anotacion de lombok @RequiredArgsConstructor

    // private DepartamentoDao departamentoDao;
    // public DepartamentoServiceImpl(DepartamentoDao departamentoDao) {
    //     this.departamentoDao = departamentoDao;
    // }

    // actualmente:
    private final DepartamentoDao departamentoDao;
    // la inyeccion de dependencia por constructor se realiza de la forma anterior
    // y con la anotacion de lombok correspondiente

    @Override
    public List<Departamento> dameDepartamentos() {
        return departamentoDao.findAll();
    }

    @Override
    public Departamento dameUnDepartamento(int idDepartamento) {
        return departamentoDao.findById(idDepartamento).get();
    }

    @Override
    public void persistirDpto(Departamento departamento) {
        departamentoDao.save(departamento);
    }

}
