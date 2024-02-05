package com.example.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entities.Correo;
import com.example.entities.Departamento;
import com.example.entities.Empleado;
import com.example.entities.Telefono;
import com.example.services.DepartamentoService;
import com.example.services.EmpleadoService;

import lombok.RequiredArgsConstructor;

@Controller // sin esto no sabe que es un controlador
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final EmpleadoService empleadoService;
    private final DepartamentoService departamentoService;

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

        // le paso al modelo un objeto empleado vacio
        Empleado empleado = new Empleado();
        
        model.addAttribute("empleado", empleado);

        // tambien los departamentos
        model.addAttribute("departamentos", 
            departamentoService.dameDepartamentos());
        
        return "views/frmAltaModificacionEmpleado";
    }

    @PostMapping("/persistir")
    public String persistirEmpleado(
            @ModelAttribute(name = "empleado") Empleado empleado,
            @RequestParam(name = "numerosTel", required = false) String telefonosRecibidos,
            @RequestParam(name = "direccionesCorreo", required = false) String correosRecibidos) {

        // procesar los telefonos
        if (telefonosRecibidos != null) {
            String[] arrayTelefonos = telefonosRecibidos.split(";");
            List<String> numerosTelefonos = Arrays.asList(arrayTelefonos);

            List<Telefono> telefonos = new ArrayList<>();
            
            numerosTelefonos.stream()
                .forEach(numeroTelefono -> {
                    telefonos.add(Telefono.builder()
                    .telefono(numeroTelefono)
                    .empleado(empleado)
                    .build());
                });

            empleado.setTelefonos(telefonos);
        }

        // procesar los correos
        if (correosRecibidos != null) {
            String[] arrayCorreos = correosRecibidos.split(";");
            List<String> direccionesCorreos = Arrays.asList(arrayCorreos);

            List<Correo> correos = new ArrayList<>();
            
            direccionesCorreos.stream()
                .forEach(direccionCorreo -> {
                    correos.add(Correo.builder()
                    .correo(direccionCorreo)
                    .empleado(empleado)
                    .build());
                });
            
            empleado.setCorreos(correos);
        }

        empleadoService.persistirEmpleado(empleado);

        return "redirect:/all";
    }

    @GetMapping("/actualizar/{id}")
    public String actualizarEmpleado(@PathVariable(name = "id", required = true) 
        int idEmpleado, Model model) {

        // recuperar el empleado por el id para cambiar solo ese empleado
        Empleado empleado = empleadoService.dameUnEmpleado(idEmpleado);
        // ya vienen los correos y telefonos porque es bidireccional, por el cascadeo
        model.addAttribute("empleado", empleado);

        // recuperar los departamentos
        List<Departamento> departamentos = departamentoService.dameDepartamentos();
        model.addAttribute("departamentos", departamentos);

        // construir los numeros de telefono a partir de los telefonos recibidos conjuntamente con el empleado
        if (empleado.getTelefonos() != null) {
            String numerosTelefono = empleado.getTelefonos().stream()
                .map(Telefono::getTelefono)
                .collect(Collectors.joining(";"));

            model.addAttribute("numerosTelefono", numerosTelefono);
        }
        // construir los correos igual que los telefonos
        if (empleado.getCorreos() != null) {
            String direccionesCorreos = empleado.getCorreos().stream()
                .map(Correo::getCorreo)
                .collect(Collectors.joining(";"));

            model.addAttribute("direccionesCorreos", direccionesCorreos);
        }

        return "";
    }

}
