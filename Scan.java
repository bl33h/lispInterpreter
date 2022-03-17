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
import java.io.File;
import java.io.FileNotFoundException;

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
		if (datoEvaluar.contains("setq")) //Asignacion que vera si esta es un set de la variable
			return 1;
		else if (datoEvaluar.contains("+")||datoEvaluar.contains("-")||datoEvaluar.contains("*")||datoEvaluar.contains("/")) //Realizara la operacion aritmetica
			return 2;
        else if (datoEvaluar.contains("'")||datoEvaluar.contains("quote")) //Realizara la operacion de quote
			return 3;
        else if(datoEvaluar.contains(">"))
            return 4;
        else if(datoEvaluar.contains("<"))
            return 5;
        else if(datoEvaluar.contains("equals")||datoEvaluar.contains("="))
            return 6;
		else
			return -1; //De no encontrar la expresion dara  este dato para que sea incorrecta
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

    private ArrayList<Character> stringToChar(String expression){
        ArrayList<Character> chars = new ArrayList<Character>();
        for(int i = 0; i < expression.length(); i++)
                chars.add(expression.charAt(i));
        return chars;
    }

    public ArrayList<String> tokens(String expressions){
        String temp = "";
        ArrayList<String> new_Expressions = new ArrayList<String>();
        ArrayList<Character> chars = new ArrayList<Character>();
        chars.addAll(stringToChar(expressions));
    
        for (Character actualChar: chars){
            if(actualChar == '(');
            else{
                if(actualChar == ')'){
                    if(temp !=""){
                        new_Expressions.add(temp);
                        temp = "";
                    }
                }
                else{
                    if (actualChar != ' ')
                        temp += actualChar+"";
                    else{
                        if(temp != ""){
                            new_Expressions.add(temp);
                            temp = "";
                        }
                    }
                }
            }
        }
        return new_Expressions;
    }
}
