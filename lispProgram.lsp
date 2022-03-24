(setq var 10)
(* (+ 5 6) (+ 4 9))
(+ 5 8)
(+ (* 9 4) (* 3 2))
(* (* 9 4) (* 3 2))
(- (* 9 4) (* 3 2))
(+ var 5)
(quote 5)
(' Kiesling pijije)
(< 10 5)
(equals 10 10)
(Atom x)
(Atom (1 2 3))
(List (x))
(List (1 2 3))

ArrayList<String> instrucciones = {"equals", "print" "+", "fibonacci"}

(COND (equals 1 + 1 2)(print "si")(print "no"))
[COND, equals, 1, +, 1, 2, print, "si", print, "no"]

condition = equals 1 3
positive = print "si"
negative = print "no"

String condition = oexpression.get(1) + " "
int numeroCondiciones = 0;
boolean positivo = false
for (int i = 2; i < oexpression.length; i++) {
    if (!oexpression.get(i).contains(instrucciones)){
        condition += oexpression.get(i) + " ";
        numeroCondiciones++;
    } else if(numeroCondiciones != 2){ 
                expression = oexpression.get(i) + " "
                boolean bandera = true;
                for (int j = i+1; j < oexpression.length && bandera; i++){
                    if (!oexpression.get(j).contains(instrucciones))
                        expression += oexpression.get(j) + " ";
                    else if (oexpression.get(j).contains(instrucciones))
                        bandera = false;
                }    
                String[] expressionSplited = expression.split(" ");
                ArrayList<String> evaExpression = new ArrayList<String>();
                for (int i = 0; i <expressionSplited.length; i++)
                    evaExpression.put(expressionSplited[i]);
                condition += interpreteEvaluar(evaExpression, sc.ObtenerTipo(evaExpression))
                numeroCondiciones++;
     } else if (numeroCondiciones == 2 && !positivo){
        String positive = oexpression.get(i) + " ";
        boolean bandera = true;
                for (int j = i+1; j < oexpression.length && bandera; i++){
                    if (!oexpression.get(j).contains(instrucciones))
                        positive += oexpression.get(j) + " ";
                    else if (oexpression.get(j).contains(instrucciones))
                        bandera = false;
                } 
        positivo = true;
     } else if (numeroCondiciones == 2 && positivo){
        String negative = oexpression.get(i) + " ";
        boolean bandera = true;
                for (int j = i+1; j < oexpression.length && bandera; i++){
                    if (!oexpression.get(j).contains(instrucciones))
                        positive += oexpression.get(j) + " ";
                    else if (oexpression.get(j).contains(instrucciones))
                        bandera = false;
                } 
     }
     if (interpreteEvaluar(Acondition, sc.ObtenerTipo(Acondition)).equals("verdadero")){
         interpreteEvaluar(Apositivo, sc.ObtenerTipo(Apositivo))
     } else
        interpreteEvaluar(Anegativo, sc.ObtenerTipo(Anegativo))
}
