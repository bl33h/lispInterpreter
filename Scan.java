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
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scan {
    //---------------------------PROPIEDADES--------------------------
    ArrayList<String> tokens;
    File file = new File("lispProgram.lsp");
    Scanner scanner;

    //---------------------------METODOS------------------------------
    /*****************************************************************
     * @param mensaje
     */
    public void escribir(String mensaje){
        System.out.println(mensaje);
    }
    //****************************************************************

    /*****************************************************************
     * 
     */
    public void Bienvenida(){
        System.out.println("Bienvenido a su interprete de lisp" + "\n" + "\n" + "Porfavor ingrese su operacion a realizar");
    }
    //****************************************************************

    /*****************************************************************
     * @return lectura
     */
    public String leer(){
        scanner = new Scanner(System.in);
        String lectura = scanner.nextLine();
        return lectura;
    }
    //****************************************************************

    /*****************************************************************
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

    /*****************************************************************
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

    public ArrayList<String> read(){
        ArrayList<String> expressions = new ArrayList<String>();
        try {
            scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                expressions.add(line);
            }
        scanner.close();    
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return expressions;
    }

    private ArrayList<Character> stringToChar(ArrayList<String> expression){
        ArrayList<Character> chars = new ArrayList<Character>();
        for(String element: expression)
            for(int i = 0; i < element.length(); i++)
                chars.add(element.charAt(i));
        return chars;
    }

    public ArrayList<String> tokens(ArrayList<String> expressions){
        String temp = "";
        ArrayList<Character> chars = new ArrayList<Character>();
        chars.addAll(stringToChar(expressions));
    
        for (Character actualChar: chars){
            if(actualChar == '(');
            else{
                if(actualChar == ')'){
                    if(temp !=""){
                        expressions.add(temp);
                        temp = "";
                    }
                }
                else{
                    if (actualChar != ' ')
                        temp += actualChar+"";
                    else{
                        if(temp != ""){
                            expressions.add(temp);
                            temp = "";
                        }
                    }
                }
            }
        }
        return expressions;
    }
}
