/**
 * 
 */
package logic;

/**
 * @author PC
 *
 */
public class Tablero {
	private Casilla[] casillas;

	/**
	 * Constructor
	 */
	public Tablero() {
		casillas = new Casilla[25];
		crearCasillas();
		System.out.println(toString());
	}

	public Casilla[] getCasillas() {
		return casillas;
	}

//	private void setCasillas(Casilla[] casillas) {
//		this.casillas = casillas;
//	}

	/**
	 * Creamos las casillas del juego con sus puntos y caracteristicas
	 */
	private void crearCasillas() {
		creaCasilla(1000, false, false, 1);
		creaCasilla(250, false, false, 5);// 5 casillas de 250ptos
		creaCasilla(50, false, false, 8);
		creaCasilla(2, true, false, 1);// casilla multiplicadora
		creaCasilla(0, false, true, 2);// casillas bonus
		creaCasilla(0, false, false, 8); // casillas sin puntos
	}

	/**
	 * Metodo recursivo que crea las casillas
	 * 
	 * @param puntos
	 * @param multiplicador
	 * @param bonus
	 * @param cantidad
	 */
	private void creaCasilla(int puntos, boolean multiplicador, boolean bonus, int cantidad) {
		if (cantidad > 0) {
			int posicion = (int) (Math.random() * 25);
			while (casillas[posicion] != null) {
				posicion = (int) (Math.random() * 25);
			}
			casillas[posicion] = new Casilla(puntos, multiplicador, bonus);
			// recursivo, disminuimos la cantidad de casillas a hacer
			creaCasilla(puntos, multiplicador, bonus, --cantidad);
		}
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		int i = 0;
		for (Casilla c : casillas) {
			str.append("Posicion " + i +" " + c.toString());
			str.append("\n");
			i++;
		}
		return str.toString();
	}

}
