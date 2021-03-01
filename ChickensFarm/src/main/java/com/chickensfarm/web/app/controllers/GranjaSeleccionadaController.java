package com.chickensfarm.web.app.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.chickensfarm.web.app.models.Chicken;
import com.chickensfarm.web.app.models.Egg;
import com.chickensfarm.web.app.models.Farm;
import com.chickensfarm.web.app.models.services.ChickenServicio;
import com.chickensfarm.web.app.models.services.EggServicio;
import com.chickensfarm.web.app.models.services.FarmServicio;

@Controller
public class GranjaSeleccionadaController {

	private FarmServicio farmServicio = new FarmServicio();
	private EggServicio eggServicio = new EggServicio();
	private ChickenServicio chickenServicio = new ChickenServicio();

	// Mostrar vista comprar Huevos y le pasamos los atributos
	@RequestMapping(value = "/comprarHuevos/{idGranja}", method = RequestMethod.GET)
	public ModelAndView comprarHuevos(@PathVariable("idGranja") Long idGranja, ModelAndView mv) {

		Farm granja = farmServicio.buscarPorIdGranja(idGranja);

		// pasamos datos a la vista
		mv.addObject("titulo", "Comprar Huevos");
		mv.addObject("tituloh1", "Granja: " + granja.getNombreGranja());
		mv.addObject("fechaGranja", granja.getFechaGranja());
		mv.addObject("granja", granja);

		// seteamos el nombre de la vista
		mv.setViewName("comprarhuevos");
		return mv;
	}

	// Mostrar vista comprar Gallinas y le pasamos los atributos
	@RequestMapping(value = "/comprarGallinas/{idGranja}", method = RequestMethod.GET)
	public ModelAndView comprarGallinas(@PathVariable("idGranja") Long idGranja, ModelAndView mv) {

		Farm granja = farmServicio.buscarPorIdGranja(idGranja);

		// pasamos datos a la vista
		mv.addObject("titulo", "Comprar Gallinas");
		mv.addObject("tituloh1", "Granja: " + granja.getNombreGranja());
		mv.addObject("fechaGranja", granja.getFechaGranja());
		mv.addObject("granja", granja);

		// seteamos el nombre de la vista
		mv.setViewName("comprargallinas");
		return mv;
	}

	// Mostrar vista vender Huevos y le pasamos los atributos
	@RequestMapping(value = "/venderHuevos/{idGranja}", method = RequestMethod.GET)
	public ModelAndView venderHuevos(@PathVariable("idGranja") Long idGranja, ModelAndView mv) {

		Farm granja = farmServicio.buscarPorIdGranja(idGranja);

		// pasamos datos a la vista
		mv.addObject("titulo", "Vender Huevos");
		mv.addObject("tituloh1", "Granja: " + granja.getNombreGranja());
		mv.addObject("fechaGranja", granja.getFechaGranja());
		mv.addObject("granja", granja);

		// seteamos el nombre de la vista
		mv.setViewName("venderhuevos");
		return mv;
	}

	// Mostrar vista vender Gallinas y le pasamos los atributos
	@RequestMapping(value = "/venderGallinas/{idGranja}", method = RequestMethod.GET)
	public ModelAndView venderGallinas(@PathVariable("idGranja") Long idGranja, ModelAndView mv) {

		Farm granja = farmServicio.buscarPorIdGranja(idGranja);

		// pasamos datos a la vista
		mv.addObject("titulo", "Vender Gallinas");
		mv.addObject("tituloh1", "Granja: " + granja.getNombreGranja());
		mv.addObject("fechaGranja", granja.getFechaGranja());
		mv.addObject("granja", granja);

		// seteamos el nombre de la vista
		mv.setViewName("vendergallinas");
		return mv;
	}

}
