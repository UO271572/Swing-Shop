package igu;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Color;

public class VentanaInicioSesion extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txIdentificador;
	private JButton btValidar;
	private JButton btCancelar;
	private JLabel lbImagen;


	private VentanaInicial ventanaInicial;
	
	/**
	 * Create the dialog.
	 */
	public VentanaInicioSesion(VentanaInicial ventanaInicial) {
		this.ventanaInicial = ventanaInicial;
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaInicioSesion.class.getResource("/img/arbolnavidad.jpg")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setTitle("Christmas sales");
		setBounds(100, 100, 547, 302);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel pnPrincipal = new JPanel();
			pnPrincipal.setBackground(Color.WHITE);
			getContentPane().add(pnPrincipal, BorderLayout.CENTER);
			pnPrincipal.setLayout(null);
			{
				JLabel lbIniciaSesion = new JLabel("Introduce tu identificador:");
				lbIniciaSesion.setBounds(70, 80, 240, 32);
				pnPrincipal.add(lbIniciaSesion);
			}
			pnPrincipal.add(getTxIdentificador());
			pnPrincipal.add(getBtValidar());
			pnPrincipal.add(getBtCancelar());
			pnPrincipal.add(getLbImagen());
		}
	}
	
	
	
	public VentanaInicial getVentanaInicial() {
		return ventanaInicial;
	}

	private JTextField getTxIdentificador() {
		if (txIdentificador == null) {
			txIdentificador = new JTextField();
			txIdentificador.setBounds(70, 111, 221, 32);
			txIdentificador.setColumns(10);
		}
		return txIdentificador;
	}
	private JButton getBtValidar() {
		if (btValidar == null) {
			btValidar = new JButton("Validar");
			btValidar.setMnemonic('V');
			btValidar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					comprobarCampo();
					//mostrarPanelDeJuego();
				}
			});
			btValidar.setBounds(442, 239, 89, 23);
		}
		return btValidar;
	}
	
	/**
	 * Comprueba que el identificador sea valido, en caso contrario se indicara un mensaje de error
	 */
	private void comprobarCampo() {
		String input = getTxIdentificador().getText();
		switch (getVentanaInicial().getGame().identificadorValido(input)) {
		case -1:
			JOptionPane.showMessageDialog(null, "Ocurrio un error inesperado compruebe los ficheros de clientes", null, 1);
			break;
		case 0: 
			mostrarPanelDeJuego();
			break;
		case 1:
			JOptionPane.showMessageDialog(null, "Te has logueado! Pero no puedes jugar ahora", null, 1);
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "No estas registrado, ve al puesto para hacer tutarjeta de socio", null, 1);
			break;	
		}
	}
	
	
	
	/**
	 * Muestra la siguiente ventana
	 */
	private void mostrarPanelDeJuego() {
		this.dispose();
		PanelDeJuego juego = new PanelDeJuego(this);
		juego.setLocationRelativeTo(this);
		juego.setVisible(true);
	}

	
	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("Cancelar");
			btCancelar.setMnemonic('C');
			btCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btCancelar.setBounds(343, 239, 89, 23);
		}
		return btCancelar;
	}
	private JLabel getLbImagen() {
		if (lbImagen == null) {
			lbImagen = new JLabel("");
			lbImagen.setBounds(343, 11, 188, 217);
			setImagenAdaptada(lbImagen,"/img/arbolnavidad.jpg");
		}
		return lbImagen;
	}
	
	/**
	 * Metodo para rescalar la imagen
	 * @param label
	 * @param rutaImagen
	 */
	private void setImagenAdaptada(JLabel label, String rutaImagen){
		 Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage(); 
		 Image imgEscalada = imgOriginal.getScaledInstance(label.getWidth(),label.getHeight(), Image.SCALE_FAST);
		 ImageIcon icon = new ImageIcon(imgEscalada);
		 label.setIcon(icon);
	}
	
}

