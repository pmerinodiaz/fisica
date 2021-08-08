//  
//  NotacionCientifica.java
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
//  Creaci�n  : 8-Julio-2001
// 
//  -------------------------------------------------------------------------
//  Esta informaci�n no es necesariamente definitiva y est� sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//  -------------------------------------------------------------------------

import java.awt.*;
import java.applet.*;

public class NotacionCientifica extends Applet
{
	// Anchura del Applet.
	final int ancho = 250;
	
	// Altura del Applet.
	final int alto  = 200;
	
	// Color de fondo del Applet.
	public final Color fondo = new Color(255, 204, 0);
	
	// Componentes AWT:
	// Campos de Textos que permiten al usuario ingresar los datos. Tambien
	// sirven para imprimir los resultados.
	public TextField tfNumero, tfResult;
	
	// Bot�n que tiene la acci�n de llevar a cabo el c�lculo de Expresar en
	// Notaci�n Cient�fica un n�mero ingresado por el usuario.
	public Button    btTransf;
	
	//
	// Nombre : init()
	// Entrada: No tiene
	// Salida : No tiene
	// Funci�n: Permite inicializar el Applet y llamar al m�todo que setea los 
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
	// Funci�n: Permite inicializar los componentes AWT (textfield y botones),
	//          posicionarlos y aderirlos al Applet.
	//
	private void setComponentes()
	{
		tfNumero = new TextField("600");
		tfResult = new TextField("6.0 x 10^2");
		tfResult.setEditable(false);
		btTransf = new Button("Transformar");
		
		setLayout(null);
		
		tfNumero.setBounds(90, 45,  100, 20);
		btTransf.setBounds(90, 80,  100, 20);
		tfResult.setBounds(45, 150, 150, 20);
		
		add(tfNumero);
		add(btTransf);
		add(tfResult);
	}
	
	//
	// Nombre : setTextos(...)
	// Entrada: El cantexto gr�fico g.
	// Salida : No tiene
	// Funci�n: Imprime en pantalla algunos mensajes para guiar al usuario
	//          a ingresar los datos.
	//
	private void setTextos(Graphics g)
	{
		g.setFont(new Font("Verdana", Font.PLAIN, 11));
		g.setColor(Color.black);
		g.drawString("N�mero:", 40, 63);
		g.drawString("N�mero en Notaci�n Cient�fica", 45, 140);
	}
	
	//
	// Nombre : paint(...)
	// Entrada: El contexto gr�fico para la aplicaci�n.
	// Salida : No tiene
	// Funci�n: Pinta por primera vez el Applet y adem�s se llama al m�todo que
	//          setea los textos.
	//
	public void paint(Graphics g) 
	{
		setTextos(g);
  }
	
	//
	// Nombre : action(...)
	// Entrada: El evento y el objeto.
	// Salida : Verdadero si se hace click sobre el bot�n. En caso contrario
	//          falso.
	// Funci�n: Permite darle vida al bot�n. Es decir, incorporarle un evento y 
	//          una acci�n que realize cuando se haga click sobre �l.
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
	// Funci�n: Algoritmo que permite expresar un n�mero ingresado por el usuario
	//          en Notaci�n Cient�fica. 
	//          de un n�mero. Por ejemplo:
	//          0.022   --> 2.2 x 10^-2
	//          1402369 --> 1.402369 x 10^6
	//          105.00  --> 1.05 x 10^-3
	//          6.0230  --> 6.0230 x 10^0
	//
	private void calcula()
	{
	  int i, j;
		int indexComa, indexPunt, indexCP=-1;
		String numero;
		
		if (tfNumero.getText().equalsIgnoreCase(""))
    {
		  tfResult.setText("Ingrese el N�mero");		 
			return;
		}
		
		numero = tfNumero.getText();
		
		indexComa = numero.indexOf(',');
		indexPunt = numero.indexOf('.');
		
		if ( indexComa != -1 ) indexCP = indexComa;
		if ( indexPunt != -1 ) indexCP = indexPunt;
		
		if (indexCP == -1)
		{
			for (i=0; i<numero.length(); i++)
				if (numero.charAt(i) != '0') break;
			
			for (j=numero.length()-1; j>=0; j--)
				if (numero.charAt(j) != '0') break;
									
			if ((i == numero.length()) || (j == -1))
				tfResult.setText("0.0 x 10^0");
			else 
			{
				if (i == j)
					tfResult.setText(""+numero.charAt(i)+".0 x 10^"+numero.substring(i+1, numero.length()).length()+"");
				else tfResult.setText(""+numero.charAt(i)+"."+numero.substring(i+1, j+1)+" x 10^"+numero.substring(i+1, numero.length()).length()+"");
			}	
		}
		else
		{
			for (i=0; i<numero.length(); i++)
				if ((numero.charAt(i) != '0') && (i != indexCP))
					break;
			
			for (j=numero.length()-1; j>=0; j--)
				if (numero.charAt(j) != '0')
					break;
			
			if (i == j)
					tfResult.setText(""+numero.charAt(i)+".0 x 10^ -"+(numero.substring(indexCP-1, i).length()-1)+"");
			else tfResult.setText(""+numero.charAt(i)+"."+numero.substring(i+1, indexCP)+""+numero.substring(indexCP+1, numero.length())+" x 10^ -"+(numero.substring(0, indexCP).length()-1)+"");		
		}
	}
}
