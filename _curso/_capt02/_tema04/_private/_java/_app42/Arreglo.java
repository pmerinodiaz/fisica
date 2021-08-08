//  
//  Arreglo.java
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

//
// Clase que permite: Crear un arreglo, setearlo, incorporarle datos, 
// obtener datos y más. Los datos son numéricos (double).
//

class Arreglo
{
	// Datos miembros.
	private double[] dato;
	private int indice;
	
	// Contructor que setea el tamaño y el índice.
	public Arreglo(int tamanio)
	{
		dato = new double[tamanio];
		indice = 0;
		setDatos();
	}	
	
	// Setea los datos dejándolos en 0.
	public void setDatos()
	{
		for (int i=0; i<dato.length; i++)
			dato[i] = 0;
		indice = 0;	
	}
	
	// Inserta un elemento al final del arreglo.
	public void inserta(double x)
	{
		if (desborde() == false)
		{
			dato[indice] = x;
			indice++;
		}	
  }
	
	// Retorna un dato con índice i.
	public double obtiene(int i)
	{
		return (dato[i]);
	}	
	
	// Verifica si hay desborde en el arreglo.
	public boolean desborde()
	{
		if (indice >= dato.length)
			return (true);
		return (false);
	}	
}

