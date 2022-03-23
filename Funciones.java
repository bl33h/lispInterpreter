import java.util.HashMap;
import java.util.LinkedHashMap;
/**
 * Copyright (C), 2022-2023, The_Kiesling FabianJuarez SaraEcheverria
 * FileName: Funciones
 * Author:   Fabian Juarez , Sara Echeverria , Jose Pablo Kiesling
 * Date:     26/02/2022
 * @author Jose Pablo Kiesling, Fabian Juarez y Sara Echeverria
 * @version:
        - Creacion: 26/02/2022
        - Ultima modificacion:
    Clase que tiene como fin ser un
 */
public class Funciones {
    HashMap<String, String> functions = new HashMap<String, String>();
    HashMap<String, LinkedHashMap<String, String>> parameters = new HashMap<String, LinkedHashMap<String, String>>();

    public void newFunction(String name, String parameters, String instructions){
        LinkedHashMap<String, String> parametersFunction = new LinkedHashMap<String, String>();
        String[] parametersSplited = parameters.trim().split(",");
        for(String parameter: parametersSplited)
            parametersFunction.put(parameter, "");
        this.parameters.put(name, parametersFunction);
        functions.put(name, instructions);
    }

    public String useFunction(String name, String parameters){
        String translate = "";
        String[] parametersSplited = parameters.trim().split(",");
        if(functions.containsKey(name)){
            String instructions = functions.get(name);
            LinkedHashMap<String, String> parametersFunction = new LinkedHashMap<String, String>();
            if(parametersSplited.length == parametersFunction.size()){
                int i = 0;
                for(String parameter: parametersFunction.keySet()){
                    parametersFunction.put(parameter, parametersSplited[i]);
                    instructions = instructions.replace(parameter, parametersFunction.get(parameter));
                    i++;
                }
                translate = instructions;
            }
        }
        return translate;
    } 
}
