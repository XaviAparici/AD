package APP;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Principal {
	
	public static void mostrarTitols(){
		// Carrega la configuracio i crea un session factory
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		// Obri una nova sessió de la session factory
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		//Recuperar llista d’objectes
		List llistaLlibre = new ArrayList();
		llistaLlibre = session.createQuery("FROM Llibre").list();
		for(int i=0 ; i<llistaLlibre.size() ; i++) {
			Llibre l = (Llibre) llistaLlibre.get(i);
			System.out.println(l.mostrarIdTitol());
		}
				
		//Commit de la transacció i tanca de sessió
		session.getTransaction().commit();
		session.close();
	}
	
	
	public static void detalls(int id) {
		// Carrega la configuracio i crea un session factory
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		// Obri una nova sessió de la session factory
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		//Recuperar un objecte a partir del seu id
		Llibre l = (Llibre) session.get(Llibre.class, id);
		System.out.println(l.mostrarDetalles());
				
		//Commit de la transacció i tanca de sessió
		session.getTransaction().commit();
		session.close();
	}
	
	public static void nouLlibre(String titol, String autor, String anyNaixement, String anyPublicacio, String editorial, String pagines) {
		// Carrega la configuracio i crea un session factory
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		// Obri una nova sessió de la session factory
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		//Crear nou objecte
		Llibre l = new Llibre(titol,autor,anyNaixement,anyPublicacio,editorial,pagines);
		Serializable id = session.save(l);
				
		//Commit de la transacció i tanca de sessió
		session.getTransaction().commit();
		session.close();
	}
	
	public static void actualitzaLlibre(int id,String titol, String autor, String anyNaixement, String anyPublicacio, String editorial, String pagines) {
		// Carrega la configuracio i crea un session factory
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		// Obri una nova sessió de la session factory
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		// Actualitza la información d’un objecte donat el seu id
		Llibre l = (Llibre) session.load(Llibre.class, id);
		l.setTitol(titol);
		l.setAutor(autor);
		l.setAnyNaixement(anyNaixement);
		l.setAnyPublicacio(anyPublicacio);
		l.setEditorial(editorial);
		l.setPagines(pagines);
		session.update(l);
				
		//Commit de la transacció i tanca de sessió
		session.getTransaction().commit();
		session.close();
	}
	
	public static void borraLlibre(int id) {
		// Carrega la configuracio i crea un session factory
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		// Obri una nova sessió de la session factory
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Llibre l = (Llibre) session.load(Llibre.class, id);
		l.setId(id);
		session.delete(l);
			
		//Commit de la transacció i tanca de sessió
		session.getTransaction().commit();
		session.close();
	}

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
	
		int n=0;
		int id;
		String titol, autor, anyNaixement, anyPublicacio, editorial, pagines;
		
		while(n!=6) {
			System.out.println("1.Mostrar tots els títols de la biblioteca");
			System.out.println("2.Mostrar la informació detallada d’un llibre a partir del seu id.");
			System.out.println("3.Afegir un nou llibre a la biblioteca.");
			System.out.println("4.Modificar atributs d’un llibre a partir del seu id.");
			System.out.println("5.Esborrar un llibre a partir del seu id.");
			System.out.println("6.Salir");
			System.out.println("\nIntroduce una opcion:");
			n = teclado.nextInt();
			
			switch(n) {
			case 1:
				mostrarTitols();
				break;
				
			case 2:
				System.out.println("Introdueix el id a mostrar:");
				id = teclado.nextInt();
				detalls(id);
				break;
				
			case 3:
				System.out.println("Introdueix el titol:");
				titol = teclado.next();
				System.out.println("Introdueix el autor:");
				autor = teclado.next();
				System.out.println("Introdueix el any naixement:");
				anyNaixement = teclado.next();
				System.out.println("Introdueix el any publicacio:");
				anyPublicacio = teclado.next();
				System.out.println("Introdueix el editorial:");
				editorial = teclado.next();
				System.out.println("Introdueix el pagines:");
				pagines = teclado.next();
				nouLlibre(titol,autor,anyNaixement,anyPublicacio,editorial,pagines);
				break;
				
			case 4:
				System.out.println("Introdueix el id a actualitzar:");
				id = teclado.nextInt();
				System.out.println("Introdueix el titol:");
				titol = teclado.next();
				System.out.println("Introdueix el autor:");
				autor = teclado.next();
				System.out.println("Introdueix el any naixement:");
				anyNaixement = teclado.next();
				System.out.println("Introdueix el any publicacio:");
				anyPublicacio = teclado.next();
				System.out.println("Introdueix el editorial:");
				editorial = teclado.next();
				System.out.println("Introdueix el pagines:");
				pagines = teclado.next();
				actualitzaLlibre(id,titol,autor,anyNaixement,anyPublicacio,editorial,pagines);
				break;
				
			case 5:
				System.out.println("Introdueix el id a borrar:");
				id = teclado.nextInt();
				borraLlibre(id);
				break;
			}
		}
		
	}

}
