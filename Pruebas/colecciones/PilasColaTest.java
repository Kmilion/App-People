package colecciones;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

public class PilasColaTest {

	Stack<Integer> pila;
	Queue<Integer> cola;

	@Before
	public void setUp() throws Exception {
		pila = new Stack<Integer>();
		cola = new ArrayDeque<Integer>();
	}

	@Test
	public void pilaVaciaTest() {
		assertTrue(pila.isEmpty());
	}

	@Test
	public void colaVaciaTest() {
		assertTrue(cola.isEmpty());
	}

	@Test
	public void pilaTest() {
		assertTrue(pila.isEmpty());
		pila.push(44);
		assertFalse(pila.isEmpty());
		Integer esperado = 44;
		assertEquals(pila.peek(), esperado);
		pila.push(45);
		pila.push(46);
		pila.push(47);
		pila.push(48);
		pila.push(49);
		esperado = 49;
		assertEquals(pila.peek(), esperado);
		System.out.println("Pila");
		while (!pila.isEmpty()) {
			System.out.println(pila.pop());
		}
		assertTrue(pila.isEmpty());
	}

	@Test
	public void colaTest() {
		assertTrue(cola.isEmpty());
		cola.offer(44);
		assertFalse(cola.isEmpty());
		Integer esperado = 44;
		assertEquals(cola.peek(), esperado);
		cola.offer(45);
		cola.offer(46);
		cola.offer(47);
		cola.offer(48);
		cola.offer(49);
		//es el primero de la lista
		assertEquals(cola.peek(), esperado);
		assertEquals(cola.poll(), esperado);
		System.out.println("Cola");
		while (!cola.isEmpty()) {
			System.out.println(cola.poll());
		}
		assertTrue(cola.isEmpty());
	}

}
