//  
//  IncertidPorcentual.java
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
//  Creación  : 6-Julio-2001
// 
//  -------------------------------------------------------------------------
//  Esta información no es necesariamente definitiva y está  sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//  -------------------------------------------------------------------------

import java.awt.*;
import java.applet.*;

//
//  Clase que deriva de la clase Applet y que tiene la función de inicializar, 
//  construir y setear los componentes del Applet. Este Applet permite al 
//  usuario ingresar los datos (medida e incertidumbre) para así poder calcular
//  la Incertidumbre Porcentual de una medición. 
//
public class IncertidPorcentual extends Applet
{
	// Anchura del Applet.
	final int ancho = 300;

	// Altura del Applet.
	final int alto  = 200;
	
	// Color de fondo del Applet.
	public final Color fondo = new Color(255, 204, 0);
	
	// Componentes AWT:
	// Campos de Textos para el ingreso de datos e impresión de resultados.
	public TextField tfMedida, tfIncert, tfResult;
	
	// Lista de opciones para las unidades de la medición.
	public Choice chUnidad;
	
	// Etiqueta para la unidad de la incertidumbre.
	public Label     lbUnidad;
	
	// Boton que tiene la acción de llevar a cabo el cálculo de la Incertidumbre
	// Porcentual.
	public Button    btCalcul;
	
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
	// Función: Permite inicializar los componentes AWT (label, textfield y botones),
	//          posicionarlos y aderirlos al Applet.
	//
	private void setComponentes()
	{
		tfMedida = new TextField("5");
		tfIncert = new TextField("0.5");
		tfResult = new TextField("10.0%");
		tfResult.setEditable(false);
		
		lbUnidad = new Label("s");
		
		btCalcul = new Button("Calcular");
		
		chUnidad = new Choice();
		
		chUnidad.addItem("s");
		chUnidad.addItem("min");
		chUnidad.addItem("hor");
		chUnidad.addItem("pulg");
		chUnidad.addItem("cm");
		chUnidad.addItem("pie");
		chUnidad.addItem("m");
		chUnidad.addItem("mi");
		chUnidad.addItem("milla náutica");
		chUnidad.addItem("fermi");
		chUnidad.addItem("angstrom");
		chUnidad.addItem("año luz");
		chUnidad.addItem("parsec");
		
		setLayout(null);
		
		tfMedida.setBounds(90,  20,  80, 20);
		chUnidad.setBounds(180, 20,  90, 20);
		tfIncert.setBounds(90,  45,  80, 20);
		lbUnidad.setBounds(180, 50,  70, 20);
		btCalcul.setBounds(90,  80,  80, 20);
		tfResult.setBounds(55, 160, 150, 20);
		
		add(tfMedida);
		add(chUnidad);
		add(tfIncert);
		add(lbUnidad);
		add(btCalcul);
		add(tfResult);
	}
	
	//
	// Nombre : setTextos(...)
	// Entrada: El cantexto gráfico g.
	// Salida : No tiene
	// Función: Imprime en pantalla algunos mensajes.
	//
	private void setTextos(Graphics g)
	{
		g.setFont(new Font("Verdana", Font.PLAIN, 11));
		g.setColor(Color.black);
		g.drawString("Medida:", 20, 35);
		g.drawString("Incertidumbre:", 20, 63);
		g.drawString("Incertidumbre Porcentual", 70, 150);
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
	// Nombre : action(...)
	// Entrada: El evento y el objeto.
	// Salida : No tiene
	// Función: Permite darle vida al botón y a la lista de opciones. Es decir, 
	//          incorporarle un evento y una acción que realize cuando se 
	//          haga click sobre ellos.
	//
	public boolean action(Event evento, Object x) 
	{
  	if (evento.target instanceof Choice) 
		{
      lbUnidad.setText(chUnidad.getSelectedItem());
			return true;
		}
		else if (evento.target instanceof Button) 
		     {
           calcula();
					 return true;
				 }		
    return false;
  }
	
	//
	// Nombre : calcula()
	// Entrada: No tiene.
	// Salida : No tiene.
	// Función: Realiza el cálculo de la Incertidumbre Porcentual de una 
	//          medición tomada por el usuario. La fórmula es:
	//
	//          Incertidumbre Porcentual = (incertidumbre/medida)*100 
	//
	private void calcula()
	{
	  float medid, incer;
		
		if (tfMedida.getText().equalsIgnoreCase(""))
    {
		  tfResult.setText("Ingrese la Medida");		 
			return;
		}
		
		if (tfIncert.getText().equalsIgnoreCase(""))	
		{
		  tfResult.setText("Ingrese la Incertidumbre ");
			return;	
		}
						
		medid = new Float(tfMedida.getText()).floatValue();
		incer = new Float(tfIncert.getText()).floatValue();
		
		if (medid == 0)
		     tfResult.setText("Indeterminado");
		else tfResult.setText("" +(incer/medid)*100+ " %");
  } 	
}


