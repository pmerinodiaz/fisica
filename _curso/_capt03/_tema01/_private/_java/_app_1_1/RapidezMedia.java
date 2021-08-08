//  
//  RapidezMedia.java
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
//  Creación  : 15-Julio-2001
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
//  usuario ingresar los datos (distancia y tiempo) para así poder calcular
//  la rapidez media de una partícula medida en [m/s]. 
//
public class RapidezMedia extends Applet
{
  
  // Anchura del Applet.
	final int ancho = 335;
	
	// Altura del Applet.
	final int alto  = 300;
	
	// Color de fondo para el Applet.
	final Color fondo = new Color(255, 204, 0);
	
	// Componentes AWT:
	// Etiquetas de textos en las cuales se imprime mensajes.
	private Label lbDist, lbTiemp, lbResult;
	
	// Campos de textos en los cuales se ingresan los datos de la Distancia
	// recorrida y el tiempo transcurrido. También existe un cuadro de texto
	//  correspondiente en donde imprimiremos el resultado.
	private TextField tfDist, tfTiemp, tfResult;
	
	// Lista de opciones que permiten elegir las opciones para las unidades
	// de la Distancia recorrida y del Tiempo transcurrido. 
	private Choice chDist, chTiemp;
	
	// Boton que tiene la acción de llevar a cabo el cálculo de la Velocidad
	// media y además permite realizar la animación del tren en movimiento.
	private Button btCalc;
	
	// Variables que servirán para realizar el cálculo de la Velocidad Media
	// de una partícula.
	private double distancia, tiempo, velocidad;
	
	// Sirve para detener o reproducir la animación.
	private boolean pause = true;
	
	// Imagen que sirve para traer al applet el archivo de imagen tren.gif, el
	// cual posteriormente será animado.
	private Image imagen;
	
	// Sonido que se reproduce al momento de empezar la animación del tren.
	private AudioClip sonido;
	
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
	// Función: Permite inicializar los componentes AWT pra el Applet e incorporarlos
  //          a éste.
	//
	private void setComponentes()
	{
		setLayout(null);       
		
		setLabel();            
		setTextField();        
		setChoice();           
		setButton();           
		setImage();
		setAudio();
	}
	
	//
	// Nombre : setLabel()
	// Entrada: No tiene
	// Salida : No tiene
	// Función: Permite inicializar las etiquetas, posicionarlas e incorporarlas
	//          al Applet.
	//
	private void setLabel()
	{
		lbDist   = new Label("Distancia recorrida:");  
		lbTiemp  = new Label("Tiempo transcurrido:");  
		lbResult = new Label("Rapidez Media");             
		
		lbDist.setBounds(0, 15, 120, 10);              
		lbTiemp.setBounds(0, 40, 120, 15);             
		lbResult.setBounds(135, 255, 100, 10);         
		
		add(lbDist);                                   
		add(lbTiemp);                                  
		add(lbResult);                                 
	}	
	
	//
	// Nombre : setTextField()
	// Entrada: No tiene
	// Salida : No tiene
	// Función: Permite inicializar los cuadros de textos, posicionarlos e 
	//          incorporarlos al Applet.
	//
	private void setTextField()
	{
		tfDist   = new TextField("469");          
		tfTiemp  = new TextField("7");          
		tfResult = new TextField();          
		
		tfDist.setBounds(125, 7, 100, 20);   
		tfTiemp.setBounds(125, 32, 100, 20); 
		tfResult.setBounds(70, 270, 200, 20);
		
		tfResult.setEditable(false);         
		
		add(tfDist);    
		add(tfTiemp);   
		add(tfResult);  
	}	
	
	//
	// Nombre : setChoice()
	// Entrada: No tiene
	// Salida : No tiene
	// Función: Permite inicializar las listas de opciones, posicionarlas e 
	//          incorporarlas al Applet.
	//
	private void setChoice()
	{
		String opcDist[]   = {"km", "pulg", "cm", "pie", "m", "mi", "milla náutica", "fermi", "angstrom", "año luz", "parsec"}; 
		String opcTiemp[]  = {"hr", "s", "min", "día", "año"}; 
		int i;
				
		chDist   = new Choice();
		chTiemp  = new Choice();
		
		for (i=0; i<opcDist.length; i++) chDist.addItem(opcDist[i]);
		for (i=0; i<opcTiemp.length; i++) chTiemp.addItem(opcTiemp[i]);
		
		chDist.setBounds(230, 7, 100, 20);
		chTiemp.setBounds(230, 32, 100, 20);
		
		add(chDist);
		add(chTiemp);
	}
	
	//
	// Nombre : setButton()
	// Entrada: No tiene
	// Salida : No tiene
	// Función: Permite inicializar los botones, posicionarlos e incorporarlos
  //          al Applet.
	//
	private void setButton()
	{
		btCalc = new Button("Calcular");
		
		btCalc.setBounds(125, 70, 100, 20);
		
		add(btCalc);
	}
	
	//
	// Nombre : setImage()
	// Entrada: No tiene
	// Salida : No tiene
	// Función: Permite incorporar la imágen del tren (archivo tren.gif).
	//
	private void setImage()
	{
		imagen = getImage(getDocumentBase(), "imagen/tren.gif");  
	}	
	
	//
	// Nombre : setAudio()
	// Entrada: No tiene
	// Salida : No tiene
	// Función: Permite incorporar el sonido que se reproducirá al momento
	//          de comenzar la animación del tren (archivo sonidotren.gif).
	//
	private void setAudio()
	{
		sonido = getAudioClip(getDocumentBase(), "sonido/sonidotren.au");
	}
	
