//  
//  RegresionLineal.java
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
//  Creación  : 27-Julio-2001
// 
//  -------------------------------------------------------------------------
//  Esta información no es necesariamente definitiva y está  sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//  -------------------------------------------------------------------------
//

import java.awt.*;
import java.awt.event.*;
import java.applet.*;

//
// Clase que deriva de la clase Applet y que implementa a la clase
// ActionListener. Esta clase tiene por funcionalidad encontrar y
// graficar la regresión lineal de un caonjunto de datos ingresados
// por el usuario.
//

public class RegresionLineal extends Applet implements ActionListener
{
	// Ancho del applet.
	private final int ancho = 790;
	
	// Alto del applet.
	private final int alto  = 380;
	
	// Color de fondo del Applet.
	private final Color fondo = new Color(255, 204, 0);
	
	// Fuente para el gráfico.
	private Font fontPequenia = new Font("SanSerif", Font.PLAIN, 9);
		
	// Componentes AWT que permiten la interacción con el usuario.
	// Grupo de Datos (D)
	private Label     lbTitD, lbPeri, lbRadi;
	private TextField tfPeri, tfRadi;
  private Button    btAgre, btCalc, btLimp;
	private TextArea  taList;
	
	// Grupo de Resultados (R)
	private Label     lbTitR, lbResA, lbResB, lbResEc;
	private TextField tfResA, tfResB, tfResEc;
	
	// Grupo de Pronostico (P)
	private Label     lbTitPro, lbProPer, lbProRad;
	private TextField tfProPer, tfProRad;
	private Button    btProPro;
	
	// Variables objetos Arreglo que sirven para almacenar los datos ingresados.
	private Arreglo X = new Arreglo(100);
	private Arreglo Y = new Arreglo(100);
	
	// Estas variables sirven par realizar los cálculos necesarios.
	private double a, b;
	private double periodo;
	private int n = 0;
	
	// Variables que sirven para crear la escala en píxeles del eje X e Y
	private int scalaX = 20;
	private int scalaY = 20;
	private int minPosX = 0 - 180;
	private int maxPosX = 0 + 180;
	private int minPosY = 0 - 180;
	private int maxPosY = 0 + 180;
	
	// Variables para dibujar los puntos.
	private double ptoX, ptoY;
	
	// Candado para el gráfico y el pronóstico.
	private boolean graficar = false;
	private boolean pronosticar = false;
		
	//-------------------------------------------------------------------------//
	// Nombre   : init()                                                       //
	// Entradas : No tiene                                                     //
	// Salidas  : No tiene                                                     //
	// Descripc : Inicializa el Applet. Setea su tamaño, color de fodo y llama //
	//            a los métodos que setean los componentes.                    //
  //-------------------------------------------------------------------------//  
	public void init()
	{
		setBackground(fondo);
		setLayout(null);
		setGrupD();
		setGrupR();
		setGrupP();
		resize(ancho, alto);
	}

	//-------------------------------------------------------------------------//
	// Nombre   : setGrupD()                                                   //
	// Entradas : No tiene                                                     //
	// Salidas  : No tiene                                                     //
	// Descripc : LLamar a los métodos que inicializan los componentes AWT     //
	//            del grupo de los datos ingresados.                           //
  //-------------------------------------------------------------------------//
	private void setGrupD()
	{
		lbTitD = new Label("Ingrese los datos");
		lbPeri = new Label("Período (T):");
		lbRadi = new Label("Radio    (R):");
		tfPeri = new TextField();
		tfRadi = new TextField();
		btAgre = new Button("Agregar >>");
		btCalc = new Button("Calcular");
		btLimp = new Button("Limpiar");
		taList = new TextArea();
		lbTitD.setBounds( 10,   0, 100,  20);
		lbPeri.setBounds( 15,  45,  70,  20);
		lbRadi.setBounds( 15,  80,  70,  20);
		tfPeri.setBounds( 85,  40, 100,  25);
		tfRadi.setBounds( 85,  75, 100,  25);
		btAgre.setBounds( 85, 110, 100,  25);
		btCalc.setBounds( 85, 145, 100,  25);
		btLimp.setBounds( 85, 180, 100,  25);
		taList.setBounds(195,  40, 200, 165);
		taList.setEditable(false);
		btAgre.addActionListener(this);
		btCalc.addActionListener(this);
		btLimp.addActionListener(this);
		add(lbTitD);
		add(lbPeri);
		add(lbRadi);
		add(tfPeri);
		add(tfRadi);
		add(btAgre);
		add(btCalc);
		add(btLimp);
		add(taList);
	}	
		
