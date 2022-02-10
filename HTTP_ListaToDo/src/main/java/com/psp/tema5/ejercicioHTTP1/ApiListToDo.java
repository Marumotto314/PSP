package com.psp.tema5.ejercicioHTTP1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/ejemplo-todo")
public class ApiListToDo
{
	// Atributos
	private Collection<ItemToDo> tareas = Collections.synchronizedCollection(new ArrayList<>());
	@GetMapping("lista-items")
	public Collection<ItemToDo> listaTareas(@RequestParam(value = "soloCompletadas", defaultValue = "false") boolean soloCompletadas)
	{
		if(!soloCompletadas)
			return tareas;
		
		Collection<ItemToDo> tareasCompletadas = Collections.synchronizedCollection(new ArrayList<>());;
		for(ItemToDo tarea : tareas)
			if(tarea.isCompletado())
				tareasCompletadas.add(tarea);
		
		return tareasCompletadas;
	}
	
	@PostMapping("nuevo-item")
	public String nuevoItem(@RequestBody ItemToDo nuevaTarea)
	{
		nuevaTarea.setNumero(tareas.size()+1);
		tareas.add(nuevaTarea);
		return "OK";
	}
	
	/**
	 * Marcará una tarea como realizada
	 * @param numero de la tarea
	 * @return Devuelve el elemento actualizado o nullo en caso de que no exista
	 */
	@PatchMapping("marcar-item")
	public ItemToDo marcarItem(@RequestBody ItemToDo tareaRecibida)
	{
		int numero = tareaRecibida.getNumero();
		for(ItemToDo tarea : tareas)
		{			
			if(tarea.getNumero() == numero)
			{
				tarea.setCompletado(true);
				return tarea;
			}
		}
		
		return null;
	}
//	public ItemToDo marcarItem(@RequestParam int numero)
//	{
//		for(ItemToDo tarea : tareas)
//		{			
//			if(tarea.getNumero() == numero)
//			{
//				tarea.setCompletado(true);
//				return tarea;
//			}
//		}
//		return null;
//	}
}
