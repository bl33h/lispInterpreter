/**
 * Copyright (C), 2022-2023, The_Kiesling FabianJuarez SaraEcheverria
 * FileName: View
 * Author:   Fabian Juarez , Sara Echeverria , Jose Pablo Kiesling
 * Date:     26/02/2022
 * @author Jose Pablo Kiesling, Fabian Juarez y Sara Echeverria
 * @version:
        - Creacion: 26/02/2022
        - Ultima modificacion: 25/03/2022
 */

import java.util.ArrayList;

public class Controlador{
    public static void main(String[] args){
        Scan sc = new Scan();
        Interprete miInterprete = new Interprete();
        ArrayList<ArrayList<String>> tokens = new ArrayList<ArrayList<String>>();
        String variable;
        
        sc.Bienvenida();
        ArrayList<String> expresions = sc.read();
        //Read
        for (String s: expresions)
            tokens.add(sc.tokens(s));

        //Loop
        for (ArrayList<String> al: tokens){
            if (al != null){
                String expresion = "";
                for (String s: al)
                    expresion += s + " ";
                sc.escribir(expresion);
                //Evaluate
                variable = miInterprete.operate(al, sc.obtenerTipo(al));
                //Print
                sc.escribir(variable.toString());
                sc.escribir("\n");
            } else
                sc.escribir("ERROR: Ha agregado una cantidad incorrecta de parentesis");
        }
        
    }
}
