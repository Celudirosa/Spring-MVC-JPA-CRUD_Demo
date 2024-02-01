package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // sin esto no sabe que es un controlador
@RequestMapping("/")
public class MainController {

    @GetMapping("/all")
    public ModelAndView dameEmpleados() {

        ModelAndView modelo = new ModelAndView("views/listadoEmpleados");

        return modelo;
    }

}
