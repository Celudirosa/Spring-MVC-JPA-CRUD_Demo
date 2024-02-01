package com.example;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entities.Correo;
import com.example.entities.Departamento;
import com.example.entities.Empleado;
import com.example.entities.Genero;
import com.example.entities.Telefono;
import com.example.services.CorreoService;
import com.example.services.DepartamentoService;
import com.example.services.EmpleadoService;
import com.example.services.TelefonoService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringMvcJpaCrudDemoApplication implements CommandLineRunner {

	private final DepartamentoService departamentoService;
	private final EmpleadoService empleadoService;
	private final TelefonoService telefonoService;
	private final CorreoService correoService;

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcJpaCrudDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// creamos departamentos
		Departamento dpto1 = Departamento.builder()
		.nombre("RRHH")
		.build();
		
		Departamento dpto2 = Departamento.builder()
		.nombre("INFORMATICA")
		.build();
		
		Departamento dpto3 = Departamento.builder()
		.nombre("CONTABILIDAD")
		.build();

		departamentoService.persistirDpto(dpto1);
		departamentoService.persistirDpto(dpto2);
		departamentoService.persistirDpto(dpto3);

		// ahora creamos los empleados
		Empleado emp1 = Empleado.builder()
			.nombre("Celia")
			.primerApellido("Luque")
			.segundoApellido("Díaz")
			.genero(Genero.MUJER)
			.fechaAlta(LocalDate.of(2000, Month.DECEMBER, 23))
			.salario(234567890)
			.departamento(departamentoService.dameUnDepartamento(1))
			.build();

		Empleado emp2 = Empleado.builder()
			.nombre("Isabel")
			.primerApellido("Álvarez")
			.segundoApellido("Díaz")
			.genero(Genero.MUJER)
			.fechaAlta(LocalDate.of(2000, Month.DECEMBER, 23))
			.salario(234567890)
			.departamento(departamentoService.dameUnDepartamento(2))
			.build();

		empleadoService.persistirEmpleado(emp1);
		empleadoService.persistirEmpleado(emp2);

		// creamos los telefonos
		// telefonos empleado 1
		// List<Telefono> telefonosEmpleado1 = new ArrayList<>();

		Telefono telefono1Empleado1 = Telefono.builder()
			.telefono("639147900")
			.empleado(empleadoService.dameUnEmpleado(1))
			.build();

		Telefono telefono2Empleado1 = Telefono.builder()
			.telefono("924678055")
			.empleado(empleadoService.dameUnEmpleado(1))
			.build();

		// telefonosEmpleado1.add(telefono1Empleado1);
		// telefonosEmpleado1.add(telefono2Empleado1);

		// telefonos empleado 2
		// List<Telefono> telefonosEmpleado2 = new ArrayList<>();

		Telefono telefono1Empleado2 = Telefono.builder()
			.telefono("639147900")
			.empleado(empleadoService.dameUnEmpleado(2))
			.build();

		Telefono telefono2Empleado2 = Telefono.builder()
			.telefono("924678055")
			.empleado(empleadoService.dameUnEmpleado(2))
			.build();

		// telefonosEmpleado2.add(telefono1Empleado2);
		// telefonosEmpleado2.add(telefono2Empleado2);
		
		telefonoService.persistirTelefono(1, telefono1Empleado1);
		telefonoService.persistirTelefono(1, telefono2Empleado1);
		telefonoService.persistirTelefono(2, telefono1Empleado2);
		telefonoService.persistirTelefono(2, telefono2Empleado2);

		// creamos los correos
		// correos empleado 1
		// List<Correo> correosEmpleado1 = new ArrayList<>();

		Correo correo1Empleado1 = Correo.builder()
			.correo("ujwjdijweil@krrliwreiwer.com")
			.build();

		Correo correo2Empleado1 = Correo.builder()
			.correo("erasdc@casdecszdc.com")
			.build();

		// correosEmpleado1.add(correo1Empleado1);
		// correosEmpleado1.add(correo2Empleado1);

		// correos empleado 2
		// List<Correo> correosEmpleado2 = new ArrayList<>();

		Correo correo1Empleado2 = Correo.builder()
			.correo("ujwjdijweil@krrliwreiwer.com")
			.build();

		// correosEmpleado2.add(correo1Empleado2);

		correoService.persistirCorreo(1, correo1Empleado1);
		correoService.persistirCorreo(1, correo2Empleado1);
		correoService.persistirCorreo(2, correo1Empleado2);

	}

}