	//-------------------------------------------------------------------------//
	// Nombre   : setGrupR()                                                   //
	// Entradas : No tiene                                                     //
	// Salidas  : No tiene                                                     //
	// Descripc : LLamar a los métodos que inicializan los componentes AWT     //
	//            del grupo de los resultados.                                 //
  //-------------------------------------------------------------------------//	
	private void setGrupR()
	{
		lbTitR  = new Label("Resultados");
		lbResA  = new Label("a =");
		lbResB  = new Label("b =");
		lbResEc = new Label("Ec:");
		tfResA  = new TextField();
		tfResB  = new TextField();
		tfResEc = new TextField();
		lbTitR.setBounds ( 10, 230,  70,  20);
		lbResA.setBounds ( 15, 270,  20,  20);
		lbResB.setBounds ( 15, 300,  20,  20);
		lbResEc.setBounds( 15, 330,  20,  20);
		tfResA.setBounds ( 40, 265, 120,  25);
		tfResB.setBounds ( 40, 295, 120,  25);
		tfResEc.setBounds( 40, 325, 120,  25);
		tfResA.setEditable (false);
		tfResB.setEditable (false);
		tfResEc.setEditable(false);
		add(lbTitR);
		add(lbResA);
		add(lbResB);
		add(lbResEc);
		add(tfResA);
		add(tfResB);
		add(tfResEc);
	}	
	
