package org.iesalandalus.programacion.citasclinica.vista;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.citasclinica.modelo.Cita;
import org.iesalandalus.programacion.citasclinica.modelo.Paciente;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private Consola() {

	}

	public static void mostrarMenu() {
		System.out.println("Men�");
		System.out.println("_________________________");
		System.out.println("");
		System.out.println("1 - Insertar cita.");
		System.out.println("2 - Buscar cita.");
		System.out.println("3 - Borrar cita.");
		System.out.println("4 - Mostrar citas por fecha.");
		System.out.println("5 - Mostrar todas las citas.");
		System.out.println("0 - Salir.");
	}

	public static Opciones elegirOpcion() {
		Opciones[] opcion = Opciones.values();
		System.out.println("");
		System.out.println("Escoge una opci�n");
		int opcionElegida = Entrada.entero();
		while (opcionElegida < 0 || opcionElegida > 5) {
			System.out.println("Esta opci�n no se encuentra disponible. Por favor, escoge una opci�n entre 0 y 5:");
			opcionElegida = Entrada.entero();
		}
		return opcion[opcionElegida];
	}

	public static Paciente leerPaciente() throws OperationNotSupportedException {
		Paciente paciente;
		System.out.println("");
		System.out.println("Introduzca el nombre:");
		String nombre = Entrada.cadena();
		System.out.println("Introduzca el tel�fono:");
		String telefono = Entrada.cadena();
		System.out.println("Introduzca el DNI:");
		String dni = Entrada.cadena();
		paciente = new Paciente(nombre, dni, telefono);
		return paciente;
	}

	public static LocalDateTime leerFechaHora() {
		String formatoCadena = "dd/MM/yyyy HH:mm";
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(formatoCadena);
		LocalDateTime fechaHora = null;
		try {
			System.out.println("Introduzca una fecha y hora con el siguiente formato: dd/MM/aaaa HH:mm:");
			fechaHora = LocalDateTime.parse(Entrada.cadena(), formatoFecha);
		} catch (DateTimeParseException e) {
		}
		return fechaHora;
	}

	public static Cita leerCita() throws OperationNotSupportedException {
		Cita cita = new Cita(leerPaciente(), leerFechaHora());
		return cita;
	}

	public static LocalDate leerFecha() {
		String formatoCadena = "dd/MM/yyyy";
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(formatoCadena);
		LocalDate fecha = null;
		boolean fechaValida = false;
		do {
			try {
				System.out.println("Introduzca una fecha con el siguiente formato: dd/MM/aaaa:");
				fecha = LocalDate.parse(Entrada.cadena(), formatoFecha);
				fechaValida = true;
			} catch (DateTimeParseException e) {
				fechaValida = false;
			}
		} while (!fechaValida);
		return fecha;
	}
}
