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

public class Controlador{
    public static void main(String[] args){
        Scan sc = new Scan();
        Interprete miInterprete = new Interprete();
        Variable variable;
        
        sc.Bienvenida();
        String dato = "";
        do {
            dato = sc.leer();
            variable = miInterprete.operate(dato, sc.obtenerTipo(dato));
            sc.escribir(variable.toString());
        }while(!dato.equals("exit"));

    }
}
