import java.io.*;


public class authLogAnalyzer {

	public static void main(String[] args) throws Exception {
		FileInputStream leebyte;
		InputStreamReader leechar;
		BufferedReader lee;
		leebyte = new FileInputStream(args[0]);
		leechar = new InputStreamReader(leebyte);
		lee = new BufferedReader(leechar);

		//Primero, una lectura para crear los arrays con un tamaño igual al número de lineas del archivo
		String linea=lee.readLine();
		int numlin=0;
		while (linea!=null){
			numlin++;
			linea=lee.readLine();
		}
		lee.close();
		
		leebyte = new FileInputStream(args[0]);
		leechar = new InputStreamReader(leebyte);
		lee = new BufferedReader(leechar);
		
	
		String[] palabras;
		String[] ips;
		ips=new String[numlin];
		int i;
		int numips=0;
		boolean existe;
		
		linea=lee.readLine();
		while (linea!=null){
			palabras=linea.split(" ");
			if (palabras[5].equals("Invalid")){
				i=0;	
				existe=false;

						while (ips[i]!=null){
								if(palabras[palabras.length-1].equals(ips[i])){
								
								existe=true;
								break;
								}
							i++;
						}
						if(existe==false){
					ips[numips]=palabras[9];
					numips++;
						}
			}
			linea=lee.readLine();
		}
		lee.close();
		//Sabemos el numero total de ips que nos atacan: numips
		
		String[] atacantes=new String[numips];
		Integer[] contadores=new Integer[numips];
		for (int q=0;q<numips;q++){contadores[q]=0;}
		int total=0;
		String dia="";
		int contdia=1;
		leebyte = new FileInputStream(args[0]);
		leechar = new InputStreamReader(leebyte);
		lee = new BufferedReader(leechar);
		
		FileOutputStream escribebyte;
		OutputStreamWriter escribechar;
		BufferedWriter escribe;
		escribebyte = new FileOutputStream(args[1]);
		escribechar = new OutputStreamWriter(escribebyte);
		escribe = new BufferedWriter(escribechar);
		
		linea=lee.readLine();
		while(linea!=null){
			palabras=linea.split(" ");
			if (palabras[5].equals("Invalid")){
				
				if (!(palabras[0]+" "+palabras[1]).equals(dia)){
					if (!dia.equals("")){System.out.println(dia+" "+contdia+" entradas.");
					escribe.write(dia+" "+contdia+" entradas."+"\n");}
					dia=palabras[0]+" "+palabras[1];
				contdia=1;
				}else {contdia++;}
				
				
				
				total++;
				i=0;
				existe=false;
					while(atacantes[i]!=null){
						if(palabras[palabras.length-1].equals(atacantes[i])){
						contadores[i]=contadores[i]+1;	
						existe=true;
						break;
						}
						i++;
					}
					if(existe==false){
				atacantes[i]=palabras[9];
				contadores[i]=1;
					}
			}
			linea=lee.readLine();
		}
		
		lee.close();
		System.out.println(dia+" "+contdia+" entradas.");
		escribe.write(dia+" "+contdia+" entradas."+"\n");
		
		System.out.println();
		escribe.write("\n");
		
		for (int k=0;k<numips;k++){
		System.out.println(contadores[k]+" entradas desde la IP "+atacantes[k]);
		escribe.write(contadores[k]+" entradas desde la IP "+atacantes[k]+"\n");
		}
		System.out.println();
		escribe.write("\n");
		
		System.out.println(total);
		escribe.write(total+"\n");
		
		escribe.close();
	}

}
