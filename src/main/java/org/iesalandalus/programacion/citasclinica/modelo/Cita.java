package org.iesalandalus.programacion.citasclinica.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Cita {
	public static final String FORMATO_FECHA_HORA = ("dd/MM/yyyy HH:mm");
	private LocalDateTime fechaHora;
	public Paciente paciente;

	public Cita(Paciente paciente, LocalDateTime fechaHora) {
		super();
		setFechaHora(fechaHora);
		setPaciente(paciente);
	}

	public Cita(Cita copiaCita) {
		if (copiaCita == null) {
			throw new NullPointerException("ERROR: No se puede copiar una cita nula.");
		} else {
			setFechaHora(copiaCita.getFechaHora());
			setPaciente(copiaCita.getPaciente());
		}
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	private void setFechaHora(LocalDateTime fechaHora) {
		if (fechaHora == null) {
			throw new NullPointerException("ERROR: La fecha y hora de una cita no puede ser nula.");
		}
		this.fechaHora = fechaHora;
	}

	public Paciente getPaciente() {
		return new Paciente(paciente);
	}

	private void setPaciente(Paciente paciente) {
		if (paciente == null) {
			throw new NullPointerException("ERROR: El paciente de una cita no puede ser nulo.");
		}
		this.paciente = new Paciente(paciente);
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaHora);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cita other = (Cita) obj;
		return Objects.equals(fechaHora, other.fechaHora);
	}

	@Override
	public String toString() {
		return paciente.toString() + ", fechaHora=" + fechaHora.format(DateTimeFormatter.ofPattern(FORMATO_FECHA_HORA))
				+ "";
	}

}
