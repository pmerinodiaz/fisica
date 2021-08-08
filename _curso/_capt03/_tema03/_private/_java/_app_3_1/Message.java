//
//  Message.java
//  Copyright (c) 2001, Patricio Merino Díaz
//  Todos los derechos reservados.
//  
//  No se asume ninguna  responsabilidad por el  uso o  alteración  de este
//  software.  Este software se proporciona como es y sin garantía de ningún
//  tipo de su funcionamiento y en ningún caso sera el autor responsable de
//  daños o perjuicios que se deriven del mal uso del software,  aun cuando
//  este haya sido notificado de la posibilidad de dicho daño.
// 
//  Compilador: javac 1.1.2
//  Autor     : Patricio Merino Díaz
//  Creación  : 20-Julio-2001
// 
//  -------------------------------------------------------------------------
//  Esta información no es necesariamente definitiva y está  sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//  -------------------------------------------------------------------------
//

import java.awt.*;
import java.awt.event.*;

//
// Clase que se extiende de la clase Frame y que muestra la aplicación
// que contiene un botón y una etiqueta. La única razón de implementar
// esta aplicación fué que se puede mostrar al momento de ocurrir una
// no corrección de los datos ingresados.
//
public class Message extends Frame 
{
	// Posición X inicial.
	private int x;
	
	// Posición Y inicial.
	private int y;
	
	// Anchura del frame.
	private int ancho;
  
	// Altura del frame.
	private int alto;
	
	// Mensage que se muestra al usuario.
	private String msg;
	
	// Paneles para incorporar los componentes AWT;
	private Panel panel1;
	private Panel panel2;
	
	// Componentes AWT que son usados.
	private Button aceptar;
	private Label etiqueta;
			
  //-------------------------------------------------------------------------//
	// Nombre   : Message(...)                                                 //
	// Entradas : Las coordenadas de despliegue del frame y el mensaje         //
	// Salidas  : No tiene                                                     //
	// Descripc : Inicializa el Frame. Setea su tamaño, color de fodo y llama  //
	//            a los métodos que setean los componentes.                    //
  //-------------------------------------------------------------------------//    
	public Message(int x, int y, int ancho, int alto, String msg) 
	{
  	super("Advertencia");
		aceptar  = new Button("Aceptar");
		etiqueta = new Label(msg);
		panel1   = new Panel();
		panel2   = new Panel();
    panel1.add(aceptar);
    panel2.add(etiqueta);
    add("South", panel1);
		add("Center", panel2);
		setLocation(x, y);
		setSize(ancho, alto);
		setBackground(Color.lightGray);
		setResizable(false);
		aceptar.addActionListener(new ActionButton());
		addWindowListener(new WindowCierre());
	}
	
	private class ActionButton implements ActionListener
	{
	
		//-------------------------------------------------------------------------//
		// Nombre   : actionPerformed(...)                                                  //
		// Entradas : El evento sobre el botón                                     //
		// Salidas  : No tiene                                                     //
		// Descripc : Agregar una acción de cerrar el frame cuando se pulsa el     //
		//            botón aceptar                                                //
 	  //-------------------------------------------------------------------------//    
		public void actionPerformed(ActionEvent e) 
		{
    	Object x = e.getSource();
	  
			if (x == aceptar) hide();
		}
	}
	
	private class WindowCierre extends WindowAdapter
	{
	  //-------------------------------------------------------------------------//
	  // Nombre   : windowClosing(...)                                           //
	  // Entradas : El evento sobre el frame                                     //
	  // Salidas  : No tiene                                                     //
	  // Descripc : Agregar una acción de cerrar el frame.                       //
 	  //-------------------------------------------------------------------------//
	  public void windowClosing(WindowEvent e) 
	  {
    	hide();
	  }
	}	
}


