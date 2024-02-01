package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.services.EmpleadoService;

import lombok.RequiredArgsConstructor;

@Controller // sin esto no sabe que es un controlador
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final EmpleadoService empleadoService;

    // // antiguamente se hacia esto:
    // @GetMapping("/all")
    // public ModelAndView dameEmpleados() {
    //     ModelAndView modelo = new ModelAndView("views/listadoEmpleados");
    //     List<Empleado> empleados = empleadoService.dameTodosLosEmpleados();
    //     modelo.addObject("empleados", empleados);
    //     return modelo;
    // }

    // acualmente:
    @GetMapping("/all")
    public String dameEmpleados(Model model) {

        model.addAttribute("empleados", empleadoService.dameTodosLosEmpleados());

        return "views/listadoEmpleados";
    }

    @GetMapping("/detalles")
    public String detallesEmpleado(int idEmpleado, Model model) {

        return "views/empleadoDetalles";
    }

}
