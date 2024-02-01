package com.example.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.entities.Empleado;
import com.example.services.EmpleadoService;

import lombok.RequiredArgsConstructor;

@Controller // sin esto no sabe que es un controlador
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final EmpleadoService empleadoService;

    // antiguamente se hacia esto:
    @GetMapping("/all")
    public ModelAndView dameEmpleados() {

        ModelAndView modelo = new ModelAndView("views/listadoEmpleados");

        List<Empleado> empleados = empleadoService.dameTodosLosEmpleados();

        modelo.addObject("empleados", empleados);

        return modelo;
    }

}
