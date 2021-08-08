//  
//  Tiempo.java
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
//  Creaci�n  : 15-Julio-2001
// 
//  -------------------------------------------------------------------------
//  Esta informaci�n no es necesariamente definitiva y est� sujeta a cambios
//  que pueden ser incorporados en cualquier momento, sin avisar.
//  -------------------------------------------------------------------------


class Tiempo
{
	// Unidad Destino.
	final String unidDst = "hr";
	
	// Unidad Original.
	public String unidOrg;
	
	// Valor del tiempo de destino.
	public double tiempDst;
	
	// valor del tiempo original.
	public double tiempOrg;
	
	//
	// Nombre : Tiempo()
	// Entrada: La unidad Original del Tiempo y su valor.
	// Salida : No tiene.
	// Funci�n: Constructor de la clase que inicializa los valores.
	//	
	public Tiempo(String unidOrg, double tiempOrg)
	{
		this.unidOrg  = unidOrg;
		this.tiempDst = 0;
		this.tiempOrg = tiempOrg;
	}
	
	//
	// Nombre : convTiemp(...)
	// Entrada: No tiene
	// Salida : El tiempo transcurrido en unidades que se desea.
	// Funci�n: Realizar un filtro con respecto a las unidades originales.
	//
	public double convTiemp()
	{
	 	int opcion = 0;
		
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

