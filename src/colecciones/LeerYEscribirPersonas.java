package colecciones;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LeerYEscribirPersonas {

	public static LinkedList<Persona> getPersonas(String archivo) {
		LinkedList<Persona> personas = new LinkedList<Persona>();
		Scanner sc = null;
		try {
			sc = new Scanner(new File(archivo));

			while (sc.hasNext()) {

				String linea = sc.nextLine();
				String datos[] = linea.split(" ");

				int dni = Integer.parseInt(datos[0]);
				String apellido = datos[1];
				int edad = Integer.parseInt(datos[2]);

				Persona p = new Persona(dni, apellido, edad);

				if (!personas.contains(p))
					personas.add(p);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		sc.close();

		return personas;
	}

	public static void ordenarPersonasPorDNI(List<Persona> lista) {
		Collections.sort(lista, new DniComparator());
	}

	public static void ordenarPersonasPorApellido(List<Persona> lista) {
		Collections.sort(lista, new ApellidoComparator());
	}

	public static void ordenarPersonasPorEdad(List<Persona> lista) {
		Collections.sort(lista, new EdadComparator());
	}

	public static List<Persona> getPersonasMayoresDeEdad(List<Persona> personas, Integer edad) {
		List<Persona> personasMayores = new ArrayList<Persona>();
		for (Persona p : personas) {
			if (p.getEdad() > edad)
				personasMayores.add(p);
		}
		return personasMayores;
	}

	public static void escribirPersonas(List<Persona> personas, String file) throws IOException {
		PrintWriter salida = new PrintWriter(new FileWriter(file));

		for (Persona p : personas) {
			salida.println(p);
		}
		salida.close();

	}

	public static void escribirMayoresDeEdadOrdenadasPorDNI(List<Persona> personas, int edad) throws IOException {
		List<Persona> personasMayores = getPersonasMayoresDeEdad(personas, edad);
		ordenarPersonasPorDNI(personasMayores);
		escribirPersonas(personasMayores, "MayoresDe" + edad + "OrdenadasPorDNI" + ".csv");
	}

	public static void escribirMayoresDeEdadOrdenadasPorEdad(List<Persona> personas, int edad) throws IOException {
		List<Persona> personasMayores = getPersonasMayoresDeEdad(personas, edad);
		ordenarPersonasPorEdad(personasMayores);
		escribirPersonas(personasMayores, "MayoresDe" + edad + "OrdenadasPorEdad" + ".csv");
	}

}
