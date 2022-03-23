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
        ArrayList<ArrayList<String>> tokens = new ArrayList<ArrayList<String>>();
        String variable;
        
        sc.Bienvenida();
        ArrayList<String> expresions = sc.read();
        //Lectura y separación por tokens
        for (String s: expresions)
            tokens.add(sc.tokens(s));
        System.out.println(tokens);

        //Evaluate
        for (ArrayList<String> al: tokens){
            System.out.println(al);
            String expresion = "";
            for (String s: al)
                expresion += s + " ";
            System.out.println(expresion);
            variable = miInterprete.operate(expresion, sc.obtenerTipo(al));
            sc.escribir(variable.toString());
        }
        
    }
}
