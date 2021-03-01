package com.chickensfarm.web.app.models;

import java.math.BigDecimal;
import java.util.Date;

public class Chicken {

	private Long idGallina;

	private Long idGranja;
	private Date fechaGallina;
	private boolean gallinaVendida = false;
	private boolean gallinaMurio = false;
	private BigDecimal precioCompraGallina;
	private BigDecimal precioVtaGallina;

	private Farm granja;
	
	public Chicken(Long idGallina, Long idGranja, Date fechaGallina, boolean gallinaVendida, boolean gallinaMurio,
			BigDecimal precioCompraGallina, BigDecimal precioVtaGallina, Farm granja) {
		
		this.idGallina = idGallina;
		this.idGranja = idGranja;
		this.fechaGallina = fechaGallina;
		this.gallinaVendida = gallinaVendida;
		this.gallinaMurio = gallinaMurio;
		this.precioCompraGallina = precioCompraGallina;
		this.precioVtaGallina = precioVtaGallina;
		this.granja = granja;
	}
	
	public Chicken() {
		
	}



	public Long getIdGallina() {
		return idGallina;
	}

	public boolean isGallinaMurio() {
		return gallinaMurio;
	}

	public void setGallinaMurio(boolean gallinaMurio) {
		this.gallinaMurio = gallinaMurio;
	}

	public void setIdGallina(Long idGallina) {
		this.idGallina = idGallina;
	}

	public void setGallinaVendida(boolean gallinaVendida) {
		this.gallinaVendida = gallinaVendida;
	}

	public boolean isGallinaVendida() {
		return gallinaVendida;
	}

	public Date getFechaGallina() {
		return fechaGallina;
	}

	public void setFechaGallina(Date fechaGallina) {
		this.fechaGallina = fechaGallina;
	}

	public BigDecimal getPrecioCompraGallina() {
		return precioCompraGallina;
	}

	public void setPrecioCompraGallina(BigDecimal precioCompraGallina) {
		this.precioCompraGallina = precioCompraGallina;
	}

	public BigDecimal getPrecioVtaGallina() {
		return precioVtaGallina;
	}

	public void setPrecioVtaGallina(BigDecimal precioVtaGallina) {
		this.precioVtaGallina = precioVtaGallina;
	}

	public Long getIdGranja() {
		return idGranja;
	}

	public void setIdGranja(Long idGranja) {
		this.idGranja = idGranja;
	}

	public Farm getGranja() {
		return granja;
	}

	public void setGranja(Farm granja) {
		this.granja = granja;
	}

}
