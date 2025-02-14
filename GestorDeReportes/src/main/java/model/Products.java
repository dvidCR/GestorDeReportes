package model;

public class Products {
	
	private int id_producto;
	private String nombre;
	private String categoria;
	private float precio;
	private int stock;
	
	public Products() {
		
	}
	
	public Products(int id_producto, String nombre, String categoria, float precio, int stock) {
		this.id_producto = id_producto;
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
		this.stock = stock;
	}
	
	public Products(String nombre, String categoria, float precio, int stock) {
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
		this.stock = stock;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
