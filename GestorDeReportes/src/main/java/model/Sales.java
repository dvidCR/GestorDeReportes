package model;

public class Sales {
	
	private int id_venta;
	private int id_empleado;
	private int id_producto;
	private int cantidad;
	private String fehca_venta;
	private float total_venta;
	
	public Sales() {
		
	}
	
	public Sales(int id_empleado, int id_producto, int cantidad, String fehca_venta, float total_venta) {
		this.id_empleado = id_empleado;
		this.id_producto = id_producto;
		this.cantidad = cantidad;
		this.fehca_venta = fehca_venta;
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

	public String getFehca_venta() {
		return fehca_venta;
	}

	public void setFehca_venta(String fehca_venta) {
		this.fehca_venta = fehca_venta;
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
				+ ", cantidad=" + cantidad + ", fehca_venta=" + fehca_venta + ", total_venta=" + total_venta + "]";
	}
	
}
