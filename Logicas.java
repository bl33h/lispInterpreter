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
public class Logicas<T>{
    
    /*****************************************************************
     * Metodo que realizara la igualacion de los parametros ingresados
     * @param valor Uno de los parametros a comparar
     * @param valor2 Segundo parametro que se compara
     * @return comparacion de si es verdadero o no la igualacion
     */
    public boolean equals(T valor, T valor2){
        return valor.equals(valor2);
    }
    //****************************************************************
    
        /*****************************************************************
     * Metodo que realizara la verificacion si el numero es menor que el otro
     * @param valor Uno de los parametros a comparar
     * @param valor2 Segundo parametro que se compara
     * @return comparacion de si es verdadero o no la operacion realizada
     */
    public Boolean menorComparar(int valorA, int valorB ){
        if(valorA < valorB){
            return true;
        }else{
            return false;
        }
    }
    //****************************************************************

            /*****************************************************************
     * Metodo que realizara la verificacion si el numero es mayor que el otro
     * @param valor Uno de los parametros a comparar
     * @param valor2 Segundo parametro que se compara
     * @return comparacion de si es verdadero o no la operacion realizada
     */
    public Boolean mayorComparar(int valorA, int valorB ){
        if(valorA > valorB){
            return true;
        }else{
            return false;
        }
    }
    //****************************************************************
}
