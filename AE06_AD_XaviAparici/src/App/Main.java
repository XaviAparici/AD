package App;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	//mostrarTitols
	//mostra tots els llibres (id + titol)
	//Entrada: res
	//Eixida: void
	public static void mostrarTitols(){
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Biblioteca");
		MongoCollection<Document> coleccion = database.getCollection("Llibre");
		
		MongoCursor<Document> cursor2 = coleccion.find().iterator();
		while (cursor2.hasNext()) {
			JSONObject obj = new JSONObject(cursor2.next().toJson());
			System.out.println(obj.getString("Id")+" "+obj.getString("Titol"));
		}
		System.out.println();
		
		mongoClient.close();
		
	}
	
	//detalls
	//mostra tots els atributs del llibre que li diguem
	//Entrada: String id
	//Eixida: void
	public static void detalls(String id) {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Biblioteca");
		MongoCollection<Document> coleccion = database.getCollection("Llibre");
		
		Bson query = eq("Id", id);
		MongoCursor<Document> cursor = coleccion.find(query).iterator();
		while (cursor.hasNext()) {
			System.out.println(cursor.next().toJson());
		}
		System.out.println();
		
		mongoClient.close();
	}
	
	//nouLlibre
	//crea un nou llibre amb els atributs que li pasem, la id agafa la ultima de la llista i li suma 1
	//Entrada: String titol, String autor, String anyNaixement, String anyPublicacio, String editorial, String pagines
	//Eixida: void
	public static void nouLlibre(String titol, String autor, String anyNaixement, String anyPublicacio, String editorial, String pagines) {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Biblioteca");
		MongoCollection<Document> coleccion = database.getCollection("Llibre");
		
		String id="";
		MongoCursor<Document> cursor = coleccion.find().iterator();
		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			id = obj.getString("Id");
		}
		int aux = Integer.valueOf(id);
		aux++;
		String idFinal = String.valueOf(aux);
		Document doc = new Document();
		doc.append("Id", idFinal);
		doc.append("Titol", titol);
		doc.append("Autor", autor);
		doc.append("Any_naixement", anyNaixement);
		doc.append("Any_publicacio", anyPublicacio);
		doc.append("Editorial", editorial);
		doc.append("Nombre_pagines", pagines);
		coleccion.insertOne(doc);
		System.out.println();
		
		mongoClient.close();
	}
	
	//actualitzaLlibre
	//actualitza el llibre que li diguem amb els atributs que li pasem
	//Entrada: String id,String titol, String autor, String anyNaixement, String anyPublicacio, String editorial, String pagines
	//Eixida: void
	public static void actualitzaLlibre(String id,String titol, String autor, String anyNaixement, String anyPublicacio, String editorial, String pagines) {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Biblioteca");
		MongoCollection<Document> coleccion = database.getCollection("Llibre");
		
		String titolVell="",autorVell="",anyNaixementVell="",anyPublicacioVell="",editorialVell="",paginesVell="";
		Bson query = eq("Id", id);
		MongoCursor<Document> cursor = coleccion.find(query).iterator();
		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			titolVell = obj.getString("Titol");
			autorVell = obj.getString("Autor");
			anyNaixementVell = obj.getString("Any_naixement");
			anyPublicacioVell = obj.getString("Any_publicacio");
			editorialVell = obj.getString("Editorial");
			paginesVell = obj.getString("Nombre_pagines");
		}
		coleccion.updateOne(eq("Titol", titolVell), new Document("$set", new Document("Titol", titol)));
		coleccion.updateOne(eq("Autor", autorVell), new Document("$set", new Document("Autor", autor)));
		coleccion.updateOne(eq("Any_naixement", anyNaixementVell), new Document("$set", new Document("Any_naixement", anyNaixement)));
		coleccion.updateOne(eq("Any_publicacio", anyPublicacioVell), new Document("$set", new Document("Any_publicacio", anyPublicacio)));
		coleccion.updateOne(eq("Editorial", editorialVell), new Document("$set", new Document("Editorial", editorial)));
		coleccion.updateOne(eq("Nombre_pagines", paginesVell), new Document("$set", new Document("Nombre_pagines", pagines)));
		System.out.println();
		
		mongoClient.close();
	}
	
	//borraLlibre
	//borra el llibre amb la id que li pasem
	//Entrada: String id
	//Eixida: void
	public static void borraLlibre(String id) {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Biblioteca");
		MongoCollection<Document> coleccion = database.getCollection("Llibre");
		
		coleccion.deleteOne(eq("Id", id));
		System.out.println("Llibre borrat amb éxit");
		System.out.println();
		
		mongoClient.close();
	}

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		int n=0;
		String id;
		String titulo="",autor="",naix="",publi="",edit="",nPag="";
		
		while(n!=6) {
			System.out.println("1.Mostrar tots els títols de la biblioteca");
			System.out.println("2.Mostrar la informació detallada d’un llibre a partir del seu id.");
			System.out.println("3.Afegir un nou llibre a la biblioteca.");
			System.out.println("4.Modificar atributs d’un llibre a partir del seu id.");
			System.out.println("5.Esborrar un llibre a partir del seu id.");
			System.out.println("6.Eixir");
			System.out.println("\nIntrodueix una opcio:");
			n = teclado.nextInt();
			
			switch(n) {
			case 1:
				mostrarTitols();
				break;
				
			case 2:
				System.out.println("Introdueix el id a mostrar:");
				id = teclado.next();
				detalls(id);
				break;
				
			case 3:
				System.out.println("Introdueix el titol:");
				titulo = teclado.next();
				System.out.println("Introdueix el autor:");
				autor = teclado.next();
				System.out.println("Introdueix el any de naixement:");
				naix = teclado.next();
				System.out.println("Introdueix el any de publicacio:");
				publi = teclado.next();
				System.out.println("Introdueix la editorial:");
				edit = teclado.next();
				System.out.println("Introdueix el numero de págines:");
				nPag = teclado.next();
				nouLlibre(titulo, autor, naix, publi, edit, nPag);
				break;
				
			case 4:
				System.out.println("Introdueix la id del llibre a actualitzar:");
				id = teclado.next();
				System.out.println("Introdueix el titol:");
				titulo = teclado.next();
				System.out.println("Introdueix el autor:");
				autor = teclado.next();
				System.out.println("Introdueix el any de naixement:");
				naix = teclado.next();
				System.out.println("Introdueix el any de publicacio:");
				publi = teclado.next();
				System.out.println("Introdueix la editorial:");
				edit = teclado.next();
				System.out.println("Introdueix el numero de págines:");
				nPag = teclado.next();
				actualitzaLlibre(id, titulo, autor, naix, publi, edit, nPag);
				break;
				
			case 5:
				System.out.println("Introdueix el id a borrar:");
				id = teclado.next();
				borraLlibre(id);
				break;
			}
		}

	}

}
