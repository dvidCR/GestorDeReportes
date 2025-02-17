package model;

/**
 * Clase modelo de los productos.
 * 
 * @author David Casado
 */
public class Products {
	
	private int id_producto;
	private String nombre;
	private String categoria;
	private float precio;
	private int stock;
	
	/**
	 * Constructor por defecto.
	 */
	public Products() {
		
	}
	
	/**
	 * Constructor completo.
	 * 
	 * @param id_producto
	 * @param nombre
	 * @param categoria
	 * @param precio
	 * @param stock
	 */
	public Products(int id_producto, String nombre, String categoria, float precio, int stock) {
		this.id_producto = id_producto;
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
		this.stock = stock;
	}
	
	/**
	 * Constructor pero sin pedir el ID.
	 * 
	 * @param nombre
	 * @param categoria
	 * @param precio
	 * @param stock
	 */
	public Products(String nombre, String categoria, float precio, int stock) {
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
		this.stock = stock;
	}
	
	/**
	 * 
	 * @return int
	 */
	public int getId_producto() {
		return id_producto;
	}
	
	/**
	 * 
	 * @param id_producto
	 */
	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}
	
	/**
	 * 
	 * @return String
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * 
	 * @return String
	 */
	public String getCategoria() {
		return categoria;
	}
	
	/**
	 * 
	 * @param categoria
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	/**
	 * 
	 * @return float
	 */
	public float getPrecio() {
		return precio;
	}
	
	/**
	 * 
	 * @param precio
	 */
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	/**
	 * 
	 * @return int
	 */
	public int getStock() {
		return stock;
	}
	
	/**
	 * 
	 * @param stock
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
