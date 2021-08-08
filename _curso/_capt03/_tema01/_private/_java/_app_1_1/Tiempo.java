//  
//  Tiempo.java
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
//  Creación  : 15-Julio-2001
// 
//  -------------------------------------------------------------------------
//  Esta información no es necesariamente definitiva y está  sujeta a cambios
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
	// Función: Constructor de la clase que inicializa los valores.
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
	// Función: Realizar un filtro con respecto a las unidades originales.
	//
	public double convTiemp()
	{
	 	int opcion = 0;
		
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

