//
//  VelocidadInstantanea.java
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
import java.applet.*;

//
//  Clase que deriva de la clase Applet y que tiene la implementa la
//  clase ActionListener. Esta clase permite al usuario ingresar los 
//  para una ecuación (parábola) y  la grafica. Ademas se relaciona 
//  con la Velocidad Instantánea.
//
public class VelocidadInstantanea extends Applet implements ActionListener
{
	// Anchura del Applet.
	public final int ancho = 500;
	
	// Altura del Applet.
	public final int alto = 525;
	
	// Color de fondo del Applet.
	public final Color fondo = new Color(255, 204, 0);
	
	// Componentes AWT del grupo Ecuacion.
	private Label lbTitEcuac, lbEc, lbA, lbB, lbC;
	private TextField tfA, tfB, tfC;
	private Button btGraf;
	
	// Componentes AWT del grupo Velocidad Instantánea.
	private Label lbTitVelIns, lbForVelIns, lbT, lbResVelIns;
	private TextField tfT, tfResVelIns;
	private Button btVelIns;
	
	// Componentes AWT del grupo Velocidad Media.
	private Label lbTitVelMed, lbForVelMed, lbT1, lbT2, lbResVelMed;
	private TextField tfT1, tfT2, tfResVelMed;
	private Button btVelMed;
	
	// Fuente para números (pequeña)
	private Font fontPequenia;
		
	// Variables que sirven para crear la escala en píxeles del eje X e Y
	private int scalaX = 20;
	private int scalaY = 20;
	private int minPosX = 0 - ancho/2 + 10;         // -240 
	private int maxPosX = 0 + ancho/2 - 10;         //  240 
	private int minPosY = 0 - 340/2   + 10;         // -160
	private int maxPosY = 0 + 340/2   - 10;         //  160
	
	// Variables que componen la ecuación
	private float a = 5;
	private float b = 0;
	private float c = 0;
	
	// Variables para calcular la velocidad instantánea
	private float t = 1;
	private float m = 10;
	private float n = -49;
	
	// Variables para calcular la velocidad media
	private float t1, t2;
	
	// Variable para la impresión en pantalla de errores.
	private String error = "";
	
	// Sirve para manejar los errores de datos de entrada.
	private Message mensaje;
	
