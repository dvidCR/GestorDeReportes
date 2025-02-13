package model;

public class Sales {
	
	private int id_venta;
	private int id_empleado;
	private int id_producto;
	private int cantidad;
	private String fecha_venta;
	private float total_venta;
	
	public Sales() {
		
	}
	
	public Sales(int id_empleado, int id_producto, int cantidad, String fecha_venta, float total_venta) {
		this.id_empleado = id_empleado;
		this.id_producto = id_producto;
		this.cantidad = cantidad;
		this.fecha_venta = fecha_venta;
		this.total_venta = total_venta;
	}

	public int getId_venta() {
		return id_venta;
	}

	public void setId_venta(int id_venta) {
		this.id_venta = id_venta;
	}

	public int getId_empleado() {
		return id_empleado;
	}

	public void setId_empleado(int id_empleado) {
		this.id_empleado = id_empleado;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getFecha_venta() {
		return fecha_venta;
	}

	public void setFecha_venta(String fecha_venta) {
		this.fecha_venta = fecha_venta;
	}

	public float getTotal_venta() {
		return total_venta;
	}

	public void setTotal_venta(float total_venta) {
		this.total_venta = total_venta;
	}

	@Override
	public String toString() {
		return "Sales [id_venta=" + id_venta + ", id_empleado=" + id_empleado + ", id_producto=" + id_producto
				+ ", cantidad=" + cantidad + ", fehca_venta=" + fecha_venta + ", total_venta=" + total_venta + "]";
	}
	
}
