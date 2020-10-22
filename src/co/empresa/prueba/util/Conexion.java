package co.empresa.prueba.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
	private Connection con = null;
	private static Conexion db;
	private PreparedStatement preparedStatement;
	
	private static final String url = "jbdc:mysql://localhost:3306/";
	private static final String dbName = "sistema";
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String userName = "root@localhost";
	private static final String password = "";
	
	public Conexion() {
		try {
			Class.forName(driver).newInstance();
			con = (Connection)DriverManager.getConnection(url+dbName,userName,password);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cerrarConexion(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Conexion getConexion() {
		if ( db == null ) {
            db = new Conexion();
        }
		return db;
	}
	
	//metedo de consultas
	public ResultSet query() throws SQLException{
        ResultSet res = preparedStatement.executeQuery();
        return res;
	}
	//metodo de actualizacion
	public int execute() throws SQLException {
        int result = preparedStatement.executeUpdate();
        return result;
 
    }
	
	public Connection getCon() {
		return this.con;
	}
	
	public PreparedStatement setPreparedStatement(String sql) throws SQLException {
		this.preparedStatement = con.prepareStatement(sql);
		return this.preparedStatement;
	}
	
}
