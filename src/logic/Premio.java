/**
 * 
 */
package logic;

/**
 * @author PC
 *
 */
public class Premio implements Comparable<Premio>{
	private String codigo;
	private String seccion;
	private String denominacion;
	private String descripcion;
	private int puntos;
	private int unidades;

	/**
	 * @param codigo
	 * @param seccion
	 * @param denominacion
	 * @param descripcion
	 * @param puntos
	 */
	public Premio(String codigo, String seccion, String denominacion, String descripcion, int puntos) {
		this.codigo = codigo;
		this.seccion = seccion;
		this.denominacion = denominacion;
		this.descripcion = descripcion;
		this.puntos = puntos;
		this.unidades = 0;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getSeccion() {
		return seccion;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getPuntos() {
		return puntos;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	/**
	 * toString redefinido para algunos elementos solamente
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(getDenominacion() + " - ");
		str.append(getPuntos() + " - ");
		str.append(getCodigo()+ " - ");
		str.append(getUnidades());
		return str.toString();
	}

	@Override
	public int compareTo(Premio o) {
		if(getPuntos() < o.getPuntos()) {
			return -1;
		}else if(getPuntos() == o.getPuntos()) {
			return 0;
		}else {
			return +1;
		}
	}
	
}
