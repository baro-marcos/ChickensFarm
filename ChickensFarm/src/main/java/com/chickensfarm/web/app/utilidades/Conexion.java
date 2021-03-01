package com.chickensfarm.web.app.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

	private Connection con;

	public Conexion() {

		try {

			Class.forName("com.mysql.jdbc.Driver");

			String db = "jdbc:mysql://localhost/chickensfarm";

			con = DriverManager.getConnection(db, "admin", "12345");

		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public Connection getConexion() {
		return con;
	}

	public void cerrarConexion() {
		try {
			con.close();
			con = null;
		} catch (SQLException ex) {
			Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
