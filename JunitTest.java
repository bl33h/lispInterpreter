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
	private ArrayList<String> expresiones = new ArrayList<String>();	
	private ArrayList<ArrayList<String>> tokens = new ArrayList<ArrayList<String>>();
	private Scan sc = new Scan();
	private String variable;
	private Funciones fun = new Funciones();

	@Test
	void test_Suma() {
		assertEquals(12, calc.Evaluate("+ 4 8"));
	}

	@Test
	void test_Suma_Var() {
		expresiones.add("(setq var 10)");
		expresiones.add("(+ 4 var)");
		for (String s: expresiones)
            tokens.add(sc.tokens(s));

        //Evaluate
        for (ArrayList<String> al: tokens){
            String expresion = "";
            for (String s: al)
                expresion += s + " ";
            variable = inter.operate(al, sc.obtenerTipo(al));
        }
		assertEquals("14", variable.toString());
	}
	
	@Test
	void test_Suma_Var2() {
		expresiones.add("(setq var 10)");
		expresiones.add(("setq var2 10"));
		expresiones.add("(+ var2 var)");
		for (String s: expresiones)
            tokens.add(sc.tokens(s));

        //Evaluate
        for (ArrayList<String> al: tokens){
            String expresion = "";
            for (String s: al)
                expresion += s + " ";
            variable = inter.operate(al, sc.obtenerTipo(al));
        }
		assertEquals("20", variable.toString());
	}
	
	@Test
	void test_Resta() {
		assertEquals(-20, calc.Evaluate("- 28 8"));
	}

	@Test
	void test_Resta_Var() {
		expresiones.add("(setq var 10)");
		expresiones.add("(- 4 var)");
		for (String s: expresiones)
            tokens.add(sc.tokens(s));

        //Evaluate
        for (ArrayList<String> al: tokens){
            String expresion = "";
            for (String s: al)
                expresion += s + " ";
            variable = inter.operate(al, sc.obtenerTipo(al));
        }
		assertEquals("6", variable.toString());
	}

	@Test
	void test_Resta_Var2() {
		expresiones.add("(setq var 10)");
		expresiones.add(("setq var2 10"));
		expresiones.add("(- var2 var)");
		for (String s: expresiones)
            tokens.add(sc.tokens(s));

        //Evaluate
        for (ArrayList<String> al: tokens){
            String expresion = "";
            for (String s: al)
                expresion += s + " ";
            variable = inter.operate(al, sc.obtenerTipo(al));
        }
		assertEquals("0", variable.toString());
	}
	
	@Test
	void test_Division() {
		assertEquals(14, calc.Evaluate("/ 10 140"));
	}

	@Test
	void test_Division_Var() {
		expresiones.add("(setq var 10)");
		expresiones.add("(/ var 140)");
		for (String s: expresiones)
            tokens.add(sc.tokens(s));

        //Evaluate
        for (ArrayList<String> al: tokens){
            String expresion = "";
            for (String s: al)
                expresion += s + " ";
            variable = inter.operate(al, sc.obtenerTipo(al));
        }
		assertEquals("14", variable.toString());
	}
	
	@Test
	void test_Division_Var2() {
		expresiones.add("(setq var 10)");
		expresiones.add(("setq var2 10"));
		expresiones.add("(/ var2 var)");
		for (String s: expresiones)
            tokens.add(sc.tokens(s));

        //Evaluate
        for (ArrayList<String> al: tokens){
            String expresion = "";
            for (String s: al)
                expresion += s + " ";
            variable = inter.operate(al, sc.obtenerTipo(al));
        }
		assertEquals("1", variable.toString());
	}
	
	@Test
	void test_Multiplicacion() {
		assertEquals(10, calc.Evaluate("* 5 2"));
	}

	@Test
	void test_Multiplicacion_Var() {
		expresiones.add("(setq var 5)");
		expresiones.add("(* var 2)");
		for (String s: expresiones)
            tokens.add(sc.tokens(s));

        //Evaluate
        for (ArrayList<String> al: tokens){
            String expresion = "";
            for (String s: al)
                expresion += s + " ";
            variable = inter.operate(al, sc.obtenerTipo(al));
        }
		assertEquals("10", variable.toString());
	}
	
	@Test
	void test_Multiplicacion_Var2() {
		expresiones.add("(setq var 10)");
		expresiones.add(("setq var2 10"));
		expresiones.add("(* var2 var)");
		for (String s: expresiones)
            tokens.add(sc.tokens(s));

        //Evaluate
        for (ArrayList<String> al: tokens){
            String expresion = "";
            for (String s: al)
                expresion += s + " ";
            variable = inter.operate(al, sc.obtenerTipo(al));
        }
		assertEquals("100", variable.toString());
	}
	
	@Test
	void test_Quote() {
		assertEquals("5", inter.quote("' 5"));
	}
	
	@Test
	void test_Quote_Var() {
		expresiones.add("(setq var 5)");
		expresiones.add("(' var)");
		for (String s: expresiones)
            tokens.add(sc.tokens(s));

        //Evaluate
        for (ArrayList<String> al: tokens){
            String expresion = "";
            for (String s: al)
                expresion += s + " ";
            variable = inter.operate(al, sc.obtenerTipo(al));
        }
		assertEquals("5", variable.toString());
	}
	
	@Test
	void test_Defun_norm() {
		expresiones.add("(defun name x (print x)");
		expresiones.add("(name (5)");
		for (String s: expresiones)
            tokens.add(sc.tokens(s));

        //Evaluate
        for (ArrayList<String> al: tokens){
            String expresion = "";
            for (String s: al)
                expresion += s + " ";
            variable = inter.operate(al, sc.obtenerTipo(al));
        }
		assertEquals("5"+ "\n", variable.toString());
	}
	
	@Test
	void test_Defun_Recur() {
		expresiones.add("(defun fact x (Cond (equals x 0) (print 1) (* x fact (- 1 x))))");
		expresiones.add("(fact (6)");
		for (String s: expresiones)
            tokens.add(sc.tokens(s));

        //Evaluate
        for (ArrayList<String> al: tokens){
            String expresion = "";
            for (String s: al)
                expresion += s + " ";
            variable = inter.operate(al, sc.obtenerTipo(al));
        }
		assertEquals("720"+ "\n", variable.toString());
	}
	
	
	@Test
	void test_Set() {
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("setq");
		arr.add("car");
		arr.add("10");
		assertEquals("car: 10", inter.newVariable(arr));
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
	void test_Equal_Var() {
		expresiones.add("(setq var 5)");
		expresiones.add("(equals var 2)");
		for (String s: expresiones)
            tokens.add(sc.tokens(s));

        //Evaluate
        for (ArrayList<String> al: tokens){
            String expresion = "";
            for (String s: al)
                expresion += s + " ";
            variable = inter.operate(al, sc.obtenerTipo(al));
        }
		assertEquals("Falso", variable.toString());
	}
	
	@Test
	void test_Equal_Var2() {
		expresiones.add("(setq var 10)");
		expresiones.add(("setq var2 10"));
		expresiones.add("(equals var2 var)");
		for (String s: expresiones)
            tokens.add(sc.tokens(s));

        //Evaluate
        for (ArrayList<String> al: tokens){
            String expresion = "";
            for (String s: al)
                expresion += s + " ";
            variable = inter.operate(al, sc.obtenerTipo(al));
        }
		assertEquals("verdadero", variable.toString());
	}	
	
	@Test
	void test_Mayor() {
		assertEquals("verdadero", logic.mayorComparar("> 10 5"));
	}
	
	@Test
	void test_Mayor_Var() {
		expresiones.add("(setq var 5)");
		expresiones.add("(> var 2)");
		for (String s: expresiones)
            tokens.add(sc.tokens(s));

        //Evaluate
        for (ArrayList<String> al: tokens){
            String expresion = "";
            for (String s: al)
                expresion += s + " ";
            variable = inter.operate(al, sc.obtenerTipo(al));
        }
		assertEquals("verdadero", variable.toString());
	}
	
	@Test
	void test_Mayor_Var2() {
		expresiones.add("(setq var 10)");
		expresiones.add(("setq var2 0"));
		expresiones.add("(> var2 var)");
		for (String s: expresiones)
            tokens.add(sc.tokens(s));

        //Evaluate
        for (ArrayList<String> al: tokens){
            String expresion = "";
            for (String s: al)
                expresion += s + " ";
            variable = inter.operate(al, sc.obtenerTipo(al));
        }
		assertEquals("Falso", variable.toString());
	}
	
	@Test
	void test_Menor() {
		assertEquals("Falso", logic.menorComparar("< 10 5"));
	}
	
	@Test
	void test_Menor_Var() {
		expresiones.add("(setq var 5)");
		expresiones.add("(< var 2)");
		for (String s: expresiones)
            tokens.add(sc.tokens(s));

        //Evaluate
        for (ArrayList<String> al: tokens){
            String expresion = "";
            for (String s: al)
                expresion += s + " ";
            variable = inter.operate(al, sc.obtenerTipo(al));
        }
		assertEquals("Falso", variable.toString());
	}
	
	@Test
	void test_Menor_Var2() {
		expresiones.add("(setq var 10)");
		expresiones.add(("setq var2 0"));
		expresiones.add("(< var2 var)");
		for (String s: expresiones)
            tokens.add(sc.tokens(s));

        //Evaluate
        for (ArrayList<String> al: tokens){
            String expresion = "";
            for (String s: al)
                expresion += s + " ";
            variable = inter.operate(al, sc.obtenerTipo(al));
        }
		assertEquals("Falso", variable.toString());
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
