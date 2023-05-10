package igu;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import events.EventoClickPanel;
import logic.Game;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class PanelDeJuego extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnCentral;
	private JPanel pnJuego;
	private JPanel pnPanelJuego;
	private JPanel pnPuntos;
	private JPanel pnNorte;
	private JPanel pnCentro;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lbPuntos;
	private JTextField txPuntos;
	private JLabel lblNewLabel_3;
	private JButton btCanjear;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	// Evento para click en el panel de juego
	private EventoClickPanel eventoCkickPanel;
	private VentanaInicioSesion inicioSesion;

	/**
	 * Create the frame.
	 */
	public PanelDeJuego(VentanaInicioSesion inicioSesion) {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				pintarInterrogantes();
			}

		});
		setMinimumSize(new Dimension(400, 400));
		this.inicioSesion = inicioSesion;
		this.eventoCkickPanel = new EventoClickPanel(inicioSesion.getVentanaInicial().getGame(), this);

		setIconImage(Toolkit.getDefaultToolkit().getImage(PanelDeJuego.class.getResource("/img/arbolnavidad.jpg")));
		setTitle("Chistmas sales");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 807, 574);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnCentral(), BorderLayout.CENTER);
	}

	private JPanel getPnCentral() {
		if (pnCentral == null) {
			pnCentral = new JPanel();
			pnCentral.setBackground(Color.WHITE);
			pnCentral.setLayout(new BorderLayout(0, 0));
			pnCentral.add(getPnJuego(), BorderLayout.CENTER);
		}
		return pnCentral;
	}

	private JPanel getPnJuego() {
		if (pnJuego == null) {
			pnJuego = new JPanel();
			pnJuego.setBackground(Color.WHITE);
			pnJuego.setLayout(new BorderLayout(0, 0));
			pnJuego.add(getPnPanelJuego(), BorderLayout.CENTER);
			pnJuego.add(getPnPuntos(), BorderLayout.EAST);
		}
		return pnJuego;
	}

	private JPanel getPnPanelJuego() {
		if (pnPanelJuego == null) {
			pnPanelJuego = new JPanel();
			pnPanelJuego.setLayout(new GridLayout(5, 5, 0, 0));
			iniciarTableroDeJuego();
		}
		return pnPanelJuego;
	}

	/**
	 * Crea los botones que permitira interactuar con el tablero
	 */
	private void iniciarTableroDeJuego() {
		getPnPanelJuego().removeAll();
		for (int i = 0; i < 25; i++) {
			JButton boton = crearBoton(i);
			getPnPanelJuego().add(boton);
		}
		validate();	
	}
	

	/**
	 * Reescala las imagenes al cambiar el tamaño de la ventana
	 */
	private void pintarInterrogantes() {
		Game game = getInicioSesion().getVentanaInicial().getGame();
		for (int i = 0; i < 25; i++) {
			JButton bt = (JButton) getPnPanelJuego().getComponents()[i];
			if (bt.isEnabled() == false) {
				if (!game.getTablero().getCasillas()[i].isEsBonus()
						&& !game.getTablero().getCasillas()[i].isEsMultiplicador()) {
					setImagenAdaptada(bt, "/img/" + game.getTablero().getCasillas()[i].getPuntos() + ".png");
				} else if (game.getTablero().getCasillas()[i].isEsBonus()) {
					setImagenAdaptada(bt, "/img/bonus.png");
				} else if (game.getTablero().getCasillas()[i].isEsMultiplicador()) {
					setImagenAdaptada(bt, "/img/multiplicador.png");
				} 
			}else {
				setImagenAdaptada(bt, "/img/interrogacion.png");
			}
		}
	}

	/**
	 * Crea un boton
	 * 
	 * @param i
	 * @return
	 */
	private JButton crearBoton(int i) {
		JButton b = new JButton("");
		b.setActionCommand(Integer.toString(i));
		// AÑADIR EL LISTENER
		b.setMnemonic('B');
		b.addActionListener(eventoCkickPanel);
		b.setEnabled(true);
		b.setBackground(Color.ORANGE);
		return b;
	}
	
	private void setImagenAdaptada(JButton boton, String rutaImagen){
		 Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage(); 
		 Image imgEscalada = imgOriginal.getScaledInstance(boton.getWidth(),boton.getHeight(), Image.SCALE_FAST);
		 ImageIcon icon = new ImageIcon(imgEscalada);
		 boton.setIcon(icon);
	}
	
	

	private JPanel getPnPuntos() {
		if (pnPuntos == null) {
			pnPuntos = new JPanel();
			pnPuntos.setBackground(Color.WHITE);
			pnPuntos.setLayout(new BorderLayout(0, 0));
			pnPuntos.add(getPnNorte(), BorderLayout.NORTH);
			pnPuntos.add(getPnCentro(), BorderLayout.CENTER);
		}
		return pnPuntos;
	}

	private JPanel getPnNorte() {
		if (pnNorte == null) {
			pnNorte = new JPanel();
			pnNorte.setBackground(Color.WHITE);
			pnNorte.setLayout(new GridLayout(0, 1, 0, 20));
			pnNorte.add(getLblNewLabel());
			pnNorte.add(getLblNewLabel_1());
			pnNorte.add(getLbPuntos());
			pnNorte.add(getTxPuntos());
		}
		return pnNorte;
	}

	private JPanel getPnCentro() {
		if (pnCentro == null) {
			pnCentro = new JPanel();
			pnCentro.setBackground(Color.WHITE);
			pnCentro.setLayout(new GridLayout(0, 1, 0, 0));
			pnCentro.add(getLblNewLabel_3());
			pnCentro.add(getBtCanjear());
			pnCentro.add(getLblNewLabel_4());
			pnCentro.add(getLblNewLabel_5());
		}
		return pnCentro;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
		}
		return lblNewLabel;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("");
		}
		return lblNewLabel_1;
	}

	private JLabel getLbPuntos() {
		if (lbPuntos == null) {
			lbPuntos = new JLabel("Puntos actuales:");
			lbPuntos.setFont(new Font("Tahoma", Font.BOLD, 11));
		}
		return lbPuntos;
	}

	public JTextField getTxPuntos() {
		if (txPuntos == null) {
			txPuntos = new JTextField();
			txPuntos.setHorizontalAlignment(SwingConstants.CENTER);
			txPuntos.setText("0");
			txPuntos.setEditable(false);
			txPuntos.setColumns(10);
		}
		return txPuntos;
	}

	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("");
		}
		return lblNewLabel_3;
	}

	public JButton getBtCanjear() {
		if (btCanjear == null) {
			btCanjear = new JButton("Canjear");
			btCanjear.setToolTipText("Canjea tus puntos obtenidos");
			btCanjear.setMnemonic('C');
			btCanjear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVentanaPremios();
				}
			});
			btCanjear.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return btCanjear;
	}

	/**
	 * Comprueba la finalizacion del juego y muestra la siguiente ventana de premios
	 */
	private void mostrarVentanaPremios() {
		if (inicioSesion.getVentanaInicial().getGame().isFinalizado()) {
			VentanaPremios premios = new VentanaPremios(this);
			premios.setLocationRelativeTo(this);
			premios.setVisible(true);
		}else {
			int dialogButton = JOptionPane.showConfirmDialog(null,
					"¿Seguro que quieres canjear ya tus premios?\n!El juego aun no ha acabado!", "WARNING",
					JOptionPane.YES_NO_OPTION);
			if (dialogButton == JOptionPane.YES_OPTION) {
				inicioSesion.getVentanaInicial().getGame().setFinalizado(true);
				mostrarVentanaPremios();
			}
		}
	}

	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("");
		}
		return lblNewLabel_4;
	}

	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("");
		}
		return lblNewLabel_5;
	}

	public VentanaInicioSesion getInicioSesion() {
		return inicioSesion;
	}
	
}
