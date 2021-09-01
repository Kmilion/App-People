package colecciones;

import java.io.IOException;
import java.util.List;

public class AppPersonas {

	public static void main(String[] args) throws IOException {
		List<Persona> personas = LeerYEscribirPersonas.getPersonas("personas.in");
		LeerYEscribirPersonas.escribirMayoresDeEdadOrdenadasPorEdad(personas, 30);
		LeerYEscribirPersonas.escribirMayoresDeEdadOrdenadasPorDNI(personas, 35); 	
	}
}
