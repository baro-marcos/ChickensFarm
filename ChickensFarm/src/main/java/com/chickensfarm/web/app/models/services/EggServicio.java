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

import com.chickensfarm.web.app.models.Egg;
import com.chickensfarm.web.app.models.Farm;
import com.chickensfarm.web.app.utilidades.Conexion;

public class EggServicio {

	private Conexion con = new Conexion();

	/**
	 * Lista de todos los Huevos pertenecientes a una Granja especifica
	 * 
	 * @param idGranja
	 * @return HuevosGranja
	 */
	public List<Egg> listarHuevosGranja(Long idGranja) {

		List<Egg> HuevosGranja = new ArrayList<>();

		try {

			String sql = " SELECT * FROM huevos WHERE idGranja = " + idGranja;
			Statement st = con.getConexion().createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Egg huevo = new Egg();
				huevo.setIdHuevo(rs.getLong("idHuevo"));
				huevo.setIdGranja(rs.getLong("idGranja"));
				huevo.setFechaHuevo(rs.getDate("fechaHuevo"));
				huevo.setHuevoVendido(rs.getBoolean("huevoVendido"));
				huevo.setHuevoNacioGallina(rs.getBoolean("huevoNacioGallina"));
				huevo.setPrecioCompraHuevo(rs.getBigDecimal("precioCompraHuevo"));
				huevo.setPrecioVtaHuevo(rs.getBigDecimal("precioVtaHuevo"));

				HuevosGranja.add(huevo);
			}

		} catch (SQLException ex) {
			Logger.getLogger(FarmServicio.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}

		return HuevosGranja;
	}

	/**
	 * Lista de todos los Huevos que no nacieron ni se vendieron, pertenecientes a
	 * una Granja especifica
	 * 
	 * @param idGranja
	 * @return HuevosGranja
	 */
	public List<Egg> listarHuevosDisponiblesGranja(Long idGranja) {

		List<Egg> HuevosGranja = new ArrayList<>();

		try {

			String sql = " SELECT * FROM huevos WHERE idGranja = " + idGranja
					+ " AND huevoVendido = 'false' AND huevoNacioGallina = 'false' ORDER BY idHuevo ";
			Statement st = con.getConexion().createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Egg huevo = new Egg();
				huevo.setIdHuevo(rs.getLong("idHuevo"));
				huevo.setIdGranja(rs.getLong("idGranja"));
				huevo.setFechaHuevo(rs.getDate("fechaHuevo"));
				huevo.setHuevoVendido(rs.getBoolean("huevoVendido"));
				huevo.setHuevoNacioGallina(rs.getBoolean("huevoNacioGallina"));
				huevo.setPrecioCompraHuevo(rs.getBigDecimal("precioCompraHuevo"));
				huevo.setPrecioVtaHuevo(rs.getBigDecimal("precioVtaHuevo"));

				HuevosGranja.add(huevo);
			}

		} catch (SQLException ex) {
			Logger.getLogger(FarmServicio.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}

		return HuevosGranja;
	}

	/**
	 * Almacena Huevo para una granja en BBDD
	 * 
	 * @param huevo
	 */
	public void guardarHuevoEnGranja(Egg huevo) {

		java.util.Date utilDate = huevo.getFechaHuevo(); // lo almaceno en un objeto de tipo util.Date
		// conversión
		java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime()); // lo convertimos a sql.Date
		huevo.setFechaHuevo(fechaConvertida);

		try {
			String sqlInsert = " INSERT INTO huevos (idGranja, fechaHuevo, huevoVendido, huevoNacioGallina, "
					+ "precioCompraHuevo, precioVtaHuevo) VALUES (?, ?, ?, ?, ?, ?) ";
			PreparedStatement psNuevo = con.getConexion().prepareStatement(sqlInsert);
			psNuevo.setLong(1, huevo.getIdGranja());
			psNuevo.setDate(2, (Date) huevo.getFechaHuevo());
			psNuevo.setBoolean(3, huevo.isHuevoVendido());
			psNuevo.setBoolean(4, huevo.isHuevoNacioGallina());
			psNuevo.setBigDecimal(5, huevo.getPrecioCompraHuevo());
			psNuevo.setBigDecimal(6, huevo.getPrecioVtaHuevo());
			psNuevo.executeUpdate();
			psNuevo.close();

		} catch (SQLException ex) {

			Logger.getLogger(FarmServicio.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	/**
	 * Devuelve los datos de un Huevo especifico para una Granja
	 * 
	 * @param idHuevo
	 * @param idGranja
	 * @return huevo
	 */
	public Egg buscarHuevoPorIdGranja(Long idHuevo, Long idGranja) {

		Egg huevo = new Egg();

		try {

			String sql = " SELECT * FROM huevos WHERE idHuevo = " + idHuevo + " AND idGranja = " + idGranja;
			Statement st = con.getConexion().createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				huevo.setIdGranja(rs.getLong("idGranja"));
				huevo.setFechaHuevo(rs.getDate("fechaHuevo"));
				huevo.setHuevoVendido(rs.getBoolean("huevoVendido"));
				huevo.setHuevoNacioGallina(rs.getBoolean("huevoNacioGallina"));
				huevo.setPrecioCompraHuevo(rs.getBigDecimal("precioCompraHuevo"));
				huevo.setPrecioVtaHuevo(rs.getBigDecimal("precioVtaHuevo"));
			}

		} catch (SQLException ex) {
			Logger.getLogger(FarmServicio.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}

		return huevo;
	}

	/**
	 * Actualiza los datos de un Huevo al ser vendido
	 * @param huevo
	 */
	
	public void actualizarHuevoGranja(Egg huevo) {

		try {

				String sqlUpdate = " UPDATE huevos SET huevoVendido = ?, precioVtaHuevo = ? WHERE idHuevo = ? ";
				PreparedStatement psUpdate = con.getConexion().prepareStatement(sqlUpdate);
				psUpdate.setBoolean(1, huevo.isHuevoVendido());
				psUpdate.setBigDecimal(2, huevo.getPrecioVtaHuevo());
				psUpdate.setLong(3, huevo.getIdHuevo());
				psUpdate.executeUpdate();

				psUpdate.close();
		} catch (SQLException ex) {

			Logger.getLogger(FarmServicio.class.getName()).log(Level.SEVERE, null, ex);

		}

	}

}
