//
//  VelocidadMedia.java
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
//  Creación  : 19-Julio-2001
// 
//  -------------------------------------------------------------------------
//  Esta información no es necesariamente definitiva y está  sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//  -------------------------------------------------------------------------
import java.awt.*;
import java.applet.*;
import java.awt.event.*;

//
//  Clase que deriva de la clase Applet y que tiene la función de inicializar, 
//  construir y setear los componentes del Applet. Este Applet permite al 
//  usuario ingresar los datos de distancias y tiempos determinados, para así 
//  poder calcular la la velocidad media de una partícula medida en [m/s]. 
//
public class VelocidadMedia extends Applet implements ActionListener, ItemListener, MouseListener
{
	// Anchura del Applet.
	public final int ancho = 400;
	
	// Altura del Applet.
	public final int alto = 520;
	
	// Color de fondo del Applet.
	public final Color fondo = new Color(255, 204, 0);
	
	// Color del globo.
	public final Color colorGlobo = new Color(255, 102, 0);
	
	// Radio del globo
	public final int radio = 10;
	
	// Componentes AWT:
	private Label lbTitPos, lbY1, lbY2,lbTitTmp, lbT1, lbT2, lbTitRes, lbVelMed;
	private TextField tfY1, tfY2, tfT1, tfT2, tfRes;
	private Choice chY1, chY2, chT1, chT2;
	private Button btCal;
	private Checkbox chbPosY1, chbPosY2;
	private CheckboxGroup chbgPos;
		
	// Sirve para detener o reproducir la animación.
	private boolean pausa = true;
	
	// Variables para realizar los calculos
	private float y1=568, y2=946, t1, t2, deltaX, deltaT;
	
	// Variables que sirven para realizar la animación.
	private int posY1=-56, posY2=-94;
	
