//  
//  TransTiemp.java
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
//  Creaci�n  : 9-Julio-2001
// 
//  -------------------------------------------------------------------------
//  Esta informaci�n no es necesariamente definitiva y est� sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//  -------------------------------------------------------------------------
import java.awt.*;
import java.applet.*;

//
//  Clase que deriva de la clase Applet y que tiene la funci�n de inicializar, 
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
	// Campos de Textos para el ingreso de datos y visualizaci�n de la 
	// resultados.
	public TextField tfTiempo, tfResult;
	
	// Lista de opciones para que el usuario pueda elegir las unidades
	// del tiempo y las unidades en las cuales se desea transformar.
	public Choice chUniOrg, chUniDst;
	
	// Boton que tiene la acci�n de llevar a cabo la Transformaci�n de
	// la unidad de tiempo ingresada.
	public Button btConver;
	
	// Unidades disponibles en la lista de opciones.
	String opcion[] = {"s", "min", "hr", "d�a", "a�o"};
	
	// Variable que servir�n para realizar la transformaci�n de unidades.
	double tiempo;
	
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
	// Funci�n: Permite inicializar los componentes AWT (textfield, listas y botones),
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
	// Entrada: El cantexto gr�fico g.
	// Salida : No tiene
	// Funci�n: Imprime en pantalla algunos mensajes para guiar al usuario
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
	// Funci�n: Realiza el c�lculo de la transformaci�n. Adem�s verifica que los datos fueron 
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
	// Funci�n: Comprobar que los datos fueron ingresados correctamente. Es
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
	// Funci�n: Realizar un filtro con respecto a las unidades originales.
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
		if (unidOrg.equals("d�a")) opcion = 4;
		if (unidOrg.equals("a�o")) opcion = 5;
		
		switch (opcion)
		{
			case 1:
			{
				if (unidDst.equals("s"))   tiempDst = tiempOrg;
				if (unidDst.equals("min")) tiempDst = tiempOrg/60;
				if (unidDst.equals("hr"))  tiempDst = tiempOrg/3600;
				if (unidDst.equals("d�a")) tiempDst = tiempOrg/86400;
				if (unidDst.equals("a�o")) tiempDst = tiempOrg/31536000;
				break;
			}	
			case 2:
			{
				if (unidDst.equals("s"))   tiempDst = tiempOrg*60;
				if (unidDst.equals("min")) tiempDst = tiempOrg;
				if (unidDst.equals("hr"))  tiempDst = tiempOrg/60;
				if (unidDst.equals("d�a")) tiempDst = tiempOrg/1440;
				if (unidDst.equals("a�o")) tiempDst = tiempOrg/525600;
				break;
			}	
			case 3:
			{
				if (unidDst.equals("s"))   tiempDst = tiempOrg*3600;
				if (unidDst.equals("min")) tiempDst = tiempOrg*60;
				if (unidDst.equals("hr"))  tiempDst = tiempOrg;
				if (unidDst.equals("d�a")) tiempDst = tiempOrg/24;
				if (unidDst.equals("a�o")) tiempDst = tiempOrg/8760;
				break;
			}
			case 4:
			{
				if (unidDst.equals("s"))   tiempDst = tiempOrg*86400;
				if (unidDst.equals("min")) tiempDst = tiempOrg*1440;
				if (unidDst.equals("hr"))  tiempDst = tiempOrg*24;
				if (unidDst.equals("d�a")) tiempDst = tiempOrg;
				if (unidDst.equals("a�o")) tiempDst = tiempOrg/365;
				break;
			}
			case 5:
			{
				if (unidDst.equals("s"))   tiempDst = tiempOrg*31536000;
				if (unidDst.equals("min")) tiempDst = tiempOrg*525600;
				if (unidDst.equals("hr"))  tiempDst = tiempOrg*8760;
				if (unidDst.equals("d�a")) tiempDst = tiempOrg*365;
				if (unidDst.equals("a�o")) tiempDst = tiempOrg;	
				break;
			}	
		}
		return (tiempDst);
  } 
}

