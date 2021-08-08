//  
//  TransTiemp.java
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
//  Creación  : 9-Julio-2001
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
//  usuario Transformar unidades de tiempo a distintas otras unidades.
//
public class TransTiemp extends Applet
{
	// Anchura del applet.
	final int ancho = 300;
	
	// Altura del applet.
	final int alto  = 200;
	
	// Color de fondo del applet.
	public final Color fondo = new Color(255, 204, 0);
	
	// Componentes AWT:
	// Campos de Textos para el ingreso de datos y visualización de la 
	// resultados.
	public TextField tfTiempo, tfResult;
	
	// Lista de opciones para que el usuario pueda elegir las unidades
	// del tiempo y las unidades en las cuales se desea transformar.
	public Choice chUniOrg, chUniDst;
	
	// Boton que tiene la acción de llevar a cabo la Transformación de
	// la unidad de tiempo ingresada.
	public Button btConver;
	
	// Unidades disponibles en la lista de opciones.
	String opcion[] = {"s", "min", "hr", "día", "año"};
	
	// Variable que servirán para realizar la transformación de unidades.
	double tiempo;
	
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
	// Función: Permite inicializar los componentes AWT (textfield, listas y botones),
	//          posicionarlos y aderirlos al Applet.
	//
	private void setComponentes()
	{
		tfTiempo = new TextField("");
		tfResult = new TextField("");
		tfResult.setEditable(false);
		
		chUniOrg = new Choice();
		chUniDst = new Choice();
		
		btConver = new Button("Transformar");
		
		for (int i=0; i<opcion.length; i++)
		{
			chUniOrg.addItem(opcion[i]);
			chUniDst.addItem(opcion[i]);
		}	
			
		setLayout(null);
		
		tfTiempo.setBounds(80,  20,  100, 20);
		chUniOrg.setBounds(190, 20,  100, 20);
		chUniDst.setBounds(80,  45,  100, 20);
		btConver.setBounds(95, 100, 100, 20);
		tfResult.setBounds(20, 170,  260, 20);
		
		add(tfTiempo);
		add(chUniOrg);
		add(chUniDst);
		add(btConver);
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
		g.drawString("Tiempo:", 5, 35);
		g.drawString("Transformar a:", 5, 63);
		g.drawString("Resultado", 120, 165);
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
	// Función: Realiza el cálculo de la transformación. Además verifica que los datos fueron 
	//          ingresados correctamente.
	//
	private void calcula()
	{
		if (verifica())
		{
			tfResult.setText(""+convTiemp(tiempo)+" "+chUniDst.getSelectedItem()+"");
		}
	}
	
	//
	// Nombre : verifica()
	// Entrada: No tiene.
	// Salida : Retorna verdadero (1) si los datos fueron ingresados correcta
	//          mente. En caso contrario retorna falso (0).
	// Función: Comprobar que los datos fueron ingresados correctamente. Es
	//          decir, los cuadros de textos deben ser ingresados con datos y
	//          la variable Tiempo deben ser positivas. 
	//
	private boolean verifica()
	{
		if (tfTiempo.getText().equalsIgnoreCase(""))
    {
		  tfResult.setText("Ingrese el Tiempo");		 
			return (false);
		}
		
		tiempo    = new Double(tfTiempo.getText()).doubleValue();
		
		if (tiempo < 0)
		{
		  tfResult.setText("El Tiempo debe ser positivo");
			return (false);	
		}
		
		return (true);
	}
	
	//
	// Nombre : convTiemp(...)
	// Entrada: El tiempo en unidades origrinales.
	// Salida : El tiempo en unidades que se desea.
	// Función: Realizar un filtro con respecto a las unidades originales.
	//
	public double convTiemp(double tiempOrg)
	{
	 	String unidOrg  = chUniOrg.getSelectedItem();
		String unidDst  = chUniDst.getSelectedItem();
		double tiempDst = 0;
		int opcion      = 0;
		
		if (unidOrg.equals("s"))   opcion = 1;
		if (unidOrg.equals("min")) opcion = 2;
		if (unidOrg.equals("hr"))  opcion = 3;
		if (unidOrg.equals("día")) opcion = 4;
		if (unidOrg.equals("año")) opcion = 5;
		
		switch (opcion)
		{
			case 1:
			{
				if (unidDst.equals("s"))   tiempDst = tiempOrg;
				if (unidDst.equals("min")) tiempDst = tiempOrg/60;
				if (unidDst.equals("hr"))  tiempDst = tiempOrg/3600;
				if (unidDst.equals("día")) tiempDst = tiempOrg/86400;
				if (unidDst.equals("año")) tiempDst = tiempOrg/31536000;
				break;
			}	
			case 2:
			{
				if (unidDst.equals("s"))   tiempDst = tiempOrg*60;
				if (unidDst.equals("min")) tiempDst = tiempOrg;
				if (unidDst.equals("hr"))  tiempDst = tiempOrg/60;
				if (unidDst.equals("día")) tiempDst = tiempOrg/1440;
				if (unidDst.equals("año")) tiempDst = tiempOrg/525600;
				break;
			}	
			case 3:
			{
				if (unidDst.equals("s"))   tiempDst = tiempOrg*3600;
				if (unidDst.equals("min")) tiempDst = tiempOrg*60;
				if (unidDst.equals("hr"))  tiempDst = tiempOrg;
				if (unidDst.equals("día")) tiempDst = tiempOrg/24;
				if (unidDst.equals("año")) tiempDst = tiempOrg/8760;
				break;
			}
			case 4:
			{
				if (unidDst.equals("s"))   tiempDst = tiempOrg*86400;
				if (unidDst.equals("min")) tiempDst = tiempOrg*1440;
				if (unidDst.equals("hr"))  tiempDst = tiempOrg*24;
				if (unidDst.equals("día")) tiempDst = tiempOrg;
				if (unidDst.equals("año")) tiempDst = tiempOrg/365;
				break;
			}
			case 5:
			{
				if (unidDst.equals("s"))   tiempDst = tiempOrg*31536000;
				if (unidDst.equals("min")) tiempDst = tiempOrg*525600;
				if (unidDst.equals("hr"))  tiempDst = tiempOrg*8760;
				if (unidDst.equals("día")) tiempDst = tiempOrg*365;
				if (unidDst.equals("año")) tiempDst = tiempOrg;	
				break;
			}	
		}
		return (tiempDst);
  } 
}

