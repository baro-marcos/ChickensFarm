package com.chickensfarm.web.app.models.services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.chickensfarm.web.app.models.Chicken;
import com.chickensfarm.web.app.models.Egg;
import com.chickensfarm.web.app.utilidades.Conexion;

public class ChickenServicio {

	private Conexion con = new Conexion();

	/**
	 * Lista de todas las Gallinas pertenecientes a una Granja especifica
	 * 
	 * @param idGranja
	 * @return GallinasGranja
	 */
	public List<Chicken> listarGallinasGranja(Long idGranja) {

		List<Chicken> GallinasGranja = new ArrayList<>();

		try {

			String sql = " SELECT * FROM gallinas WHERE idGranja = " + idGranja;
			Statement st = con.getConexion().createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Chicken gallina = new Chicken();
				gallina.setIdGallina(rs.getLong("idGallina"));
				gallina.setIdGranja(rs.getLong("idGranja"));
				gallina.setFechaGallina(rs.getDate("fechaGallina"));
				gallina.setGallinaVendida(rs.getBoolean("gallinaVendida"));
				gallina.setGallinaMurio(rs.getBoolean("gallinaMurio"));
				gallina.setPrecioCompraGallina(rs.getBigDecimal("precioCompraGallina"));
				gallina.setPrecioVtaGallina(rs.getBigDecimal("precioVtaGallina"));

				GallinasGranja.add(gallina);
			}

		} catch (SQLException ex) {
			Logger.getLogger(FarmServicio.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}

		return GallinasGranja;
	}

	/**
	 * Lista de todas las Gallinas que no murieron ni se vendieron, pertenecientes a
	 * una Granja especifica
	 * 
	 * @param idGranja
	 * @return GallinasGranja
	 */
	public List<Chicken> listarGallinasDisponiblesGranja(Long idGranja) {

		List<Chicken> GallinasGranja = new ArrayList<>();

		try {

			String sql = " SELECT * FROM gallinas WHERE idGranja = " + idGranja
					+ " AND gallinaVendida = 'false' AND gallinaMurio = 'false' ORDER BY idGallina";
			Statement st = con.getConexion().createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Chicken gallina = new Chicken();
				gallina.setIdGallina(rs.getLong("idGallina"));
				gallina.setIdGranja(rs.getLong("idGranja"));
				gallina.setFechaGallina(rs.getDate("fechaGallina"));
				gallina.setGallinaVendida(rs.getBoolean("gallinaVendida"));
				gallina.setGallinaMurio(rs.getBoolean("gallinaMurio"));
				gallina.setPrecioCompraGallina(rs.getBigDecimal("precioCompraGallina"));
				gallina.setPrecioVtaGallina(rs.getBigDecimal("precioVtaGallina"));

				GallinasGranja.add(gallina);
			}

		} catch (SQLException ex) {
			Logger.getLogger(FarmServicio.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}

		return GallinasGranja;
	}

	/**
	 * Almacena Gallina para una granja en BBDD
	 * 
	 * @param gallina
	 */
	public void guardarGallinaEnGranja(Chicken gallina) {

		java.util.Date utilDate = gallina.getFechaGallina(); // lo almaceno en un objeto de tipo util.Date
		// conversión
		java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime()); // lo convertimos a sql.Date
		gallina.setFechaGallina(fechaConvertida);

		try {
			String sqlInsert = " INSERT INTO gallinas (idGranja, fechaGallina, gallinaVendida, gallinaMurio, "
					+ "precioCompraGallina, precioVtaGallina) VALUES (?, ?, ?, ?, ?, ?) ";
			PreparedStatement psNuevo = con.getConexion().prepareStatement(sqlInsert);
			psNuevo.setLong(1, gallina.getIdGranja());
			psNuevo.setDate(2, (Date) gallina.getFechaGallina());
			psNuevo.setBoolean(3, gallina.isGallinaVendida());
			psNuevo.setBoolean(4, gallina.isGallinaMurio());
			psNuevo.setBigDecimal(5, gallina.getPrecioCompraGallina());
			psNuevo.setBigDecimal(6, gallina.getPrecioVtaGallina());
			psNuevo.executeUpdate();
			psNuevo.close();

		} catch (SQLException ex) {

			Logger.getLogger(FarmServicio.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	/**
	 * Devuelve los datos de una Gallina especifica de una Granja
	 * 
	 * @param idGallina
	 * @param idGranja
	 * @return gallina
	 */
	public Chicken buscarGallinaPorIdGranja(Long idGallina, Long idGranja) {

		Chicken gallina = new Chicken();

		try {

			String sql = " SELECT * FROM gallinas WHERE idGallina = " + idGallina + " AND idGranja = " + idGranja;
			Statement st = con.getConexion().createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				gallina.setIdGallina(rs.getLong("idGallina"));
				gallina.setIdGranja(rs.getLong("idGranja"));
				gallina.setFechaGallina(rs.getDate("fechaGallina"));
				gallina.setGallinaVendida(rs.getBoolean("gallinaVendida"));
				gallina.setGallinaMurio(rs.getBoolean("gallinaMurio"));
				gallina.setPrecioCompraGallina(rs.getBigDecimal("precioCompraGallina"));
				gallina.setPrecioVtaGallina(rs.getBigDecimal("precioVtaGallina"));
			}

		} catch (SQLException ex) {
			Logger.getLogger(FarmServicio.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}

		return gallina;
	}

	/**
	 * Actualiza los datos de una Gallina al ser vendida
	 * @param gallina
	 */
	public void actualizarGallinaGranja(Chicken gallina) {

		try {

				String sqlUpdate = " UPDATE gallinas SET gallinaVendida = ?, precioVtaGallina = ? WHERE idGallina = ? ";
				PreparedStatement psUpdate = con.getConexion().prepareStatement(sqlUpdate);
				psUpdate.setBoolean(1, gallina.isGallinaVendida());
				psUpdate.setBigDecimal(2, gallina.getPrecioVtaGallina());
				psUpdate.setLong(3, gallina.getIdGallina());
				psUpdate.executeUpdate();

				psUpdate.close();

			

		} catch (SQLException ex) {

			Logger.getLogger(FarmServicio.class.getName()).log(Level.SEVERE, null, ex);

		}

	}

}
