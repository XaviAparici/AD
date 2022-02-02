package Paquet;

import Paquet.Controlador;
import Paquet.Modelo;
import Paquet.Vista;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Modelo modelo = new Modelo();
		Vista vista = new Vista();
		Controlador controlador = new Controlador(modelo,vista);
	}

}