	// Variables que sirven para la elección de gráficas.
	private boolean parabola = true;
	private boolean tangente = true;
	
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
		setBackground(fondo);
		setComponentes();
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
		fontPequenia = new Font("SanSerif", Font.PLAIN, 9);
		setEc();
		setVelIns();
		setVelMed();
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : setEc()                                                      //
	// Entradas : No tiene                                                     //
	// Salidas  : No tiene                                                     //
	// Descripc : Inicializa los componentes AWT del Applet del grupo Ecuacion.//
  //            Setea su tamaño posición y los adjunta al Applet.            //
  //-------------------------------------------------------------------------//
	private void setEc()
	{
		lbTitEcuac  = new Label("Ecuación");
		lbEc        = new Label("x( t ) = at^2 + bt + c");
		lbA         = new Label("a =");
		lbB         = new Label("b =");
		lbC         = new Label("c =");
		
		tfA         = new TextField("5");
		tfB         = new TextField("0");
		tfC         = new TextField("0");
		
		btGraf = new Button("Graficar");
		
		lbTitEcuac.setBounds(20, 0, 55, 15);
		lbEc.setBounds(30, 25, 125, 15);
		lbA.setBounds(15,  60, 15, 15);
		lbB.setBounds(15,  85, 15, 15);
		lbC.setBounds(15, 110, 15, 15);
		
		tfA.setBounds(40,  55, 100, 20);
		tfB.setBounds(40,  80, 100, 20);
		tfC.setBounds(40, 105, 100, 20);
		
		btGraf.setBounds(40, 130, 100, 20);
		
		btGraf.addActionListener(this);
		
		add(lbTitEcuac);
		add(lbEc);
		add(lbA);
		add(lbB);
		add(lbC);
		
		add(tfA);
		add(tfB);
		add(tfC);
		
		add(btGraf);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : setVelIns()                                                  //
	// Entradas : No tiene                                                     //
	// Salidas  : No tiene                                                     //
	// Descripc : Inicializa los componentes AWT del Applet del grupo Velocidad//
	//            instatánea.  Setea su tamaño posición y los adjunta al Applet//
  //-------------------------------------------------------------------------//
	private void setVelIns()
	{
		lbTitVelIns = new Label("Velocidad Instantánea");
		lbForVelIns = new Label("Vel. ins.=lím(Delta x/Delta t)");
		lbT         = new Label("t =");
		lbResVelIns = new Label("Vel. ins. =");
		
		tfT         = new TextField("1");
		tfResVelIns = new TextField("10.0 [m/s]");
		
		btVelIns    = new Button("Calcular");
		
		lbTitVelIns.setBounds(175, 0, 125, 15);
		lbForVelIns.setBounds(175, 25, 152, 15);
		lbT.setBounds(180, 60, 20, 15);
		lbResVelIns.setBounds(180, 133, 60, 15);
		
		tfT.setBounds(200,  55, 120, 20);
		tfResVelIns.setBounds(240,  130, 80, 20);
		
		btVelIns.setBounds(240, 80, 80, 20);
		
		tfResVelIns.setEditable(false);
		
		btVelIns.addActionListener(this);
		
		add(lbTitVelIns);
		add(lbForVelIns);
		add(lbT);
		add(lbResVelIns);
		
		add(tfT);
		add(tfResVelIns);
		
		add(btVelIns);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : setVelMed()                                                  //
	// Entradas : No tiene                                                     //
	// Salidas  : No tiene                                                     //
	// Descripc : Inicializa los componentes AWT del Applet del grupo Velocidad//
	//            media.  Setea su tamaño posición y los adjunta al Applet     //
  //-------------------------------------------------------------------------//
	private void setVelMed()
	{
		lbTitVelMed = new Label("Velocidad Media");
		lbForVelMed = new Label("Vel. med. = (Delta x/Delta t)");
		lbT1        = new Label("t1 =");
		lbT2        = new Label("t2 =");
		lbResVelMed = new Label("Vel. med. =");
			
		tfT1        = new TextField("9");
		tfT2        = new TextField("21");
		tfResVelMed = new TextField("");
						
		btVelMed = new Button("Calcular");
				
		lbTitVelMed.setBounds(340, 0, 95, 15);
		lbForVelMed.setBounds(340, 25, 150, 15);
		lbT1.setBounds(350,  60, 20, 15);
		lbT2.setBounds(350,  85, 20, 15);
		lbResVelMed.setBounds(350, 133, 60, 15);
		
		tfT1.setBounds(380,  55, 110, 20);
		tfT2.setBounds(380,  80, 110, 20);
		tfResVelMed.setBounds(420, 130, 70, 20);
		
		btVelMed.setBounds(380, 105, 110, 20);
		
		tfResVelMed.setEditable(false);
				
		btVelMed.addActionListener(this);
		
		add(lbTitVelMed);
		add(lbForVelMed);
		add(lbT1);
		add(lbT2);
		add(lbResVelMed);
				
		add(tfT1);
		add(tfT2);
		add(tfResVelMed);
		
		add(btVelMed);
	}

	//-------------------------------------------------------------------------//
	// Nombre   : paint(...)                                                   //
	// Entradas : El contexto gráfico de la aplicación                         //
	// Salidas  : No tiene                                                     //
	// Descripc : Pinta por primera vez el Applet.                             // 
  //-------------------------------------------------------------------------// 
	public void paint(Graphics g)
	{
		pintaGrup(g, 5, 5, 160, 170);
		pintaGrup(g, 170, 5, 160, 170);
		pintaGrup(g, 335, 5, 160, 170);
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
		pintaCuadro(g, 5, 180, 490, 340, Color.white);
		g.translate(ancho/2, (180+180+340)/2);
		pintaMallas(g);
		pintaCartes(g);
		if (parabola) grafParabola(g);
		if (tangente) grafTangente(g);
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
	// Nombre   : pintaCartes(...)                                             //
	// Entradas : El contexto gráfico del Applet                               //
	// Salidas  : No tiene                                                     //
	// Descripc : Dibujar en pantalla los ejes coordenados                     //
	//            X <-> t (s)                                                  //
	//            Y <-> x (m)                                                  //
  //-------------------------------------------------------------------------//           
	private void pintaCartes(Graphics g)
	{
		g.setColor(Color.black);
		g.drawLine(minPosX, 0, maxPosX, 0);
		g.drawLine(0, minPosY, 0, maxPosY);
		g.setColor(Color.blue);
		g.drawString("t,s", minPosX-4, 0+25);
		g.drawString("t,s", maxPosX-4, 0+25);
		g.drawString("x,m", 0-18, minPosY-3);
		g.drawString("x,m", 0-18, maxPosY+7);
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
			if (i != 0) g.drawString(""+(-i)+"", 4, i+4);
		}	
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : grafParabola(...)                                            //
	// Entradas : El contexto gráfico del Applet                               //
	// Salidas  : No tiene                                                     //
	// Descripc : Grafica el desplazamiento (x) en función del tiempo (t)      //
  //-------------------------------------------------------------------------//           
	private void grafParabola(Graphics g)
	{
		float x;
		float posX = 0;
		float posY = 0;
		
		g.setColor(Color.red);
		
		for (float tmov=minPosX/10; tmov<=maxPosX/10; tmov+=0.01)
		{
			x = f(tmov);
			
			posX = tmov*10;
			posY = -x;
			
			if ((minPosY <= posY) && (posY <= maxPosY) && (minPosX <= posX) && (posX <= maxPosX))
				g.drawLine((int) posX, (int) posY, (int) (posX+0.1), (int) posY);
		}	
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : grafTangente(...)                                            //
	// Entradas : El contexto gráfico del Applet                               //
	// Salidas  : No tiene                                                     //
	// Descripc : Grafica la velocidad instantánea de la curva, es decir una   //
	//            línea recta tangente a la parábola                           //
  //-------------------------------------------------------------------------//           
	private void grafTangente(Graphics g)
	{
		float x;
		float posX = 0;
		float posY = 0;
		
		m = df(t);
		n = f(t) - m*t;
		
		g.setColor(Color.blue);
		
		for (float tmov=minPosX/10; tmov<=maxPosX/10; tmov+=0.01)
		{
			x = g(tmov);
			
			posX = tmov*10;
			posY = -x;
			
			if ((minPosY <= posY) && (posY <= maxPosY) && (minPosX <= posX) && (posX <= maxPosX))
				g.drawLine((int) posX, (int) posY, (int) (posX+0.1), (int) posY);
		}	
	}	
		
	//-------------------------------------------------------------------------//
	// Nombre   : f(...)                                                       //
	// Entradas : El tiempo t.                                                 //
	// Salidas  : El desplazamiento x.                                         //
	// Descripc : Calcular el desplazamiento en cada t que se ingresa.         //
  //-------------------------------------------------------------------------//           
	private float f(float t)
  {
    return (a*t*t + b*t + c);
	}	
		
	//-------------------------------------------------------------------------//
	// Nombre   : df(...)                                                      //
	// Entradas : El tiempo t                                                  //
	// Salidas  : La velocidad instantánea v.                                  //
	// Descripc : Calcular la velocidad instantánea en cada t que se ingresa.  //
  //-------------------------------------------------------------------------//           
	private float df(float t)
  {
    return (2*a*t + b);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : g(...)                                                       //
	// Entradas : El tiempo t.                                                 //
	// Salidas  : El desplazamiento x.                                         //
	// Descripc : Calcular el desplazamiento x. Pero ahora para la recta tang. // 
  //-------------------------------------------------------------------------//           
	private float g(float t)
	{
		return (m*t + n);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : ActionPerformed(...)                                         //
	// Entradas : El evento sobre un botón                                     //
	// Salidas  : No tiene                                                     //
	// Descripc : Implmentar el método que proporciona la acción de un botón   //
	//            pulsado por el usuario                                       // 
  //-------------------------------------------------------------------------//           
	public void actionPerformed(ActionEvent e)
	{
		Object x = e.getSource();
		
		if (x == btGraf) 
		{
			if (verifDatosEc())
			{
				parabola = true;
				tangente = false;
				repaint();
			}	
			else 
			{
				mensaje = new Message(200, 200, 200, 110, error);
				mensaje.show();
			}
		}
		
		if (x == btVelIns)
		{
			if ((verifDatosEc()) && (verifDatosVelIns()))
			{
				tfResVelIns.setText(""+df(t)+" [m/s]");
				parabola = true;
				tangente = true;
				repaint();
			}	
			else 
			{
				mensaje = new Message(200, 200, 200, 110, error);
				mensaje.show();
			}
		}
		
		if (x == btVelMed)
		{
			if ((verifDatosEc()) && (verifDatosVelMed()))
				tfResVelMed.setText(""+((f(t2)-f(t1))/(t2-t1))+" [m/s]");
			else 
			{
				mensaje = new Message(200, 200, 200, 110, error);
				mensaje.show();
			}
		}
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : verifDatosEc(...)                                            //
	// Entradas : No tiene                                                     //
	// Salidas  : True si los datos son correctos. False en caso contrario     //
	// Descripc : Setear los datos de entrada para a, b y c. Además verifica si//
	//            cumplen con los parámetros requeridos para el problema       //
  //-------------------------------------------------------------------------//           
	private boolean verifDatosEc()
	{
		if (tfA.getText().equals("")) 
		{ 
			error = "Ingrese el dato a !!";
			return (false); 
		}
		
		if (tfB.getText().equals("")) 
		{ 
			error = "Ingrese el dato b !!";
			return (false); 
		}
		
		if (tfC.getText().equals("")) 
		{ 
			error = "Ingrese el dato c !!";
			return (false); 
		}
		
		a = new Float(tfA.getText()).floatValue();
		b = new Float(tfB.getText()).floatValue();
		c = new Float(tfC.getText()).floatValue();
		
		return (true);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : verifDatosVelIns(...)                                        //
	// Entradas : No tiene                                                     //
	// Salidas  : True si los datos son correctos. False en caso contrario     //
	// Descripc : Setear el dato de entrada para t.                            // 
  //-------------------------------------------------------------------------//           
	private boolean verifDatosVelIns()
	{
		if (tfT.getText().equals("")) 
		{ 
			error = "Ingrese el tiempo t !!";
			return (false); 
		}
		
		t = new Float(tfT.getText()).floatValue();
		
		return (true);
	}	
	
	//-------------------------------------------------------------------------//
	// Nombre   : verifDatosVelMed(...)                                        //
	// Entradas : No tiene                                                     //
	// Salidas  : True si los datos son correctos. False en caso contrario     //
	// Descripc : Setear los datos de entrada para t1 y t2.                    // 
  //-------------------------------------------------------------------------//           
	private boolean verifDatosVelMed()
	{
		if (tfT1.getText().equals("")) 
		{ 
			error = "Ingrese el tiempo t1 !!";
			return (false); 
		}
		
		if (tfT2.getText().equals("")) 
		{ 
			error = "Ingrese el tiempo t2 !!";
			return (false); 
		}
		
		t1 = new Float(tfT1.getText()).floatValue();
		t2 = new Float(tfT2.getText()).floatValue();
		
		if (t1 == t2) 
		{ 
			error = "t1 debe ser distinto a t2 !!";
			return (false); 
		}
			
		return (true);
	}
}


