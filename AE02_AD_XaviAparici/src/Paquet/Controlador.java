package Paquet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Controlador {

	private Modelo modelo;
	private Vista vista;
	private ActionListener actionListenerBuscar, actionListenerReemplazar;
	
	public Controlador(Modelo modelo, Vista vista) {
		this.modelo = modelo;
		this.vista = vista;
		control();
	}
	
	public void control() {
		final ArrayList<String> contenido =  modelo.getContenido(modelo.ficheroLectura());
		mostrarContenido(contenido);
		
		actionListenerBuscar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String buscar = vista.getTextFieldBuscar().getText();
				modelo.buscarTexto(buscar, contenido);
			}
		};
		vista.getBtnBuscar().addActionListener(actionListenerBuscar);
		
		actionListenerReemplazar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String reemplazar = vista.getTextFieldReemplazar().getText();
				String buscar = vista.getTextFieldBuscar().getText();
				modelo.reemplazarTexto(buscar , reemplazar, contenido);
				mostrarFichero();
			}
		};
		vista.getBtnReemplazar().addActionListener(actionListenerReemplazar);
	}
	
	private void mostrarContenido(ArrayList<String> contenido) {
		for(String linea : contenido) {
			vista.getTextAreaOriginal().append(linea+"\n");
		}
	}
	
	private void mostrarContenidoCopia(ArrayList<String> contenido) {
		for(String linea : contenido) {
			vista.getTextAreaModificado().append(linea+"\n");
		}
	}
	
	private void mostrarFichero() {
		mostrarContenidoCopia(modelo.getContenido(modelo.ficheroEscritura()));
	}
}
