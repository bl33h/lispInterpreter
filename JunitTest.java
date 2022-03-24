import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;


/**
 * @author Fabian Juarez , Sara Echeverria , Jose Pablo Kiesling
 *
 */
class JunitTest {
	
	private Aritmeticas calc = new Aritmeticas();
	private Logicas logic = new Logicas();
	private Interprete inter = new Interprete();
	
	@Test
	void test_Suma() {
		assertEquals(12, calc.Evaluate("+ 4 8"));
	}

	@Test
	void test_Resta() {
		assertEquals(-20, calc.Evaluate("- 28 8"));
	}


	@Test
	void test_Division() {
		assertEquals(14, calc.Evaluate("/ 10 140"));
	}


	@Test
	void test_Multiplicacion() {
		assertEquals(10, calc.Evaluate("* 5 2"));
	}


	@Test
	void test_Quote() {
		assertEquals("5", inter.quote("' 5"));
	}

	
	@Test
	void test_Defun() {
		
	}
	
	@Test
	void test_Set() {
		assertEquals("car: 10", inter.newVariable("setq car 10"));
	}
	
	@Test
	void test_Atom() {
		assertEquals("T", logic.isAtom("Atom x"));
	}
	
	@Test
	void test_List() {
		assertEquals("T", logic.isList("List (1 2 3 4 5 6 7 8 9 10)"));
	}
	
	@Test
	void test_Equal() {
		assertEquals("verdadero", logic.equals("equals 10 10"));
	}
	
	@Test
	void test_Mayor() {
		assertEquals("verdadero", logic.mayorComparar("> 10 5"));
	}
	
	@Test
	void test_Menor() {
		assertEquals("Falso", logic.menorComparar("< 10 5"));
	}
	
	@Test
	void test_Condicionales() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Cond");
		list.add("equals");
		list.add("3");
		list.add("+");
		list.add("1");
		list.add("2");
		list.add("*");
		list.add("6");
		list.add("3");
		list.add("*");
		list.add("5");
		list.add("6");
		assertEquals("18", inter.Condicionales(list));
	}
	
}
