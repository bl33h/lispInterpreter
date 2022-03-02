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
     * push: agrega un elemento genérico
	 * @param value 
     */
	void push(T value);
	//****************************************************************
	
	/*****************************************************************
     * pull: retorna el último valor ingresado y lo elimina de la pila
     * @return T
     */
	T pull();
	//****************************************************************
	
	/*****************************************************************
     * peek: retorna el último valor de la pila
     * @return T
     */
	T peek();
	//****************************************************************
	
	/*****************************************************************
     * count: retorna el número de elementos en la pila
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