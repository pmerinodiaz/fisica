//  
//  TransLong.java
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
//  Creación  : 8-Julio-2001
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
//  usuario Transformar mediciones a distintas unidades.
//
public class TransLong extends Applet
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
	public TextField tfMedida, tfResult;
	
	// Lista de opciones para que el usuario pueda elegir las unidades
	// de la medición tomada y las unidades en las cuales se desea 
	// transformar.
	public Choice chUniOrg, chUniDst;
	
	// Boton que tiene la acción de llevar a cabo la Transformación de
	// la medida tomada.
	public Button btConver;
	
	// Unidades disponibles en la lista de opciones.
	String opcion[] = {"pulg", "cm", "pie", "m", "mi", "km", "milla náutica", "fermi", "angstrom", "año luz", "parsec"};
	
	// Variable que servirán para realizar la Transformación
	// de unidades.
	private double distancia;
	
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
		tfMedida = new TextField("");
		tfResult = new TextField("");
		tfResult.setEditable(false);
		
		chUniOrg = new Choice();
		chUniDst = new Choice();
		
		btConver = new Button("Convertir");
		
		for (int i=0; i<opcion.length; i++)
		{
			chUniOrg.addItem(opcion[i]);
			chUniDst.addItem(opcion[i]);
		}	
			
		setLayout(null);
		
		tfMedida.setBounds(80,  20,  100, 20);
		chUniOrg.setBounds(190, 20,  100, 20);
		chUniDst.setBounds(80,  45,  100, 20);
		btConver.setBounds(95, 100, 100, 20);
		tfResult.setBounds(20, 170,  260, 20);
		
		add(tfMedida);
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
		g.drawString("Medida:", 10, 35);
		g.drawString("Convertir a:", 10, 63);
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
	// Función: Realiza transformación de unidades. Además verifica que los datos 
	//          fueron ingresados correctamente.
	//
	private void calcula()
	{
		if (verifica())
		{
			tfResult.setText(""+convDist(distancia)+" "+chUniDst.getSelectedItem()+"");
		}
	}
	
	//
	// Nombre : verifica()
	// Entrada: No tiene.
	// Salida : Retorna verdadero (1) si los datos fueron ingresados correcta
	//          mente. En caso contrario retorna falso (0).
	// Función: Comprobar que los datos fueron ingresados correctamente. Es
	//          decir, los cuadros de textos deben ser ingresados con datos y
	//          la variable Distancia deben ser positivas. 
	//
	private boolean verifica()
	{
		if (tfMedida.getText().equalsIgnoreCase(""))
    {
		  tfResult.setText("Ingrese la Distancia");		 
			return (false);
		}
		
		distancia = new Double(tfMedida.getText()).doubleValue();
				
		if (distancia < 0)
		{
		  tfResult.setText("La Distancia debe ser positiva");
			return (false);	
		}
		
		return (true);
	}
	
	//
	// Nombre : convDist(...)
	// Entrada: La distancia en unidades origrinales.
	// Salida : La distancia en unidades que se desea.
	// Función: Realizar un filtro con respecto a las unidades originales.
	//
	public double convDist(double distOrg)
	{
	 	String unidOrg = chUniOrg.getSelectedItem();
		String unidDst = chUniDst.getSelectedItem();
		double distDst = 0;
		int opcion     = 0;	
			
		if (unidOrg.equals("pulg"))          opcion = 1;
		if (unidOrg.equals("cm"))            opcion = 2;
		if (unidOrg.equals("pie"))           opcion = 3;
		if (unidOrg.equals("m"))             opcion = 4;
		if (unidOrg.equals("mi"))            opcion = 5;
		if (unidOrg.equals("km"))            opcion = 6;
		if (unidOrg.equals("milla náutica")) opcion = 7;
		if (unidOrg.equals("fermi"))         opcion = 8;
		if (unidOrg.equals("angstrom"))      opcion = 9;
		if (unidOrg.equals("año luz"))       opcion = 10;
		if (unidOrg.equals("parsec"))        opcion = 11;
		
		switch (opcion)
		{
			case 1: 
			{
				if (unidDst.equals("pulg"))          distDst = distOrg;
				if (unidDst.equals("cm"))            distDst = distOrg*2.54;
				if (unidDst.equals("pie"))           distDst = distOrg*0.083312;
				if (unidDst.equals("m"))             distDst = distOrg*0.0254;
				if (unidDst.equals("mi"))            distDst = distOrg*0.000015776;
				if (unidDst.equals("km"))            distDst = distOrg*0.0000254;
				if (unidDst.equals("milla náutica")) distDst = distOrg*0.000013711;
				if (unidDst.equals("fermi"))         distDst = distOrg*2.54*Math.pow(10, 13);
				if (unidDst.equals("angstrom"))      distDst = distOrg*254000000;
				if (unidDst.equals("año luz"))       distDst = distOrg*2.684989429*Math.pow(10, -18);
				if (unidDst.equals("parsec"))        distDst = distOrg*8.236163895*Math.pow(10, -19);
				break;
			}	
			case 2:
			{
				if (unidDst.equals("pulg"))          distDst = distOrg*0.394;
				if (unidDst.equals("cm"))            distDst = distOrg;
				if (unidDst.equals("pie"))           distDst = distOrg*0.032786885;
				if (unidDst.equals("m"))             distDst = distOrg*0.01;
				if (unidDst.equals("mi"))            distDst = distOrg*0.000006209;
				if (unidDst.equals("km"))            distDst = distOrg*0.00001;
				if (unidDst.equals("milla náutica")) distDst = distOrg*0.000005396;
				if (unidDst.equals("fermi"))         distDst = distOrg*Math.pow(10, 13);
				if (unidDst.equals("angstrom"))      distDst = distOrg*Math.pow(10, 18);
				if (unidDst.equals("año luz"))       distDst = distOrg*1.057082452*Math.pow(10, -18);
				if (unidDst.equals("parsec"))        distDst = distOrg*3.24258421*Math.pow(10, -19);
				break;
			}
			case 3:
			{
				if (unidDst.equals("pulg"))          distDst = distOrg*12.00307279;
				if (unidDst.equals("cm"))            distDst = distOrg*30.5;
				if (unidDst.equals("pie"))           distDst = distOrg*0.032786885;
				if (unidDst.equals("m"))             distDst = distOrg*0.304878048;
				if (unidDst.equals("mi"))            distDst = distOrg*0.000189393;
				if (unidDst.equals("km"))            distDst = distOrg*0.000304878;
				if (unidDst.equals("milla náutica")) distDst = distOrg*0.000164581;
				if (unidDst.equals("fermi"))         distDst = distOrg*3.048780488*Math.pow(10, 14);
				if (unidDst.equals("angstrom"))      distDst = distOrg*3048780488.0;
				if (unidDst.equals("año luz"))       distDst = distOrg*3.222812355*Math.pow(10, -17);
				if (unidDst.equals("parsec"))        distDst = distOrg*9.885927469*Math.pow(10, -18);
				break;
			}
			case 4:
			{
				if (unidDst.equals("pulg"))          distDst = distOrg*39.37007874;
				if (unidDst.equals("cm"))            distDst = distOrg*100;
				if (unidDst.equals("pie"))           distDst = distOrg*3.28;
				if (unidDst.equals("m"))             distDst = distOrg;
				if (unidDst.equals("mi"))            distDst = distOrg*0.000621212;
				if (unidDst.equals("km"))            distDst = distOrg*0.001;
				if (unidDst.equals("milla náutica")) distDst = distOrg*0.868997068;
				if (unidDst.equals("fermi"))         distDst = distOrg*Math.pow(10, 15);
				if (unidDst.equals("angstrom"))      distDst = distOrg*1.609756412*Math.pow(10, 13);
				if (unidDst.equals("año luz"))       distDst = distOrg*1.057082452*Math.pow(10, -16);
				if (unidDst.equals("parsec"))        distDst = distOrg*3.236245955*Math.pow(10, -17);
				break;
			}
			case 5:
			{
				if (unidDst.equals("pulg"))          distDst = distOrg*63385.82681;
				if (unidDst.equals("cm"))            distDst = distOrg*161040;
				if (unidDst.equals("pie"))           distDst = distOrg*5280;
				if (unidDst.equals("m"))             distDst = distOrg*1609.756093;
				if (unidDst.equals("mi"))            distDst = distOrg;
				if (unidDst.equals("km"))            distDst = distOrg*1.61;
				if (unidDst.equals("milla náutica")) distDst = distOrg*0.869565217;
				if (unidDst.equals("fermi"))         distDst = distOrg*1.609756412*Math.pow(10, 18);
				if (unidDst.equals("angstrom"))      distDst = distOrg*Math.pow(10, 10);
				if (unidDst.equals("año luz"))       distDst = distOrg*1.701645255*Math.pow(10, -13);
				if (unidDst.equals("parsec"))        distDst = distOrg*5.219770722*Math.pow(10, -14);
				break;
			}
			case 6:
			{
				if (unidDst.equals("pulg"))          distDst = distOrg*39370.07874;
				if (unidDst.equals("cm"))            distDst = distOrg*100000;
				if (unidDst.equals("pie"))           distDst = distOrg*3280;
				if (unidDst.equals("m"))             distDst = distOrg*1000;
				if (unidDst.equals("mi"))            distDst = distOrg*0.621118012;
				if (unidDst.equals("km"))            distDst = distOrg;
				if (unidDst.equals("milla náutica")) distDst = distOrg*0.539956803;
				if (unidDst.equals("fermi"))         distDst = distOrg*Math.pow(10, 18);
				if (unidDst.equals("angstrom"))      distDst = distOrg*Math.pow(10, 13);
				if (unidDst.equals("año luz"))       distDst = distOrg*1.057082452*Math.pow(10, -13);
				if (unidDst.equals("parsec"))        distDst = distOrg*5.219770722*Math.pow(10, -14);
				break;
			}
			case 7:
			{
				if (unidDst.equals("pulg"))          distDst = distOrg*72930.67027;
				if (unidDst.equals("cm"))            distDst = distOrg*185318;
				if (unidDst.equals("pie"))           distDst = distOrg*6076;
				if (unidDst.equals("m"))             distDst = distOrg*1.150751868;
				if (unidDst.equals("mi"))            distDst = distOrg*1.15;
				if (unidDst.equals("km"))            distDst = distOrg*1.852;
				if (unidDst.equals("milla náutica")) distDst = distOrg;
				if (unidDst.equals("fermi"))         distDst = distOrg*1.150751869*Math.pow(10, 15);
				if (unidDst.equals("angstrom"))      distDst = distOrg*1.150751869*Math.pow(10, 10);
				if (unidDst.equals("año luz"))       distDst = distOrg*1.216439608*Math.pow(10, -16);
				if (unidDst.equals("parsec"))        distDst = distOrg*3.73140984*Math.pow(10, -17);
				break;
			}	
			case 8:
			{
				if (unidDst.equals("pulg"))          distDst = distOrg*3.937007874*Math.pow(10, -14);
				if (unidDst.equals("cm"))            distDst = distOrg*Math.pow(10, -13);
				if (unidDst.equals("pie"))           distDst = distOrg*3.28*Math.pow(10, -15);
				if (unidDst.equals("m"))             distDst = distOrg*Math.pow(10, -15);
				if (unidDst.equals("mi"))            distDst = distOrg*6.21212*Math.pow(10, -19);
				if (unidDst.equals("km"))            distDst = distOrg*Math.pow(10, -18);
				if (unidDst.equals("milla náutica")) distDst = distOrg*8.68997068*Math.pow(10, -16);
				if (unidDst.equals("fermi"))         distDst = distOrg;
				if (unidDst.equals("angstrom"))      distDst = distOrg*0.01;
				if (unidDst.equals("año luz"))       distDst = distOrg*1.057082452*Math.pow(10, -31);
				if (unidDst.equals("parsec"))        distDst = distOrg*3.24258421*Math.pow(10, -32);
		    break;
			}
			case 9:
			{
				if (unidDst.equals("pulg"))          distDst = distOrg*0.000000003;
				if (unidDst.equals("cm"))            distDst = distOrg*Math.pow(10, -18);
				if (unidDst.equals("pie"))           distDst = distOrg*3.28*Math.pow(10, -10);
				if (unidDst.equals("m"))             distDst = distOrg*Math.pow(10, -10);
				if (unidDst.equals("mi"))            distDst = distOrg*6.21212*Math.pow(10, -14);
				if (unidDst.equals("km"))            distDst = distOrg*Math.pow(10, -13);
				if (unidDst.equals("milla náutica")) distDst = distOrg*8.68997068*Math.pow(10, -11);
				if (unidDst.equals("fermi"))         distDst = distOrg*100;
				if (unidDst.equals("angstrom"))      distDst = distOrg;
				if (unidDst.equals("año luz"))       distDst = distOrg*6.566723043*Math.pow(10, -27);
				if (unidDst.equals("parsec"))        distDst = distOrg*2.014332221*Math.pow(10, -27);
				break;
			}	
			case 10:
			{
				if (unidDst.equals("pulg"))          distDst = distOrg*3.724409449*Math.pow(10, 17);
				if (unidDst.equals("cm"))            distDst = distOrg*9.46*Math.pow(10, 17);
				if (unidDst.equals("pie"))           distDst = distOrg*3.10288*Math.pow(10, 16);
				if (unidDst.equals("m"))             distDst = distOrg*9.46*Math.pow(10, 15);
				if (unidDst.equals("mi"))            distDst = distOrg*5.87666552*Math.pow(10, 12);
				if (unidDst.equals("km"))            distDst = distOrg*9.46*Math.pow(10, 12);
				if (unidDst.equals("milla náutica")) distDst = distOrg*8.220712263*Math.pow(10, 15);
				if (unidDst.equals("fermi"))         distDst = distOrg*9.46*Math.pow(10, 30);
				if (unidDst.equals("angstrom"))      distDst = distOrg*1.522829566*Math.pow(10, 26);
				if (unidDst.equals("año luz"))       distDst = distOrg;
				if (unidDst.equals("parsec"))        distDst = distOrg*0.306748466;
				break;
			}	
			case 11:
			{
				if (unidDst.equals("pulg"))          distDst = distOrg*1.21415748*Math.pow(10, 18);
				if (unidDst.equals("cm"))            distDst = distOrg*3.08396*Math.pow(10, 18);
				if (unidDst.equals("pie"))           distDst = distOrg*1.01153888*Math.pow(10, 17);
				if (unidDst.equals("m"))             distDst = distOrg*3.09*Math.pow(10, 16);
				if (unidDst.equals("mi"))            distDst = distOrg*1.91579296*Math.pow(10, 13);
				if (unidDst.equals("km"))            distDst = distOrg*3.08396*Math.pow(10, 13);
				if (unidDst.equals("milla náutica")) distDst = distOrg*2.679952198*Math.pow(10, 16);
				if (unidDst.equals("fermi"))         distDst = distOrg*3.08396*Math.pow(10, 31);
				if (unidDst.equals("angstrom"))      distDst = distOrg*4.964424385*Math.pow(10, 26);
				if (unidDst.equals("año luz"))       distDst = distOrg;
				if (unidDst.equals("parsec"))        distDst = distOrg;
				break;
			}
	 }  
	 return (distDst);
	}	
}

