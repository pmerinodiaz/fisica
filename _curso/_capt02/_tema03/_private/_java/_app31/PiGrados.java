//  
//  PiGrados.java
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
//  Creación  : 26-Julio-2001
// 
//  -------------------------------------------------------------------------
//  Esta información no es necesariamente definitiva y está  sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//  -------------------------------------------------------------------------

import java.awt.*;
import java.applet.*;
import java.awt.event.*;

//
//  Clase que deriva de la clase Applet. y que implementa a la clase
//  ActionListener. Esta clase tiene por funcionalidad transformar
//  grados a radianes y visceversa.
//
public class PiGrados extends Applet implements ActionListener
{
	// Anchura del applet.
	final int ancho = 350;
	
	// Altura del applet.
	final int alto  = 200;
	
	// Color de fondo del applet.
	public final Color fondo = new Color(255, 204, 0);
 
	// Componentes AWT:
	// Campos de Textos para el ingreso de datos y visualización de la 
	// resultados.
	private TextField tfAngulo, tfResult;
	private Choice chModo;
	
	// Boton que tiene la acción de llevar a cabo la transformación.
	private Button btTransf;
	
	// Variables para realizar los cálculos correspondientes.
	private double angulo, anguloRad, anguloGrad;
	private final double PI = 3.14159265358979323846;
	
	//
	// Nombre : init()
	// Entrada: No tiene
	// Salida : No tiene
	// Función: Permite inicializar el Applet y llamar al método que setea los 
  //          componentes AWT del Applet.
	//
	public void init()
	{
		setBackground(fondo);
		resize(ancho, alto);
		setComponentes();
	}

	//
	// Nombre : setComponentes()
	// Entrada: No tiene
	// Salida : No tiene
	// Función: Permite inicializar los componentes AWT (textfield y botones),
	//          posicionarlos y aderirlos al Applet.
	//
	private void setComponentes()
	{
		tfAngulo = new TextField("360");
		tfResult = new TextField();
		tfResult.setEditable(false);
		btTransf = new Button("Calcular");
		chModo   = new Choice();
		chModo.addItem("Grados");
		chModo.addItem("Radianes");
		
		setLayout(null);
		
		tfAngulo.setBounds(90, 45,  100, 23);
		chModo.setBounds  (195,45,  100 , 23);
		btTransf.setBounds(145, 80,  100, 20);
		tfResult.setBounds(90, 150, 205, 20);
		
		btTransf.addActionListener(this);
		
		add(tfAngulo);
		add(chModo);
		add(btTransf);
		add(tfResult);
	}
	
	//
	// Nombre : setTextos(...)
	// Entrada: El cantexto gráfico g.
	// Salida : No tiene
	// Función: Imprime en pantalla algunos mensajes para guiar al usuario
	//          a ingresar los datos.
	//
	private void setTextos(Graphics g)
	{
		g.setFont(new Font("Verdana", Font.PLAIN, 11));
		g.setColor(Color.black);
		g.drawString("Angulo:", 40, 63);
		g.drawString("Resultado", 175, 140);
	}
	
	//
	// Nombre : paint(...)
	// Entrada: El contexto gráfico para la aplicación.
	// Salida : No tiene
	// Función: Pinta por primera vez el Applet y además se llama al método que
	//          setea los textos.
	//
	public void paint(Graphics g) 
	{
		setTextos(g);
  }
	
	//
	// Nombre : actionPerformed(...)
	// Entrada: El evento.
	// Salida : No tiene.
	// Función: Permite darle vida al botón. Es decir, incorporarle un evento y 
	//          una acción que realize cuando se haga click sobre él.
	//
	public void actionPerformed(ActionEvent e) 
	{
  	Object x = e.getSource();
		
		if (x == btTransf) 
			transforma();
	}
	
	//
	// Nombre : transforma()
	// Entrada: No tiene.
	// Salida : No tiene.
	// Función: Transformar de grados a radianes y viceversa.
	//
	private void transforma()
	{
	  if (tfAngulo.getText().equals(""))
    {
		  tfResult.setText("Ingrese el Angulo");		 
			return;
		}
		
		angulo = new Double(tfAngulo.getText()).doubleValue();
		
		if (chModo.getSelectedItem().equals("Grados"))
    {
			anguloRad  = angulo/180.0*PI;
			tfResult.setText(""+anguloRad+" rad");
		}	
			
		if (chModo.getSelectedItem().equals("Radianes"))
		{
			anguloGrad = angulo*180.0/PI;
			tfResult.setText(""+anguloGrad+" grad");
		}	
  }
}
