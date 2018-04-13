import java.io.IOException;
import java.io.PrintWriter;

import ucn.*;
public class main {
	static int[][] matriz = { { 2, 2, 1, 1, 1, 2, 2 }, 
			                  { 2, 2, 1, 1, 1, 2, 2 }, 
			                  { 1, 1, 1, 1, 1, 1, 1 }, 
			                  { 1, 1, 1, 0, 1, 1, 1 },
			                  { 1, 1, 1, 1, 1, 1, 1 },
			                  { 2, 2, 1, 1, 1, 2, 2 },
			                  { 2, 2, 1, 1, 1, 2, 2 } };

	public static void main(String[] args) throws IOException {
		//LEER PORFAVOR,el archivo de salida no pude enviar el string resultante en consola, ruego ver la consola porfavor y tener compasion :(
		resolver(matriz,"",1);
		
		
	}
	/**
	 * Metodo que usa backtracking para resolver el senku(solitario)
	 * @param matriz
	 * @param movimientos
	 * @param contador
	 * @return
	 * @throws IOException 
	 */
	public static boolean resolver(int matriz[][], String movimientos, int contador) throws IOException{
		
		int aux[][] = matriz;
		String aux2 = movimientos;
		int aux3 = contador;
		for(int i=0;i<7;i++){
			for(int j=0;j<7;j++){
				if(matriz[i][j]==2 || matriz[i][j]==0){ //Compruebo si la posicion actual es una ficha
					continue;
				}
				if(esValido(matriz,i,j,i-2,j)){ //Movimiento hacia la izquierda
					matriz[i-2][j]=1;
					matriz[i][j]=0;
					matriz[i-1][j]=0;
					movimientos = movimientos + "Movimiento" + contador + ":\n" + imprimirMovimientos(matriz) + "\n";
					if(!resolver(matriz,movimientos,contador+1)){//Si el camino no funciona y retorna falso, se devolvera todo a como estaba y continuara con el siguiente
						matriz[i-2][j]=0;
						matriz[i][j]=1;
						matriz[i-1][j]=1;
						movimientos = aux2;
						contador = aux3;
					} else return true;
					
				}
				
				if(esValido(matriz,i,j,i,j-2)){ //Movimiento hacia arriba
					
					matriz[i][j-2]=1;
					matriz[i][j]=0;
					matriz[i][j-1]=0;
					movimientos = movimientos + "Movimiento" + contador + ":\n" + imprimirMovimientos(matriz) + "\n";
					if(!resolver(matriz,movimientos,contador+1)){//Si el camino no funciona y retorna falso, se devolvera todo a como estaba y continuara con el siguiente
						matriz[i][j-2]=0;
						matriz[i][j]=1;
						matriz[i][j-1]=1;
						movimientos = aux2;
						contador = aux3;
					
						
					} else return true;
				}
				if(esValido(matriz,i,j,i+2,j)){ //Movimiento hacia la derecha
			
					matriz[i+2][j]=1;
					matriz[i][j]=0;
					matriz[i+1][j]=0;
					movimientos = movimientos + "Movimiento" + contador + ":\n" + imprimirMovimientos(matriz) + "\n";
					if(!resolver(matriz,movimientos,contador+1)){//Si el camino no funciona y retorna falso, se devolvera todo a como estaba y continuara con el siguiente
						matriz[i+2][j]=0;
						matriz[i][j]=1;
						matriz[i+1][j]=1;
						movimientos = aux2;
						contador = aux3;
					} else return true;
				}
				if(esValido(matriz,i,j,i,j+2)){ //Movimiento hacia abajo
					
					matriz[i][j+2]=1;
					matriz[i][j]=0;
					matriz[i][j+1]=0;
					movimientos = movimientos + "Movimiento" + contador + ":\n" + imprimirMovimientos(matriz) + "\n";
					if(!resolver(matriz,movimientos,contador+1)){//Si el camino no funciona y retorna falso, se devolvera todo a como estaba y continuara con el siguiente
						matriz[i][j+2]=0;
						matriz[i][j]=1;
						matriz[i][j+1]=1;
						movimientos = aux2;
						contador = aux3;
						
					} else return true;
				}
				int a = 0;
				for(int k=0;k<7;k++){
					for(int l=0;l<7;l++){
						if(matriz[k][l] == 1) a++;
					}
				}
				if(a == 1 && matriz[3][3]==1) {
					StdOut.println(movimientos);
					crearArchivo(movimientos);
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Metodo que imprime el tablero
	 * @param m
	 * @return
	 */
	public static String imprimirMovimientos(int m[][]){
	String movimiento = "";
	for(int i=0;i<matriz.length;i++){
		for(int j=0;j<matriz.length;j++){
			movimiento = movimiento + m[i][j];
		}
		movimiento = movimiento + "\n";
	}
	return movimiento;
	}

/**
 * Metodo que comprueba si se puede mover la pieza dependiendo de si es arriba abajo izquierda o derecha
 * @param matriz
 * @param xInicial
 * @param yInicial
 * @param xFinal
 * @param yFinal
 * @return
 */
public static boolean esValido(int matriz[][], int xInicial, int yInicial, int xFinal, int yFinal){
	if(xFinal>=0 && yFinal>=0 && xFinal<7 && yFinal<7){ //Comprueba si esta dentro del tablero
		if(matriz[xFinal][yFinal]==0 && matriz[(xInicial + xFinal)/2][(yInicial + yFinal)/2]== 1 ){ //Comprueba si es posible mover la ficha con las condiciones del juego
			return true;
		}
	}
	return false; //Si alguna no se cumple se retornada inmediatamente false y seguira con el siguiente camino
	}
/**
 * Metodo que escribe el string EN ASIATICO NOSE PORQUE
 * @param movimiento
 * @throws IOException
 */
public static void crearArchivo(String movimiento) throws IOException {
	PrintWriter salida = new PrintWriter("solucion.txt");
	salida.println(movimiento);
	salida.close();
	
	}
}
