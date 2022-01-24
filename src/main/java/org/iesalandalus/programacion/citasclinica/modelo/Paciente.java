package org.iesalandalus.programacion.citasclinica.modelo;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Paciente {
	private static final String ER_DNI = ("([0-9]{8})([A-Za-z])");
	private static final String ER_TELEFONO = ("[69][0-9]{8}");
	private String nombre;
	private String dni;
	private String telefono;

	public Paciente(String nombre, String dni, String telefono) {
		setNombre(nombre);
		setDni(dni);
		setTelefono(telefono);
	}

	public Paciente(Paciente copiaPaciente) {
		if (copiaPaciente == null) {
			throw new NullPointerException("ERROR: No es posible copiar un paciente nulo.");
		} else {
			setNombre(copiaPaciente.nombre);
			setDni(copiaPaciente.dni);
			setTelefono(copiaPaciente.telefono);
		}
	}

	private String formateaNombre(String nombre) {
		nombre = nombre.replaceAll("\\s+", " ").trim().toLowerCase();
		String nuevonombre = "";
		nuevonombre += nombre.substring(0, 1).toUpperCase();
		for (int i = 1; i < nombre.length(); i++) {
			if (nombre.charAt(i - 1) == ' ') {
				nuevonombre += nombre.substring(i, i + 1).toUpperCase();
			} else {
				nuevonombre += nombre.substring(i, i + 1);
			}
		}
		return nuevonombre;
	}

	private boolean comprobarLetraDni(String dni) {
		boolean verificador = false;
		Pattern patron;
		Matcher comparador;
		patron = Pattern.compile(ER_DNI);
		comparador = patron.matcher(dni);

		if (comparador.matches()) {
			int numeroDni = Integer.parseInt(comparador.group(1));
			String[] letraValida = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S",
					"Q", "V", "H", "L", "C", "K", "E" };
			if (comparador.group(2).equals(letraValida[numeroDni % 23])) {
				verificador = true;
			}
		}
		return verificador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null || nombre.trim().isEmpty()) {
			throw new NullPointerException("ERROR: El nombre de un paciente no puede ser nulo o vacío.");
		} else {
			this.nombre = formateaNombre(nombre);
		}
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		if (telefono == null || telefono.trim().isEmpty()) {
			throw new NullPointerException("ERROR: El teléfono de un paciente no puede ser nulo o vacío.");
		} else if (telefono.matches(ER_TELEFONO)) {
			this.telefono = telefono;
		} else {
			throw new IllegalArgumentException("ERROR: El teléfono no tiene un formato válido.");
		}
	}

	public String getDni() {
		return dni;
	}

	private void setDni(String dni) {
		if (dni == null || dni.trim().isEmpty()) {
			throw new NullPointerException("ERROR: El DNI de un paciente no puede ser nulo o vacío.");
		} else if (!dni.matches(ER_DNI)) {
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		} else if (!comprobarLetraDni(dni)) {
			throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
		}
		this.dni = dni;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		return Objects.equals(dni, other.dni);
	}

	public String getIniciales() {
		String[] palabra = nombre.split(" ");
		String iniciales = "";
		for (int i = 0; i < palabra.length; i++) {
			iniciales += palabra[i].charAt(0);
		}
		return iniciales.toUpperCase();
	}

	@Override
	public String toString() {
		return String.format("nombre=%s (%s), DNI=%s, teléfono=%s", getNombre(), getIniciales(), getDni(),
				getTelefono());
	}
}
