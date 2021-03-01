package com.chickensfarm.web.app.controllers;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.chickensfarm.web.app.models.Chicken;
import com.chickensfarm.web.app.models.Egg;
import com.chickensfarm.web.app.models.Farm;
import com.chickensfarm.web.app.models.services.ChickenServicio;
import com.chickensfarm.web.app.models.services.EggServicio;
import com.chickensfarm.web.app.models.services.FarmServicio;

@Controller
public class CrearGranjaController {

	private FarmServicio farmServicio = new FarmServicio();
	private EggServicio eggServicio = new EggServicio();
	private ChickenServicio chickenServicio = new ChickenServicio();
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public ModelAndView guardar(@ModelAttribute Farm granja, ModelAndView mv, 
			@RequestParam(value = "precioCompraHuevos") BigDecimal precioCompraHuevos,
			@RequestParam(value = "precioCompraGallinas") BigDecimal precioCompraGallinas) {
		
		
		BigDecimal impTotHuevos = precioCompraHuevos.multiply(new BigDecimal(granja.getCantidadHuevos()));
		BigDecimal impTotGallinas = precioCompraGallinas.multiply(new BigDecimal(granja.getCantidadGallinas()));
				
		granja.setImporteTotalHuevos(impTotHuevos);
		granja.setImporteTotalGallinas(impTotGallinas);
		
		Long idGenerado = farmServicio.guardarGranja(granja);
		
		//Insertamos los Huevos
		for(int i = 1; granja.getCantidadHuevos() >= i; i++) {
			Egg huevo = new Egg();
			huevo.setIdGranja(idGenerado);
			huevo.setFechaHuevo(granja.getFechaGranja());
			huevo.setHuevoVendido(false);
			huevo.setHuevoNacioGallina(false);
			huevo.setPrecioCompraHuevo(precioCompraHuevos);
			huevo.setPrecioVtaHuevo(BigDecimal.ZERO);
			
			eggServicio.guardarHuevoEnGranja(huevo);
		}
		
		//Insertamos las Gallinas
		for(int i = 1; granja.getCantidadGallinas() >= i; i++) {
			Chicken gallina = new Chicken();
			gallina.setIdGranja(idGenerado);
			gallina.setFechaGallina(granja.getFechaGranja());
			gallina.setGallinaVendida(false);
			gallina.setGallinaMurio(false);
			gallina.setPrecioCompraGallina(precioCompraGallinas);
			gallina.setPrecioVtaGallina(BigDecimal.ZERO);
			
			chickenServicio.guardarGallinaEnGranja(gallina);
		}
				
		System.out.println("Granja guardada con éxito");
				
		// seteamos el nombre de la vista
		mv.setViewName("redirect:/index");
		return mv;
				
	}

}
