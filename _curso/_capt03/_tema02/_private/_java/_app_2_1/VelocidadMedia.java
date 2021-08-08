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
//  Creación  : 18-Julio-2001
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
//  usuario ingresar los datos de distancias y tiempos determinados, para así 
//  poder calcular la rapidez media y la velocidad media de una partícula medida 
//  en [km/hr]. 
//
public class VelocidadMedia extends Applet
{
	// Anchura del Applet.
	public final int anchoApplet = 460;
	
	// Altura del Applet.
	public final int altoApplet  = 500;
	
	// Color de fondo del Applet.
	final Color fondo = new Color(255, 204, 0);
	
	// Componentes AWT:
	// Etiquetas que sirven para mostrar los mensajes dentro del Applet.
	private Label lbTitDat, lbDisIda, lbTmpIda, lbDisVue, lbTmpVue;
	private Label lbTitRes;
	
	// Cuadros de Textos que sirven para ingresar datos.
	private TextField tfDisIda, tfTmpIda, tfDisVue, tfTmpVue;
	
	// Listas de opciones que sirven para escoger entre varios items.
	private Choice chDisIda, chDirIda, chTmpIda, chDisVue, chDirVue, chTmpVue;
	
	// Botón que servirá para ejecutar el cálculo.
	private Button btCal;
	
	// Areas de Textos que utilizo para mostrar la variada información obtenida.
	private TextArea taRes;
	
	// Variables para calcular los resultados.
	private float disIda, tmpIda, disVue, tmpVue;
  private float disTot, desTot, tmpTot, rapMed, velMed;
	
	// Elemento gráfico en el cual se dibujarán todas las animaciones.
	//private Graphics grafico;
	
	// Imagen de un avión que posteriormente se animará.
	private Image imagen;
	
	// Contador del número de problemas que sirve para mostrar en pantalla
	// el N° del problema que llevamos resuelto.
	private int contProb = 1;
	
	// Sirve para detener o reproducir la animación.
	private boolean pause = true;
	
	// Coordenadas del movimiento
	private int x1, x2, x3, x4;
	
