package co.empresa.prueba.dao;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.empresa.prueba.modelo.Ingrediente;
import co.empresa.prueba.util.Conexion;

public class IngredienteDao {

	private Conexion conexion;

	private static final String INSERT_INGREDIENTE_SQL = "INSERT INTO ingrediente(nombre,cantidad,precio) VALUES (?,?,?);";
	private static final String DELETE_INGREDIENTE_SQL = "DELETE FROM ingrediente WHERE id = ?";
	private static final String UPDATE_INGREDIENTE_SQL = "UPDATE ingrediente SET nombre = ? , cantidad = ?, WHERE precio = ?";
	private static final String SELECT_INGREDIENTE_BY_ID_SQL = "SELECT * FROM ingrediente WHERE id = ?;";
	private static final String SELECT_ALL_INGREDIENTE_SQL = "SELECT * FROM ingrediente WHERE id = ?;";
	
	
	public IngredienteDao() {
		this.conexion = Conexion.getConexion();
	}
	
	public void insert(Ingrediente ingrediente) throws SQLException{
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(INSERT_INGREDIENTE_SQL); 
	        preparedStatement.setString(1, ingrediente.getNombre());
	        preparedStatement.setString(2, ingrediente.getCantidad());
	        preparedStatement.setString(3, ingrediente.getPrecio());
	        conexion.execute();
		} catch (SQLException e) {
			
		}	
	}
	
	public void delete(int id) throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(DELETE_INGREDIENTE_SQL); 
	        preparedStatement.setInt(1, id);
	        conexion.execute();
		} catch (SQLException e) {
			
		}
		
	}
	
	public void update(Ingrediente ingrediente) throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(UPDATE_INGREDIENTE_SQL); 
	        preparedStatement.setString(1, ingrediente.getNombre());
	        preparedStatement.setString(2, ingrediente.getCantidad());
	        preparedStatement.setString(3, ingrediente.getPrecio());
	        preparedStatement.setInt(4, ingrediente.getId());
	        conexion.execute();
		} catch (SQLException e) {
			
		}	
	}
	
	public List<Ingrediente> selectAll(){
		List < Ingrediente > ingredientes = new ArrayList < > ();
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_ALL_INGREDIENTE_SQL);
			ResultSet rs = conexion.query();
			while(rs.next()){
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String cantidad = rs.getString("cantidad");
				String precio = rs.getString("precio");
				ingredientes.add(new Ingrediente(id,nombre,cantidad,precio));
			}
		} catch (SQLException e) {
		
		}
		return ingredientes;
	}
	
	public Ingrediente select(int id){
		Ingrediente ingrediente = null;
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_INGREDIENTE_BY_ID_SQL);
			preparedStatement.setInt(1, id);
			ResultSet rs = conexion.query();
			while(rs.next()){
				String nombre = rs.getString("nombre");
				String cantidad = rs.getString("cantidad");
				String precio = rs.getString("precio");
				ingrediente = (new Ingrediente(id,nombre,cantidad,precio));
			}
		} catch (SQLException e) {
		 
		}
		return ingrediente;
	}
	
	
	
}
	

