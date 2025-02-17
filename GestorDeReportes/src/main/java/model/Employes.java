package model;

/**
 * Clase modelo de empleados.
 * 
 * @author David Casado
 */

public class Employes {
	
	private int id_empleado;
	private String nombre;
	private String cargo;
	private String fecha_contratacion;
	
	/**
	 * Constructor por defecto.
	 */
	public Employes() {
		
	}
	
	/**
	 * Constructor completo.
	 * 
	 * @param id_empleado
	 * @param nombre
	 * @param cargo
	 * @param fecha_contratacion
	 */
	public Employes(int id_empleado, String nombre, String cargo, String fecha_contratacion) {
		this.id_empleado = id_empleado;
		this.nombre = nombre;
		this.cargo = cargo;
		this.fecha_contratacion = fecha_contratacion;
	}
	
	/**
	 * Constructor pero sin pedir el ID
	 * 
	 * @param nombre
	 * @param cargo
	 * @param fecha_contratacion
	 */
	public Employes(String nombre, String cargo, String fecha_contratacion) {
		this.nombre = nombre;
		this.cargo = cargo;
		this.fecha_contratacion = fecha_contratacion;
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
	
	/**´
	 * 
	 * @return String
	 */
	public String getCargo() {
		return cargo;
	}
	
	/**
	 * 
	 * @param cargo
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	/**
	 * 
	 * @return String
	 */
	public String getFecha_contratacion() {
		return fecha_contratacion;
	}
	
	/**
	 * 
	 * @param fecha_contratacion
	 */
	public void setFecha_contratacion(String fecha_contratacion) {
		this.fecha_contratacion = fecha_contratacion;
	}
	
}
