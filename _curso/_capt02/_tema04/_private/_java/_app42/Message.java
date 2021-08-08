//
//  Message.java
//  Copyright (c) 2001, Patricio Merino D�az
//  Todos los derechos reservados.
//  
//  No se asume ninguna  responsabilidad por el  uso o  alteraci�n  de este
//  software.  Este software se proporciona como es y sin garant�a de ning�n
//  tipo de su funcionamiento y en ning�n caso sera el autor responsable de
//  da�os o perjuicios que se deriven del mal uso del software,  aun cuando
//  este haya sido notificado de la posibilidad de dicho da�o.
// 
//  Compilador: javac 1.1.2
//  Autor     : Patricio Merino D�az
//  Creaci�n  : 20-Julio-2001
// 
//  -------------------------------------------------------------------------
//  Esta informaci�n no es necesariamente definitiva y est� sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//  -------------------------------------------------------------------------
//

import java.awt.*;
import java.awt.event.*;

//
// Clase que se extiende de la clase Frame y que muestra la aplicaci�n
// que contiene un bot�n y una etiqueta. La �nica raz�n de implementar
// esta aplicaci�n fu� que se puede mostrar al momento de ocurrir una
// no correcci�n de los datos ingresados.
//
public class Message extends Frame 
{
	// Posici�n X inicial.
	private int x;
	
	// Posici�n Y inicial.
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
	// Descripc : Inicializa el Frame. Setea su tama�o, color de fodo y llama  //
	//            a los m�todos que setean los componentes.                    //
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
		// Entradas : El evento sobre el bot�n                                     //
		// Salidas  : No tiene                                                     //
		// Descripc : Agregar una acci�n de cerrar el frame cuando se pulsa el     //
		//            bot�n aceptar                                                //
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
	  // Descripc : Agregar una acci�n de cerrar el frame.                       //
 	  //-------------------------------------------------------------------------//
	  public void windowClosing(WindowEvent e) 
	  {
    	hide();
	  }
	}	
}


