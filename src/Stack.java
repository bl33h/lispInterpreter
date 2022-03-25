/**
 * Copyright (C), 2022-2023, The_Kiesling FabianJuarez SaraEcheverria
 * FileName: View
 * Author:   Fabian Juarez , Sara Echeverria , Jose Pablo Kiesling
 * Date:     26/02/2022
 * @author Jose Pablo Kiesling, Fabian Juarez y Sara Echeverria
 * @version:
        - Creacion: 01/03/2022
        - Ultima modificacion: 25/03/2022
    JCF que servirá por sus operaciones a la optimización de código
 */

import java.util.Vector;

public class Stack <T>{ 

    //---------------------------PROPIEDADES--------------------------
    private Vector<T> vector;

    //---------------------------MÉTODOS------------------------------
    /*****************************************************************
     * Stack: instancia el vector
     */
    public Stack (){
        vector = new Vector<T>();
    }
    //****************************************************************

    /*****************************************************************
     * push: agrega un elemento genérico
     * @param value 
     */
    public void push(T value) {
        vector.add(value);
    }
    //****************************************************************

    /*****************************************************************
     * pull: retorna el último valor ingresado y lo elimina de la pila
     * @return T
     */
    public T pull() {
        T value;
        if (isEmpty()) //Si la pila está vacía
            value = null;
        else{ //Si se tiene algún elemento
            value = peek();
            vector.remove(vector.get(count()-1));
        }
        return value;
    }
    //****************************************************************

    /*****************************************************************
     * peek: retorna el último valor de la pila
     * @return T
     */
    public T peek() {
        return vector.lastElement();
    }
    //****************************************************************

    /*****************************************************************
     * count: retorna el número de elementos en la pila
     * @return int
     */
    public int count() {
        return vector.size();
    }
    //****************************************************************

    /*****************************************************************
     * isEmpty: retorna el estado de la pila
     * @return boolean
     */
    public boolean isEmpty() {
        return vector.isEmpty();
    }
    //****************************************************************
}