	//
	// Nombre : stop()
	// Entrada: No tiene.
	// Salida : No tiene
	// Función: Parar la animación.
	//
	public void stop()
	{
		pause = true;
	}
	
	//
	// Nombre : paint(...)
	// Entrada: El contexto gráfico para la aplicación.
	// Salida : No tiene
	// Función: Pinta por primera vez el Applet y además se llama a la animación.
	//
	public void paint(Graphics g)
	{
		anima(g);
	}
	
	//
	// Nombre : action(...)
	// Entrada: El evento y el objeto.
	// Salida : Retorna verdadero (1) si el evento ocurrió. En caso contrario
	//          devuelve flaso (0).
	// Función: Permite darle vida al botón. Es decir, incorporarle un evento
	//          y una acción que realize cuando se clickee sobre él.
	//
	public boolean action(Event e, Object x) 
	{
    if (e.target instanceof Button) 
		{
   		pause = false;
			calcula();
			repaint();
			return (true);
    }
    return (false);
  }
	
	//
	// Nombre : calcula()
	// Entrada: No tiene.
	// Salida : No tiene.
	// Función: Realiza el cálculo de la velocidad media de una partícula en 
	//          reposo (inicialmente). Además verifica que los datos fueron 
	//          ingresados correctamente.
	//
	private void calcula()
	{
		if (verifica())
		{
			Distancia dst = new Distancia(chDist.getSelectedItem(), distancia);
			Tiempo tmp = new Tiempo(chTiemp.getSelectedItem(), tiempo);
			
			velocidad = dst.convDist()/tmp.convTiemp();
			tfResult.setText(""+velocidad+" [km/hr]");
		}
	}
	
	/*----------------------------------------------------------------------------*/
	/* Nombre     : verifica()                                                    */
	/* Entrada    : No tiene.                                                     */
	/* Salida     : Retorna verdadero (1) si los datos fueron ingresados correcta */
	/*              mente. En caso contrario retorna falso (0).                   */
	/* Descripción: Comprobar que los datos fueron ingresados correctamente. Es   */
	/*              decir, los cuadros de textos deben ser ingresados con datos y */
	/*              las variables Distancia y Tiempo deben ser positivas.         */
	/*----------------------------------------------------------------------------*/
	private boolean verifica()
	{
		if (tfDist.getText().equalsIgnoreCase(""))
    {
		  tfResult.setText("Ingrese la Distancia");		 
			return (false);
		}
		
		if (tfTiemp.getText().equalsIgnoreCase(""))	
		{
		  tfResult.setText("Ingrese la Tiempo ");
			return (false);	
		}
						
		distancia = new Double(tfDist.getText()).doubleValue();
		tiempo    = new Double(tfTiemp.getText()).doubleValue();
		
		if (distancia < 0)
		{
		  tfResult.setText("La Distancia debe ser positiva");
			return (false);	
		}
		
		if (tiempo <= 0)
		{
		  tfResult.setText("El Tiempo debe ser positivo");
			return (false);	
		}
		
		return (true);
	}
	
	/*----------------------------------------------------------------------------*/
	/* Nombre     : anima()                                                       */
	/* Entrada    : El contexto gráfico de la Aplicación.                         */
	/* Salida     : No tiene.                                                     */
	/* Descripción: Presentar la animación. Dejar en estado de pausa o stop si    */
	/*              el Applet no está fuera de vista. En caso contrario, se       */
	/*              reproduce la animación del tren.                              */
	/*----------------------------------------------------------------------------*/
	private void anima(Graphics g)
	{
		Color cielo = new Color(0, 153, 255);
		Color piso  = new Color(183, 91, 0);
		int x0 = 0;
		int y0 = 110;
		int anchoCielo = ancho;
		int altoCielo  = 90;
		int anchoPiso  = ancho;
		int altoPiso   = 30;
		int anchoTren  = 36;
		int altoTren   = 42;
		
		g.setColor(cielo);
		g.fillRect(x0, y0, anchoCielo, altoCielo);
		
		g.setColor(piso);
		g.fillRect(x0, y0+altoCielo, anchoPiso, altoPiso);
		
		g.setColor(Color.black);
		g.drawRect(x0, y0, ancho-1, altoCielo+altoPiso);
		
		g.drawImage(imagen, 0, y0+altoCielo-altoTren+8, this);
		
		g.setColor(Color.white);
		g.drawLine(anchoTren, y0+altoCielo+10, ancho-2, y0+altoCielo+10); 
		g.drawString(""+tfDist.getText()+" "+chDist.getSelectedItem()+"", ancho/2, y0+altoCielo+25);
		
		g.setColor(Color.blue);
		g.fillRect(140, 120, 80, 15);
		
		g.setColor(Color.white);
		g.drawString(""+tfTiemp.getText()+" "+chTiemp.getSelectedItem()+"", 160, 132);
		
		if (pause == false)
		{
			sonido.play();
			for (int i=0; i<=ancho-40 && pause == false; i++)
			{
				g.setColor(cielo);
				g.fillRect(i+2, y0+altoCielo-altoTren, anchoTren, altoTren);
				g.drawImage(imagen, i, y0+altoCielo-altoTren+8, this);
				pausa(14);
			}
			sonido.stop();
			pause = true;
		}	
	}	
	
	//
	// Nombre : pausa(...)
	// Entrada: El tiempo que se desea dar una pausa.
	// Salida : No tiene.
	// Función: Hacer que la aplicación tenga un momento de pausa, para
	//          así poder visualizar la animación a nuestra velocidad.
	//
	private void pausa(int tiempo) 
	{
  	try { Thread.sleep(tiempo); }
	  catch (InterruptedException e) {}
  }
}
