class Arreglo
{
	private double[] dato;
	private int indice;
	
	public Arreglo(int tamanio)
	{
		dato = new double[tamanio];
		indice = 0;
		setDatos();
	}	
	
	public void setDatos()
	{
		for (int i=0; i<dato.length; i++)
			dato[i] = 0;
		indice = 0;	
	}
	
	public void inserta(double x)
	{
		if (desborde() == false)
		{
			dato[indice] = x;
			indice++;
		}	
  }
	
	public double obtiene(int i)
	{
		return (dato[i]);
	}	
	
	public boolean desborde()
	{
		if (indice >= dato.length)
			return (true);
		return (false);
	}	
}

