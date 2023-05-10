/**
 * 
 */
package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author PC
 *
 */
public abstract class FileUtil {

	/**
	 * Carga el fichero de clientes registrados
	 * 
	 * @param nombreFicheroEntrada
	 * @param listaCatalogo
	 */
	public static List<Cliente> loadClients(String nombreFicheroEntrada) {
		String linea;
		String[] datosCliente = null;
		List<Cliente> datos = new ArrayList<>();

		try {
			BufferedReader fichero = new BufferedReader(new FileReader(nombreFicheroEntrada));
			while (fichero.ready()) {
				linea = fichero.readLine();
				datosCliente = linea.split("@");
				// obtenemos el nombre y apellido
				String[] nombreYapellidos = datosCliente[1].split(" ");

				datos.add(new Cliente(datosCliente[0], nombreYapellidos[0], nombreYapellidos[1], nombreYapellidos[2],
						Integer.parseInt(datosCliente[2])));
			}
			fichero.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha encontrado.");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
		return datos;
	}

	/**
	 * Carga del fichero encontrado los regalos
	 * @param ficheroRegalos
	 * @return
	 */
	public static List<Premio> loadRegalos(String ficheroRegalos) {
		String linea;
		String[] datosRegalos = null;
		List<Premio> regalos = new ArrayList<>();
		BufferedReader fichero ;
		try {
			fichero = new BufferedReader(new FileReader(ficheroRegalos));
			while(fichero.ready()) {
				linea = fichero.readLine();
				datosRegalos = linea.split("@");
				regalos.add(new Premio(datosRegalos[0], datosRegalos[1], datosRegalos[2], datosRegalos[3], Integer.parseInt(datosRegalos[4])));
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha encontrado.");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
		
		return regalos;
	}

	/**
	 * Modifica el fichero con la lista de clientes
	 * 
	 * @param nombreFicheroEntrada
	 * @param clientes
	 */
	public static void changeClientState(String nombreFicheroEntrada, List<Cliente> clientes) {
		try {
			BufferedWriter fichero = new BufferedWriter(new FileWriter(nombreFicheroEntrada));
			for (Cliente c : clientes) {
				fichero.write(c.toString() + "\n");
			}
			fichero.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha encontrado.");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
	}

	/**
	 * Guarda a fichero
	 * 
	 * @param nombreFicheroSalida
	 * @param txPedido
	 */
	public static void saveToFile(String nombreFicheroSalida, String txPedido) {
		try {
			String filePedido = "C:\\Pedidos\\" + nombreFicheroSalida + ".dat";
			Path path = Paths.get(filePedido);
			if (!Files.exists(path.getParent())) {
				Files.createDirectory(path.getParent());
			}
			BufferedWriter fichero = new BufferedWriter(new FileWriter(filePedido,true));
			fichero.write(txPedido);
			fichero.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha podido guardar");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida");
		}
	}

	@SuppressWarnings("deprecation")
	public static void saveToFile(String ficheroEntregas, Cliente clienteJugando, List<Premio> cesta, Date date,
			String comentarios) {
		try {
			BufferedWriter fichero = new BufferedWriter(new FileWriter(ficheroEntregas,true));
			for(Premio p : cesta) {
				String datos = clienteJugando.getIdentificador()+"@"+p.getCodigo()+"\n";
				if(p.getSeccion().equals("V")) {
					datos = clienteJugando.getIdentificador()+"@"+p.getCodigo()+"@"+
				date.getDay()+"/"+date.getMonth()+"/"+date.getYear()+"@"+comentarios+"\n";
				}
				fichero.write(datos);
			}
			fichero.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha podido guardar");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida");
		}
	}

	

}
