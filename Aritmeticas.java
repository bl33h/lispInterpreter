/**
 * Copyright (C), 2022-2023, The_Kiesling FabianJuarez SaraEcheverria
 * FileName: View
 * Author:   Fabian Juarez , Sara Echeverria , Jose Pablo Kiesling
 * Date:     26/02/2022
 * @author Jose Pablo Kiesling, Fabian Juarez y Sara Echeverria
 * @version:
        - Creacion: 26/02/2022
        - Ultima modificacion:
    Clase que tiene como fin realizar las operaciones aritmeticas
 */

import java.util.Arrays;
public class Aritmeticas {
    //--------------------------- PROPIEDADES --------------------------
    private Stack<Integer> stack;
    private String[] operands = {"0", "1", "2","3","4","5","6","7","8","9"};
    private String[] operators = {"+","-","*","/"};

    //--------------------------- METODOS ------------------------------
    public Integer Evaluate(String expresion){
        stack = new Stack<Integer>();
        boolean flag = true;
        int num1, num2, result = 0;
        String[] values = expresion.split(" "); //Separar los valores en la expresion
        
        for(int i = values.length-1; i >= 0; i--){ //Recorrer toda la expresion  
            if(Arrays.asList(operands).contains(values[i])) //Si es Numero
                stack.push(Integer.parseInt(values[i]));

            else if(Arrays.asList(operators).contains(values[i])){ //Si es Operador
                if (stack.count() >= 2){ //Si hay dos o más en la pila es porque se pueden hacer operaciones

                    //Sacar los dos últimos números ingresados
                    num1 = stack.pull();
                    num2 = stack.pull();
                    switch(values[i]){
                        case "+": //Suma
                            stack.push(num1 + num2);
                        break;

                        case "-": //Resta
                            stack.push(num2 - num1);
                        break;

                        case "*": //Multiplicacion
                            stack.push(num1 * num2);
                        break;

                        case "/": //División
                            if (num1 != 0) //Si el segundo numero es distinto de cero se puede hacer la division
                                stack.push(num2 / num1);
                            else{ //División indefinida
                                System.out.println("¡ERROR! División entre cero!");
                                return null;
                            }
                        break;

                    }
                }
                else{ //Faltan numeros
                    System.out.println("¡ERROR! Le han faltado operandos!");
                    flag = false;
                }
            }
            else{ //No ingreso numeros ni operadores
                System.out.println("¡ERROR! Ha ingresado un carácter invalido!");
                flag = false;
            }
        }
        if (stack.count() == 1 && flag) //Si ya solo queda un elemento en la pila
            result = stack.pull();
        else if (!flag) //Si queda mas de un elemento es porque faltaron operadores
            return null;
        else {
            System.out.println("¡ERROR! Le han faltado operadores!");
            flag = false;
        }
        if (flag) //Si la operacion es valida
            return result;
        else //Existió algún error respecto a la operación (sintaxis o logica)
            return null;
    }
}
