package co.empresa.prueba.modelo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingrediente implements Serializable {
	
	private Integer id;
	
	private String nombre;
	
	private String cantidad;
	
	private String precio;
	
	public Ingrediente(String nombre, String cantidad, String precio) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	
	
}