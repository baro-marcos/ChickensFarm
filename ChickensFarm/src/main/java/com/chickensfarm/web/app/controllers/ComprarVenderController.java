package com.chickensfarm.web.app.controllers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
public class ComprarVenderController {

	private FarmServicio farmServicio = new FarmServicio();
	private EggServicio eggServicio = new EggServicio();
	private ChickenServicio chickenServicio = new ChickenServicio();

	@RequestMapping(value = "/agregarHuevos", method = RequestMethod.POST)
	public ModelAndView comprarHuevos(@ModelAttribute Farm granja, ModelAndView mv,
			@RequestParam(value = "cantidadHuevosComprados") int cantidadHuevosComprados,
			@RequestParam(value = "precioCompraHuevos") BigDecimal precioCompraHuevos,
			@RequestParam(value = "fechaCompraHuevo") Date fechaCompraHuevo) {

		BigDecimal impTotHuevosComprados = precioCompraHuevos.multiply(BigDecimal.valueOf(cantidadHuevosComprados));

		if (impTotHuevosComprados.compareTo(granja.getImporteTotalGranja()) < 0) {

			int cantidadHuevosEnGranja = granja.getCantidadHuevos() + cantidadHuevosComprados;
			BigDecimal impTotHuevosGranja = granja.getImporteTotalHuevos().add(impTotHuevosComprados);

			granja.setCantidadHuevos(cantidadHuevosEnGranja);
			granja.setImporteTotalHuevos(impTotHuevosGranja);

			BigDecimal nuevoImporteTotalGranja = granja.getImporteTotalGranja().subtract(impTotHuevosComprados);
			granja.setImporteTotalGranja(nuevoImporteTotalGranja);

			farmServicio.actualizarGranja(granja);

			// Insertamos los Huevos
			for (int i = 1; cantidadHuevosComprados >= i; i++) {
				Egg huevo = new Egg();
				huevo.setIdGranja(granja.getIdGranja());
				huevo.setFechaHuevo(fechaCompraHuevo);
				huevo.setHuevoVendido(false);
				huevo.setHuevoNacioGallina(false);
				huevo.setPrecioCompraHuevo(precioCompraHuevos);
				huevo.setPrecioVtaHuevo(BigDecimal.ZERO);

				eggServicio.guardarHuevoEnGranja(huevo);
			}

			System.out.println("Compra de huevos guardada con éxito");

			// seteamos el nombre de la vista
			mv.setViewName("redirect:/index");
			return mv;

		} else {

			System.out.println("Error en la Compra de huevos");

			// seteamos el nombre de la vista
			mv.setViewName("manejoerrores");
			return mv;

		}

	}

	@RequestMapping(value = "/agregarGallinas", method = RequestMethod.POST)
	public ModelAndView comprarGallinas(@ModelAttribute Farm granja, ModelAndView mv,
			@RequestParam(value = "cantidadGallinasCompradas") int cantidadGallinasCompradas,
			@RequestParam(value = "precioCompraGallinas") BigDecimal precioCompraGallinas,
			@RequestParam(value = "fechaCompraGallina") Date fechaCompraGallina) {

		BigDecimal impTotGallinasCompradas = precioCompraGallinas
				.multiply(BigDecimal.valueOf(cantidadGallinasCompradas));

		if (impTotGallinasCompradas.compareTo(granja.getImporteTotalGranja()) < 0) {

			int cantidadGallinasEnGranja = granja.getCantidadGallinas() + cantidadGallinasCompradas;
			BigDecimal impTotGallinasGranja = granja.getImporteTotalGallinas().add(impTotGallinasCompradas);

			granja.setCantidadGallinas(cantidadGallinasEnGranja);
			granja.setImporteTotalGallinas(impTotGallinasGranja);

			BigDecimal nuevoImporteTotalGranja = granja.getImporteTotalGranja().subtract(impTotGallinasCompradas);
			granja.setImporteTotalGranja(nuevoImporteTotalGranja);

			farmServicio.actualizarGranja(granja);

			// Insertamos las Gallinas
			for (int i = 1; cantidadGallinasCompradas >= i; i++) {
				Chicken gallina = new Chicken();
				gallina.setIdGranja(granja.getIdGranja());
				gallina.setFechaGallina(fechaCompraGallina);
				gallina.setGallinaVendida(false);
				gallina.setGallinaMurio(false);
				gallina.setPrecioCompraGallina(precioCompraGallinas);
				gallina.setPrecioVtaGallina(BigDecimal.ZERO);

				chickenServicio.guardarGallinaEnGranja(gallina);
			}

			System.out.println("Compra de Gallinas guardada con éxito");

			// seteamos el nombre de la vista
			mv.setViewName("redirect:/index");
			return mv;

		} else {

			System.out.println("Error en la Compra de Gallinas");

			// seteamos el nombre de la vista
			mv.setViewName("manejoerrores");
			return mv;

		}

	}
	