	// Cursores para el trabajo
	private Cursor cursorNormal, cursorPresic, cursorEspera;
	
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
	// Descripc : Inicializa los componentes AWT del Applet. Setea su tamaño   //
	//            posición y los adjunta al Applet.                            //
  //-------------------------------------------------------------------------//  
	private void setComponentes()
	{
		String unidLong[] = {"m", "pulg", "cm", "pie", "km", "mi", "milla náutica", "fermi", "angstrom", "año luz", "parsec"}; 
		String unidTemp[] = {"s", "hr", "min", "día", "año"}; 
		
		lbTitPos = new Label("Posición");
		lbY1     = new Label("y1 :");
		lbY2     = new Label("y2 :");
		lbTitTmp = new Label("Tiempo");
		lbT1     = new Label("t1 :");
		lbT2     = new Label("t2 :");
		lbTitRes = new Label("Resultado");
		lbVelMed = new Label("Velocidad Media:");
		
		tfY1  = new TextField("568");
		tfY2  = new TextField("946");
		tfT1  = new TextField("56");
		tfT2  = new TextField("160");
		tfRes = new TextField();
		
		chY1 = new Choice();
		chY2 = new Choice();
		chT1 = new Choice();
		chT2 = new Choice();
		
		btCal = new Button("Calcular");
		
		chbgPos = new CheckboxGroup();
		
		chbPosY1 = new Checkbox("Posición con mouse", chbgPos, true);
		chbPosY2 = new Checkbox("Posición con mouse", chbgPos, false);
		
		cursorNormal = new Cursor(0);
		cursorPresic = new Cursor(1);
		cursorEspera = new Cursor(3);
		
		for (int i=0; i<unidLong.length; i++) { chY1.addItem(unidLong[i]); chY2.addItem(unidLong[i]); }	
		for (int i=0; i<unidTemp.length; i++) {	chT1.addItem(unidTemp[i]); chT2.addItem(unidTemp[i]); }
		
		lbTitPos.setBounds(20, 0, 55, 15);
		lbY1.setBounds(10, 263, 20, 15);
		lbY2.setBounds(10, 291, 20, 15);
		lbT1.setBounds(70, 353, 20, 15);
		lbT2.setBounds(70, 383, 20, 15);
		lbTitTmp.setBounds(20, 328, 55, 15);
		lbTitRes.setBounds(20, 463, 60, 15);
		lbVelMed.setBounds(8, 490, 100, 15);
		
		tfY1.setBounds(35, 260, 110, 23);
		tfY2.setBounds(35, 288, 110, 23);
		tfT1.setBounds(100, 350, 110, 23);
		tfT2.setBounds(100, 380, 110, 23);
		tfRes.setBounds(115, 484, 270, 23);
		
		chY1.setBounds(150, 260, 100, 20);
		chY2.setBounds(150, 288, 100, 20);
		chT1.setBounds(215, 350, 100, 20);
		chT2.setBounds(215, 380, 100, 20);
		
		btCal.setBounds(ancho/2-55, 430, 110, 20);
		
		chbPosY1.setBounds(255, 260, 135, 23);
		chbPosY2.setBounds(255, 288, 135, 23);
		
		tfRes.setEditable(false);
		
		chY1.addItemListener(this);
		chY2.addItemListener(this);
		
		btCal.addActionListener(this);
		
		addMouseListener(this);  
				
		add(lbTitPos);
		add(lbY1);
		add(lbY2);
		add(lbTitTmp);
		add(lbT1);
		add(lbT2);
		add(lbTitRes);
		add(lbVelMed);
		
		add(tfY1);
		add(tfY2);
		add(tfT1);
		add(tfT2);
		add(tfRes);
		
		add(chY1);
		add(chY2);
		add(chT1);
		add(chT2);
		
		add(btCal);
		
		add(chbPosY1);
		add(chbPosY2);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : paint(...)                                                   //
	// Entradas : El contexto gráfico de la aplicación                         //
	// Salidas  : No tiene                                                     //
	// Descripc : Pinta por primera vez el Applet.                             // 
  //-------------------------------------------------------------------------//           
	public void paint(Graphics g)
	{
		pintaCuadro(g, 5, 5, ancho-5, 320);
		pintaCuadro(g, 5, 335, ancho-5, 410);
		pintaCuadro(g, 5, 470, ancho-5, 515);
		grafica(g);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : start(...)                                                   //
	// Entradas : El contexto gráfico de la aplicación                         //
	// Salidas  : No tiene                                                     //
	// Descripc : LLamar a paint para que realize el pintado.                  // 
  //-------------------------------------------------------------------------//           
	public void start(Graphics g)
	{
		pausa = true;
		paint(g);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : stop()                                                       //
	// Entradas : No tiene                                                     //
	// Salidas  : No tiene                                                     //
	// Descripc : Detiene el applet                                            // 
  //-------------------------------------------------------------------------//           
	public void stop()
	{
		pausa = true;
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : update(...)                                                  //
	// Entradas : No tiene                                                     //
	// Salidas  : No tiene                                                     //
	// Descripc : Refrescar el Applet y reducir el flasheo                     // 
  //-------------------------------------------------------------------------//           
	public void update(Graphics g)
	{
		paint(g);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : pintaCuadro(...)                                             //
	// Entradas : Las corrdenadas de incicio y término para el cuadro          //
	// Salidas  : No tiene                                                     //
	// Descripc : Dibujar en pantalla un cuadro que se asemeje a un agrupador  //
	//            de componentes                                               //
  //-------------------------------------------------------------------------//           
	private void pintaCuadro(Graphics g, int x1, int y1, int x2, int y2)
	{
		g.setColor(Color.gray);
		g.drawRect(x1, y1, x2-x1, y2-y1);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : grafica(...)                                                 //
	// Entradas : El contexto gráfico del applet                               //
	// Salidas  : No tiene                                                     //
	// Descripc : Presentar el gráfico                                         //
	//-------------------------------------------------------------------------//           
	private void grafica(Graphics g)
	{
		pintaCuadro(g, 10, 20, 380, 230, Color.white);
		g.translate(ancho/2, 135);
		pintaEjes(g, Color.black);
		pintaCoor(g, Color.black);
		pintaCruz(g, y1, 0, posY1, Color.blue);
		pintaCruz(g, y2, 0, posY2, Color.red);
		
		if (pausa == false)
		{
			if (y1 < y2)
				movArrib(g, posY1, posY2);
			else
				movAbajo(g, posY1, posY2);
		}
		
		setCursor(cursorNormal);
		pausa = true;
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : pintaCuadro(...)                                             //
	// Entradas : El contexto gráico, las coordenadas de inicio del cuadro, el //
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
	// Entradas : El contexto gráico y el color del cuadro.                    //
	// Salidas  : No tiene.                                                    //
	// Descripc : Pinta las coordenadas (los ejes) de referencia.              //  
	//-------------------------------------------------------------------------//
	private void pintaEjes(Graphics g, Color color)
	{
		g.setColor(color);
		g.drawLine(-100, 0, 100, 0);
		g.drawLine(0, -100, 0, 100);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : pintaCoor(...)                                               //
	// Entradas : El contexto gráfico y el color del cuadro.                   //
	// Salidas  : No tiene.                                                    //
	// Descripc : Pinta los títulos de los ejes coordenadas de referencia.     //  
	//-------------------------------------------------------------------------//
	private void pintaCoor(Graphics g, Color color)
	{
		g.setColor(color);
		g.drawString("Y", 0-3, 0-105);
		g.drawString("Y", 0-3, 0+112);
		g.drawString("X", 0-110, 0+3);
		g.drawString("X", 0+110, 0+3);
		g.drawString(", "+chY1.getSelectedItem()+"", 0+10, 0-105);
		g.drawString(", "+chY1.getSelectedItem()+"", 0+110+10, 0+3);
	}	
	
	//-------------------------------------------------------------------------//
	// Nombre   : pintaGlobo(...)                                              //
	// Entradas : El contexto gráfico y las coordenadas de despligue del globo //
	// Salidas  : No tiene.                                                    //
	// Descripc : Pinta el circulo que simula un globo imagen en cierta        //
	//            posición.                                                    //  
	//-------------------------------------------------------------------------//
	private void pintaGlobo(Graphics g, int x, int y, Color color)
	{
		g.setColor(color);
		g.fillOval(x-radio, y-radio, 2*radio, 2*radio);
	}	
	
	//-------------------------------------------------------------------------//
	// Nombre   : movArrib(...)                                                //
	// Entradas : El contexto gráfico y las coordenadas de inicio y fin del    //
	//            desplazamiento                                               // 
	// Salidas  : No tiene.                                                    //
	// Descripc : Realizar el movimiento del globo hacia arriba                //  
	//-------------------------------------------------------------------------//
	private void movArrib(Graphics g, int yInicial, int yFinal)
	{
		for (int i = yInicial; i >= yFinal; i--)
		{
			pintaGlobo(g, 0, i, colorGlobo);
			pausa(20);
			pintaGlobo(g, 0, i, Color.white);
			pintaEjes(g, Color.black);
		}
		pintaGlobo(g, 0, yFinal, colorGlobo);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : movAbajo(...)                                                //
	// Entradas : El contexto gráfico y las coordenadas de inicio y fin del    //
	//            desplazamiento                                               // 
	// Salidas  : No tiene.                                                    //
	// Descripc : Realizar el movimiento del globo hacia abajo                 //  
	//-------------------------------------------------------------------------//
	private void movAbajo(Graphics g, int yInicial, int yFinal)
	{
		for (int i = yInicial; i <= yFinal; i++)
		{
			pintaGlobo(g, 0, i, colorGlobo);
			pausa(20);
			pintaGlobo(g, 0, i, Color.white);
			pintaEjes(g, Color.black);
		}	
		pintaGlobo(g, 0, yFinal, colorGlobo);
	}
	
	//------------------------------------------------------------------//
	// Nombre : pintaCruz(...)                                          //
	// Entrada: El contexto gráfico, las coordenadas y el color         //
	// Salida : No tiene.                                               //
	// Función: Pintar una cruz en determinado punto                    //
	//------------------------------------------------------------------//
	private void pintaCruz(Graphics g, float tfy, int x, int y, Color color)
	{
	  g.setColor(color);
		g.drawLine(x-5, y, x+5, y);
		g.drawLine(x, y-5, x, y+5);
		g.drawString(""+tfy+"", 25, y); 
	}
	
	//------------------------------------------------------------------//
	// Nombre : pausa(...)                                              //
	// Entrada: El tiempo que se desea dar una pausa.                   //
	// Salida : No tiene.                                               //
	// Función: Hacer que la aplicación tenga un momento de pausa, para //
	//          así poder visualizar la animación a nuestra velocidad.  //
	//------------------------------------------------------------------//
	private void pausa(int tiempo) 
	{
  	try { Thread.sleep(tiempo); }
	  catch (InterruptedException e) {}
  }
	
	//-------------------------------------------------------------------------//
	// Nombre   : actionPerformed(...)                                         //
	// Entradas : El evento sobre el botón.                                    //
	// Salidas  : No tiene.                                                    //
	// Descripc : Generar una acción al pulsar sobre el botón                  //
	//-------------------------------------------------------------------------//
	public void actionPerformed(ActionEvent e)
  {
		Object x = e.getSource(); 
		
		if (x == btCal) calcula();
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : actionPerformed(...)                                         //
	// Entradas : El evento sobre la lista.                                    //
	// Salidas  : No tiene.                                                    //
	// Descripc : Generar la acción de cambiar el item seleccionado en la otra //
	//            lista.                                                       //
	//-------------------------------------------------------------------------//
	public void itemStateChanged(ItemEvent e)
	{
    Object x = e.getSource();
		
		if (x == chY1) chY2.select(chY1.getSelectedIndex());
		if (x == chY2) chY1.select(chY2.getSelectedIndex());
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : mouseEntered(...)                                            //
	// Entradas : El evento sobre el mouse.                                    //
	// Salidas  : No tiene.                                                    //
	// Descripc : Capturar las coordenadas de la posición del mouse y si está  //
	//            en el área gráfica, cambiar el cursor                        //
	//-------------------------------------------------------------------------//
	public void mouseEntered(MouseEvent e) 
	{
	  if ((100 <= e.getX()) && (e.getX() <= 300) && (35 <= e.getY()) && (e.getY() <= 235))
		  setCursor(cursorPresic);
		else
		  setCursor(cursorNormal);
			
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : mouseExcited(...)                                            //
	// Entradas : El evento sobre el mouse.                                    //
	// Salidas  : No tiene.                                                    //
	// Descripc : Setear el cursor cuando el mouse se salga del applet         //
	//-------------------------------------------------------------------------//
	public void mouseExited(MouseEvent e)
	{
	  setCursor(cursorNormal);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : mouseCicked(...)                                             //
	// Entradas : El evento sobre el mouse.                                    //
	// Salidas  : No tiene.                                                    //
	// Descripc : Capturar la coordenada y para cuando se haga click en el     //
	//            Applet                                                       //
	//-------------------------------------------------------------------------//
	public void mouseClicked(MouseEvent e)  
	{
	  int y = obtieneY(e.getY());
		
		if ((100 <= e.getX()) && (e.getX() <= 300) && (35 <= e.getY()) && (e.getY() <= 235))
		{
		  if (chbPosY1.getState()) tfY1.setText(""+y+"");
			if (chbPosY2.getState()) tfY2.setText(""+y+"");
			setPos();
			repaint();
		}
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : obtieneY(...)                                                //
	// Entradas : La coordenada Y del mouse.                                   //
	// Salidas  : El valor de Y.                                               //
	// Descripc : Transformar de coordenadas del Applet a coordenadas de Y     //
	//-------------------------------------------------------------------------//
	private int obtieneY(int mouseY)
	{
	  int dY;
		
		dY = 235 - mouseY;
		
		if (dY == 100)
		  return (0);
		else if (dY > 100)
		       return (10*(dY-100));
		     else
					 return (10*(dY-100));
  }		
	
	//-------------------------------------------------------------------------//
	// Nombre   : calcula()                                                    //
	// Entradas : No tiene.                                                    //
	// Salidas  : No tiene.                                                    //
	// Descripc : Lo primero que hace se verificar la corrección de los datos  //
	//            Si están buenos, entonces se realizan los cálculos           //  
	//-------------------------------------------------------------------------//
	private void calcula()
	{
		setCursor(cursorEspera);
		
		if (verifica())
		{
			tfRes.setText(""+(deltaX/deltaT)+" ["+chY1.getSelectedItem()+"/"+chT1.getSelectedItem()+"]");
			pausa = false;
			repaint();
		}
		
		setCursor(cursorNormal);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : verifica()                                                   //
	// Entradas : No tiene.                                                    //
	// Salidas  : No tiene.                                                    //
	// Descripc : Verificar el ingreso correcto de datos                       //
	//-------------------------------------------------------------------------//
	private boolean verifica()
	{
		if (tfY1.getText().equals("")) { tfRes.setText("Ingrese y1"); return (false); }
		if (tfY2.getText().equals("")) { tfRes.setText("Ingrese y2"); return (false); }
		if (tfT1.getText().equals("")) { tfRes.setText("Ingrese t1"); return (false); }
		if (tfT2.getText().equals("")) { tfRes.setText("Ingrese t2"); return (false); }
		
		y1 = new Float(tfY1.getText()).floatValue();
		y2 = new Float(tfY2.getText()).floatValue();
		
		t1 = new Float(tfT1.getText()).floatValue();
		t2 = new Float(tfT2.getText()).floatValue();
		
		if ((-1000 > y1) || (y1 > 1000)) { tfRes.setText("y1 está fuera del rango [-1000, 1000] !!"); return (false); }
		if ((-1000 > y2) || (y2 > 1000)) { tfRes.setText("y2 está fuera del rango [-1000, 1000] !!"); return (false); }
		
		if (t1 < 0)   { tfRes.setText("t1 debe ser positivo !!"); return (false); }
		if (t2 < 0)   { tfRes.setText("t2 debe ser positivo !!"); return (false); }
		
		if (t1 == t2) { tfRes.setText("t1 y t2 deben ser distintos !!"); return (false); }
		if (t1 > t2)  { tfRes.setText("t2 debe ser mayor que t1 !!");    return (false); }
		
		posY1 = (int) (-y1/10);
		posY2 = (int) (-y2/10);
		
		deltaX = y1 - y2;
		deltaT = t1 - t2;
	
	  return (true);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : setPos()                                                     //
	// Entradas : No tiene.                                                    //
	// Salidas  : No tiene.                                                    //
	// Descripc : Reinicializa las coordenadas de posición de las cruzes y     //
	//            de inicio y término de la animación.                         // 
	//-------------------------------------------------------------------------//
	private void setPos()
	{
	  y1 = new Float(tfY1.getText()).floatValue();
		y2 = new Float(tfY2.getText()).floatValue();
		
		posY1 = (int) (-y1/10);
		posY2 = (int) (-y2/10);
	}	
	
	// Interfaces sin implementar
	public void mousePressed(MouseEvent e)  {}
	public void mouseReleased(MouseEvent e) {}
}
