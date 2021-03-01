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

import com.chickensfarm.web.app.models.Farm;
import com.chickensfarm.web.app.utilidades.Conexion;

public class FarmServicio {

	private Conexion con = new Conexion();

	/**
	 * Lista todas las Granjas
	 * 
	 * @return granjas
	 */
	public List<Farm> listarGranjas() {

		List<Farm> granjas = new ArrayList<>();

		try {

			String sql = " SELECT * FROM granjas ";
			Statement st = con.getConexion().createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Farm granja = new Farm();
				granja.setIdGranja(rs.getLong("idGranja"));
				granja.setNombreGranja(rs.getString("nombreGranja"));
				granja.setFechaGranja(rs.getDate("fechaGranja"));
				granja.setCantidadHuevos(rs.getInt("cantidadHuevos"));
				granja.setCantidadGallinas(rs.getInt("cantidadGallinas"));
				granja.setImporteTotalHuevos(rs.getBigDecimal("importeTotalHuevos"));
				granja.setImporteTotalGallinas(rs.getBigDecimal("importeTotalGallinas"));
				granja.setImporteTotalGranja(rs.getBigDecimal("importeTotalGranja"));

				granjas.add(granja);
			}

		} catch (SQLException ex) {
			Logger.getLogger(FarmServicio.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}

		return granjas;
	}

	/**
	 * Guarda una Granja en BBDD y retorna el id generado
	 * 
	 * @param granja
	 * @return idGenerado
	 */
	public Long guardarGranja(Farm granja) {

		Long idGenerado = 0L;

		java.util.Date utilDate = granja.getFechaGranja(); // lo almaceno en un objeto de tipo util.Date
		// conversión
		java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime()); // lo convertimos a sql.Date
		granja.setFechaGranja(fechaConvertida);

		try {
			String sqlInsert = " INSERT INTO granjas (nombreGranja, fechaGranja, cantidadHuevos, cantidadGallinas, "
					+ "importeTotalHuevos, importeTotalGallinas, importeTotalGranja) VALUES (?, ?, ?, ?, ?, ?, ?) ";
			PreparedStatement psNuevo = con.getConexion().prepareStatement(sqlInsert);
			psNuevo.setString(1, granja.getNombreGranja());
			psNuevo.setDate(2, (Date) granja.getFechaGranja());
			psNuevo.setInt(3, granja.getCantidadHuevos());
			psNuevo.setInt(4, granja.getCantidadGallinas());
			psNuevo.setBigDecimal(5, granja.getImporteTotalHuevos());
			psNuevo.setBigDecimal(6, granja.getImporteTotalGallinas());
			psNuevo.setBigDecimal(7, granja.getImporteTotalGranja());
			psNuevo.executeUpdate();

			String sqlIdGen = "SELECT LAST_INSERT_ID() AS ultimoID";
			Statement stIdGen = con.getConexion().createStatement();
			ResultSet rsIdGen = stIdGen.executeQuery(sqlIdGen);

			if (rsIdGen.next()) {
				idGenerado = rsIdGen.getLong("ultimoID");
			}

			rsIdGen.close();
			stIdGen.close();
			psNuevo.close();

		} catch (SQLException ex) {

			Logger.getLogger(FarmServicio.class.getName()).log(Level.SEVERE, null, ex);
			return idGenerado;
		}

		return idGenerado;

	}

	/**
	 * Devuelve una Granja especifica
	 * 
	 * @param idGranja
	 * @return granja
	 */
	public Farm buscarPorIdGranja(Long idGranja) {

		Farm granja = new Farm();

		try {

			String sql = " SELECT * FROM granjas WHERE idGranja = " + idGranja;
			Statement st = con.getConexion().createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				granja.setIdGranja(rs.getLong("idGranja"));
				granja.setNombreGranja(rs.getString("nombreGranja"));
				granja.setFechaGranja(rs.getDate("fechaGranja"));
				granja.setCantidadHuevos(rs.getInt("cantidadHuevos"));
				granja.setCantidadGallinas(rs.getInt("cantidadGallinas"));
				granja.setImporteTotalHuevos(rs.getBigDecimal("importeTotalHuevos"));
				granja.setImporteTotalGallinas(rs.getBigDecimal("importeTotalGallinas"));
				granja.setImporteTotalGranja(rs.getBigDecimal("importeTotalGranja"));
			}

		} catch (SQLException ex) {
			Logger.getLogger(FarmServicio.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}

		return granja;
	}

	/**
	 * Elimina una Granja especifica en BBDD
	 * 
	 * @param idGranja
	 */
	public void eliminarGranja(Long idGranja) {

		try {
			String sqlEliminar = " DELETE FROM granjas WHERE idGranja = ? ";

			PreparedStatement psElim = con.getConexion().prepareStatement(sqlEliminar);
			psElim.setLong(1, idGranja);
			psElim.executeUpdate();
			psElim.close();

		} catch (SQLException ex) {
			Logger.getLogger(FarmServicio.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	/**
	 * Actualizar datos de la Granja
	 * 
	 * @param granja
	 */
	public void actualizarGranja(Farm granja) {

		try {
			String sqlUpdate = " UPDATE granjas SET cantidadHuevos = ?, "
					+ "cantidadGallinas = ?, importeTotalHuevos = ?, importeTotalGallinas = ?, importeTotalGranja = ? "
					+ " WHERE idGranja = ? ";
			PreparedStatement psUpdate = con.getConexion().prepareStatement(sqlUpdate);
			psUpdate.setInt(1, granja.getCantidadHuevos());
			psUpdate.setInt(2, granja.getCantidadGallinas());
			psUpdate.setBigDecimal(3, granja.getImporteTotalHuevos());
			psUpdate.setBigDecimal(4, granja.getImporteTotalGallinas());
			psUpdate.setBigDecimal(5, granja.getImporteTotalGranja());
			psUpdate.setLong(6, granja.getIdGranja());
			psUpdate.executeUpdate();

			psUpdate.close();

		} catch (SQLException ex) {

			Logger.getLogger(FarmServicio.class.getName()).log(Level.SEVERE, null, ex);

		}

	}

}
