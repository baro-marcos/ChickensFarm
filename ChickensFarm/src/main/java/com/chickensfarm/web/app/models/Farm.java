package com.chickensfarm.web.app.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Farm {

	private Long idGranja;

	private String nombreGranja;
	private Date fechaGranja;
	private int cantidadHuevos;
	private int cantidadGallinas;

	private BigDecimal importeTotalHuevos;
	private BigDecimal importeTotalGallinas;
	private BigDecimal importeTotalGranja;

	private List<Egg> huevos = new ArrayList<>();
	private List<Chicken> gallinas = new ArrayList<>();
	
	public Farm(Long idGranja, String nombreGranja, Date fechaGranja, int cantidadHuevos, int cantidadGallinas,
			BigDecimal importeTotalHuevos, BigDecimal importeTotalGallinas, BigDecimal importeTotalGranja,
			List<Egg> huevos, List<Chicken> gallinas) {
		
		this.idGranja = idGranja;
		this.nombreGranja = nombreGranja;
		this.fechaGranja = fechaGranja;
		this.cantidadHuevos = cantidadHuevos;
		this.cantidadGallinas = cantidadGallinas;
		this.importeTotalHuevos = importeTotalHuevos;
		this.importeTotalGallinas = importeTotalGallinas;
		this.importeTotalGranja = importeTotalGranja;
		this.huevos = huevos;
		this.gallinas = gallinas;
	}
	
	public Farm() {
		
	}
	
	public Farm(Long idGranja, String nombreGranja, Date fechaGranja, int cantidadHuevos, int cantidadGallinas,
			BigDecimal importeTotalHuevos, BigDecimal importeTotalGallinas, BigDecimal importeTotalGranja) {
		
		this.idGranja = idGranja;
		this.nombreGranja = nombreGranja;
		this.fechaGranja = fechaGranja;
		this.cantidadHuevos = cantidadHuevos;
		this.cantidadGallinas = cantidadGallinas;
		this.importeTotalHuevos = importeTotalHuevos;
		this.importeTotalGallinas = importeTotalGallinas;
		this.importeTotalGranja = importeTotalGranja;
	}

	public int getCantidadHuevos() {
		return cantidadHuevos;
	}

	public void setCantidadHuevos(int cantidadHuevos) {
		this.cantidadHuevos = cantidadHuevos;
	}

	public int getCantidadGallinas() {
		return cantidadGallinas;
	}

	public void setCantidadGallinas(int cantidadGallinas) {
		this.cantidadGallinas = cantidadGallinas;
	}

	public BigDecimal getImporteTotalHuevos() {
		return importeTotalHuevos;
	}

	public void setImporteTotalHuevos(BigDecimal importeTotalHuevos) {
		this.importeTotalHuevos = importeTotalHuevos;
	}

	public BigDecimal getImporteTotalGallinas() {
		return importeTotalGallinas;
	}

	public void setImporteTotalGallinas(BigDecimal importeTotalGallinas) {
		this.importeTotalGallinas = importeTotalGallinas;
	}

	public BigDecimal getImporteTotalGranja() {
		return importeTotalGranja;
	}

	public void setImporteTotalGranja(BigDecimal importeTotalGranja) {
		this.importeTotalGranja = importeTotalGranja;
	}

	public Long getIdGranja() {
		return idGranja;
	}

	public void setIdGranja(Long idGranja) {
		this.idGranja = idGranja;
	}

	public String getNombreGranja() {
		return nombreGranja;
	}

	public void setNombreGranja(String nombreGranja) {
		this.nombreGranja = nombreGranja;
	}

	public Date getFechaGranja() {
		return fechaGranja;
	}

	public void setFechaGranja(Date fechaGranja) {
		this.fechaGranja = fechaGranja;
	}

	public List<Egg> getHuevos() {
		return huevos;
	}

	public void setHuevos(List<Egg> huevos) {
		this.huevos = huevos;
	}

	public List<Chicken> getGallinas() {
		return gallinas;
	}

	public void setGallinas(List<Chicken> gallinas) {
		this.gallinas = gallinas;
	}

}
