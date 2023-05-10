/**
 * 
 */
package logic;

/**
 * @author PC
 *
 */
public class Cliente {

	private String identificador;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int puedeJugar;
	
	
	/**
	 * @param identificador
	 * @param nombre
	 * @param apellido1
	 * @param apellido2
	 * @param puedeJugar
	 */
	public Cliente(String identificador, String nombre, String apellido1, String apellido2, int puedeJugar) {
		super();
		this.identificador = identificador;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.puedeJugar = puedeJugar;
	}
	
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public int getPuedeJugar() {
		return puedeJugar;
	}
	public void setPuedeJugar(int puedeJugar) {
		this.puedeJugar = puedeJugar;
	}

	@Override
	public String toString() {
		return identificador + "@" + nombre + " " + apellido1
				+ " " + apellido2 + "@" + puedeJugar;
	}
	
	
	
	
	
	
}
