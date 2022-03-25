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

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Interprete {
    //--------------------------- PROPIEDADES --------------------------
    private HashMap<String, String> variables = new HashMap<String, String>();
    private Aritmeticas aritmeticas = new Aritmeticas();
    private Logicas logicas = new Logicas();
    private ArrayList<String> instrucciones = new ArrayList<String>(Arrays.asList("setq", "print", "+", "-", "*", "/", "'", "quote", ">", "<", "equals", "=", "Atom", "List", "Cond", "Defun"));
    private ArrayList<String> nameFunctions = new ArrayList<String>();
    private Scan sc = new Scan();
    private HashMap<String, ArrayList<String>> functions = new HashMap<String, ArrayList<String>>();
    private HashMap<String, LinkedHashMap<String, String>> parameters = new HashMap<String, LinkedHashMap<String, String>>();
    private boolean end = false;
    private int instruccionesCreadas = 1;

    //--------------------------- METODOS ------------------------------
    /*******************************************************************
     * recibe una expresion y una opcion segun dicha expresion
     * @param expresion
     * @param option
     */
    public String operate(ArrayList<String> oexpression, int option){
        if (!end){
            String expresion = "";
                for (String s: oexpression)
                    expresion += s + " ";
            if(findVariables(expresion) != null){
                expresion = findVariables(expresion);
                if (option == 1)
                    return newVariable(oexpression);
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
                else if (option == 10){
                    newFunction(convertToArrayList(expresion));
                    return "";
                }
                else{
                    if (isHere(instrucciones, oexpression.get(0)))
                        return useFunction(convertToArrayList(expresion));
                    else
                        return "ERROR: Ha ingresado una instrucción inválida";
                }
            }
            else 
                    return "ERROR: La variable no ha sido creada";
        }
        else{
            return "-----------";
        }
    }
    //****************************************************************

    /*****************************************************************
     * crea una funcion
     * @param oexpression
     */
    public void newFunction(ArrayList<String> oexpression){
        String name = oexpression.get(1); 
        this.instrucciones.add(name); //Agregar la funcion a una instruccion valida
        nameFunctions.add(name);
        ArrayList<String> instrucciones = new ArrayList<String>();
        LinkedHashMap<String, String> parametersFunction = new LinkedHashMap<String, String>();
        String[] parametersSplited = oexpression.get(2).trim().split(","); //Parametros
        for(String parameter: parametersSplited)
            parametersFunction.put(parameter, ""); //Indicar variables de la funcion
        this.parameters.put(name, parametersFunction);

        //Ver si hay otras instrucciones
        for (int i = 3; i < oexpression.size(); i++){
            String expresion = "";
            if (isHere(getInstrucciones(), oexpression.get(i))){
                expresion = oexpression.get(i) + " ";
                boolean flag = true;
                int cont = 0;
                for (int j = i+1; j < oexpression.size() && flag; j++){
                    if (!isHere(getInstrucciones(),oexpression.get(j))){
                        expresion += oexpression.get(j) + " ";
                        cont++;
                    }
                    else
                        flag = false;
                }
                i += cont;
            }
            instrucciones.add(expresion);
        }

        functions.put(name, instrucciones); //Agregar la funcion
    }
    //****************************************************************

    /*****************************************************************
     * metodo que usa una funcion creada
     * @param oexpression
     * @return
     */
    public String useFunction(ArrayList<String> oexpression){
        String name = oexpression.get(0);
        String result = "";

        //---Parametros
        ArrayList<String> newParameters = new ArrayList<String>();
        String parameters = "";
        for (int i = 1; i <oexpression.size(); i++){
            if (isHere(getInstrucciones(), oexpression.get(i))){
                String expresion = oexpression.get(i) + " ";
                boolean flag = true;
                int cont = 0;
                for (int j = i+1; j < oexpression.size() && flag; j++){
                    if (!isHere(getInstrucciones(),oexpression.get(j))){
                        expresion += oexpression.get(j) + " ";
                        cont++;
                    }
                    else
                        flag = false;
                }
                i += cont;
                parameters += operate(convertToArrayList(expresion), sc.obtenerTipo(convertToArrayList(expresion))); //Operar parametros
            }
            else
                parameters += oexpression.get(i) + " ";

            newParameters.add(parameters); //Añadir parametros
        }
        //---

        //---Asignacion de parametros
        String[] parametersSplited = parameters.split(" ");
        ArrayList<String> instructions = functions.get(name);
        LinkedHashMap<String, String> parametersFunction = this.parameters.get(name);
        String instrucciones = "";
        
        //Separar instrucciones
        for (int i = 0; i < instructions.size(); i++){
            instrucciones += instructions.get(i).trim() + " ";
        }

        if(parametersSplited.length == parametersFunction.size()){
            int i = 0;
            for(String parameter: parametersFunction.keySet()){ //Reemplazar parametros
                parametersFunction.put(parameter, parametersSplited[i]);             
                instrucciones = instrucciones.replace(parameter, parametersFunction.get(parameter));
                i++;
            } 
        }
        //---

        //--- Realizar acciones
        ArrayList<String> evaExpression = convertToArrayList(instrucciones);
        ArrayList<String> newInstructions = new ArrayList<String>();
        for (int i = 0; i < evaExpression.size(); i++){ //Recorrer la expresion a evaluar
            String expresion = "";
            if (isHere(getInstrucciones(), evaExpression.get(i))){
                if (evaExpression.get(i).equals("Cond")){
                    String condition = "";
                    for (int j = i; j < evaExpression.size(); j++){
                        condition += evaExpression.get(j) + " ";
                    }
                    expresion += operate(convertToArrayList(condition), sc.obtenerTipo(convertToArrayList(condition))); //Operar condiciones
                    i = evaExpression.size();
                    end = true; //Se acaba la funcion
                } else{
                    expresion = evaExpression.get(i) + " ";
                    boolean flag = true;
                    int cont = 0;
                    for (int j = i+1; j < evaExpression.size() && flag; j++){
                        if (!isHere(getInstrucciones(),evaExpression.get(j))){
                            expresion += evaExpression.get(j) + " ";
                            cont++;
                        }
                        else
                            flag = false;
                    }
                    i += cont;
                }
            }
            newInstructions.add(expresion); //Añadir todas las instrucciones que ya fueron analizadas
        }
        for (String ins: newInstructions) //Agregar 
            result += operate(convertToArrayList(ins), sc.obtenerTipo(convertToArrayList(ins))) + "\n";
        //---
        if (instruccionesCreadas != 1){
            result = instruccionesCreadas + "\n";
        }
        return result;
    }
    //****************************************************************

    /*****************************************************************
     * crea una nueva variable entera
     * @param expresion
     */
    public String newVariable(ArrayList<String> expresion){
        String name = expresion.get(1);
        String value = "";
        for (int i = 2; i < expresion.size(); i++){
            String operation = "";
            if (isHere(instrucciones, expresion.get(i))){
                for (int j = i; j < expresion.size(); j++){
                    operation += expresion.get(j) + " ";
                }
                value = operate(convertToArrayList(operation), sc.obtenerTipo(convertToArrayList(operation)));
                i = expresion.size();
            }
            else 
                value = expresion.get(i);
        }

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
    private String verifyVariable(String name){
        String variable = null;
        if(variables.containsKey(name))
            variable = variables.get(name);
        return variable;
    }
    //****************************************************************

    /*****************************************************************
     * devuelve las instrucciones
     * @return
     */
    public ArrayList<String> getInstrucciones(){
        return this.instrucciones;
    }
    //****************************************************************

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
        boolean optimizar = false;

        for (int i = 2; i < oexpression.size(); i++) {

            //Optimizar la condicion
            if (numeroCondiciones == 2)
                if (operate(convertToArrayList(condition), sc.obtenerTipo(convertToArrayList(condition))).equals("verdadero"))
                    optimizar = true;
            
            //--- Condicion
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
            
            //Expresion positiva    
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

            //Expresion negativa
            else if (numeroCondiciones == 2 && positivo && !optimizar){
                if (isHere(getInstrucciones(),oexpression.get(i)))
                    negative = oexpression.get(i) + " ";
                String function = "";
                for (int j = i+1; j < oexpression.size(); j++){
                    if (isHere(instrucciones, oexpression.get(j))){
                        for (int k = j; k < oexpression.size(); k++){
                            function += oexpression.get(k) + " ";
                        }
                        instruccionesCreadas *= Integer.parseInt(oexpression.get(j+2));
                        negative += operate(convertToArrayList(function), sc.obtenerTipo(convertToArrayList(function)));
                    }
                    else{
                        negative += oexpression.get(j) + " ";
                    }
                }
                break;
            }
        }
        if (operate(convertToArrayList(condition), sc.obtenerTipo(convertToArrayList(condition))).equals("verdadero"))
            conditional = operate(convertToArrayList(positive), sc.obtenerTipo(convertToArrayList(positive))); //Condicion positiva
        else
            conditional = operate(convertToArrayList(negative), sc.obtenerTipo(convertToArrayList(negative))); //Condicion negativa
        return conditional;
    }
    //****************************************************************

    /*****************************************************************
     * verificar si es una instruccion
     * @param collection
     * @param evaluate
     * @return
     */
    private boolean isHere(ArrayList<String> collection, String evaluate){
        boolean here = false;
        for (int i = 0; i < collection.size() && here == false; i++)
            if (evaluate.contains(collection.get(i)))
                here = true;
        return here;
    }
    //****************************************************************

    /*****************************************************************
     * convertir un string a un arraylist
     * @param expression
     * @return
     */
    private ArrayList<String> convertToArrayList(String expression){
        String[] splitedExpression = expression.split(" ");
        ArrayList<String> evaExpression = new ArrayList<String>();
        for (int j = 0; j < splitedExpression.length; j++)
            evaExpression.add(splitedExpression[j]);
        return evaExpression;
    }
    //****************************************************************

    /*****************************************************************
     * encontrar las variables en una expresion y convertirlas a su valor
     * @param expresion
     * @return
     */
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
    //****************************************************************
}
