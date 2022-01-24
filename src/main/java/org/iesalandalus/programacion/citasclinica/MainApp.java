package org.iesalandalus.programacion.citasclinica;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.citasclinica.modelo.Cita;
import org.iesalandalus.programacion.citasclinica.modelo.Citas;
import org.iesalandalus.programacion.citasclinica.vista.Consola;
import org.iesalandalus.programacion.citasclinica.vista.Opciones;

public class MainApp {
	public static final int NUM_MAX_CITAS = 20;
	private static Citas listaCitas = new Citas(NUM_MAX_CITAS);

	public static void main(String[] args) throws OperationNotSupportedException {
		System.out.println("Programa para gestionar las citas de la Clínica.");
		System.out.println("_______________________________________________");
		System.out.println("");
		boolean opcion = true;
		do {
			ejecutarOpcion(Consola.elegirOpcion());
		} while (opcion);
	}

	private static void insertarCita() {
		try {
			listaCitas.insertar(Consola.leerCita());
			System.out.println("Cita asignada");
		} catch (IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
			System.out.println("");
			System.out.println("Vuelva a introducir los datos :(");
		}
	}

	private static void buscarCita() {
		try {
			Cita cita = listaCitas.buscar(Consola.leerCita());
			if (cita == null) {
				System.out.println("No existe la cita buscada");
			} else {
				System.out.println(cita);
			}
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void borrarCita() {
		try {
			listaCitas.borrar(Consola.leerCita());
			System.out.println("Cita borrada con exito");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void mostrarCitasDia() {
		System.out.println("Lista de todas las citas");
		try {
			Cita[] citas = listaCitas.getCitas(Consola.leerFecha());
			if (citas[0] != null) {
				for (Cita cita : citas) {
					if (cita != null) {
						System.out.println(cita);
					}
				}
			} else {
				System.out.println("No hay citas para mostrar");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void mostrarCitas() {
		System.out.println("Lista de todas las citas");
		try {
			Cita[] citas = listaCitas.getCitas(Consola.leerFecha());
			if (citas[0] != null) {
				for (Cita cita : citas) {
					if (cita != null) {
						System.out.println(cita);
					}
				}
			} else {
				System.out.println("No hay citas para mostrar");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
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
