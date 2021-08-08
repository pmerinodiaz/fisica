//  
//  Arcos.java
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
//  Creaci�n  : 25-Julio-2001
// 
//  -------------------------------------------------------------------------
//  Esta informaci�n no es necesariamente definitiva y est� sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//  -------------------------------------------------------------------------
//

import java.awt.*;
import java.applet.*;
import java.awt.event.*;

// 
// Clase derivada de la clase Applet y que implementa la clase
// Action Listener. Esta clase tiene por funcionalidad dibujar
// arcos ingresados por el usuario. En formato de radianes o
// de grados. Adem�s permite pintar o dibujar el arco.
//

public class Arcos extends Applet implements ActionListener
{
	// Ancho del Applet.
	public final int ancho = 380;
	
	// Alto del Applet.
	public final int alto  = 480;
	
	// Color de fondo del Applet.
	public final Color fondo = new Color(255, 204, 0);
	
	// Radio de la circunferencia.
	public final int radio = 125;
	
	// Componentes AWT que permiten interactuar con el usuario.
	private Label lbTit, lbIni, lbFin;
	private Checkbox chRad, chGrad;
	private CheckboxGroup chModo;
	private TextField tfIni, tfFin;
	private Button btDib, btPint;
	
	// Variables que sirven para los �ngulos. 
	private double ini, fin;
	
	// Opci�n de dibujar o pintar el arco.
	private boolean tipoDibuja = true;
	
	// Variable que sirve para manejar los errores.
	private String error;
		
	//-------------------------------------------------------------------------//
	// Nombre   : init()                                                       //
	// Entradas : No tiene                                                     //
	// Salidas  : No tiene                                                     //
	// Descripc : Inicializa el Applet. Setea su tama�o, color de fodo y llama //
	//            a los m�todos que setean los componentes.                    //
  //-------------------------------------------------------------------------//  
	public void init()
	{
		setLayout(null);
		setBackground(fondo);
		setComponentes();
		resize(ancho, alto);
	}

	//-------------------------------------------------------------------------//
	// Nombre   : setComponentes()                                             //
	// Entradas : No tiene                                                     //
	// Salidas  : No tiene                                                     //
	// Descripc : LLamar a los m�todos que inicializan los componentes AWT     //
	//            del Applet.                                                  //
  //-------------------------------------------------------------------------//
	private void setComponentes()
	{
		lbTit  = new Label("Ingrese los datos");
		lbIni  = new Label("Comenzar en:");
		lbFin  = new Label("Terminar en:");
		
		chModo = new CheckboxGroup();
		chGrad = new Checkbox("Grados  ", chModo, true);
		chRad  = new Checkbox("Radianes", chModo, false);
		
		tfIni = new TextField("0");
		tfFin = new TextField("180");
		
		btDib  = new Button("Dibujar");
		btPint = new Button("Pintar");
		
		chGrad.setBounds(150, 20, 100, 20);
		chRad.setBounds (150, 40, 100, 20);
		
		lbTit.setBounds(15,   0, 100, 20);
		lbIni.setBounds(60,  70, 80, 20); 
		lbFin.setBounds(60, 100, 80, 20); 
		
		tfIni.setBounds(150,  70, 110, 20); 
		tfFin.setBounds(150, 100, 110, 20); 
		
		btDib.setBounds (90,  140, 80, 20); 
		btPint.setBounds(180, 140, 80, 20); 
		
		btDib.addActionListener(this);
		btPint.addActionListener(this);
		
		add(chGrad);
		add(chRad);
		add(lbTit);
		add(lbIni);
		add(lbFin);
		add(tfIni);
		add(tfFin);
		add(btDib);
		add(btPint);
	}	
	
