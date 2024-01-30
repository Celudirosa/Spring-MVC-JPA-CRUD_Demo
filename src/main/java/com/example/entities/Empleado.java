package com.example.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

// para que la clase se convierta en una entidad
@Entity
// para que te crea una tabla con otro nombre, si no te crearia una con el nombre de la clase en minúsculas
@Table(name = "empleados")
@Data
@Builder
// cuando serializas algo, hay que convertir de objeto a flujo, hace falta un numero de version para que cuando
// se haga el proceso contrario se pueda usar ese numero de serie
public class Empleado implements Serializable {

    //para que se deserialice
    private static final long serialVersionUID = 1L;
    // primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // esto genera que sea autoincremental
    private int id;

    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaAlta;
    private double salario;

    @Enumerated(EnumType.STRING) // con esto ya te saca el nombre
    private Genero genero;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Departamento departamento;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "departamento")
    private List<Correo> correos;

}
