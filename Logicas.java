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
public class Logicas{
    
    /*****************************************************************
     * Metodo que realizara la igualacion de los parametros ingresados
     * @param valor Uno de los parametros a comparar
     * @param valor2 Segundo parametro que se compara
     * @return comparacion de si es verdadero o no la igualacion
     */
    public String equals(String expresion){
        String valor = "";
        String valor2 = "";
        String expresionFinal ="";
        String[] expresionSplit = expresion.split(" ");
        int i =0;
        for(i =0; i <expresionSplit.length-1;i++){
            if(expresionSplit[i].equals("=")||expresionSplit[i].equals("equals")){
                i++;
                break;
            }
        }
        	valor = expresionSplit[i];
            valor2 = expresionSplit[i+1];
        if(valor.equals(valor2)){
            expresionFinal = "verdadero";
            return expresionFinal;
        }else{
            expresionFinal = "Falso";
            return expresionFinal;
        }
    }
    //****************************************************************

        /*****************************************************************
     * Metodo que realizara la verificacion si el numero es menor que el otro
     * @param expresion traera los 2 numeros a comparar
     * @return comparacion de si es verdadero o no la operacion realizada
     */
    public String menorComparar(String expresion){
        int valor = 0;
        int valor2 = 0;
        String expresionFinal ="";
        String[] expresionSplit = expresion.split(" ");
        int i =0;
        for(i =0; i <expresionSplit.length-1;i++){
            if(expresionSplit[i].equals("<")){
                i++;
                break;
            }
        }
        	valor = Integer.parseInt(expresionSplit[i]);
            valor2 = Integer.parseInt(expresionSplit[i+1]);
        if(valor < valor2){
            expresionFinal = "verdadero";
            return expresionFinal;
        }else{
            expresionFinal = "Falso";
            return expresionFinal;
        }
    }
    //****************************************************************

    /*****************************************************************
     * Metodo que realizara la verificacion si el numero es mayor que el otro
     * @param expresion traera los 2 numeros a comparar
     * @return comparacion de si es verdadero o no la operacion realizada
     */
    public String mayorComparar(String expresion){
        int valor = 0;
        int valor2 = 0;
        String expresionFinal ="";
        String[] expresionSplit = expresion.split(" ");
        int i = 0;
        for(i =0; i <expresionSplit.length-1;i++){
            if(expresionSplit[i].equals(">")){
                i++;
                break;
            }
        }
        	valor = Integer.parseInt(expresionSplit[i]);
            valor2 = Integer.parseInt(expresionSplit[i+1]);
        if(valor > valor2){
            expresionFinal = "verdadero";
            return expresionFinal;
        }else{
            expresionFinal = "Falso";
            return expresionFinal;
        }
    }
    //****************************************************************

    /*****************************************************************
     * metodo que devuelve si es atom la expresion
     * @param expresion
     * @return
     */
    public String isAtom(String expresion){
        String atom = "NIL";
        String[] values= expresion.split(" ");

        int i = 0;
        for(i=0; i < values.length ; i++)
        	if(values[i].contains("Atom")){
        		i++;
        		break;
        	}
        if((values.length-i)<=1) {
            atom = "T";
        }
        return atom;
    }
    //****************************************************************

    /*****************************************************************
     * metodo que devuelve si es list la expresion
     * @param expresion
     * @return
     */
    public String isList(String expresion){
        String list = "NIL";
        String[] values= expresion.split(" ");

        int i = 0;
        for(i=0; i < values.length ; i++)
        	if(values[i].contains("List")){
        		i++;
        		break;
        	}
        if((values.length-i)>1) {
            list = "T";
        }
        return list;
    }
    //****************************************************************

    
}