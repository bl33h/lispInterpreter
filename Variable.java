/**
 * Copyright (C), 2022-2023, The_Kiesling FabianJuarez182 bl33h
 * FileName: Variable
 * Author:   Fabian Juarez , Sara Echeverria , Jose Pablo Kiesling
 * Date:     26/02/2022
 * @author Jose Pablo Kiesling, Fabian Juarez y Sara Echeverria
 * @version:
        - Creacion: 26/02/2022
        - Ultima modificacion:
 */


public class Variable {
    //---------------------------PROPIEDADES--------------------------
    private String name;
    private int value;

    //---------------------------METODOS------------------------------
    /*****************************************************************
     * Crea una variable
     * @param name
     * @param value
     */
    public Variable(String name, int value){
        this.name = name;
        this.value = value;
    }
    //****************************************************************

    /*****************************************************************
     * retorna el valor de la variable
     * @return
     */
    public int getValue(){
        return this.value;
    }
    //****************************************************************

    /*****************************************************************
     * retorna el nombre de la variable
     * @return
     */
    public String getName(){
        return this.name;
    }
    //****************************************************************

    /*****************************************************************
     * override de toString para mostrar la instancia
     */
    public String toString(){
        return this.name + ": " + this.value;
    }
    //****************************************************************
}
