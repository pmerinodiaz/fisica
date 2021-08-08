//  
//  CifrasSignificativas.java
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
//  Creación  : 7-Julio-2001
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
//  usuario ingresar un número y calcular su cantidad de Cifras Significativas.
//
public class CifrasSignificativas extends Applet
{
	// Anchura del applet.
	final int ancho = 250;
	
	// Altura del applet.
	final int alto  = 200;
	
	// Color de fondo del applet.
	public final Color fondo = new Color(255, 204, 0);
 
	// Componentes AWT:
	// Campos de Textos para el ingreso de datos y visualización de la 
	// resultados.
	public TextField tfNumero, tfResult;
	
	// Boton que tiene la acción de llevar a cabo el cálculo de Contar las
	// Cifras Significativas de un número.
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
	// Función: Permite inicializar los componentes AWT (textfield y botones),
	//          posicionarlos y aderirlos al Applet.
	//
	private void setComponentes()
	{
		tfNumero = new TextField("1425");
		tfResult = new TextField("4");
		tfResult.setEditable(false);
		btCalcul = new Button("Calcular");
		
		setLayout(null);
		
		tfNumero.setBounds(90, 45,  100, 20);
		btCalcul.setBounds(90, 80,  100, 20);
		tfResult.setBounds(45, 150, 150, 20);
		
		add(tfNumero);
		add(btCalcul);
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
		g.drawString("Número:", 40, 63);
		g.drawString("Cantidad de Cifras Significativas", 40, 140);
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
	// Salida : Verdadero si se hace click sobre el botón. En caso contrario
	//          falso.
	// Función: Permite darle vida al botón. Es decir, incorporarle un evento y 
	//          una acción que realize cuando se haga click sobre él.
	//
	public boolean action(Event evento, Object x) 
	{
  	if (evento.target instanceof Button) 
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
	// Función: Algoritmo que permite contabilizar la cantidad de Cifras Significativas
	//          de un número. Por ejemplo:
	//          2.50    --> 3 cifras significativas
	//          2.503   --> 4 cifras significativas 
	//          0.00103 --> 3 cifras significativas
	//
	private void calcula()
	{
	  int i=0, j=0;
		int indexComa, indexPunt, indexCP = -1;
		int contCS = 0, contCeros = 0;
		String numero;
		boolean seguir = true;
		
		if (tfNumero.getText().equalsIgnoreCase(""))
    {
		  tfResult.setText("Ingrese el Número");		 
			return;
		}
		
		numero = tfNumero.getText();
		
		indexComa = numero.indexOf(',');
		indexPunt = numero.indexOf('.');
		
		if ( indexComa != -1 ) indexCP = indexComa;
		if ( indexPunt != -1 ) indexCP = indexPunt;
		
		if (indexCP == -1)
		{
			while ((i < numero.length()) && (seguir == true))
			{
				if ((numero.charAt(i) != '+') && (numero.charAt(i) != '-') && (numero.charAt(i) != '0'))
				     seguir = false;
				else i++;	
			}		
				
			if (i == numero.length())
				contCS = 0;
			else contCS = numero.substring(i, numero.length()).length();
		}
		else
		{
			while ((i < numero.length()) && (seguir == true))
			{
				if ((numero.charAt(i) != '+') && (numero.charAt(i) != '-') && (numero.charAt(i) != '0'))
				     seguir = false;
				else i++;	
			}
			
			if (i == indexCP)
				contCS = 0;
			else contCS = numero.substring(i, indexCP).length();
			
			i = numero.length()-1;
			seguir = true;
			
			while ((i > indexCP) && (seguir == true))
			{
				if (numero.charAt(i) != '0')
				     seguir = false;
				else i--;	
			}
			
			if (contCS == 0)
			{
				j = indexCP+1;
				seguir = true;
				while ((j < numero.length()) && (seguir == true))
				{
					if (numero.charAt(j) != '0')
						seguir = false;
					else j++;
				}	
				
				if (j <= i)
					contCS = numero.substring(j, i).length()+1;
				else contCS = 0;	
			}	
			else contCS+= numero.substring(indexCP+1, i+1).length();
		}
		tfResult.setText("" +contCS+ "");
	}
}

