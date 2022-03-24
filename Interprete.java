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

import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.Arrays;

public class Interprete {
    //--------------------------- PROPIEDADES --------------------------
    private HashMap<String, Integer> variables = new HashMap<String, Integer>();
    private Aritmeticas aritmeticas = new Aritmeticas();
    private Logicas logicas = new Logicas();
    private ArrayList<String> instrucciones = new ArrayList<String>(Arrays.asList("setq", "print", "+", "-", "*", "/", "'", "quote", ">", "<", "equals", "=", "Atom", "List", "Cond", "Defun"));
    private Scan sc = new Scan();

    //--------------------------- METODOS ------------------------------
    /*****************************************************************
     * recibe una expresion y una opcion según dicha expresion
     * @param expresion
     * @param option
     */
    public String operate(ArrayList<String> oexpression, int option){
        String expresion = "";
            for (String s: oexpression)
                expresion += s + " ";
        expresion = findVariables(expresion);
        if (option == 1)
            return newVariable(expresion);
        else if (option == 2)
            return aritmeticas.Evaluate(expresion) + "";
        else if (option == 3)
            return quote(expresion);
        else if (option == 4)
            return logicas.mayorComparar(expresion);
        else if (option == 5)
            return logicas.menorComparar(expresion);
        else if (option == 6)
            return logicas.equals(expresion);
        else if (option == 7)
            return logicas.isAtom(expresion);
        else if (option == 8)
            return logicas.isList(expresion);
        else if (option == 9)
            return Condicionales(oexpression);
        else
            return "";
    }
    //****************************************************************

    /*****************************************************************
     * crea una nueva variable entera
     * @param expresion
     */
    public String newVariable(String expresion){
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
       variables.put(name, value);
       return name +": " + value;
    }
    //****************************************************************

    /*****************************************************************
     * operacion que entrar toda la variable.
     * @param expresion
     */
    public String quote(String expresion){
        String expresionFinal ="";
        String[] expresionSplit = expresion.split(" ");
        int i =0;
        for(i =0; i <expresionSplit.length-1;i++){
            if(expresionSplit[i].equals("quote")||expresionSplit[i].equals("'")){
                i++;
                break;
            }
        }
        for(int j = i ; j<=expresionSplit.length-1; j++) {
        	expresionFinal += expresionSplit[j] + " ";
        }
        return expresionFinal.trim();
    }
    //****************************************************************

    /*****************************************************************
     * verifica si existe la variable
     * @param name
     * @return
     */
    private Integer verifyVariable(String name){
        Integer variable = null;
        if(variables.containsKey(name))
            variable = variables.get(name);
        return variable;
    }
    //****************************************************************

    public ArrayList<String> getInstrucciones(){
        return this.instrucciones;
    }

    /*****************************************************************
     * metodo para condicionales
     * @param oexpression
     * @return
     */
    public String Condicionales(ArrayList<String> oexpression){
        String conditional = "";
        String condition = oexpression.get(1) + " ";
        int numeroCondiciones = 0;
        String positive = "";
        String negative = "";
        boolean positivo = false;

        for (int i = 2; i < oexpression.size(); i++) {
            if (!isHere(getInstrucciones(), oexpression.get(i)) && numeroCondiciones != 2){
                condition += oexpression.get(i) + " ";
                numeroCondiciones++;
            }
            else if(numeroCondiciones != 2){ 
                String expression = oexpression.get(i) + " ";
                boolean flag = true;
                int cont = 0;
                for (int j = i+1; j < oexpression.size() && flag; j++){
                    if (!isHere(getInstrucciones(),oexpression.get(j))){
                        expression += oexpression.get(j) + " ";
                        cont++;
                    }
                    else 
                        flag = false;
                }    
                condition += operate(convertToArrayList(expression), sc.obtenerTipo(convertToArrayList(expression)));
                numeroCondiciones++;
                i += cont;
            } 
            else if (numeroCondiciones == 2 && !positivo){
                if (isHere(getInstrucciones(),oexpression.get(i)))
                    positive = oexpression.get(i) + " ";
                boolean flag = true;
                int cont = 0;
                for (int j = i+1; j < oexpression.size() && flag; j++){
                    if (!isHere(getInstrucciones(),oexpression.get(j))){
                        positive += oexpression.get(j) + " ";
                        cont++;
                    }
                    else
                        flag = false;
                }
                positivo = true;
                i += cont;
            }
            else if (numeroCondiciones == 2 && positivo){
                if (isHere(getInstrucciones(),oexpression.get(i)))
                    negative = oexpression.get(i) + " ";
                boolean flag = true;
                for (int j = i+1; j < oexpression.size() && flag; j++){
                    if (!isHere(getInstrucciones(),oexpression.get(j)))
                        negative += oexpression.get(j) + " ";
                    else 
                        flag = false;
                }
                break; 
            }
        }
        if (operate(convertToArrayList(condition), sc.obtenerTipo(convertToArrayList(condition))).equals("verdadero"))
            conditional = operate(convertToArrayList(positive), sc.obtenerTipo(convertToArrayList(positive)));
        else
            conditional = operate(convertToArrayList(negative), sc.obtenerTipo(convertToArrayList(negative)));
        return conditional;
    }

    private boolean isHere(ArrayList<String> collection, String evaluate){
        boolean here = false;
        for (int i = 0; i < collection.size() && here == false; i++)
            if (evaluate.contains(collection.get(i)))
                here = true;
        return here;
    }

    private ArrayList<String> convertToArrayList(String expression){
        String[] splitedExpression = expression.split(" ");
        ArrayList<String> evaExpression = new ArrayList<String>();
        for (int j = 0; j < splitedExpression.length; j++)
            evaExpression.add(splitedExpression[j]);
        return evaExpression;
    }

    private String findVariables(String expresion){
        String newExpresion = "";
        String variable = "";
        String[] parts = expresion.split(" ");
        for (int i = 0; i < parts.length; i++){
            //Valores de variables
            Pattern pattern = Pattern.compile("([a-z]+)", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(parts[i]);

            if (matcher.find()){
                variable = matcher.group().trim();
                if(!variable.matches("[+-]?\\d*(\\.\\d+)?"))
                    if(verifyVariable(variable) != null)
                        parts[i] = verifyVariable(variable).toString();

            }
        }
        for (int i = 0; i < parts.length; i++)
            newExpresion += parts[i] + " ";

        return newExpresion;
    }
}
