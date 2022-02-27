/**
 * Copyright (C), 2022-2023, The_Kiesling FabianJuarez SaraEcheverria
 * FileName: View
 * Author:   Fabian Juarez , Sara Echeverria , Jose Pablo Kiesling
 * Date:     26/02/2022
 * @author Jose Pablo Kiesling, Fabian Juarez y Sara Echeverria
 * @version:
        - Creacion: 26/02/2022
        - Ultima modificacion:
    Clase que tiene como fin ser un
 */
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Interprete {
    //---------------------------PROPIEDADES--------------------------
    ArrayList<Variable> variables = new ArrayList<Variable>();

    //---------------------------METODOS------------------------------
    /*****************************************************************
     * recibe una expresion y una opcion según dicha expresion
     * @param expresion
     * @param option
     */
    public void operate(String expresion, int option){
        if (option == 1)
            newIntVariable(expresion);
        if (option == 2)
            addOperation(expresion);
        if (option == 3)
            substractOperation(expresion);
        if (option == 4)
            multiplyOperation(expresion);
        if (option == 5)
            divideOperation(expresion);
        
    }
    //****************************************************************

    /*****************************************************************
     * crea una nueva variable entera
     * @param expresion
     */
    private void newIntVariable(String expresion){
        String name = "";
        Integer value = 0;
        
        //Nombre de la variable
        Pattern pattern = Pattern.compile("[ ]+[a-z]+[ ]+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expresion);
        if (matcher.find()) {
            name = matcher.group().trim();
        }
        
        //Valor de la variable
        pattern = Pattern.compile("[ ]+[0-9]+[ ]*", Pattern.CASE_INSENSITIVE); 
        matcher = pattern.matcher(expresion);
        if (matcher.find()) {
            value = Integer.parseInt(matcher.group().trim());
        }
        
       //Instanciar la variable y agregarla al arreglo dinámico
       Variable variable = new Variable(name, value.toString());
       variables.add(variable);
    }
    //****************************************************************

       /*****************************************************************
     * operacion de adicion.
     * @param expresion
     */

     // --- SUMA ---
     private void addOperation(String expresion) {
		Pattern pattern = Pattern.compile("([a-z]+|[0-9]+)", Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(expresion);
	    Integer total = 0;
	    
	    while (matcher.find()) {
	    	total += Integer.parseInt(matcher.group().trim());
	    }
	    
	    Variable miResult = new Variable();
	    miResult.addResults(" suma ", "" + total);
	    return miResult;
	}
    //****************************************************************
}
