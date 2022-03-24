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
    private HashMap<String, Integer> variables = new HashMap<String, Integer>();
    private Aritmeticas aritmeticas = new Aritmeticas();
    private Logicas logicas = new Logicas();
    private ArrayList<String> instrucciones = new ArrayList<String>(Arrays.asList("setq", "print", "+", "-", "*", "/", "'", "quote", ">", "<", "equals", "=", "Atom", "List", "Cond", "Defun"));
    private Scan sc = new Scan();
    private HashMap<String, ArrayList<String>> functions = new HashMap<String, ArrayList<String>>();
    private HashMap<String, LinkedHashMap<String, String>> parameters = new HashMap<String, LinkedHashMap<String, String>>();

    

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
        else if (option == 10){
            newFunction(convertToArrayList(expresion));
            return "";
        }
        else{
            if (isHere(instrucciones, oexpression.get(0)))
                return useFunction(convertToArrayList(expresion));
            else
                return "";
        }
    }
    //****************************************************************

    public void newFunction(ArrayList<String> oexpression){
        String name = oexpression.get(1); 
        this.instrucciones.add(name);
        ArrayList<String> instrucciones = new ArrayList<String>();
        LinkedHashMap<String, String> parametersFunction = new LinkedHashMap<String, String>();
        String[] parametersSplited = oexpression.get(2).trim().split(",");
        for(String parameter: parametersSplited)
            parametersFunction.put(parameter, "");
        this.parameters.put(name, parametersFunction);
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
        System.out.println("Nombre:" + name);
        System.out.println("parametros:" + parametersFunction);
        functions.put(name, instrucciones);
        for (int i=0; i < instrucciones.size(); i++)
            System.out.println(instrucciones.get(i));
    }

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
                parameters += operate(convertToArrayList(expresion), sc.obtenerTipo(convertToArrayList(expresion)));
            }
            else
                parameters += oexpression.get(i) + " ";
            
            
            newParameters.add(parameters);
        }
        //---

        //---Asignacion de parametros
        String[] parametersSplited = parameters.split(" ");
        ArrayList<String> instructions = functions.get(name);
        LinkedHashMap<String, String> parametersFunction = this.parameters.get(name);
        String instrucciones = "";
        
        for (int i = 0; i < instructions.size(); i++){
            instrucciones += instructions.get(i).trim() + " ";
        }
        System.out.println(instrucciones);
        if(parametersSplited.length == parametersFunction.size()){
            int i = 0;
            for(String parameter: parametersFunction.keySet()){
                parametersFunction.put(parameter, parametersSplited[i]);             
                instrucciones = instrucciones.replace(parameter, parametersFunction.get(parameter));
                i++;
            } 
        }
        System.out.println(instrucciones);
        //---

        ArrayList<String> evaExpression = convertToArrayList(instrucciones);
        ArrayList<String> newInstructions = new ArrayList<String>();

        System.out.println(evaExpression);

        for (int i = 0; i < evaExpression.size(); i++){
            String expresion = "";
            if (isHere(getInstrucciones(), evaExpression.get(i))){
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
            newInstructions.add(expresion);
        }
        for (String ins: newInstructions){
            result += operate(convertToArrayList(ins), sc.obtenerTipo(convertToArrayList(ins))) + "\n";
        }
        return result;
    }

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

    private int priority(String expression){
        if(expression.equals("Cond")){
            return 3;
        } else if (expression.equals("*")){
            return 2;
        } else
            return 1;
    }
}
