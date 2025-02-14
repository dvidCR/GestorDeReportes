package model;

public class Employes {
	
	private int id_empleado;
	private String nombre;
	private String cargo;
	private String fecha_contratacion;
	
	public Employes() {
		
	}
	
	public Employes(int id_empleado, String nombre, String cargo, String fecha_contratacion) {
		this.id_empleado = id_empleado;
		this.nombre = nombre;
		this.cargo = cargo;
		this.fecha_contratacion = fecha_contratacion;
	}
	
	public Employes(String nombre, String cargo, String fecha_contratacion) {
		this.nombre = nombre;
		this.cargo = cargo;
		this.fecha_contratacion = fecha_contratacion;
	}

	public int getId_empleado() {
		return id_empleado;
	}

	public void setId_empleado(int id_empleado) {
		this.id_empleado = id_empleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getFecha_contratacion() {
		return fecha_contratacion;
	}

	public void setFecha_contratacion(String fecha_contratacion) {
		this.fecha_contratacion = fecha_contratacion;
	}
	
}
