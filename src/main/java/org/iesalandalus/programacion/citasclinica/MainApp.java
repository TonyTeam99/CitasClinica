package org.iesalandalus.programacion.citasclinica;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.citasclinica.modelo.Cita;
import org.iesalandalus.programacion.citasclinica.modelo.Citas;
import org.iesalandalus.programacion.citasclinica.modelo.Paciente;
import org.iesalandalus.programacion.citasclinica.vista.Consola;
import org.iesalandalus.programacion.citasclinica.vista.Opciones;

public class MainApp {
	public static final int NUM_MAX_CITAS = 20;
	private static Citas listaCitas = new Citas(NUM_MAX_CITAS);

	public static void main(String[] args) throws OperationNotSupportedException {
		System.out.println("Programa para gestionar las citas de la Clínica.");
		System.out.println("_______________________________________________");
		System.out.println("");
		Consola.mostrarMenu();
		ejecutarOpcion(Consola.elegirOpcion());
	}

	private static void insertarCita() throws OperationNotSupportedException {
		try {
			Cita cita = Consola.leerCita();
			listaCitas.insertar(cita);
			System.out.println("Cita asignada");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		} catch (IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
			System.out.println("");
			System.out.println("Vuelva a introducir los datos :(");
			insertarCita();
		}
	}

	private static void buscarCita() throws OperationNotSupportedException {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime fecha = Consola.leerFechaHora();
		Paciente paciente = new Paciente("pepe", "12345678A", "123456789");
		Cita cita = new Cita(paciente, fecha);
		Cita citaBuscada;
		citaBuscada = listaCitas.buscar(cita);
		if (citaBuscada == null) {
			System.out.println("No existen citas para la fecha: " + fecha.format(formato));
		} else {
			Cita[] citas = listaCitas.getCitas(fecha.toLocalDate());
			for (Cita citaEncontrada : citas) {
				if (cita.equals(citaEncontrada)) {
					System.out.println("La cita es " + citaEncontrada);
				}
			}
		}
		Consola.mostrarMenu();
		ejecutarOpcion(Consola.elegirOpcion());
	}

	private static void borrarCita() throws OperationNotSupportedException {
		LocalDateTime fechaHora = Consola.leerFechaHora();
		Paciente paciente = new Paciente("pepe", "12345678A", "123456789");
		Cita cita = new Cita(paciente, fechaHora);
		try {
			listaCitas.borrar(cita);
			System.out.println("Cita borrada correctamente");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		} catch (IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
			System.out.println("");
			System.out.println("Vuelva a introducir los datos :(");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		}
	}

	private static void mostrarCitasDia() throws OperationNotSupportedException {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime fecha = Consola.leerFechaHora();
		System.out.println("Lista de citas para fecha: " + fecha.format(formato));
		Cita[] citasFecha = listaCitas.getCitas(fecha);
		boolean citasDisponibles = false;
		for (int i = 0; i <= citasFecha.length - 1; i++) {
			if (citasFecha[i] != null) {
				citasDisponibles = true;
			}
		}

		if (citasFecha.length == 0 || !citasDisponibles) {
			System.out.println("No hay citas para esta fecha :(");
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		} else {
			for (Cita cita : citasFecha) {
				if (cita != null) {
					System.out.println(cita);
				}
			}
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		}
	}

	private static void mostrarCitas() throws OperationNotSupportedException {
		System.out.println("Lista de todas las citas");
		if (listaCitas.getTamano() == 0) {
			System.out.println("No hay citas para mostrar :(");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		} else {
			Cita[] citas = listaCitas.getCitas();
			for (Cita cita : citas) {
				if (cita != null) {
					System.out.println(cita);
				}
			}
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		}
	}

	private static void ejecutarOpcion(Opciones opcion) throws OperationNotSupportedException {
		switch (opcion) {
		case SALIR:
			System.out.println("");
			System.out.print("¡Sesión terminada!");
			break;
		case INSERTAR_CITA:
			insertarCita();
			break;
		case BUSCAR_CITA:
			buscarCita();
			break;
		case BORRAR_CITA:
			borrarCita();
			break;
		case MOSTRAR_CITAS_DIA:
			mostrarCitasDia();
			break;
		case MOSTRAR_CITAS:
			mostrarCitas();
			break;
		}
	}
}
