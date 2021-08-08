//  
//  Estadisticas.java
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
//

import java.awt.*;
import java.awt.event.*;
import java.applet.*;

//
// Clase que deriva de la clase Applet y que implementa a la clase
// ActionListener. Esta clase tien por funcionalidad calcular la
// media aritmética, la varianza y la desviación estándart de un 
// conjunto de datos ingresados por el usuario.
//
public class Estadisticas extends Applet implements ActionListener
{
	// Ancho del applet.
	private final int ancho = 520;
	
	// Alto del applet.
	private final int alto  = 400;
	
	// Color de fondo del Applet.
	private final Color fondo = new Color(255, 204, 0);
		
	// Componentes AWT que permiten la interacción con el usuario.
	private Label lbDat, lbMed, lbRes, lbMedAri, lbVarian, lbDesTip;
	private TextField tfMed, tfMedAri, tfVarian, tfDesTip;
	private Button btAgre, btCalc, btLimp;
  private TextArea taLis;
	
	// Variable objeto Arreglo que sirve para almacenar los datos ingresados.
	private Arreglo X = new Arreglo(100);
	
	// Estas variables sirven par realizar los cálculos necesarios.
	private double media, varianza, desviacion;
	private int n = 0;
	
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
		lbDat = new Label("Ingrese los datos");
		lbMed = new Label("Medida:");
		lbRes = new Label("Resultados");
		lbMedAri = new Label("Media Aritmética   :");
		lbVarian = new Label("Varianza                 :");
		lbDesTip = new Label("Desviación Típica:");
		
		tfMed = new TextField();
		tfMedAri = new TextField();
		tfVarian = new TextField();
		tfDesTip = new TextField();
		
		btAgre = new Button("Agregar >>");
		btCalc = new Button("Calcular");
		btLimp = new Button("Limpiar");
		
		taLis = new TextArea();
		
		lbDat.setBounds (10,    0, 100, 20);
		lbMed.setBounds (35,   45,  50, 20);
		lbRes.setBounds (10,  250,  70, 20); 
		lbMedAri.setBounds (35,  285,  120, 20); 
		lbVarian.setBounds (35,  320,  120, 20); 
		lbDesTip.setBounds (35,  355,  120, 20); 
		
		tfMed.setBounds (100,  40, 100, 25);
		tfMedAri.setBounds (160,  280, 310, 25);
		tfVarian.setBounds (160,  315, 310, 25);
		tfDesTip.setBounds (160,  350, 310, 25);
		
		btAgre.setBounds(100,  75, 100, 25);
		btCalc.setBounds(100, 110, 100, 25);
		btLimp.setBounds(100, 145, 100, 25);
		
		taLis.setBounds (220, 40, 250, 170);
		
		tfMedAri.setEditable(false);
		tfVarian.setEditable(false);
		tfDesTip.setEditable(false);
		
		taLis.setEditable(false);
		
		btAgre.addActionListener(this);
		btCalc.addActionListener(this);
		btLimp.addActionListener(this);
		
		add(lbDat);
		add(lbMed);
		add(lbRes);
		add(lbMedAri);
		add(lbVarian);
		add(lbDesTip);
		add(tfMed);
		add(tfMedAri);
		add(tfVarian);
		add(tfDesTip);
		add(btAgre);
		add(btCalc);
		add(btLimp);
		add(taLis);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : paint(...)                                                   //
	// Entradas : El contexto gráfico de la aplicación                         //
	// Salidas  : No tiene                                                     //
	// Descripc : Pinta por primera vez el Applet.                             // 
  //-------------------------------------------------------------------------//
	public void paint(Graphics g)
	{
		pintaGrup(g, 5,  10, ancho-10, 230);
		pintaGrup(g, 5, 260, ancho-10, 130);
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
	// Nombre   : actionPerformed(...)                                         //
	// Entradas : El evento sobre un botón                                     //
	// Salidas  : No tiene                                                     //
	// Descripc : Implmentar el método que proporciona la acción de un botón   //
	//            pulsado por el usuario                                       // 
  //-------------------------------------------------------------------------// 
	public void actionPerformed(ActionEvent e)
	{
		Object objeto = e.getSource();
		double xi;
				
		if (objeto == btAgre)
		{
			if (verifica())
			{
				xi = new Double(tfMed.getText()).doubleValue(); 
				X.inserta(xi);
				tfMed.setText("");
				taLis.append(""+xi+"\n");
				n++;
			}	
		}	
		
		if (objeto == btCalc)
		{
			if (n > 0)
			{
				calculaMediaAritmetica();
				calculaVarianza();
				calculaDesviacionTipica();
			}
			else 
			{
				Message mensaje = new Message(200, 200, 300, 100, "Debe ingresar por lo menos un dato !!");
				mensaje.show();
			}	
		}
		
		if (objeto == btLimp)
		{
			X.setDatos();
			n = 0;
			tfMed.setText("");
			tfMedAri.setText("");
			tfVarian.setText("");
			tfDesTip.setText("");
			for (int i=0; i<100; i++)
				taLis.setText("");
		}	
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : verifica()                                                   //
	// Entradas : No tiene                                                     //
	// Salidas  : True si los datos son correctos. False en caso contrario     //
	// Descripc : Comprobar que fueron ingresados los datos.                   // 
  //-------------------------------------------------------------------------//
	private boolean verifica()
	{
		if (tfMed.getText().equals(""))
		{
			Message mensaje = new Message(200, 200, 250, 100, "Ingrese la medida !!");
			mensaje.show();
			return (false);
		}
		
		return (true);
	}
	
	//-------------------------------------------------------------------------//
	// Nombre   : calculaMediaAritmetica()                                     //
	// Entradas : No tiene                                                     //
	// Salidas  : No tiene                                                     //
	// Descripc : Calcular la media aritmética del conjunto de datos ingresados//
  //-------------------------------------------------------------------------//
	private void calculaMediaAritmetica()
	{
		double sumatoriaX = 0;
		
		for (int i=0; i<n; i++)
			sumatoriaX+=X.obtiene(i);
		
		media = sumatoriaX/(float) n;
		
		tfMedAri.setText(""+media+"");
	}	
	
	//-------------------------------------------------------------------------//
	// Nombre   : calculaVarianza()                                            //
	// Entradas : No tiene                                                     //
	// Salidas  : No tiene                                                     //
	// Descripc : Calcular la varianza del conjunto de datos ingresados        //
  //-------------------------------------------------------------------------//
	private void calculaVarianza()
	{
		double sumatoria = 0;
		
		for (int i=0; i<n; i++)
			sumatoria+=Math.pow((X.obtiene(i)-media), 2.0);
		
		varianza = sumatoria/(float) n;
		
		tfVarian.setText(""+varianza+"");
	}	
	
	//-------------------------------------------------------------------------//
	// Nombre   : calculaDesviacionTipica()                                    //
	// Entradas : No tiene                                                     //
	// Salidas  : No tiene                                                     //
	// Descripc : Calcular la desviación típica del conjunto de datos          //  
	//            ingresados                                                   //
  //-------------------------------------------------------------------------//
	private void calculaDesviacionTipica()
	{
		desviacion = Math.pow(varianza, 0.5);
		
		tfDesTip.setText(""+desviacion+"");
	}	
}	
	
