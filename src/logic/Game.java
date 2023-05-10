package logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Game {

	private List<Cliente> clientes = null;
	private List<Premio> premios = null;
	private static final String FICHERO_ARTICULOS = "files/clientes.dat";
	private static final String FICHERO_REGALOS = "files/regalos.dat";
	private static final String FICHERO_ENTREGAS = "files/entregas.dat";
	private Cliente clienteJugando = null;
	private int casillasADescubrir;
	private boolean isFinalizado;
	// private List<Premio> premios;
	private Tablero tablero;
	private int puntos = 0;
	private List<Premio> cesta = null;

	/**
	 * Constructor por defecto
	 */
	public Game() {
		clientes = new ArrayList<>();
		premios = new ArrayList<>();
		loadClientes();
		loadRegalos();
		setCasillasADescubrir(3);
		setFinalizado(false);
		tablero = new Tablero();
		cesta = new ArrayList<>();
	}

	/**
	 * Carga el fichero de premios y lo asigna a game
	 */
	private void loadRegalos() {
		premios = FileUtil.loadRegalos(FICHERO_REGALOS);
	}

	/**
	 * Cargamos los clientes del fichero clientes.dat
	 */
	private void loadClientes() {
		clientes = FileUtil.loadClients(FICHERO_ARTICULOS);
	}

	
	
	
	/**
	 * Devuelve la lista de clientes original
	 * 
	 * @return
	 */
	public List<Cliente> getClientes() {
		return clientes;
	}

	public List<Premio> getCesta() {
		return cesta;
	}

	public Tablero getTablero() {
		return tablero;
	}
	
	/**
	 * Indica si la cesta de regalos del cliente tiene algun tipo de regalo experiencia/viaje
	 * @return
	 */
	public boolean tieneExperiencias() {
		boolean b = false;
		for(Premio p : cesta) {
			if(p.getSeccion().equals("V")) {
				b=true;
				break;
			}
		}
		return b;
	}
	

	/**
	 * Devuelve el int representante del problema o validez del codigo introducido
	 * 
	 * @param input
	 * @return
	 */
	public int identificadorValido(String input) {
		if (clientes.isEmpty()) {
			return -1;
		}
		for (Cliente c : clientes) {
			if (c.getIdentificador().equals(input)) {
				if (c.getPuedeJugar() == 1) {
					// identificador valido y puede jugar y por tanto ahora cambiamos su estado
					setClienteJugando(c);
					// modificar el fichero para que el jugador no pueda volver a jugar
					c.setPuedeJugar(0);
					FileUtil.changeClientState("files/clientes.dat", clientes);
					return 0;
				} else {
					// identificador valido pero no puede acceder a jugar
					return 1;
				}
			}
		}
		//// no se encuentra en la lista
		return 2;
	}

	/**
	 * Destapa una casilla desencadenando los puntos y actualizaciones
	 * 
	 * @param parseInt
	 */
	public void destapar(int posicionDescubierta) {
		// actualizamos la cantidad de casillas que quedan a descubrir
		if (tablero.getCasillas()[posicionDescubierta].isEsBonus()) {
			setCasillasADescubrir(getCasillasADescubrir());
		} else {
			setCasillasADescubrir(getCasillasADescubrir() - 1);
		}
		// actualizar puntos
		if (tablero.getCasillas()[posicionDescubierta].isEsMultiplicador()) {
			puntos = puntos * tablero.getCasillas()[posicionDescubierta].getPuntos();
		} else {
			puntos += tablero.getCasillas()[posicionDescubierta].getPuntos();
		}
	}

	/**
	 * Añade un premio a la cesta de regalos
	 * 
	 * @param p
	 */
	public void añadirACesta(Premio p) {
		Premio pGuardar = null;
		for (Premio pcesta : cesta) {
			if (p.getCodigo().equals(pcesta.getCodigo())) {
				pGuardar = p;
				pcesta.setUnidades(pcesta.getUnidades() + 1);
				break;
			}
		}
		if (pGuardar == null) {
			p.setUnidades(1);
			cesta.add(p);
		}
	}

	/**
	 * Elimina una unidad del premio seleccionado
	 */
	public void eliminarDeCesta(Premio p) {
		for (Premio pcesta : cesta) {
			if (p.getCodigo().equals(pcesta.getCodigo())) {
				if (pcesta.getUnidades() == 0) {
					cesta.remove(pcesta);
				} else {
					pcesta.setUnidades(pcesta.getUnidades() - 1);
					if (pcesta.getUnidades() == 0) {
						cesta.remove(pcesta);
					}
				}
				break;
			}
		}
	}

	/**
	 * Devuelve la cantidad de puntos que hay en la cesta de regalos
	 * @return
	 */
	public int getPuntosCesta() {
		int puntos = 0;
		for (Premio p : cesta) {
			int puntosPremio = p.getPuntos();
			int cantidad = p.getUnidades();
			for (int i = 0; i < cantidad; i++) {
				puntos += puntosPremio;
			}
		}
		return puntos;
	}

	public Cliente getClienteJugando() {
		return clienteJugando;
	}

	public void setClienteJugando(Cliente clienteJugando) {
		this.clienteJugando = clienteJugando;
	}

	public int getCasillasADescubrir() {
		return casillasADescubrir;
	}

	public int getPuntos() {
		return puntos;
	}

	public List<Premio> getPremios() {
		return premios;
	}

	/**
	 * Indica las casillas que quedan por descubrir, si esta llega a 0 el juego
	 * aparecera como finalizado
	 * 
	 * @param casillasADescubrir
	 */
	public void setCasillasADescubrir(int casillasADescubrir) {
		this.casillasADescubrir = casillasADescubrir;
		if (this.casillasADescubrir == 0) {
			setFinalizado(true);
//			System.out.println("El juego ha finalizado, canjea tus premios");
		}
	}

	/**
	 * Retorna si el juego esta finalizado
	 * @return
	 */
	public boolean isFinalizado() {
		return isFinalizado;
	}

	
	public void setFinalizado(boolean isFinalizado) {
		this.isFinalizado = isFinalizado;
	}

	/**
	 * Guarda en fichero los datos del premio de un cliente
	 */
	public void saveToFile(Date date, String txArea) {
		FileUtil.saveToFile(FICHERO_ENTREGAS,clienteJugando,cesta,date,txArea);
	}
	

}
