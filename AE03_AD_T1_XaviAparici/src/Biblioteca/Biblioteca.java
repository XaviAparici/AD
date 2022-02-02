package Biblioteca;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Biblioteca {
	
	private static ArrayList<Llibre> lista = new ArrayList<Llibre>();
	
	//recuperarTots
	//guarda els objectes del xml en un ArrayList
	//Entrada: res
	//Eixida: void
	public static void recuperarTots(){
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(new File("Biblioteca.xml"));
			NodeList nodeList = document.getElementsByTagName("libro");
			String id,titol,autor,any,editorial,pagines;
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					id = eElement.getElementsByTagName("id").item(0).getTextContent();
					titol = eElement.getElementsByTagName("titulo").item(0).getTextContent();
					autor = eElement.getElementsByTagName("autor").item(0).getTextContent();
					any = eElement.getElementsByTagName("anyoPubli").item(0).getTextContent();
					editorial = eElement.getElementsByTagName("editorial").item(0).getTextContent();
					pagines = eElement.getElementsByTagName("nPaginas").item(0).getTextContent();
					Llibre l = new Llibre(id,titol,autor,any,editorial,pagines);
					lista.add(l);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//crearLlibre
	//a partir d'un objecte Llibre que li pasem per parametres el anyadix a la llista i despres crida al metode escriuXML i retorna el id del llibre creat
	//Entrada: Objecte Llibre
	//Eixida: identificador (int)
	public static int crearLlibre(Llibre llibre) {
		lista.add(llibre);
		escriuXML();
		System.out.println("Llibre creat");
		return lista.size();
	}
	
	//escriuXML
	//escriu en un ficher xml la llista de objectes Llibre
	//Entrada: res
	//Eixida: void
	public static void escriuXML() {
		try {
			DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder build = dFact.newDocumentBuilder();
			Document doc = build.newDocument();
			Element raiz = doc.createElement("libros");
			doc.appendChild(raiz);
			for (Llibre l : lista) {
				Element llibre = doc.createElement("libro");
				raiz.appendChild(llibre);
				Element id = doc.createElement("id");
				id.appendChild(doc.createTextNode(String.valueOf(l.getId())));
				llibre.appendChild(id);
				Element titulo = doc.createElement("titulo");
				titulo.appendChild(doc.createTextNode(String.valueOf(l.getTitol())));
				llibre.appendChild(titulo);
				Element autor = doc.createElement("autor");
				autor.appendChild(doc.createTextNode(String.valueOf(l.getAutor())));
				llibre.appendChild(autor);
				Element anyoPubli = doc.createElement("anyoPubli");
				anyoPubli.appendChild(doc.createTextNode(String.valueOf(l.getAny())));
				llibre.appendChild(anyoPubli);
				Element editorial = doc.createElement("editorial");
				editorial.appendChild(doc.createTextNode(String.valueOf(l.getEditorial())));
				llibre.appendChild(editorial);
				Element nPag = doc.createElement("nPaginas");
				nPag.appendChild(doc.createTextNode(String.valueOf(l.getPagines())));
				llibre.appendChild(nPag);
			}
			TransformerFactory tranFactory = TransformerFactory.newInstance(); // Crear serializador
			Transformer aTransformer = tranFactory.newTransformer();
			aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1"); // Darle formato al documento
			aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			try {
				FileWriter fw = new FileWriter("Biblioteca.xml"); // Definir el nombre del fichero y guardar
				StreamResult result = new StreamResult(fw);
				aTransformer.transform(source, result);
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (TransformerException ex) {
			System.out.println("Error escribiendo el documento");
		} catch (ParserConfigurationException ex) {
			System.out.println("Error construyendo el documento");
		}
	}
	
	//recuperarLlibre
	//retorna un objecte Llibre a partir de un id
	//Entrada: identificador
	//Eixida: Objecte Llibre
	public static Llibre recuperarLlibre(int identificador) {
		int posicionRetornar = identificador-1;
		return lista.get(posicionRetornar);
	}
	
	//mostrarLlibre
	//mostra tots els atributs del objecte llibre que li pasem per parametre
	//Entrada: Un objecte Llibre
	//Eixida: void
	public static void mostrarLlibre(Llibre llibre) {
		System.out.println("Id: "+llibre.getId());
		System.out.println("Titol: "+llibre.getTitol());
		System.out.println("Autor: "+llibre.getAutor());
		System.out.println("Any: "+llibre.getAny());
		System.out.println("Editorial: "+llibre.getEditorial());
		System.out.println("Págines: "+llibre.getPagines());
	}
	
	//borrarLLibre
	//li pasem un llibre y desde la llista el borra
	//Entrada: Un objecte Llibre
	//Eixida: void
	public static void borrarLlibre(Llibre borrar) {
		lista.remove(borrar);
		escriuXML();
		System.out.println("Llibre borrat");
	}
	
	//actualitzaLlibre
	//demana els datos per teclat al usuari i els asigna al objecte llibre que li pasem per parametres
	//Entrada: Objecte Llibre
	//Eixida: void
	public static void actualitzaLlibre(Llibre actualizar) {
		Scanner teclado = new Scanner(System.in);
		String titol,autor,any,editorial,pagines;
		System.out.println("Introdueix el titol:");
		titol = teclado.next();
		System.out.println("Introdueix el autor:");
		autor = teclado.next();
		System.out.println("Introdueix el any:");
		any = teclado.next();
		System.out.println("Introdueix la editorial:");
		editorial = teclado.next();
		System.out.println("Introdueix les pagines:");
		pagines = teclado.next();
		actualizar.setTitol(titol);
		actualizar.setAutor(autor);
		actualizar.setAny(any);
		actualizar.setEditorial(editorial);
		actualizar.setPagines(pagines);
		escriuXML();
		System.out.println("Llibre actualitzat");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner teclado = new Scanner(System.in);

		int opcion;
		
		recuperarTots();//guarda els objectes del xml en una llista
		
		do {
			System.out.println("1.Mostrar tots els titols de la biblioteca");
			System.out.println("2.Mostrar informació detallada d'un llibre");
			System.out.println("3.Crear nou llibre");
			System.out.println("4.Actualizar llibre");
			System.out.println("5.Borrar llibre");
			System.out.println("6.Tancar la biblioteca");
			System.out.println("Introdueix una opció: ");
			
			opcion = teclado.nextInt();
			
			switch(opcion) {
			case 1:
				for(Llibre l: lista) {
					System.out.println("ID: "+l.getId());
					System.out.println("Titol: "+l.getTitol());
				}
				break;
				
			case 2:
				int identificador;
				System.out.println("Introdueix el id del llibre a mostrar:");
				identificador = teclado.nextInt();
				Llibre l = recuperarLlibre(identificador);
				mostrarLlibre(l);
				break;
				
			case 3:
				String titol,autor,any,editorial,pagines;
				
				System.out.println("Introdueix el titol:");
				titol = teclado.next();
				System.out.println("Introdueix el autor:");
				autor = teclado.next();
				System.out.println("Introdueix el any:");
				any = teclado.next();
				System.out.println("Introdueix la editorial:");
				editorial = teclado.next();
				System.out.println("Introdueix les pagines:");
				pagines = teclado.next();
				
				String id = String.valueOf(lista.size()+1);//
				Llibre llibre = new Llibre(id,titol,autor,any,editorial,pagines);
				crearLlibre(llibre);
				break;
				
			case 4:
				int idActualizar;
				System.out.println("Introdueix el id del llibre a actualitzar:");
				idActualizar = teclado.nextInt();
				Llibre actualizar = recuperarLlibre(idActualizar);
				actualitzaLlibre(actualizar);
				break;
				
			case 5:
				int idBorrar;
				System.out.println("Introdueix el id del llibre a borrar:");
				idBorrar = teclado.nextInt();
				Llibre borrar = recuperarLlibre(idBorrar);
				borrarLlibre(borrar);
				break;
			}
		}
			while(opcion!=6);
		
		
	}


}
