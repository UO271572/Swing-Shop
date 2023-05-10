/**
 * 
 */
package logic;

/**
 * @author PC
 *
 */
public class Casilla {
	private int puntos;
	private boolean esMultiplicador;
	private boolean esBonus;
	
	/**
	 * @param puntos
	 * @param esMultiplicador
	 */
	public Casilla(int puntos, boolean esMultiplicador,boolean esBonus) {
		setPuntos(puntos);
		setEsMultiplicador(esMultiplicador);
		setEsBonus(esBonus);
	}
	
	public int getPuntos() {
		return puntos;
	}
	private void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public boolean isEsMultiplicador() {
		return esMultiplicador;
	}
	private void setEsMultiplicador(boolean esMultiplicador) {
		this.esMultiplicador = esMultiplicador;
	}

	public boolean isEsBonus() {
		return esBonus;
	}

	private void setEsBonus(boolean esBonus) {
		this.esBonus = esBonus;
	}

	@Override
	public String toString() {
		return "Casilla [puntos=" + puntos + ", esMultiplicador=" + esMultiplicador + ", esBonus=" + esBonus + "]";
	}
	
	
	
}
