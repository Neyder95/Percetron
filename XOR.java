package xor;

import java.util.Random;

public class XOR {
    public static void main(String args[]){
        //Arreglo de valores XOR
        double [][] arregloXOR = {
                        {1, 1, 0},
                        {1, 0, 1},
                        {0, 1, 1},
                        {0, 0, 0}
                        };
     
        //Pesos Aleatorios
        double peso1X1;
        double peso2X1;
        double peso1X2;
        double peso2X2;
        double peso1Y1;
        double peso1Y2;
        double ancla;
        double ancla2;
        double ancla3;

        //Factor de Aprendizaje-cada iteracion va avanzar en en este factor de aprendizaje,el perceptron aprende mil veces lo que tiene que hacer
        double factorAprendizaje=0.5;
        
        //Definicion de Variables
        double errorDelta3=0;
        double errorDelta1=0;
        double errorDelta2=0;
        double y1=0;
        double y2=0;
        double y3=0;
        int fila=0;
        int iteraciones=1;
        
        while(fila<=3){//Ciclo que recorre las 4 filas de arregloXOR, es decir, este ciclo va a recorrer la matriz 
            //Asigna 0 a las variables para hacer los calculos desde el principio 
            y1=0;y2=0;y3=0;errorDelta3=0;errorDelta1=0;errorDelta2=0;iteraciones=0;
            
            //Asigna valores aleatorios entre 0 y 1 a todos los pesos
            peso1X1= new Random().nextDouble();
            peso2X1= new Random().nextDouble();
            peso1X2= new Random().nextDouble();
            peso2X2= new Random().nextDouble();
            peso1Y1= new Random().nextDouble();
            peso1Y2= new Random().nextDouble();
            ancla= new Random().nextDouble();
            ancla2= new Random().nextDouble();
            ancla3= new Random().nextDouble();
            
            while(iteraciones<=1000){//Ciclo que controla cuantas veces se realizan los calculos(ENTRENAMIENTO)
                
                //FORWARD PROPAGATION**********************
                //Calcula la Salida de las Neuronas de la capa oculta
                y1 = (arregloXOR[fila][0] * peso1X1) + (arregloXOR[fila][1] * peso1X2) + (1 * ancla);
                y2 = (arregloXOR[fila][0] * peso2X1) + (arregloXOR[fila][1] * peso2X2) + (1 * ancla2);

                //Implementa la funcion de activacion Sigmoide
                y1 = 1.0/(1 + Math.pow(Math.E, (-1) * y1));
                y2 = 1.0/(1 + Math.pow(Math.E, (-1) * y2));

                //Calcula la salida de la neurona 
                y3 = (y1 * peso1Y1) + (y2 * peso1Y2) + (1 * ancla3);
                //Implementa la funcion Sigmoide
                y3 = 1.0/(1 + Math.pow(Math.E, (-1) * y3));
                
                //BACKPROPAGATION**********************
                //Calcula el error de la neurona de Salida
                errorDelta3=(y3 * (1 - y3))*(arregloXOR[fila][2] - y3);

                //Ajusta los pesos de la neurona de salida
                peso1Y1 = peso1Y1 + (factorAprendizaje*errorDelta3 * y1);
                peso1Y2 = peso1Y2 + (factorAprendizaje*errorDelta3 * y2);
                ancla3 = ancla3 + (errorDelta3);

                //Calcula el error de las neuronas de capa oculta 
                errorDelta1=(y1 * (1 - y1)) * errorDelta3 - peso1Y1;
                errorDelta2=(y2 * (1 - y2)) * errorDelta3 -peso1Y2;

                //Ajusta los pesos de las neuronas de la capa oculta (Neurona 1)
                peso1X1 = peso1X1 + (factorAprendizaje*errorDelta1 * arregloXOR[fila][0]);
                peso1X2 = peso1X2 + (factorAprendizaje*errorDelta1 * arregloXOR[fila][1]);
                ancla = ancla + errorDelta1;
                //Ajusta los pesos de las neuronas de la capa oculta (Neurona 2)
                peso2X1 = peso2X1 + (factorAprendizaje*errorDelta2 * arregloXOR[fila][0]);
                peso2X2 = peso2X2 + (factorAprendizaje*errorDelta2 * arregloXOR[fila][1]);
                ancla2 = ancla2 + errorDelta2;
                iteraciones++;
            }
            //Imprime cada fila de arregloXOR al terminar el proceso
            System.out.println(""+(int)arregloXOR[fila][0]+"\tXOR\t"+(int)arregloXOR[fila][1]+"\t=\t" + (int)arregloXOR[fila][2]+"\tCalculado: "+y3);
            //incremento para la siguiente fila
            fila++;
        }
    }
}