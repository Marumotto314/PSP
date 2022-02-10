package com.psp.tema5.clienteToDo;

import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;


public class Cliente
{
	// URL
	private static final String REST_URI = "http://localhost:8080/api/ejemplo-todo";
	// Cliente 
	private static WebClient cliente;
	// Scanner
	private static Scanner entrada;
	
	public static void main(String[] args)
	{
		entrada = new Scanner(System.in);
		cliente = WebClient.create(REST_URI, Collections.singletonList(new JacksonJsonProvider()));
		
		int opcion;
		do {
			menu();
			opcion = entrada.nextInt();
			
			if(opcion == 4)
				break;
			
			switch (opcion)
			{
				case 1:
					listarTareas();
					break;
				case 2:
					añadirTarea();
					break;
				case 3:
					
					break;
				default:
					System.out.println("Opción no válida");
					break;
			}

		} while(opcion != 4);
		System.out.println("Hasta Pronto :D");
		entrada.close();
	}
	private static void añadirTarea()
	{
		System.out.println("+++++ AÑADIR TAREA +++++");
		System.out.print("Nombre: ");
		String nombre = entrada.nextLine();
		
		ItemToDo nuevaTarea = new ItemToDo();
		nuevaTarea.setNombre(nombre);
		
		cliente.reset();
//		String respuesta = cliente
//				.path("nuevo-item")
//				.type(MediaType.APPLICATION_JSON_TYPE)
//				.accept(MediaType.APPLICATION_ATOM_XML)
//				.post(nuevaTarea, ItemToDo.class);
//		System.out.println(respuesta);
		
		listarTareas();
	}
	private static void listarTareas() 
	{
		cliente.reset();
		System.out.println("+++++ LISTA TAREAS +++++");
		Collection<? extends ItemToDo> tareas = cliente
				.path("lista-items")
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.getCollection(ItemToDo.class);
		for(ItemToDo tarea : tareas)
		{
			System.out.println( (tarea.isCompletado())?"Hecho":"Sin hacer" + tarea.getNumero() + ". " + tarea.getNombre());
		}
	}
	private static void menu()
	{
		System.out.println("1. Listar tareas");
		System.out.println("2. Nuevo Item");
		System.out.println("3. Marcar Item");
		System.out.println("4. Salir");
		System.out.print("Opción: ");
	}
}