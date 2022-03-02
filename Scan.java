/**
 * Copyright (C), 2022-2023, The_Kiesling FabianJuarez SaraEcheverria
 * FileName: Scan
 * Author:   Fabian Juarez , Sara Echeverria , Jose Pablo Kiesling
 * Date:     26/02/2022
 * @author Jose Pablo Kiesling, Fabian Juarez y Sara Echeverria
 * @version:
        - Creacion: 26/02/2022
        - Ultima modificacion:
    Clase que tiene como fin ser un lector y escritura de datos
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scan {
    //---------------------------PROPIEDADES--------------------------
    ArrayList<String> tokens;
    //---------------------------METODOS------------------------------
    /*****************************************************************
    /**
     * @param mensaje
     */
    public void escribir(String mensaje){
        System.out.println(mensaje);
    }
    //****************************************************************

    public void Bienvenida(){
        System.out.println("Bienvenido a su interprete de lisp" + "\n" + "\n" + "Porfavor ingrese su operacion a realizar");
    }

    /*****************************************************************
    /**
     * @return lectura
     */
    public String leer(){
        Scanner sc = new Scanner(System.in);
        String lectura = sc.nextLine();
        return lectura;
    }
    //****************************************************************

    /*****************************************************************
    /***
	 * retornara que operacion se debera efectuar segun su numero
	 * @param datoEvaluar nos dara la expresion que se evaluara
	 * @return un numero que si es distinto a -1 y mayor que  que indicara que operacion se esta realizando
	 */
    public int obtenerTipo(String datoEvaluar){
		if (evaluar("^[(][ ]*setq[ ]+[a-z]+[ ]+[0-9]+[ ]*[)]$",datoEvaluar)) //Asignacion que vera si esta es un set de la variable
			return 1;
		else if (evaluar("^[(][ ]*[+|-|*|/]([ ]+([a-z]+|[0-9]+)[ ]*)+[)]$",datoEvaluar)) //Realizara la operacion de sumatoria para 2 operandos
			return 2;
		else
			return -1; //De no encontrar la expresion dara  este dato para que sea incorrecta
	}
    //****************************************************************
    //([a-z]+|[0-9]+)[ ]*
    /*****************************************************************
    /**
     * metodo que verificara si es una expresion o no
	 * @param regex regla a seguir(formato regex)
	 * @param expresion La expresion que nos seran brindados por el usuario
	 * @return si es verdadero es que si hay alguna operacion a evaluar, si es falso no habra ninguna
	 */
	private boolean evaluar(String regex, String expresion) {
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expresion);
        return matcher.find();
	}
    //****************************************************************
}