	// Sonido que se reproduce al momento de empezar la animación del tren.
	private AudioClip sonido;
	
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
		setDatos();
		setResultados();
		setImage();
		setAudio();
		resize(anchoApplet, altoApplet);
	}

	//-------------------------------------------------------------------------//
	// Nombre   : start(...)                                                   //
	// Entradas : El contexto gráfico de la aplicación                         //
	// Salidas  : No tiene                                                     //
	// Descripc : LLamar a paint cuando se trae a pantalla el Applet.          // 
  //-------------------------------------------------------------------------//           
	public void start(Graphics g)
	{
		paint(g);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : update(...)                                                  //
	// Entradas : El contexto gráfico de la aplicación                         //
	// Salidas  : No tiene                                                     //
	// Descripc : LLamar a paint cuando se desea repintar el Applet. Esto      //
	//            reduce el flasheo de la pantalla.                            // 
  //-------------------------------------------------------------------------//           
	public void update(Graphics g)
	{
		paint(g);
	}
	
	//--------------------------------------------------------------------------//
	// Nombre : stop()                                                          //
	// Entrada: No tiene.                                                       //
	// Salida : No tiene                                                        //
	// Función: Parar la animación.                                             //
	//--------------------------------------------------------------------------//
	public void stop()
	{
		pause = true;
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : paint(...)                                                   //
	// Entradas : El contexto gráfico de la aplicación                         //
	// Salidas  : No tiene                                                     //
	// Descripc : Pinta por primera vez el Applet.                             // 
  //-------------------------------------------------------------------------//           
	public void paint(Graphics g)
	{
		pintaCuadro(g, 5,   5, anchoApplet-5, 165);
		pintaCuadro(g, 5, 185, anchoApplet-5, 315);
		grafico(g);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : setDatos()                                                   //
	// Entradas : No tiene                                                     //
	// Salidas  : No tiene                                                     //
	// Descripc : Inicializa los componentes AWT del grupo "datos", los ubica  //
	//            y los adiere al Applet                                       // 
  //-------------------------------------------------------------------------//           
	private void setDatos()
	{
		String unidLong[] = {"km", "pulg", "cm", "pie", "m", "mi", "milla náutica", "fermi", "angstrom", "año luz", "parsec"}; 
		String unidTemp[] = {"hr", "s", "min", "día", "año"}; 
		String direc[]    = {"Este", "Oeste"}; 
		int i;
		
		lbTitDat = new Label("Datos");
		lbDisIda = new Label("Distancia de ida     :");
		lbTmpIda = new Label("Tiempo de ida        :");
		lbDisVue = new Label("Distancia de vuelta:");
		lbTmpVue = new Label("Tiempo de vuelta   :");
		
		tfDisIda = new TextField("8000");
		tfTmpIda = new TextField("2.5");
		tfDisVue = new TextField("5050");
		tfTmpVue = new TextField("1.2");
		
		chDisIda = new Choice();
		chDirIda = new Choice();
		chTmpIda = new Choice();
		chDisVue = new Choice();
		chDirVue = new Choice();
		chTmpVue = new Choice();
		
		btCal = new Button("Calcular");
		
		lbTitDat.setBounds(30,   0,  35, 15);
		lbDisIda.setBounds(10,  36, 110, 15);
		lbTmpIda.setBounds(10,  63, 110, 15);
		lbDisVue.setBounds(10,  90, 110, 15);
		lbTmpVue.setBounds(10, 117, 110, 15);
		
		tfDisIda.setBounds(125, 28,  110, 23);
		tfTmpIda.setBounds(125, 55,  110, 23);
		tfDisVue.setBounds(125, 82,  110, 23);
		tfTmpVue.setBounds(125, 109, 110, 23);
		
		chDisIda.setBounds(240, 28,  100, 20);
		chDirIda.setBounds(345, 28,  100, 20);
		chTmpIda.setBounds(240, 55,  100, 20);
		chDisVue.setBounds(240, 82,  100, 20);
		chDirVue.setBounds(345, 82,  100, 20);
		chTmpVue.setBounds(240, 109, 100, 20);
		
		btCal.setBounds(345, 140, 100, 20);
		
		for (i=0; i<unidLong.length; i++) 
		{
			chDisIda.addItem(unidLong[i]);
			chDisVue.addItem(unidLong[i]);
		}	
		
		for (i=0; i<unidTemp.length; i++) 
		{
			chTmpIda.addItem(unidTemp[i]);
			chTmpVue.addItem(unidTemp[i]);
		}	
		
		for (i=0; i<direc.length; i++) 
			chDirIda.addItem(direc[i]);
		
		for (i=direc.length-1; i>=0; i--) 
			chDirVue.addItem(direc[i]);
					
		add(lbTitDat);
		add(lbDisIda);
		add(lbTmpIda);
		add(lbDisVue);
		add(lbTmpVue);
		
		add(tfDisIda);
		add(tfTmpIda);
		add(tfDisVue);
		add(tfTmpVue);
		
		add(chDisIda);
		add(chDirIda);
		add(chTmpIda);
		add(chDisVue);
		add(chDirVue);
		add(chTmpVue);
		
		add(btCal);
	}	
	
	//-------------------------------------------------------------------------//
	// Nombre   : setResultados()                                              //
	// Entradas : No tiene                                                     //
	// Salidas  : No tiene                                                     //
	// Descripc : Inicializa los componentes AWT del grupo "resultado", los    //
	//            ubica y los adiere al Applet                                 // 
  //-------------------------------------------------------------------------//           
	private void setResultados()
	{
		lbTitRes = new Label("Resultados");
		
		lbTitRes.setBounds(30, 180, 70, 15);
	
		add(lbTitRes);
		
		taRes = new TextArea();
		
		taRes.setBounds(15, 205, 325, 100);
		
		taRes.setEditable(false);
		
		add(taRes);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : setImagen()                                                  //
	// Entradas : No tiene                                                     //
	// Salidas  : No tiene                                                     //
	// Descripc : Cargar la imágen del avión (avion.gif)                       //
  //-------------------------------------------------------------------------//           
	private void setImage()
	{
		imagen = getImage(getDocumentBase(), "imagenes/avion.gif");  
	}	
	
	//-------------------------------------------------------------------------//
	// Nombre : setAudio()                                                     //
	// Entrada: No tiene                                                       // 
	// Salida : No tiene                                                       //
	// Función: Permite incorporar el sonido que se reproducirá al momento     // 
	//          de comenzar la animación del tren (archivo sonidotren.gif).    //
	//-------------------------------------------------------------------------//
	private void setAudio()
	{
		sonido = getAudioClip(getDocumentBase(), "sonidos/sonidoavion.au");
	}
	
	//-------------------------------------------------------------------------//
	// Nombre  : action(...)                                                   //
	// Entrada : El evento y el objeto.                                        //
	// Salida  : Retorna verdadero (1) si el evento ocurrió. En caso contrario //
	//           devuelve falso (0).                                           //                     
	// Descripc: Permite darle vida al botón. Es decir, incorporarle un evento // 
	//           y una acción que realize cuando se clickee sobre él.          // 
	//-------------------------------------------------------------------------//
	public boolean action(Event e, Object x) 
	{
    if (e.target instanceof Button) 
		{
   		calcula();
			return (true);
    }
    return (false);
  }
	
	//----------------------------------------------------------------------------//
	// Nombre : calcula()                                                         //
	// Entrada: No tiene.                                                         // 
	// Salida : No tiene.                                                         //
	// Función: LLamar al método verifica para preguntar si estan todos los datos //
	//          ingresados en forma correcta. En caso afirmativo se realizan      //
	//          los cálculos. En caso contrario no se realiza nada.               //
	//----------------------------------------------------------------------------//
	private void calcula()
	{
		if (verifica())
		{
			Distancia dIda = new Distancia(chDisIda.getSelectedItem(), disIda);
			Distancia dVue = new Distancia(chDisVue.getSelectedItem(), disVue);
			
			Tiempo tIda = new Tiempo(chTmpIda.getSelectedItem(), tmpIda);
			Tiempo tVue = new Tiempo(chTmpVue.getSelectedItem(), tmpVue);
			
			disIda = (float) dIda.convDist();
			disVue = (float) dVue.convDist();
			
			tmpIda = (float) tIda.convTiemp();
			tmpVue = (float) tVue.convTiemp();
			
			disTot = disIda+disVue;
			
			if (chDirIda.getSelectedItem().equals(chDirVue.getSelectedItem()))
				desTot = disIda + disVue;
			else desTot = disIda - disVue;	
			
			tmpTot = tmpIda+tmpVue;
			
			rapMed = disTot/tmpTot;
			
			velMed = desTot/tmpTot;
			
			taRes.appendText("==========================================\n");
			taRes.appendText("Problema #"+contProb+"\n");
			taRes.appendText("-------------------\n");
			taRes.appendText("Distancia de ida     : "  +disIda+" "+chDisIda.getSelectedItem()+" dirección "+chDirIda.getSelectedItem()+"\n");
			taRes.appendText("Tiempo de ida        : "  +tmpIda+" "+chTmpIda.getSelectedItem()+"\n");
			taRes.appendText("Distancia de vuelta: "    +disVue+" "+chDisVue.getSelectedItem()+" dirección "+chDirVue.getSelectedItem()+"\n");
			taRes.appendText("Tiempo de vuelta   : "    +tmpVue+" "+chTmpVue.getSelectedItem()+"\n");
			taRes.appendText("Distancia total        : "+disTot+" km\n");
			taRes.appendText("Desplazamiento    : "     +desTot+" km\n");
			taRes.appendText("Tiempo total           : "+tmpTot+" hr\n");
			taRes.appendText("Rapidez media      : "    +disTot/tmpTot+" km/hr\n");
			taRes.appendText("Velocidad media   : "     +desTot/tmpTot+" km/hr\n");
			
			contProb++;
			
			pause = false;
			repaint();
		}
	}
	
	//----------------------------------------------------------------------------//
	// Nombre     : verifica()                                                    //
	// Entrada    : No tiene.                                                     //
	// Salida     : Retorna verdadero (1) si los datos fueron ingresados correcta //
	//              mente. En caso contrario retorna falso (0).                   //
	// Descripción: Comprobar que los datos fueron ingresados correctamente. Es   //
	//              decir, los cuadros de textos deben ser ingresados con datos y //
	//              las variables acordes al problema.                            //
	//----------------------------------------------------------------------------//
	private boolean verifica()
	{
		if (tfDisIda.getText().equals(""))
    {
		  taRes.appendText("Ingrese la distancia de ida !!\n");
			return (false);
		}
		
		if (tfTmpIda.getText().equals(""))	
		{
		  taRes.appendText("Ingrese el tiempo de ida !!\n");
			return (false);	
		}
		
		if (tfDisVue.getText().equals(""))	
		{
		  taRes.appendText("Ingrese la distancia de vuelta !!\n");
			return (false);	
		}
		
		if (tfTmpVue.getText().equals(""))	
		{
		  taRes.appendText("Ingrese el tiempo de vuelta !!\n");
			return (false);	
		}
						
		disIda = new Float(tfDisIda.getText()).floatValue();
		tmpIda = new Float(tfTmpIda.getText()).floatValue();
		disVue = new Float(tfDisVue.getText()).floatValue();
		tmpVue = new Float(tfTmpVue.getText()).floatValue();
		
		if (disIda <= 0)
    {
		  taRes.appendText("La distancia de ida debe ser positiva !!\n");
			return (false);
		}
		
		if (tmpIda <= 0)	
		{
		  taRes.appendText("El tiempo de ida debe ser positivo !!\n");
			return (false);	
		}
		
		if (disVue <= 0)	
		{
		  taRes.appendText("La distancia de vuelta debe ser positiva !!\n");
			return (false);	
		}
		
		if (tmpVue <= 0)	
		{
		  taRes.appendText("El tiempo de vuelta debe ser positivo !!\n");
			return (false);	
		}
		
		setPuntos();
		return (true);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : setPuntos()                                                  //
	// Entradas : No tiene                                                     //
	// Salidas  : No tiene                                                     //
	// Descripc : Inicializar y setear los datos de coordenadas para la        //
	//            animación del avión.                                         //
	//-------------------------------------------------------------------------// 
	private void setPuntos()
	{
		x1 = 0;
		
		if (chDirIda.getSelectedItem().equals("Este"))  x2 = x1 + acota((int) disIda);
		if (chDirIda.getSelectedItem().equals("Oeste")) x2 = x1 - acota((int) disIda);
		
		x3 = x2;
		
		if (chDirVue.getSelectedItem().equals("Este"))  x4 = x3 + acota((int) disVue);
		if (chDirVue.getSelectedItem().equals("Oeste")) x4 = x3 - acota((int) disVue);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : acota()                                                      //
	// Entradas : El número que se quiere acotar                               //
	// Salidas  : El número acotado                                            //
	// Descripc : Transformar un número que se ingresa al método y tratar de   //
	//            acotarlo para que así no se salga del rango de las coorde-   //
	//            nadas de la animación.                                       // 
	//-------------------------------------------------------------------------//
  private int acota(int x)
	{
		int max = 80;
		int num = 0;
			
		if (Math.abs(x)/100 < max)
		{
			num = x/100;
		}	
		else 	
		{
			if (x > 0 ) num =  1*max;
			if (x < 0 ) num = -1*max;
		}
		
		return (num);
	}	
	
	//-------------------------------------------------------------------------//
	// Nombre   : grafico()                                                    //
	// Entradas : No tiene                                                     //
	// Salidas  : No tiene                                                     //
	// Descripc : Presentar el gráfico                                         //
	//-------------------------------------------------------------------------//           
	private void grafico(Graphics g)
	{
		pintaCuadro(g, 5, 325, 450, 170, Color.white);
		g.translate(anchoApplet/2, 410);
		pintaBrujul(g, Color.black);
		pintaCoorde(g, Color.black);
		pintaImagen(g, 0-17, 0-17);
		
		if (pause == false)
		{
			sonido.play();
			if (chDirIda.getSelectedItem().equals("Este"))
			{
				linea(g, ""+disIda+" "+chDisIda.getSelectedItem()+"", x1, x2, -40, Color.blue);
				anima(g, x1, x2);
				if (chDirVue.getSelectedItem().equals("Este"))  
				{ 
					linea(g, ""+disVue+" "+chDisVue.getSelectedItem()+"", x3, x4, 40, Color.red); 
					anima(g, x3, x4); 
				}
				if (chDirVue.getSelectedItem().equals("Oeste")) 
				{ 
					linea(g, ""+disVue+" "+chDisVue.getSelectedItem()+"", x3, x4, 40, Color.red); 
					anima(g, x3, x4); 
				}
			}	
			if (chDirIda.getSelectedItem().equals("Oeste"))
			{
				linea(g, ""+disIda+" "+chDisIda.getSelectedItem()+"", x1, x2, -40, Color.blue);
				anima(g, x1, x2);	
				if (chDirVue.getSelectedItem().equals("Oeste")) 
				{ 
					linea(g, ""+disVue+" "+chDisVue.getSelectedItem()+"", x3, x4, 40, Color.red); 
					anima(g, x3, x4); 
				}
				if (chDirVue.getSelectedItem().equals("Este"))  
				{ 
					linea(g, ""+disVue+" "+chDisVue.getSelectedItem()+"", x3, x4, 40, Color.red); 
					anima(g, x3, x4); 
				} 
			}
			sonido.stop();	
		}
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : linea(...)                                                   //
	// Entradas : El contexto gráico, el mensaje que se desea imprimir y las   //
	//            coordenadas de inicio y final de la linea                    //  
	// Salidas  : No tiene                                                     //
	// Descripc : Dibujar la trayectoria realizada por el avión.               //  
	//-------------------------------------------------------------------------//
	private void linea(Graphics g, String msg, int xI, int xF, int y, Color c)
	{
		g.setColor(c);
		g.drawLine(xI, y, xF, y);
		if (c == Color.blue) g.drawString(msg, (xI+xF)/2, y-20);
		if (c == Color.red ) g.drawString(msg, (xI+xF)/2, y+20);
	}	
	
	//-------------------------------------------------------------------------//
	// Nombre   : anima(...)                                                   //
	// Entradas : El contexto gráico, las coordenadas de inicio y final del de //
	//            plazamiento de la imagen                                     //  
	// Salidas  : No tiene                                                     //
	// Descripc : Filtrar o seleccionar los movimientos (derecha o izquierda)  //  
	//-------------------------------------------------------------------------//
	private void anima(Graphics g, int xInicial, int xFinal)
	{
		int xI;
		int xF;
		
		if (xInicial <= xFinal)
			moverDer(g, xInicial, xFinal);
		else
      moverIzq(g, xInicial, xFinal);
	}		
	
	//-------------------------------------------------------------------------//
	// Nombre   : moverDer(...)                                                //
	// Entradas : El contexto gráico, las coordenadas de inicio y final del de //
	//            plazamiento de la imagen                                     //  
	// Salidas  : No tiene                                                     //
	// Descripc : Mover la imagen de izquierda a derecha                       //
	//            ---> --> --> --> --> --> --> --> --> -->                     //  
	//-------------------------------------------------------------------------//
	private void moverDer(Graphics g, int xInicial, int xFinal)
	{
		for (int i = xInicial; i <= xFinal; i++)
		{
			pintaCuadro(g, i-17, 0-17, 50, 50, Color.white);
			pintaBrujul(g, Color.black);
			pintaImagen(g, i, 0-17);
			pausa(15);
		}
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : moverIzq(...)                                                //
	// Entradas : El contexto gráico, las coordenadas de inicio y final del de //
	//            plazamiento de la imagen                                     //  
	// Salidas  : No tiene                                                     //
	// Descripc : Mover la imagen de derecha a izquierda                       //
	//            <--- <-- <-- <-- <-- <-- <-- <-- <-- <--                     //  
	//-------------------------------------------------------------------------//
	private void moverIzq(Graphics g, int xInicial, int xFinal)
	{
		for (int i = xInicial; i >= xFinal-17; i--)
		{
			pintaCuadro(g, i-17, 0-17, 50, 50, Color.white);
			pintaBrujul(g, Color.black);
			pintaImagen(g, i, 0-17);
			pausa(15);
		}	
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
	// Nombre   : pintaBrujul(...)                                             //
	// Entradas : El contexto gráico y el color del cuadro.                    //
	// Salidas  : No tiene.                                                    //
	// Descripc : Pinta las coordenadas (los ejes) de referencia.              //  
	//-------------------------------------------------------------------------//
	private void pintaBrujul(Graphics g, Color color)
	{
		g.setColor(color);
		g.drawLine(-220, 0, 220,  0);
		g.drawLine(0,  -80,   0, 80);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : pintaCoorde(...)                                             //
	// Entradas : El contexto gráico y el color del cuadro.                    //
	// Salidas  : No tiene.                                                    //
	// Descripc : Pinta los títulos de los ejes coordenadas de referencia.     //  
	//-------------------------------------------------------------------------//
	private void pintaCoorde(Graphics g, Color color)
	{
		g.setColor(color);
		g.drawString("N",     5, -70);
		g.drawString("S"  ,   5,  80);
		g.drawString("O",  -220,  15);
		g.drawString("E" ,  210,  15);
	}	
	
	//-------------------------------------------------------------------------//
	// Nombre   : pintaImagen(...)                                             //
	// Entradas : El contexto gráfico y las coordenadas de despligue de la     //
	//            imagen.                                                      //
	// Salidas  : No tiene.                                                    //
	// Descripc : Pinta una imagen en cierta posición.                         //  
	//-------------------------------------------------------------------------//
	private void pintaImagen(Graphics g, int x, int y)
	{
		g.drawImage(imagen, x, y, this);
	}	
	
	//-------------------------------------------------------------------------//
	// Nombre   : pintaTrayec(...)                                             //
	// Entradas : El contexto gráfico, el valor real de la distancia, la unidad//
  //            real de la distancia, las coordenadas de incio y fin, el     //
	//            ancho de la imagen, el color y si es para la derecha o       //
	//            izquierda.                                                   //
	// Salidas  : No tiene.                                                    //
	// Descripc : Pinta una línea que simule el trayecto efectuado.            //  
	//-------------------------------------------------------------------------//
	private void pintaTrayec(Graphics g, int x, int y, int des, float dis, String uni,  int anchoIm, Color color, boolean derecha)
	{
		g.setColor(color);
		if (derecha)
		{
			g.drawLine(x, y, des+anchoIm, y);
			g.drawString(""+dis+" "+uni+"", 2*des/3, y-13);
		}
		else
		{
			g.drawLine(x, y, des-anchoIm, y);
			g.drawString(""+dis+" "+uni+"", 2*des/3, y-13);
		}	
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
}


