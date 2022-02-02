package app;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;

public class main {

	public static void main(String[] args) {
		
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("luchadores");
		MongoCollection<Document> coleccion = database.getCollection("luchador");
		
		//como insertar un objeto
		Document doc = new Document();
		doc.append("nombre", "juan");
		doc.append("victorias", "1");
		doc.append("derrotas", "11");
		coleccion.insertOne(doc);
		
		//llig uno per uno tots els objectes
		MongoCursor<Document> cursor = coleccion.find().iterator();
		while (cursor.hasNext()) {
			System.out.println(cursor.next().toJson());
		}
		
		//actualizar un objecte
		coleccion.updateOne(eq("nombre", "juan"), new Document("$set", new Document("nombre", "juanito")));
		
		//ensenya el camp sols que li diguem
		MongoCursor<Document> cursor2 = coleccion.find().iterator();
		while (cursor2.hasNext()) {
			JSONObject obj = new JSONObject(cursor2.next().toJson());
			System.out.println(obj.getString("nombre"));
		}

		//borra el camp que li diguem
		coleccion.deleteOne(eq("nombre", "juanito"));
		
		mongoClient.close();

	}

}