	//-------------------------------------------------------------------------//
	// Nombre   : setGrupP()                                                   //
	// Entradas : No tiene                                                     //
	// Salidas  : No tiene                                                     //
	// Descripc : LLamar a los métodos que inicializan los componentes AWT     //
	//            del grupo de los pronósticos.                                //
  //-------------------------------------------------------------------------//	
	private void setGrupP()
	{
	  lbTitPro = new Label("Pronósticos");
		lbProPer = new Label("Período (T):");
		lbProRad = new Label("Radio    (R):");
		tfProPer = new TextField();
		tfProRad = new TextField();
		btProPro = new Button("Pronosticar");
		btProPro.addActionListener(this);
		lbTitPro.setBounds(200, 230,  70,  20);
		lbProPer.setBounds(205, 270,  70,  20);
		lbProRad.setBounds(205, 330,  70,  20);
		tfProPer.setBounds(280, 265, 115,  25);
		btProPro.setBounds(280, 295, 115,  25);
		tfProRad.setBounds(280, 325, 115,  25);
		tfProRad.setEditable(false);
		btProPro.addActionListener(this);
		add(lbTitPro);
		add(lbProPer);
		add(lbProRad);
		add(tfProPer);
		add(btProPro);
		add(tfProRad);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : paint(...)                                                   //
	// Entradas : El contexto gráfico de la aplicación                         //
	// Salidas  : No tiene                                                     //
	// Descripc : Pinta por primera vez el Applet.                             // 
  //-------------------------------------------------------------------------//
	public void paint(Graphics g)
	{
		pintaGrup(g,   5,  10, 405, 215);
		pintaGrup(g,   5, 240, 180, 130);
		pintaGrup(g, 195, 240, 215, 130);
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
	// Nombre   : start(...)                                                   //
	// Entradas : El contexto gráfico de la aplicación                         //
	// Salidas  : No tiene                                                     //
	// Descripc : LLamar a paint para que pinte.                               // 
  //-------------------------------------------------------------------------// 
	public void start(Graphics g)
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
	// Entradas : El contexto gráfico del Applet                               //
	// Salidas  : No tiene                                                     //
	// Descripc : Dibujar en pantalla el sistema de coordenadas con su malla y //
	//            medidas en los ejes. Sólo llama a otros métodos.             //                                         
  //-------------------------------------------------------------------------// 
	private void pintaGraf(Graphics g)
	{
		pintaCuadro(g, 415, 10, 360, 360, Color.white);
		g.translate((415 + 415 + 360)/2, (10 + 10 + 360)/2);
		pintaMallas(g);
		pintaCartes(g);
		if (graficar)    grafRecta(g);
		if (pronosticar) grafProno(g);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : pintaCuadro(...)                                             //
	// Entradas : El contexto gráfico, las coordenadas de inicio del cuadro, el//
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
	// Nombre   : pintaMallas(...)                                             //
	// Entradas : El contexto gráfico del Applet                               //
	// Salidas  : No tiene                                                     //
	// Descripc : Dibujar en pantalla el conjunto de líneas que se asemejan a  //
	//            una malla y además imprime los números de ubicación.         // 
  //-------------------------------------------------------------------------//           
	private void pintaMallas(Graphics g)
	{
		g.setFont(fontPequenia);
		
		for (int i=minPosX; i<=maxPosX; i+=scalaX)
		{
			g.setColor(Color.lightGray);
			g.drawLine(i, minPosY, i, maxPosY);
			g.setColor(Color.black);
			g.drawString(""+(i/10)+"", i-4, 10);
		}	
			
		for (int i=minPosY; i<=maxPosY; i+=scalaY)
		{
			g.setColor(Color.lightGray);
			g.drawLine(minPosX, i, maxPosX, i);
			g.setColor(Color.black);
			if (i != 0) g.drawString(""+(-i/10)+"", 4, i+4);
		}	
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : pintaCartes(...)                                             //
	// Entradas : El contexto gráfico del Applet                               //
	// Salidas  : No tiene                                                     //
	// Descripc : Dibujar en pantalla los ejes coordenados                     //
	//            X <-> T (Años)                                               //
	//            Y <-> R (Gm)                                                 //
  //-------------------------------------------------------------------------//           
	private void pintaCartes(Graphics g)
	{
		g.setColor(Color.black);
		g.drawLine(minPosX, 0, maxPosX, 0);
		g.drawLine(0, minPosY, 0, maxPosY);
		g.setColor(Color.blue);
		g.drawString("T,Años", minPosX+3, 0+25);
		g.drawString("T,Años", maxPosX-30, 0+25);
		g.drawString("R,Gm", 0-30, minPosY+10);
		g.drawString("R,Gm", 0-30, maxPosY-5);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : grafRecta(...)                                               //
	// Entradas : El contexto gráfico del Applet                               //
	// Salidas  : No tiene                                                     //
	// Descripc : Grafica el periodo (T) en función del radio (R)              //
  //-------------------------------------------------------------------------//           
	private void grafRecta(Graphics g)
	{
		double y;
		double posX = 0;
		double posY = 0;
		
		g.setColor(Color.red);
		
		for (float x=minPosX/10; x<=maxPosX/10; x+=0.01)
		{
			y = f(x);
			
			posX =  x*10;
			posY = -y*10;
			
			if ((minPosY <= posY) && (posY <= maxPosY) && (minPosX <= posX) && (posX <= maxPosX))
				g.drawLine((int) posX, (int) posY, (int) (posX+0.1), (int) posY);
		}	
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : grafProno(...)                                               //
	// Entradas : El contexto gráfico del Applet                               //
	// Salidas  : No tiene                                                     //
	// Descripc : Grafica el pronóstico                                        //
  //-------------------------------------------------------------------------//           
	private void grafProno(Graphics g)
	{
		double y;
		double posX = 0;
		double posY = 0;
		
		g.setColor(Color.blue);
		
		y = f(periodo);
		posX =  periodo*10;
		posY = -y*10;
			
		if ((minPosY <= posY) && (posY <= maxPosY) && (minPosX <= posX) && (posX <= maxPosX))
		{
			g.drawLine((int) posX, (int) 0, (int) posX, (int) posY);
			g.drawLine((int) 0, (int) posY, (int) posX, (int) posY);
		}	
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : f(...)                                                       //
	// Entradas : El periodo                                                   //
	// Salidas  : El radio                                                     //
	// Descripc : Retorna un punto de la función de regresión lineal           //
  //-------------------------------------------------------------------------//
	private double f(double x)
	{
		return (a + b*x);
	}	
	
	//-------------------------------------------------------------------------//
	// Nombre   : actionPerformed(...)                                         //
	// Entradas : El evento sobre un botón                                     //
	// Salidas  : No tiene                                                     //
	// Descripc : Implmentar el método que proporciona la acción de un botón   //
	//            pulsado por el usuario                                       // 
  //-------------------------------------------------------------------------// 
	public void actionPerformed(ActionEvent e)
	{
		Object objeto = e.getSource();
		double xi, yi;
		
		if (objeto == btAgre)
		{
			if (verificaDatos())
			{
				xi = new Double(tfPeri.getText()).doubleValue(); 
		    yi = new Double(tfRadi.getText()).doubleValue(); 
		    X.inserta(xi);
		    Y.inserta(yi);
		    tfPeri.setText("");
		    tfRadi.setText("");
		    taList.append("("+xi+" , "+yi+")\n");
		    n++;
				ptoX = xi;
				ptoY = yi;
			}	
		}	
		
		if (objeto == btCalc)
		{
			if (hayDatos())
			{
				calculaAyB();
				graficar = true;
				pronosticar = false;
				repaint();
			}
		}
		
		if (objeto == btLimp)
		{
			X.setDatos();
			Y.setDatos();
			n = 0;
			tfPeri.setText("");
			tfRadi.setText("");
			tfResA.setText("");
			tfResB.setText("");
			tfResEc.setText("");
			tfProPer.setText("");
			tfProRad.setText("");
			for (int i=0; i<100; i++) taList.setText("");
			graficar = false;
			pronosticar = false;
			repaint();
		}
		
		if (objeto == btProPro)
		{
			if (tfProPer.getText().equals("") == false)
			{
				if (n >= 2)
				{
					calculaAyB();
					calculaPro();
				  graficar = true;
					pronosticar = true;
				  repaint();
				}
				else tfProRad.setText("Ingrese datos"); 
			}
			else tfProRad.setText("Falta el Período");
		}	
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : verificaDatos()                                              //
	// Entradas : No tiene                                                     //
	// Salidas  : True si los datos son correctos. False en caso contrario     //
	// Descripc : Comprobar que fueron ingresados los datos.                   // 
  //-------------------------------------------------------------------------//
	private boolean verificaDatos()
	{
		if (tfPeri.getText().equals(""))
		{
			Message mensaje = new Message(200, 200, 250, 100, "Ingrese el Período (T) !!");
			mensaje.show();
			return (false);
		}
		
		if (tfRadi.getText().equals(""))
		{
			Message mensaje = new Message(200, 200, 250, 100, "Ingrese el Radio (R) !!");
			mensaje.show();
			return (false);
		}
		
		return (true);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : hayDatos()                                                   //
	// Entradas : No tiene                                                     //
	// Salidas  : True si hay datos que procesar. False en caso contrario      //
	// Descripc : Comprobar que hay por lo menos dos puntos ingresados datos.  // 
  //-------------------------------------------------------------------------//
	private boolean hayDatos()
	{
		if (n < 2)
		{
			Message mensaje = new Message(200, 200, 300, 100, "Debe ingresar por lo menos dos datos !!");
			mensaje.show();
			return (false);
		}
		
		return (true);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : calculaAyB()                                                 //
	// Entradas : No tiene                                                     //
	// Salidas  : No tiene                                                     //
	// Descripc : Determinar los valores para la pendiente de la curva (b) y   //
  //            el nivel (a).                                                //
  //-------------------------------------------------------------------------//
	private void calculaAyB()
	{
		double sumY  = 0;
		double sumXX = 0;
		double sumX  = 0;
		double sumXY = 0;
		
		for (int i=0; i<n; i++)
		{
			sumY  = sumY  + Y.obtiene(i);
			sumXX = sumXX + Math.pow(X.obtiene(i), 2.0);
			sumX  = sumX  + X.obtiene(i);
			sumXY = sumXY + X.obtiene(i)*Y.obtiene(i);
		}
		
		a = (sumY*sumXX - sumX*sumXY)/((double) (n)*sumXX - Math.pow(sumX, 2.0));
		b = ((double) (n)*sumXY - sumX*sumY)/((double) (n)*sumXX - Math.pow(sumX, 2.0));
		
		tfResA.setText (""+a+"");
		tfResB.setText (""+b+"");
		if ((a > 0)  && (b > 0))  tfResEc.setText ("R(T) = "+a+" + "+b+"T");
		if ((a > 0)  && (b < 0))  tfResEc.setText ("R(T) = "+a+" - "+Math.abs(b)+"T");
		if ((a > 0)  && (b == 0)) tfResEc.setText ("R(T) = "+a+"");
		if ((a < 0)  && (b > 0))  tfResEc.setText ("R(T) = "+a+" + "+b+"T");
		if ((a < 0)  && (b < 0))  tfResEc.setText ("R(T) = "+a+" - "+Math.abs(b)+"T");
		if ((a < 0)  && (b == 0)) tfResEc.setText ("R(T) = "+a+"");
		if ((a == 0) && (b > 0))  tfResEc.setText ("R(T) = "+b+"T");
		if ((a == 0) && (b < 0))  tfResEc.setText ("R(T) = "+b+"T");
		if ((a == 0) && (b == 0)) tfResEc.setText ("R(T) = 0");
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : calculaPro()                                                 //
	// Entradas : No tiene                                                     //
	// Salidas  : No tiene                                                     //
	// Descripc : Determinar el posible dato. Es decir el pronóstico           // 
  //-------------------------------------------------------------------------//
	private void calculaPro()
	{
		periodo = new Double(tfProPer.getText()).doubleValue();  
		tfProRad.setText (""+f(periodo)+"");
	}	
}

	
