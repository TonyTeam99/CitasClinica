package org.iesalandalus.programacion.citasclinica.modelo;

import java.time.LocalDate;
import java.util.Arrays;

import javax.naming.OperationNotSupportedException;

public class Citas {
	private int capacidad;
	private int tamano;
	Cita[] coleccionCitas;

	public Citas(int capacidadColeccion) {
		if (capacidadColeccion <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.capacidad = capacidadColeccion;
		coleccionCitas = new Cita[capacidadColeccion];
		this.tamano = 0;
	}

	public int getCapacidad() {
		return this.capacidad;
	}

	public int getTamano() {
		return this.tamano;
	}

	private boolean tamanoSuperado(int indice) {
		return indice >= tamano;
	}

	private boolean capacidadSuperada(int indice) {
		return indice >= capacidad;
	}

	private int buscarIndice(Cita cita) {
		int indice = 0;
		boolean citaEncontrada = false;
		while (!tamanoSuperado(indice) && !citaEncontrada) {
			if (coleccionCitas[indice].equals(cita)) {
				citaEncontrada = true;
			} else {
				indice++;
			}
		}
		return indice;
	}

	public void insertar(Cita cita) throws OperationNotSupportedException {
		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede insertar una cita nula.");
		}
		int indice = buscarIndice(cita);
		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más citas.");
		} else if (!tamanoSuperado(indice)) {
			throw new OperationNotSupportedException("ERROR: Ya existe una cita para esa fecha y hora.");
		} else {
			coleccionCitas[indice] = new Cita(cita);
			this.tamano++;
		}
	}

	public Cita buscar(Cita cita) {
		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede buscar una cita nula.");
		}
		int indice = buscarIndice(cita);
		if (tamanoSuperado(indice)) {
			return null;
		} else {
			return new Cita(coleccionCitas[indice]);
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		{
			int i;
			for (i = indice; !tamanoSuperado(i); i++) {
				coleccionCitas[i] = coleccionCitas[i + 1];
			}
			coleccionCitas[i] = null;
			tamano--;
		}
	}

	public void borrar(Cita cita) throws OperationNotSupportedException {
		if (cita == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una cita nula.");
		}
		if (tamanoSuperado(buscarIndice(cita))) {
			throw new OperationNotSupportedException("ERROR: No existe ninguna cita para esa fecha y hora.");
		} else {
			desplazarUnaPosicionHaciaIzquierda(buscarIndice(cita));
		}
	}

	public Cita[] getCitas() {
		Cita[] copiaColeccionCitas;
		copiaColeccionCitas = Arrays.copyOf(coleccionCitas, coleccionCitas.length);
		return copiaColeccionCitas;
	}

	public Cita[] getCitas(LocalDate fecha) {
		if (fecha == null) {
			throw new NullPointerException("ERROR: No se pueden devolver las citas para un día nulo.");
		}
		Cita[] coleccionCitasFecha = new Cita[tamano];
		int j = 0;
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionCitas[i].getFechaHora().toLocalDate().equals(fecha)) {
				coleccionCitasFecha[j++] = coleccionCitas[i];
			}
		}
		return coleccionCitasFecha;
	}
}
