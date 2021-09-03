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
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class GestionarPersonas {

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

	// Devuelve un mapa de clave entera y valor ArrayList de Personas
	public static Map<Integer, ArrayList<Persona>> agruparPorEdad(List<Persona> personas) {

		Map<Integer, ArrayList<Persona>> personasPorEdad = new TreeMap<Integer, ArrayList<Persona>>();

		// Declaro un auxiliar para cada edad
		ArrayList<Persona> aux;
		// Declaro un auxiliar Integer
		Integer key;

		for (Persona cadaPersona : personas) {
			// Tomo la primera persona de la lista y guardo en key su edad
			key = cadaPersona.getEdad();
			// Pregunto si en en el mapa personasPorEdad está la key con esa edad
			if (personasPorEdad.containsKey(key)) {
				// Si la key ya está contenida en el mapa guardo en la lista auxiliar de
				// Personas la lista con personas de la edad
				aux = personasPorEdad.get(key);
				// Si la key no está en el mapa creo una nueva lista auxiliar
			} else {
				aux = new ArrayList<Persona>();
			}
			// No importa cuál sea el resultado voy a agregar a cada persona del for each en
			// la lista auxiliar de personas
			aux.add(cadaPersona);
			// Finalmente guardo en el mapa una llave con su respectiva lista de personas
			// con esa clave
			personasPorEdad.put(key, aux);
		}

		return personasPorEdad;
	}

	
	//¿Cómo se recorre el mapa?
	public static void escribirPersonasAgrupadasPorEdad(Map<Integer, ArrayList<Persona>> map, String file)
			throws IOException {

		PrintWriter salida = new PrintWriter(new FileWriter(file));

		List<Persona> aux;
		//Por cada edad (clave, recorro el conjunto de claves). El tipo del iterador es un Entry, el mismo tipo del mapa. map.entrySet() es el conjunto de claves del mapa.
		for (Entry<Integer, ArrayList<Persona>> cadaEdad : map.entrySet()) {
			//a cada edad le pido getKey y la imprimo
			salida.println(cadaEdad.getKey());
			//en la lista auxiliar estoy guardando el value de cada edad, o sea una lista de personas
			aux = cadaEdad.getValue();
			//como es una lista la recorro con un for each
			for (Persona p : aux) {
				salida.println(p.getDni() + " " + p.getApellido());
			}
		}
		salida.close();
	}

}
