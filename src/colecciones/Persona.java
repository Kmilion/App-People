package colecciones;

import java.util.Objects;

public class Persona {

	private Integer dni;
	private String apellido;
	private Integer edad;

	public Persona(Integer dni, String apellido, Integer edad) {
		this.dni = dni;
		this.apellido = apellido;
		this.edad = edad;
	}

	@Override
	public String toString() {
		return dni + "," + apellido + "," + edad;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellido, dni, edad);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(apellido, other.apellido) && Objects.equals(dni, other.dni)
				&& Objects.equals(edad, other.edad);
	}

}
