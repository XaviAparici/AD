
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;



public class principal {
	
	public static void getInformacio(File directorio) {
		//mostrem el nom del fitxer
		System.out.println("Nom: "+directorio.getName()); 
		
		//comprobem si es un fitxer o un directori
		if(directorio.isFile()) { 
			System.out.println("Es un fitxer");
		}
		else {
			System.out.println("Es un directori");
		}
		
		//mostrem la ruta absoluta
		System.out.println("Ruta absoluta: "+directorio.getAbsolutePath()); 
		
		//mostrem la data de creació del fitxer
		String data = "yyyy-MM-dd hh:mm aa"; 
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(data);
		Date ultimaModificacion = new Date();
		System.out.println("La data es: "+ultimaModificacion);
		
		//comprobem si el fitxer es ocult
		System.out.println("Ficher oculto: "+directorio.isHidden()); 
		
		//comprobem si es un fitxer y si es així mostrem la grandària en bytes
		if(directorio.isFile()) {
			System.out.println("La gràndaria en bytes es: "+directorio.length());
		}
		
		//comprobem si es un directori i mostrem el nom d'elements, espai lliure,
		//espai disponible i espai total
		if(directorio.isDirectory()) {
			//mostrem el contingut del directori
			String[] contingutDirectori = directorio.list();
			for (String string : contingutDirectori) {
				System.out.println(string);
			}
			
			//mostrem el espai lliure i el dividim per a mostrarlo en GB
			System.out.println("Espai lliure: "+directorio.getFreeSpace()/1024/1024/1024+" GB");
			
			//mostrem el espai disponible i el dividim per a mostrarlo en GB
			System.out.println("Espai disponible: "+directorio.getUsableSpace()/1024/1024/1024+" GB");
			
			//mostrem el espai total i el dividim per a mostrarlo en GB
			System.out.println("Espai total: "+directorio.getTotalSpace()/1024/1024/1024+" GB");
		}
	}
	
	public static void creaCarpeta(String nomCarpeta,String ruta) {
		//el metode recibeix el nom de la carpeta i la ruta aon volem crear-la i la crea
		File carpeta = new File(ruta,nomCarpeta);
		carpeta.mkdir();
		System.out.println("Directorio creado");
	}
	
	public static void creaFitxer(String nomFitxer,String ruta) throws IOException {
		//el metode recibeix el nom del fitxer i la ruta aon el volem crear i el crea
		File fitxer = new File(ruta,nomFitxer);
		fitxer.createNewFile();
		System.out.println("Fichero creado");
	}
	
	public static void elimina(String borraFC, String ruta) {
		//el metode a la ruta que li pasem borra el fitxer o carpeta que li diguem
		File fixOCarp = new File(ruta, borraFC);
		if(fixOCarp.delete()) {
			System.out.println("Eliminado");
		}
		else {
			System.out.println("No eliminado");
		}
		
	}
	
	public static void renomena(String nomNou, String nomVell, String ruta) {
		//el metode recibeix la ruta amb el nom antic i renomena el fitxer/directori amb el nou nom
		File ficherVell = new File(ruta, nomVell);
		File ficherNou = new File(ruta, nomNou);
		
		if(ficherVell.renameTo(ficherNou)) {
			System.out.println("Ficher renombrat");
		}
		else {
			System.out.println("Error");
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner teclado = new Scanner(System.in);
		File directorio = new File(args[0]);
		
		String menu;
		String nomCarpeta,ruta,nomFitxer,borraFC,nomNou,nomVell;
		
		System.out.println("***MENU***");
		System.out.println("1. Informacio");
		System.out.println("2. Crea Una Carpeta");
		System.out.println("3. Crea Un Fitxer");
		System.out.println("4. Elimina");
		System.out.println("5. Renomena");
		System.out.println("6. Ix Del Programa");
		
		System.out.println();
		System.out.println("Introdueix una opció:");
		menu = teclado.nextLine();
		
		
		switch(Integer.parseInt(menu)) {
		
		case 1:
			getInformacio(directorio);
			break;
			
		case 2:
			System.out.println("Introdueix el nom de la carpeta: ");
			nomCarpeta = teclado.nextLine();
			ruta = args[0];
			creaCarpeta(nomCarpeta,ruta);
			break;
			
		case 3:
			System.out.println("Introdueix el nom del fitxer: ");
			nomFitxer = teclado.nextLine();
			ruta = args[0];
			creaFitxer(nomFitxer,ruta);
			break;
			
		case 4:
			System.out.println("Introdueix el nom del fitxer o carpeta: ");
			borraFC = teclado.nextLine();
			ruta = args[0];
			elimina(borraFC,ruta);
			break;
			
		case 5:
			System.out.println("Introdueix el nom del fitxer/directori que vols renombrar: ");
			nomVell = teclado.nextLine();
			System.out.println("Introdueix el nou nom: ");
			nomNou = teclado.nextLine();
			ruta = args[0];
			renomena(nomNou,nomVell, ruta);
			break;
			
		case 6:
			break;
		
		}

	}

}
