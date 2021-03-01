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
public class IndexController {

	private FarmServicio farmServicio = new FarmServicio();
	private EggServicio eggServicio = new EggServicio();
	private ChickenServicio chickenServicio = new ChickenServicio();

	// Mostrar vista inicio y le pasamos los atributos
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mv) {

		// Traemos las granjas
		List<Farm> listadoGranjas = farmServicio.listarGranjas();

		// pasamos datos a la vista
		mv.addObject("titulo", "Granjas de Gallinas");
		mv.addObject("tituloh1", "Granjas de Gallinas");
		mv.addObject("granjas", listadoGranjas);

		// seteamos el nombre de la vista
		mv.setViewName("index");
		return mv;
	}

	// Mostrar vista crear granja y le pasamos los atributos
	@RequestMapping(value = "/crearGranja", method = RequestMethod.GET)
	public ModelAndView crearGranja(ModelAndView mv) {

		// Creamos un objeto de tipo granja para cargar los datos que se ingresan por
		// form
		Farm granja = new Farm();

		// pasamos datos a la vista
		mv.addObject("titulo", "Granja de Gallinas");
		mv.addObject("granjanueva", granja);

		// seteamos el nombre de la vista
		mv.setViewName("creargranja");
		return mv;
	}

	@RequestMapping(value = "/borrargranja/{idGranja}", method = RequestMethod.GET)
	public ModelAndView borrarGranja(@PathVariable("idGranja") Long idGranja, ModelAndView mv) { // para que se enlace con el id que viene de la vista

		Farm granja = null;

		if (idGranja > 0) {

			granja = farmServicio.buscarPorIdGranja(idGranja);

			if (granja == null) {
				System.out.println("No existe la granja");
				mv.setViewName("redirect:/index");
				return mv;
			}

		} else {
			System.out.println("Error al buscar una granja");
			mv.setViewName("redirect:/index");
			return mv;
		}

		farmServicio.eliminarGranja(idGranja);
		System.out.println("Granja eliminada con éxito");

		// seteamos el nombre de la vista
		mv.setViewName("redirect:/index");
		return mv;
	}

	@RequestMapping(value = "/seleccionargranja/{idGranja}", method = RequestMethod.GET)
	public ModelAndView seleccionarGranja(@PathVariable("idGranja") Long idGranja, ModelAndView mv) { // para que se enlace con el id que viene de la vista

		Farm granja = null;

		if (idGranja > 0) {

			granja = farmServicio.buscarPorIdGranja(idGranja);

			if (granja == null) {
				System.out.println("No existe la granja");
				mv.setViewName("redirect:/index");
				return mv;
			}

		} else {
			System.out.println("Error al buscar una granja");
			mv.setViewName("redirect:/index");
			return mv;
		}
		
		List<Egg> listaHuevosGranja = eggServicio.listarHuevosDisponiblesGranja(idGranja);
		List<Chicken> listaGallinasGranja = chickenServicio.listarGallinasDisponiblesGranja(idGranja);

		String tituloH1 = "Granja: " + granja.getNombreGranja();

		mv.addObject("tituloh1", tituloH1);
		mv.addObject("granjaSelec", granja); // paso el objeto a la vista
		mv.addObject("granjaHuevos", listaHuevosGranja);
		mv.addObject("granjaGallinas", listaGallinasGranja);
		
		// seteamos el nombre de la vista
		mv.setViewName("granjaseleccionada");
		return mv;
	}

}
