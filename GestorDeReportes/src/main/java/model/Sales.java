package model;

/**
 * Clase modelo para las ventas.
 * 
 * @author David Casado
 */
public class Sales {
	
	private int id_venta;
	private int id_empleado;
	private int id_producto;
	private int cantidad;
	private String fecha_venta;
	private float total_venta;
	
	/**
	 * Constructor por defecto.
	 */
	public Sales() {
		
	}
	
	/**
	 * Constructor completo.
	 * 
	 * @param id_venta
	 * @param id_empleado
	 * @param id_producto
	 * @param cantidad
	 * @param fecha_venta
	 * @param total_venta
	 */
	public Sales(int id_venta, int id_empleado, int id_producto, int cantidad, String fecha_venta, float total_venta) {
		this.id_venta = id_venta;
		this.id_empleado = id_empleado;
		this.id_producto = id_producto;
		this.cantidad = cantidad;
		this.fecha_venta = fecha_venta;
		this.total_venta = total_venta;
	}
	
	/**
	 * Constructor pero sin pedir el ID.
	 * 
	 * @param id_empleado
	 * @param id_producto
	 * @param cantidad
	 * @param fecha_venta
	 * @param total_venta
	 */
	public Sales(int id_empleado, int id_producto, int cantidad, String fecha_venta, float total_venta) {
		this.id_empleado = id_empleado;
		this.id_producto = id_producto;
		this.cantidad = cantidad;
		this.fecha_venta = fecha_venta;
		this.total_venta = total_venta;
	}

	/**
	 * 
	 * @return int
	 */
	public int getId_venta() {
		return id_venta;
	}
	
	/**
	 * 
	 * @param id_venta
	 */
	public void setId_venta(int id_venta) {
		this.id_venta = id_venta;
	}
	
	/**
	 * 
	 * @return int
	 */
	public int getId_empleado() {
		return id_empleado;
	}

	/**
	 * 
	 * @param id_empleado
	 */
	public void setId_empleado(int id_empleado) {
		this.id_empleado = id_empleado;
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
	 * @return int
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * 
	 * @param cantidad
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * 
	 * @return String
	 */
	public String getFecha_venta() {
		return fecha_venta;
	}

	/**
	 * 
	 * @param fecha_venta
	 */
	public void setFecha_venta(String fecha_venta) {
		this.fecha_venta = fecha_venta;
	}

	/**
	 * 
	 * @return float
	 */
	public float getTotal_venta() {
		return total_venta;
	}

	/**
	 * 
	 * @param total_venta
	 */
	public void setTotal_venta(float total_venta) {
		this.total_venta = total_venta;
	}
	
}
