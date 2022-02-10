package com.psp.tema5.ejercicioHTTP1;

public class ItemToDo
{
	// Atributos
	private String nombre;
	private int numero;
	private boolean completado;
	
	// Getter y Setters
	public String getNombre()
	{
		return nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	public int getNumero()
	{
		return numero;
	}
	public void setNumero(int numero)
	{
		this.numero = numero;
	}
	public boolean isCompletado()
	{
		return completado;
	}
	public void setCompletado(boolean completado)
	{
		this.completado = completado;
	}
}
