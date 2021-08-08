//  
//  Balanza.java
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
//  Creación  : 24-Julio-2001
// 
//  -------------------------------------------------------------------------
//  Esta información no es necesariamente definitiva y está  sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//  -------------------------------------------------------------------------

import java.awt.*;
import java.applet.*;
import java.awt.event.*;

//
//  Clase que deriva de la clase Applet y que implementa a ItemListener.
//  Esta clase está destinada a mostrar un pequeño jueguito en donde
//  se pueden escoger entre varios objetos y pesarlos y compararlos con
//  otros objetos.

public class Balanza extends Applet implements ItemListener
{
	// Ancho del applet
	public final int ancho = 400;
	
	// Alto del applet
	public final int alto  = 470;
	
	// Color de fondo del Applet
	public final Color fondo = new Color(255, 204, 0);
	
	// Componentes AWT que permiten interactuar con el usuario.
	private Checkbox[] opcion;
	private Label titIzq, titDer, pesIzq, pesDer;
	private TextField pesI, pesD;
	private Image[] imagen;
	
	// Cooredenadas para la línea roja.
	private int x1=-180, y1=0;
	private int x2= 180, y2=0;
	
	//-------------------------------------------------------------------------//
	// Nombre   : init()                                                       //
	// Entradas : No tiene                                                     //
	// Salidas  : No tiene                                                     //
	// Descripc : Inicializa el Applet. Setea su tamaño, color de fodo y llama //
	//            a los métodos que setean los componentes.                    //
  //-------------------------------------------------------------------------//  
	public void init()
	{
		setLayout(null);
		setComponentes();
		setBackground(fondo);
		resize(ancho, alto);
	}

	//-------------------------------------------------------------------------//
	// Nombre   : setComponentes()                                             //
	// Entradas : No tiene                                                     //
	// Salidas  : No tiene                                                     //
	// Descripc : LLamar a los métodos que inicializan los componentes AWT     //
	//            del Applet.                                                  //
  //-------------------------------------------------------------------------//
	private void setComponentes()
	{
		String item[] = {"Computador", "Diskette", "Piano", "Reloj", "Telefono", "Pinguino", "Peineta", "Persona", "Mariposa", "Oso"};
		int ptoX = 40;
		int ptoY = 290;
		
		opcion = new Checkbox[10];
		imagen = new Image[10];
		
		titIzq = new Label("Lado izquierdo");
		titDer = new Label("Lado derecho");
		pesIzq = new Label("Peso Total");
		pesDer = new Label("Peso Total");
		
		pesI = new TextField();
		pesD = new TextField();
		pesI.setEditable(false);
		pesD.setEditable(false);
		
		for (int i=0; i<item.length; i++)
 	  {
			opcion[i] = new Checkbox(item[i]);
			opcion[i].setState(false);
			opcion[i].addItemListener(this);
			opcion[i].setBounds(ptoX, ptoY, 100, 20);
			add(opcion[i]);
		  ptoY+=20;
			if (i==4) 
			{
				ptoX = 245;
				ptoY = 290;
			}
		}
		
		titIzq.setBounds(15,   255, 90, 15);
		titDer.setBounds(215,  255, 90, 15);
		pesIzq.setBounds(45,   410, 80, 15);
		pesDer.setBounds(255,  410, 80, 15);
		pesI.setBounds(35,   430, 120, 20);
		pesD.setBounds(240,  430, 120, 20);
		
		add(titIzq);
		add(titDer);
		add(pesIzq);
		add(pesDer);
		add(pesI);
		add(pesD);
		
		imagen[0] = getImage(getDocumentBase(),"imagenes/Computador.gif");
		imagen[1] = getImage(getDocumentBase(),"imagenes/Diskette.gif");
		imagen[2] = getImage(getDocumentBase(),"imagenes/Piano.gif");
		imagen[3] = getImage(getDocumentBase(),"imagenes/Reloj.gif");
		imagen[4] = getImage(getDocumentBase(),"imagenes/Telefono.gif");
		imagen[5] = getImage(getDocumentBase(),"imagenes/Pinguino.gif");
		imagen[6] = getImage(getDocumentBase(),"imagenes/Peineta.gif");
		imagen[7] = getImage(getDocumentBase(),"imagenes/Persona.gif");
		imagen[8] = getImage(getDocumentBase(),"imagenes/Mariposa.gif");
		imagen[9] = getImage(getDocumentBase(),"imagenes/Oso.gif");
	}	
			
