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
    public Variable operate(String expresion, int option){
        if (option == 1)
            return newIntVariable(expresion);
        else if (option == 2)
            return addOperation(expresion);
        else
            return null;
        /*
        if (option == 3)
            substractOperation(expresion);
        if (option == 4)
            multiplyOperation(expresion);
        if (option == 5)
            divideOperation(expresion);*/
        
    }
    //****************************************************************

    /*****************************************************************
     * crea una nueva variable entera
     * @param expresion
     */
    private Variable newIntVariable(String expresion){
        String name = "";
        int value = 0;
        
        //Nombre de la variable
        Pattern pattern = Pattern.compile("[ ]+[a-z]+[ ]+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expresion);
        if (matcher.find()) 
            name = matcher.group().trim();
        
        
        //Valor de la variable
        pattern = Pattern.compile("[ ]+[0-9]+[ ]*", Pattern.CASE_INSENSITIVE); 
        matcher = pattern.matcher(expresion);
        if (matcher.find()) 
            value = Integer.parseInt(matcher.group().trim());
        
        
       //Instanciar la variable y agregarla al arreglo dinámico
       Variable variable = new Variable(name, value);
       variables.add(variable);
       return variable;
    }
    //****************************************************************

    /*****************************************************************
     * operacion de adicion.
     * @param expresion
     */

     // --- SUMA ---
     private Variable addOperation(String expresion) {
        Integer total = 0;
        String variable = "";
        String[] parts = expresion.split(" ");

        if (parts.length == 2)
            return null;

        for (int i = 0; i < parts.length; i++){
            //Valores de variables
            Pattern pattern = Pattern.compile("([a-z]+)", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(parts[i]);
            
            if (matcher.find()){
                variable = matcher.group().trim();
                if(!variable.matches("[+-]?\\d*(\\.\\d+)?"))
                    if(verifyVariable(variable) != null)
                        total += verifyVariable(variable).getValue();

                    //Si se ingresa una variable incorrecta
                    if(verifyVariable(variable) == null)
                        return null;  
            }          
        }
        //Valores de constantes
        Pattern pattern = Pattern.compile("([0-9]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expresion);
        while (matcher.find()) 
            total += Integer.parseInt(matcher.group().trim());

        Variable result = new Variable("Resultado", total);
        return result;
	}
    //****************************************************************

    /*****************************************************************
     * verifica si existe la variable
     * @param name
     * @return
     */
    private Variable verifyVariable(String name){
        Variable variable = null;
        for (int i = 0; i < variables.size(); i++)
            if (name.equals(variables.get(i).getName())){
                variable = variables.get(i);
                break;
            }
        return variable;
    }
    //****************************************************************
}
