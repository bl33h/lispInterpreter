/**
 * Copyright (C), 2022-2023, The_Kiesling FabianJuarez SaraEcheverria
 * FileName: View
 * Author:   Fabian Juarez , Sara Echeverria , Jose Pablo Kiesling
 * Date:     26/02/2022
 * @author Jose Pablo Kiesling, Fabian Juarez y Sara Echeverria
 * @version:
        - Creacion: 01/03/2022
        - Ultima modificacion:
    Interfaz de pilas
 */

public interface IStack<T> { 

	/*****************************************************************
     * push: agrega un elemento generico
	 * @param value 
     */
	void push(T value);
	//****************************************************************
	
	/*****************************************************************
     * pull: retorna el ultimo valor ingresado y lo elimina de la pila
     * @return T
     */
	T pull();
	//****************************************************************
	
	/*****************************************************************
     * peek: retorna el ultimo valor de la pila
     * @return T
     */
	T peek();
	//****************************************************************
	
	/*****************************************************************
     * count: retorna el numero de elementos en la pila
     * @return int
     */
	int count();
	//****************************************************************
	
	/*****************************************************************
     * isEmpty: retorna el estado de la pila
     * @return boolean
     */
	boolean isEmpty();
	//****************************************************************
}