package com.chickensfarm.web.app.models;

import java.math.BigDecimal;
import java.util.Date;

public class Egg {

	private Long idHuevo;

	private Long idGranja;
	private Date fechaHuevo;
	private boolean huevoVendido = false;
	private boolean huevoNacioGallina = false;
	private BigDecimal precioCompraHuevo;
	private BigDecimal precioVtaHuevo;

	private Farm granja;
	
	public Egg(Long idHuevo, Long idGranja, Date fechaHuevo, boolean huevoVendido, boolean huevoNacioGallina,
			BigDecimal precioCompraHuevo, BigDecimal precioVtaHuevo, Farm granja) {
		
		this.idHuevo = idHuevo;
		this.idGranja = idGranja;
		this.fechaHuevo = fechaHuevo;
		this.huevoVendido = huevoVendido;
		this.huevoNacioGallina = huevoNacioGallina;
		this.precioCompraHuevo = precioCompraHuevo;
		this.precioVtaHuevo = precioVtaHuevo;
		this.granja = granja;
	}
	
	public Egg() {
		
	}

	public boolean isHuevoVendido() {
		return huevoVendido;
	}

	public boolean isHuevoNacioGallina() {
		return huevoNacioGallina;
	}

	public Long getIdHuevo() {
		return idHuevo;
	}

	public void setIdHuevo(Long idHuevo) {
		this.idHuevo = idHuevo;
	}

	public void setHuevoVendido(boolean huevoVendido) {
		this.huevoVendido = huevoVendido;
	}

	public void setHuevoNacioGallina(boolean huevoNacioGallina) {
		this.huevoNacioGallina = huevoNacioGallina;
	}

	public Date getFechaHuevo() {
		return fechaHuevo;
	}

	public void setFechaHuevo(Date fechaHuevo) {
		this.fechaHuevo = fechaHuevo;
	}

	public BigDecimal getPrecioCompraHuevo() {
		return precioCompraHuevo;
	}

	public void setPrecioCompraHuevo(BigDecimal precioCompraHuevo) {
		this.precioCompraHuevo = precioCompraHuevo;
	}

	public BigDecimal getPrecioVtaHuevo() {
		return precioVtaHuevo;
	}

	public void setPrecioVtaHuevo(BigDecimal precioVtaHuevo) {
		this.precioVtaHuevo = precioVtaHuevo;
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