	//-------------------------------------------------------------------------//
	// Nombre   : paint(...)                                                   //
	// Entradas : El contexto gráfico de la aplicación                         //
	// Salidas  : No tiene                                                     //
	// Descripc : Pinta por primera vez el Applet.                             // 
  //-------------------------------------------------------------------------// 
	public void paint(Graphics g)
	{
		pintaGrup(g,   5, 260, 190, 200); 
		pintaGrup(g, 205, 260, 190, 200); 
		pintaGraf(g);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : update(...)                                                  //
	// Entradas : El contexto gráfico de la aplicación                         //
	// Salidas  : No tiene                                                     //
	// Descripc : LLamar a paint para que no exista  mucho flasheo.            // 
  //-------------------------------------------------------------------------// 
	public void update(Graphics g)
	{
		paint(g);
	}	
	
	//-------------------------------------------------------------------------//
	// Nombre   : pintaGrup(...)                                               //
	// Entradas : Las corrdenadas de incicio y la altura y anchura del cuadro  //
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
	// Entradas : El contexto gráfico del Applet                               //
	// Salidas  : No tiene                                                     //
	// Descripc : Dibujar en pantalla el sistema de coordenadas con su malla y //
	//            medidas en los ejes. Sólo llama a otros métodos.             //                                         
  //-------------------------------------------------------------------------// 
	private void pintaGraf(Graphics g)
	{
		pintaCuadro(g, 5, 5, 390, 245, Color.white);
    g.translate(ancho/2, 120);
		pintaBase(g);	
		pintaBalance(g);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : pintaCuadro(...)                                             //
	// Entradas : Las coordenadas de incicio x e y, la altura y anchura del    //
	//            cuadro y su color                                            //
	// Salidas  : No tiene                                                     //
	// Descripc : Dibujar en pantalla un cuadro cualquiera                     // 
  //-------------------------------------------------------------------------//           
	private void pintaCuadro(Graphics g, int x, int y, int ancho, int alto, Color color)
	{
		g.setColor(color);
		g.fillRect(x, y, ancho, alto);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : pintaBase(...)                                               //
	// Entradas : El contexto gráfico del applet.                              // 
	// Salidas  : No tiene                                                     //
	// Descripc : Dibujar en pantalla la base para la balanza                  // 
  //-------------------------------------------------------------------------//           
	private void pintaBase(Graphics g)
	{
		Polygon base = new Polygon();
		base.addPoint(0, 0);
		base.addPoint(-50, 80);
		base.addPoint(50,  80);
		g.setColor(Color.blue);
		g.fillPolygon(base);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : pintaBalance(...)                                            //
	// Entradas : El contexto gráfico del applet.                              // 
	// Salidas  : No tiene                                                     //
	// Descripc : Realizar los movimientos simulan el comportamiento de una    // 
	//            balanza                                                      //
  //-------------------------------------------------------------------------//           
	private void pintaBalance(Graphics g)
	{
		float pesoIzq = obtienePesoIzq();
		float pesoDer = obtienePesoDer();
		int deltaY = 0;
		int posYizq = 0;
		int posYder = 0;
		
		if ((0       < Math.abs(pesoIzq - pesoDer)) && (Math.abs(pesoIzq - pesoDer) <= 57.635))  deltaY = 15;
		if ((57.635  < Math.abs(pesoIzq - pesoDer)) && (Math.abs(pesoIzq - pesoDer) <= 115.27))  deltaY = 30;
		if ((115.27  < Math.abs(pesoIzq - pesoDer)) && (Math.abs(pesoIzq - pesoDer) <= 172.905)) deltaY = 45;
		if ((172.905 < Math.abs(pesoIzq - pesoDer)) && (Math.abs(pesoIzq - pesoDer) <= 230.54))  deltaY = 60;
		if (230.54   < Math.abs(pesoIzq - pesoDer)) deltaY = 75;
		
		g.setColor(Color.red);
		
		if (pesoIzq > pesoDer)
		{
			g.drawLine(x1, y1+deltaY, x2, y1-deltaY);
			posYizq = (y1+deltaY)/5;
			posYder = (y1-deltaY)/5;
		}	
		
		if (pesoIzq < pesoDer)
		{
			g.drawLine(x1, y1-deltaY, x2, y1+deltaY);	
			posYizq = (y1-deltaY)/5;
			posYder = (y1+deltaY)/5;
		}
		
		if (pesoIzq == pesoDer) 
		{
			g.drawLine(x1, y1, x2, y1);		
			posYizq = 0;
			posYder = 0;
		}	
		
		if (opcion[0].getState()) g.drawImage(imagen[0], -35*5, posYizq*5-35, this);
		if (opcion[1].getState()) g.drawImage(imagen[1], -35*4, posYizq*4-35, this);
		if (opcion[2].getState()) g.drawImage(imagen[2], -35*3, posYizq*3-35, this);
		if (opcion[3].getState()) g.drawImage(imagen[3], -35*2, posYizq*2-35, this);
		if (opcion[4].getState()) g.drawImage(imagen[4], -35*1, posYizq*1-35, this);
		if (opcion[5].getState()) g.drawImage(imagen[5],  35*0, posYder*0-35, this);
		if (opcion[6].getState()) g.drawImage(imagen[6],  35*1, posYder*1-35, this);
		if (opcion[7].getState()) g.drawImage(imagen[7],  35*2, posYder*2-35, this);
		if (opcion[8].getState()) g.drawImage(imagen[8],  35*3, posYder*3-35, this);
		if (opcion[9].getState()) g.drawImage(imagen[9],  35*4, posYder*4-35, this);
		
		pesI.setText(""+pesoIzq+" kg");
		pesD.setText(""+pesoDer+" kg");
	}	
	
	//-------------------------------------------------------------------------//
	// Nombre   : obtienePesoIzq()                                             //
	// Entradas : No tiene.                                                    // 
	// Salidas  : El peso total de los objetos seleccionados en la parte       //
	//            izquierda                                                    //
	// Descripc : Calcular el peso total de los objetos seleccionados          //
  //-------------------------------------------------------------------------//           
	private float obtienePesoIzq()
	{
		float peso = 0;
		
		if (opcion[0].getState()) peso+=30;
		if (opcion[1].getState()) peso+=0.02;
		if (opcion[2].getState()) peso+=200;
		if (opcion[3].getState()) peso+=0.02;
		if (opcion[4].getState()) peso+=0.5;
		
		return (peso);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : obtienePesoDer()                                             //
	// Entradas : No tiene.                                                    // 
	// Salidas  : El peso total de los objetos seleccionados en la parte       //
	//            derecha                                                      //
	// Descripc : Calcular el peso total de los objetos seleccionados          //
  //-------------------------------------------------------------------------//           
	private float obtienePesoDer()
	{
		float peso = 0;
		
		if (opcion[5].getState()) peso+=25;
		if (opcion[6].getState()) peso+=0.02;
		if (opcion[7].getState()) peso+=70;
		if (opcion[8].getState()) peso+=0.0001;
		if (opcion[9].getState()) peso+=230.54;
		
		return (peso);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : itemStateChaged(...)                                         //
	// Entradas : El evento sobre el choice.                                   // 
	// Salidas  : No tiene.                                                    //
	// Descripc : Cuando se pulsa (selección o deselección) un choice lo que se//
	//            se hace es repintar el Applet.                               //
  //-------------------------------------------------------------------------//           
	public void itemStateChanged(ItemEvent e)
	{
		repaint();
  }
}


