package com.trascender.gui.framework.principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionReportes {
	private String nombreUsuario;
	private String clave;
	
	private static ConexionReportes instance;
	
	private Connection conexion;
	
	public static ConexionReportes getInstance(){
		if (instance == null){
			instance = new ConexionReportes();
		}
		return instance;
	}
	
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public Connection getConnection() throws SQLException, ClassNotFoundException{
		return this.conexion; 
	}
	
	public void conectar() throws SQLException, ClassNotFoundException{
//		Class.forName("org.firebirdsql.jdbc.FBDriver");
		
//		 CRESPO Prueba
//		this.conexion = DriverManager.getConnection("jdbc:firebirdsql:10.101.106.100/3050:/opt/db/municipalidades.fdb","SYSDBA","masterkey");
		
		// CRESPO Producción
//		this.conexion = DriverManager.getConnection("jdbc:firebirdsql:10.101.106.101/3050:/opt/db/municipalidades.fdb","SYSDBA","masterkey");
//		
		// VICTORIA Prueba
//		this.conexion = DriverManager.getConnection("jdbc:firebirdsql:192.168.0.2/3050:/opt/db/municipalidades.fdb","SYSDBA","masterkey");
		
		
		// TRASCENDER Servidor
//		this.conexion = DriverManager.getConnection("jdbc:firebirdsql:192.168.0.150/3050:/opt/repos/data/municipalidades.fdb","SYSDBA","masterkey");
//		this.conexion = DriverManager.getConnection("jdbc:firebirdsql:192.168.0.108/3050:D:/TRASCENDER/TRASCENDER/BASE_DE_DATOS/MUNI_CRESPO_02-03-2010.FDB","SYSDBA","masterkey");
//		this.conexion = DriverManager.getConnection("jdbc:firebirdsql:192.168.0.111/3050:/opt/db/municipalidades.fdb","SYSDBA","masterkey");
//		this.conexion = DriverManager.getConnection("jdbc:firebirdsql:localhost/3050:/opt/data/municipalidades_II_produccion.fdb","SYSDBA","masterkey");
		
//		this.conexion.setAutoCommit(false);
	}
			
	public void desconectar(){
//		try{
//			this.conexion.close();
//		}
//		catch(SQLException e){
//			e.printStackTrace();
//			//TODO VER AC� QUE HACER
//		}
	}
}
