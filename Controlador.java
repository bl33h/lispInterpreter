/**
 * Copyright (C), 2022-2023, The_Kiesling FabianJuarez SaraEcheverria
 * FileName: View
 * Author:   Fabian Juarez , Sara Echeverria , Jose Pablo Kiesling
 * Date:     26/02/2022
 * @author Jose Pablo Kiesling, Fabian Juarez y Sara Echeverria
 * @version:
        - Creacion: 26/02/2022
        - Ultima modificacion:
 */

import java.util.ArrayList;

public class Controlador{
    public static void main(String[] args){
        Scan sc = new Scan();
        Interprete miInterprete = new Interprete();
        Variable variable;
        
        sc.Bienvenida();
        ArrayList<String> tokens = sc.read();
        System.out.println(sc.tokens(tokens));
        variable = miInterprete.operate(expresion, sc.obtenerTipo(expresion));
        // conversion y otras acciones pendientes
        System.out.println(variable);

    }
}