	//-------------------------------------------------------------------------//
	// Nombre   : paint(...)                                                   //
	// Entradas : El contexto gr�fico de la aplicaci�n                         //
	// Salidas  : No tiene                                                     //
	// Descripc : Pinta por primera vez el Applet.                             // 
  //-------------------------------------------------------------------------// 
	public void paint(Graphics g)
	{
		pintaGrup(g, 5, 10, ancho-10, 160);
		pintaGraf(g);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : update(...)                                                  //
	// Entradas : El contexto gr�fico de la aplicaci�n                         //
	// Salidas  : No tiene                                                     //
	// Descripc : LLamar a paint para que no exista  mucho flasheo.            // 
  //-------------------------------------------------------------------------// 
	public void update(Graphics g)
	{
		paint(g);
	}	
	
	//-------------------------------------------------------------------------//
	// Nombre   : pintaGrup(...)                                               //
	// Entradas : Las coordenadas de incicio y la altura y anchura del cuadro  //
	// Salidas  : No tiene                                                     //
	// Descripc : Dibujar en pantalla un cuadro que se asemeje a un agrupador  //
	//            de componentes                                               //
  //-------------------------------------------------------------------------//           
	private void pintaGrup(Graphics g, int x, int y, int ancho, int alto)
	{
		g.setColor(Color.gray);
		g.drawRect(x, y, ancho, alto);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : pintaGraf(...)                                               //
	// Entradas : El contexto gr�fico del la aplicaci�n                        //
	// Salidas  : No tiene                                                     //
	// Descripc : Dibujar en pantalla un el gr�fico que representa los arcos   //
	//            que el usuario desee.                                        //
  //-------------------------------------------------------------------------//           
	private void pintaGraf(Graphics g)
	{
		g.translate(190, 325);
		pintaCuadro(g, -185, -150, 370, 300, Color.white);
		pintaMalla(g, Color.lightGray);
		if (tipoDibuja)
			dibujaArco(g, Color.red);
		else
		  pintaArco(g, Color.red);
		pintaEjes(g, Color.black);	
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : pintaCuadro(...)                                             //
	// Entradas : El contexto gr�fico, las coordenadas de inicio del cuadro, el//
	//            ancho y el alto del cuadro y el color del cuadro             //
	// Salidas  : No tiene                                                     //
	// Descripc : Pinta un cuadro en pantalla                                  //  
	//-------------------------------------------------------------------------//
	private void pintaCuadro(Graphics g, int x, int y, int ancho, int alto, Color color)
	{
		g.setColor(color);
		g.fillRect(x, y, ancho, alto);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : pintaEjes(...)                                               //
	// Entradas : El contexto gr�ico y el color del cuadro.                    //
	// Salidas  : No tiene.                                                    //
	// Descripc : Pinta las coordenadas (los ejes) de referencia.              //  
	//-------------------------------------------------------------------------//
	private void pintaEjes(Graphics g, Color color)
	{
		g.setColor(color);
		g.drawLine(-185, 0, 185, 0);
		g.drawLine(0, -150, 0, 150);
		g.drawLine(-185, -150, 185, 150);
		g.drawLine(-185,  150, 185, -150);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : pintaMalla(...)                                              //
	// Entradas : El contexto gr�fico y el color.                              //    
	// Salidas  : No tiene                                                     //
	// Descripc : Dibujar el conjunto de l�neas verticales y horizontales que  //
	//            dan el aspecto de una malla.                                 //     
  //-------------------------------------------------------------------------//           
	private void pintaMalla(Graphics g, Color color)
	{
		g.setColor(color);
		
		for (int i=-185; i<=185; i+=5)
			g.drawLine(i, -150, i, 150);
		
		for (int i=-150; i<=150; i+=5)
			g.drawLine(-185, i, 185, i);
	}		
	
	//-------------------------------------------------------------------------//
	// Nombre   : dibujaArco(...)                                              //
	// Entradas : El contexto gr�fico de la Aplicaci�n y el color que se desea //
	//            usar.                                                        //
	// Salidas  : No tiene                                                     //
	// Descripc : Dibujar el arco.                                             // 
  //-------------------------------------------------------------------------//           
	private void dibujaArco(Graphics g, Color color)
	{
		g.setColor(color);
		g.drawArc(0-radio, 0-radio, radio*2, radio*2, (int) ini, (int) (fin - ini));
	}	
	
	//-------------------------------------------------------------------------//
	// Nombre   : pintaArco(...)                                               //
	// Entradas : El contexto gr�fico de la Aplicaci�n y el color que se desea //
	//            usar.                                                        //
	// Salidas  : No tiene                                                     //
	// Descripc : Pintar el arco.                                              // 
  //-------------------------------------------------------------------------//           
	private void pintaArco(Graphics g, Color color)
	{
		g.setColor(color);
		g.fillArc(0-radio, 0-radio, radio*2, radio*2, (int) ini, (int) (fin - ini));
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : ActionPerformed(...)                                         //
	// Entradas : El evento sobre un bot�n                                     //
	// Salidas  : No tiene                                                     //
	// Descripc : Implmentar el m�todo que proporciona la acci�n de un bot�n   //
	//            pulsado por el usuario                                       // 
  //-------------------------------------------------------------------------//          
	public void actionPerformed(ActionEvent e)
	{
		Object x = e.getSource();
		
		if (x == btDib)  tipoDibuja = true;
		if (x == btPint) tipoDibuja = false;
		
		if (verifica())
			 repaint();
		else
		{
			Message mensaje = new Message(200, 200, 200, 120, error);
			mensaje.show();
		}	
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : verifica()                                                   //
	// Entradas : No tiene                                                     //
	// Salidas  : True si los datos son correctos. False en caso contrario     //
	// Descripc : Setear los datos de entrada para los arcos de inicio y fin.  //
	//            Adem�s verifica si cumplen con los par�metros requeridos     //
	//            para el problema                                             //
  //-------------------------------------------------------------------------//
	private boolean verifica()
	{
		if (tfIni.getText().equals(""))
		{
			error = "Ingrese donde comenzar !!";
			return (false);
		}
		
		if (tfFin.getText().equals(""))
		{
			error = "Ingrese donde terminar !!";
			return (false);
		} 
		
		ini = new Double(tfIni.getText()).doubleValue();
		fin = new Double(tfFin.getText()).doubleValue();
		
		if (chRad.getState())
		{
			ini = ini*180.0/3.14159265358979323846;
			fin = fin*180.0/3.14159265358979323846;
		}	
		
		return (true);
	}	
}