	@RequestMapping(value = "/ventaHuevos", method = RequestMethod.POST)
	public ModelAndView venderHuevos(@ModelAttribute Farm granja, ModelAndView mv,
			@RequestParam(value = "cantidadHuevosVendidos") int cantidadHuevosVendidos,
			@RequestParam(value = "precioVtaHuevos") BigDecimal precioVtaHuevos) {

		if (granja.getCantidadHuevos() >= cantidadHuevosVendidos) {
			
			List<Egg> listaHuevosDisponibles = eggServicio.listarHuevosDisponiblesGranja(granja.getIdGranja());
			
			BigDecimal importeHuevosArestar = BigDecimal.ZERO;
			
			for (int i = 0; i < cantidadHuevosVendidos; i++) {
				Egg huevo = listaHuevosDisponibles.get(i);
				huevo.setHuevoVendido(true);
				huevo.setPrecioVtaHuevo(precioVtaHuevos);
				importeHuevosArestar = importeHuevosArestar.add(huevo.getPrecioCompraHuevo());
				eggServicio.actualizarHuevoGranja(huevo);
			}
			
			int cantidadHuevosEnGranja = granja.getCantidadHuevos() - cantidadHuevosVendidos;
			
			BigDecimal impTotHuevosVendidos = precioVtaHuevos.multiply(BigDecimal.valueOf(cantidadHuevosVendidos));
			
			BigDecimal impTotHuevosGranja = granja.getImporteTotalHuevos().subtract(importeHuevosArestar);

			granja.setCantidadHuevos(cantidadHuevosEnGranja);
			granja.setImporteTotalHuevos(impTotHuevosGranja);

			BigDecimal nuevoImporteTotalGranja = granja.getImporteTotalGranja().add(impTotHuevosVendidos);
			granja.setImporteTotalGranja(nuevoImporteTotalGranja);

			farmServicio.actualizarGranja(granja);

			System.out.println("Venta de huevos guardada con éxito");

			// seteamos el nombre de la vista
			mv.setViewName("redirect:/index");
			return mv;

		} else {

			System.out.println("Error en la Venta de huevos");

			// seteamos el nombre de la vista
			mv.setViewName("manejoerrores");
			return mv;

		}

	}
	
	@RequestMapping(value = "/ventaGallinas", method = RequestMethod.POST)
	public ModelAndView venderGallinas(@ModelAttribute Farm granja, ModelAndView mv,
			@RequestParam(value = "cantidadGallinasVendidas") int cantidadGallinasVendidas,
			@RequestParam(value = "precioVtaGallinas") BigDecimal precioVtaGallinas) {

		if (granja.getCantidadGallinas() >= cantidadGallinasVendidas) {
			
			List<Chicken> listaGallinasDisponibles = chickenServicio.listarGallinasDisponiblesGranja(granja.getIdGranja());
			
			BigDecimal importeGallinasArestar = BigDecimal.ZERO;
			
			for (int i = 0; i < cantidadGallinasVendidas; i++) {
				Chicken gallina = listaGallinasDisponibles.get(i);
				gallina.setGallinaVendida(true);
				gallina.setPrecioVtaGallina(precioVtaGallinas);
				importeGallinasArestar = importeGallinasArestar.add(gallina.getPrecioCompraGallina());
				chickenServicio.actualizarGallinaGranja(gallina);
			}
			
			int cantidadGallinasEnGranja = granja.getCantidadGallinas() - cantidadGallinasVendidas;
			
			BigDecimal impTotGallinasVendidas = precioVtaGallinas.multiply(BigDecimal.valueOf(cantidadGallinasVendidas));
			
			BigDecimal impTotGallinasGranja = granja.getImporteTotalGallinas().subtract(importeGallinasArestar);

			granja.setCantidadGallinas(cantidadGallinasEnGranja);
			granja.setImporteTotalGallinas(impTotGallinasGranja);

			BigDecimal nuevoImporteTotalGranja = granja.getImporteTotalGranja().add(impTotGallinasVendidas);
			granja.setImporteTotalGranja(nuevoImporteTotalGranja);

			farmServicio.actualizarGranja(granja);

			System.out.println("Venta de gallinas guardada con éxito");

			// seteamos el nombre de la vista
			mv.setViewName("redirect:/index");
			return mv;

		} else {

			System.out.println("Error en la Venta de gallinas");

			// seteamos el nombre de la vista
			mv.setViewName("manejoerrores");
			return mv;

		}

	}

}
