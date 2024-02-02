package com.example.controllers;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.services.EmpleadoService;

import lombok.RequiredArgsConstructor;

@Controller // sin esto no sabe que es un controlador
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final EmpleadoService empleadoService;

    private final Logger LOG = Logger.getLogger("MainController");

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
    
    // cuando se recibe un parametro con la request:
    // se utiliza tambien actualmente, pero menos que enviar una variable
    // en la ruta
    // @GetMapping("/detalles")
    // public String detallesEmpleado(@RequestParam(name = "id") int idEmpleado, Model model) {

    //     LOG.info("ID Empleado Recibido " + idEmpleado);
    //     return "views/empleadoDetalles";
    // }

    @GetMapping("/detalles/{id}")
    public String detallesEmpleado(@PathVariable(name = "id") int idEmpleado, Model model) {

        LOG.info("ID Empleado Recibido " + idEmpleado);

        model.addAttribute(
            "empleado", 
            empleadoService.dameUnEmpleado(idEmpleado));

        return "views/empleadoDetalles";
    }

    @GetMapping("/frmAltaModificacion")
    public String formularioAltaModificacion(Model model) {

   
        
        return "views/frmAltaModificacionEmpleado";
    }

}
