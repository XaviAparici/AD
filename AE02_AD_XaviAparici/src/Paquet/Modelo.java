package Paquet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Modelo {

	private String ficheroLectura;
	private String ficheroEscritura;
	
	public Modelo() {
		ficheroLectura = "AE02_T1_2_Streams_Groucho.txt";
		ficheroEscritura = "AE02_T1_2_Streams_Groucho2.txt";
	}

	public String getFicheroLectura() {
		return ficheroLectura;
	}


	public String getFicheroEscritura() {
		return ficheroEscritura;
	}

	public ArrayList<String> getContenido(String fichero){
		ArrayList<String> contenido = new ArrayList<String>();
		
		try {
			BufferedReader br;
			br = new BufferedReader(new FileReader (fichero));
			
			String aux;
			
			while((aux=br.readLine())!=null) {
				contenido.add(aux);
			}

			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return contenido;
	}
	
	public String ficheroLectura() {
		return ficheroLectura;
	}
	
	public String ficheroEscritura() {
		return ficheroEscritura;
	}
	
	public int buscarTexto(String textoBuscar, ArrayList<String> contenido) {
		String texto="";
		int cantidad=0;
		
		for(String linea : contenido) {
			texto = texto + linea + "\n";
		}
		while(texto.toLowerCase().indexOf(textoBuscar) > -1) {
			texto = texto.toLowerCase().substring(texto.toLowerCase().indexOf(textoBuscar) + textoBuscar.length(), texto.toLowerCase().length());
					cantidad++;
		}
		JOptionPane.showMessageDialog(new JFrame(), cantidad,"Cantidad", JOptionPane.INFORMATION_MESSAGE);
		return cantidad;
	}
	
	
	public void reemplazarTexto(String textoBuscar, String textoReemplazar, ArrayList<String> contenido) {
		
		try {
			File ficheroCopia = new File("AE02_T1_2_Streams_Groucho2.txt");
			FileWriter fw;
			fw = new FileWriter(ficheroCopia);
			BufferedWriter bw = new BufferedWriter(fw);
			
			String aux;
			
			for(String linea : contenido) {
				aux = linea.replaceAll(textoBuscar, textoReemplazar);
				bw.write(aux);
				bw.newLine();
			}
			
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
	
}
