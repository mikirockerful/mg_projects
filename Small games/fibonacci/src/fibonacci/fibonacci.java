package fibonacci;

import java.io.*;

public class fibonacci {
//Programa que halla el valor del numero de la serie de fibonacci que esta en la posicion dada por el usuario, de forma recursiva. ¡Derroche absoluto de recursos del pc.!
	public static int fibonaccirec (int n) {
		if (n==1 || n==2){
			return 1;
		} else {
		return fibonaccirec(n-1)+fibonaccirec(n-2);}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Introduce una posicion de la serie de Fibonacci");
		String input=teclado.readLine();
	int x=Integer.parseInt(input);
		System.out.println(fibonaccirec(x));
	}

}
