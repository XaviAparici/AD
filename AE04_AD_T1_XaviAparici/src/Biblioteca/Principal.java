package Biblioteca;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Principal {
	
	static Connection con;
	
	//lligFitxer
	//llig el fitxer de la ruta que li pase i va posant els datos en el ArrayList de Strings
	//Entrada: res
	//Eixida: ArrayList<String[]>
	public static ArrayList<String[]> lligFitxer () throws IOException{
		String ruta = ".\\src\\Biblioteca\\AE04_T1_4_JDBC_Dades.csv";
		String linea;
		int cont=0;
		ArrayList<String[]> lista = new ArrayList<>();
		
		BufferedReader br = new BufferedReader(new FileReader(ruta));
		
		while ((linea = br.readLine()) != null) {
			if(cont >= 1) {
				String[] llibres = linea.split(";");
				lista.add(llibres);
			}
			cont++;
		}
		br.close();
		return lista;
	}
	
	//escribirEnBD
	//agarra els datos del arrayList els posa en variables i els puja a la BBDD i mostra els datos que demana el enunciat per defecte
	//Entrada: String[] llibres
	//Eixida: void
	public static void escribirEnBD (String[] llibres) throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ae04_ad","root","");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String titol,autor,any_naixement,any_publicacio,editorial,n_pagines;
		
		int anyN=0, anyP=0;
		
		titol = llibres[0];
		autor = llibres[1];
		any_naixement = llibres[2];
		any_publicacio = llibres[3];
		editorial = llibres[4];
		n_pagines = llibres[5];
		
		if(titol.equals("")) {
			titol = "N.C.";
		}
		if(autor.equals("")) {
			autor = "N.C.";
		}
		if(any_naixement.equals("")) {
			any_naixement = "N.C.";
		}
		else {
			anyN = Integer.parseInt(any_naixement);
		}
		if(any_publicacio.equals("")) {
			any_publicacio = "N.C.";
		}
		else {
			anyP = Integer.parseInt(any_publicacio);
		}
		if(editorial.equals("")) {
			editorial = "N.C.";
		}
		if(n_pagines.equals("")) {
			n_pagines = "N.C.";
		}
		
		PreparedStatement psInsertar = con.prepareStatement("INSERT INTO llibres (titol,autor,any_naixement,any_publicacio,editorial,n_pagines) VALUES (?,?,?,?,?,?)");
		psInsertar.setString(1, titol);
		psInsertar.setString(2, autor);
		psInsertar.setString(3, any_naixement);
		psInsertar.setString(4, any_publicacio);
		psInsertar.setString(5, editorial);
		psInsertar.setString(6, n_pagines);
		int resultadoInsertar = psInsertar.executeUpdate();
		
		if(anyN<1950) {
			System.out.println("Titol: "+titol+" Autor: "+autor+" Any Publicació: "+any_publicacio);
		}
		if(anyP>2000) {
			System.out.println("Editorial que ha publicat en el s XXI: "+editorial);
		}
	}
	

	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub
		
		ArrayList<String[]> biblioteca = lligFitxer();
		
		for (int i = 0; i < biblioteca.size() ; i++) {
			escribirEnBD(biblioteca.get(i));
		}

	}

}
