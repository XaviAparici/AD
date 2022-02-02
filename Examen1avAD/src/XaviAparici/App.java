package XaviAparici;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		try {
			Scanner teclado = new Scanner(System.in);
			int id;
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(new File(".\\src\\XaviAparici\\config.xml"));
			NodeList nodeList = document.getElementsByTagName("configurations");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					String url = eElement.getElementsByTagName("url").item(0).getTextContent();
					String usuario = eElement.getElementsByTagName("user").item(0).getTextContent();
					String contraseña = eElement.getElementsByTagName("password").item(0).getTextContent();
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection(url,usuario,contraseña);
						
					System.out.println("Conexió realitzada amb éxit \n");
					
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT id,nom FROM preus");
					while(rs.next()) {
						System.out.println(rs.getString(1)+" "+rs.getString(2));
					}
					System.out.println("\nIntrodueix l'id d'un país per a mostrar el seu preu:");
					id = teclado.nextInt();
					
					String consulta="";
					ResultSet preu = stmt.executeQuery("SELECT nom,preu FROM preus WHERE id="+id);
					while(preu.next()) {
						System.out.println("El preu de "+preu.getString(1) +" es: "+preu.getString(2));
						consulta = "El preu de "+preu.getString(1) +" es: "+preu.getString(2);
					}
					
					preu.close();
					rs.close();
					stmt.close();
					con.close();
					
					
					DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm");
					String fecha = dtf4.format(LocalDateTime.now());
					
					String nombreFichero = "Consulta_"+fecha+".txt";
					File fichero = new File(nombreFichero);
					
					FileWriter fw = new FileWriter(fichero);
					BufferedWriter bw = new BufferedWriter(fw);
					
					bw.write(consulta);
					bw.close();
					fw.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
