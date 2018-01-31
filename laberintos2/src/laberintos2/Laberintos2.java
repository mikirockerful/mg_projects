package laberintos2;

import java.io.*;
public class Laberintos2 {
	

	public static void main(String[] args) throws Exception {
			String linea= new String();
			String [] elementos;
			int tam = 0;
			int fila=0;
			boolean resuelto=false;
			Integer [] entrada = new Integer[2];
			Integer [] salida = new Integer[2];
			BufferedReader leer = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
			//Primero, vamos a descubrir el tamaño del laberinto
			linea=leer.readLine();
			while (linea!=null){
				tam=tam+1;
				linea=leer.readLine();
			}
			//Volvemos al principio del archivo
			leer.close();
			leer = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
			
			//Ahora, vamos a convertir a matriz de enteros el laberinto
			
			int [][] laberinto = new int[tam][tam]; //Creamos la matriz laberinto
			boolean [][] correctPath = new boolean[tam][tam]; //El lab cuando lo resolvamos
		    boolean wasHere [][]= new boolean [tam][tam];
			
				linea=leer.readLine();
				while (linea!=null){
					elementos = linea.split(" ");
						for (int i=0;i<elementos.length;i++) {
							laberinto[fila][i]= Integer.parseInt(elementos[i]);
							if (elementos[i].equals("2")){entrada[0]=fila;
												entrada[1]=i;}
							if (elementos[i].equals("3")){salida[0]=fila;
							salida[1]=i;}
						}
					fila=fila+1;
					linea=leer.readLine();	
				}
			
		leer.close();
		
			//En este punto, tenemos el laberinto bien definido.
		
		@SuppressWarnings("unused")
		boolean b= recursiveSolve(entrada[0],entrada[1],wasHere,correctPath,laberinto,tam,tam,salida[0],salida[1]);
		for (int i=0;i<correctPath.length;i++){
			for (int j=0;j<correctPath.length;j++){
			if (correctPath[i][j]==true){resuelto=true;}}}
		
		//Pintamos por pantalla el laberinto, y el resuelto.
		
		
		for (int i=0;i<tam;i++){ 
			for (int j=0;j<tam;j++){
				System.out.printf("%d", laberinto[i][j]);
			}
			System.out.printf("\n");
		}
		
		if (resuelto){
		
		System.out.println();
		for (int i=0;i<tam;i++){ 
			for (int j=0;j<tam;j++){
				if (correctPath[i][j] && !(i==entrada[0] && j==entrada[1])){
					System.out.printf("%s", "*");
					} else if ((i==entrada[0] && j==entrada[1])) {
						System.out.printf("%s", "2");
					}
					else {
					System.out.printf("%s", laberinto[i][j]);
					}
				}
			System.out.println();
			}
		}else{
			System.out.println();
			System.out.println("El laberinto no tiene solución");
		}
	}
	
	
		
		public static boolean recursiveSolve(int x, int y, boolean [][] wasHere,boolean [][] correctPath, int [][] maze, int width, int height, int endx,int endy) {
		    if (x == endx && y == endy) return true; // If you reached the end
		    if (maze[x][y] == 1 || wasHere[x][y]) return false;  
		    // If you are on a wall or already were here
		    wasHere[x][y] = true;
		    if (x != 0) // Checks if not on left edge
		        if (recursiveSolve(x-1, y,wasHere,correctPath,maze,width,height,endx,endy)) { // Recalls method one to the left
		            correctPath[x][y] = true; // Sets that path value to true;
		            return true;
		        }
		    if (x != width - 1) // Checks if not on right edge
		        if (recursiveSolve(x+1, y,wasHere,correctPath,maze,width,height,endx,endy)) { // Recalls method one to the right
		            correctPath[x][y] = true;
		            return true;
		        }
		    if (y != 0)  // Checks if not on top edge
		        if (recursiveSolve(x, y-1,wasHere,correctPath,maze,width,height,endx,endy)) { // Recalls method one up
		            correctPath[x][y] = true;
		            return true;
		        }
		    if (y != height- 1) // Checks if not on bottom edge
		        if (recursiveSolve(x, y+1,wasHere,correctPath,maze,width,height,endx,endy)) { // Recalls method one down
		            correctPath[x][y] = true;
		            return true;
		        }
		    return false;
	}
}
