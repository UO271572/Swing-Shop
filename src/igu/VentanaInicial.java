package igu;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Game;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaInicial extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnCentro;
	private JLabel lbBienvenida;
	private JLabel lbMensaje;
	private JLabel lbImagen;

	private Game game;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenu mnNewMenu_1;
	private JMenuItem mnContenidos;
	private JMenuItem mntmNewMenuItem;
	

	/**
	 * Create the frame.
	 */
	public VentanaInicial(Game game) {
		setMinimumSize(new Dimension(300, 300));
		this.game = game;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaInicial.class.getResource("/img/arbolnavidad.jpg")));
		setResizable(false);
		setTitle("Christmas sales");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 407);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnCentro(), BorderLayout.CENTER);
		setLocationRelativeTo(null);
		
		cargaAyuda();
	}

	
	
	public Game getGame() {
		return game;
	}
	
	
	/**
	 * Carga la ayuda del sistema
	 */
	private void cargaAyuda() {

		URL hsURL;
		HelpSet hs;

		try {
			File fichero = new File("help/ayuda.hs");
			hsURL = fichero.toURI().toURL();
			hs = new HelpSet(null, hsURL);
		} catch (Exception e) {
			System.out.println("Ayuda no encontrada");
			return;
		}
		HelpBroker hb = hs.createHelpBroker();
		hb.initPresentation();

		//abre sobre el componente en el que se encuentre, muestra la pagina de ayuda que se va a mostrar
		hb.enableHelpKey(getRootPane(), "descripcion", hs);
		//para el menu 
		hb.enableHelpOnButton(mnContenidos, "descripcion", hs);
//		 //abre sobre el componente en el que se encuentre, muestra la pagina de ayuda que se va a mostrar
//		 hb.enableHelp(listlibrary, "añadir", hs);
	}
	



	private JPanel getPnCentro() {
		if (pnCentro == null) {
			pnCentro = new JPanel();
			pnCentro.setToolTipText("");
			pnCentro.setBackground(Color.WHITE);
			pnCentro.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					mostrarVentanaInicioSesion();
				}
			});
			pnCentro.setLayout(null);
			pnCentro.add(getLbBienvenida());
			pnCentro.add(getLbMensaje());
			pnCentro.add(getLbImagen());
		}
		return pnCentro;
	}
	
	/**
	 * Muestra la siguiente ventana (inicio de sesion)
	 */
	private void mostrarVentanaInicioSesion() {
		//this.setVisible(false);
		VentanaInicioSesion inicioSesion = new VentanaInicioSesion(this);
		inicioSesion.setLocationRelativeTo(this);
		inicioSesion.setModal(true);
		inicioSesion.setVisible(true);
	}
	
	private JLabel getLbBienvenida() {
		if (lbBienvenida == null) {
			lbBienvenida = new JLabel("Bienvenido al juego!");
			lbBienvenida.setFont(new Font("Papyrus", Font.BOLD, 30));
			lbBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
			lbBienvenida.setBounds(231, 11, 307, 69);
		}
		return lbBienvenida;
	}
	private JLabel getLbMensaje() {
		if (lbMensaje == null) {
			lbMensaje = new JLabel("Haz click en la pantalla para continuar !");
			lbMensaje.setBackground(new Color(240, 240, 240));
			lbMensaje.setHorizontalAlignment(SwingConstants.CENTER);
			lbMensaje.setFont(new Font("Papyrus", Font.PLAIN, 26));
			lbMensaje.setBounds(63, 299, 642, 42);
		}
		return lbMensaje;
	}
	private JLabel getLbImagen() {
		if (lbImagen == null) {
			lbImagen = new JLabel("");
			lbImagen.setBounds(0, 75, 769, 293);
			//lbImagen.setIcon(new ImageIcon(VentanaInicial.class.getResource("/img/pngegg.png")));
			setImagenAdaptada(lbImagen,"/img/pngegg.png");
			
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
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnNewMenu());
			menuBar.add(getMnNewMenu_1());
		}
		return menuBar;
	}
	private JMenu getMnNewMenu() {
		if (mnNewMenu == null) {
			mnNewMenu = new JMenu("Archivo");
			mnNewMenu.setMnemonic('A');
			mnNewMenu.add(getMntmNewMenuItem());
		}
		return mnNewMenu;
	}
	private JMenu getMnNewMenu_1() {
		if (mnNewMenu_1 == null) {
			mnNewMenu_1 = new JMenu("Ayuda");
			mnNewMenu_1.setMnemonic('y');
			mnNewMenu_1.add(getMnContenidos());
		}
		return mnNewMenu_1;
	}
	private JMenuItem getMnContenidos() {
		if (mnContenidos == null) {
			mnContenidos = new JMenuItem("Contenidos");
			mnContenidos.setMnemonic('C');
			mnContenidos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		}
		return mnContenidos;
	}

	private JMenuItem getMntmNewMenuItem() {
		if (mntmNewMenuItem == null) {
			mntmNewMenuItem = new JMenuItem("Salir");
			mntmNewMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			mntmNewMenuItem.setMnemonic('S');
			mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK ));
		}
		return mntmNewMenuItem;
	}
}
