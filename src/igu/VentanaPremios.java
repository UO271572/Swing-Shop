package igu;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Game;
import logic.Premio;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import events.EventoClickArticulo;
import events.EventoClickRbt;

import java.awt.Toolkit;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ButtonGroup;

public class VentanaPremios extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private PanelDeJuego panelDeJuego;
	private JPanel panel;
	private JPanel pnSur;
	private JButton btConfirmar;
	private JPanel pnArticulo;
	private JPanel pnCesta;
	private JPanel pnTienda;
	private JPanel pnImagen;
	private JPanel pnDescripcion;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lbImagen;
	private JLabel lbNombreCoste;
	private JLabel lbCoste;
	private JLabel lbNombreArticulo;
	private JScrollPane scrollPane;
	private JTextArea txDescripcion;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JButton btAñadir;
	private JButton btEliminar;
	private JPanel panel_6;
	private JLabel lbValor;
	private JTextField txValorCesta;
	private JPanel panel_7;
	private JScrollPane scCesta;
	private JList<Premio> listCesta;
	private JPanel pnOrdenarPrecio;
	private JRadioButton rdbtnOrdenarPrecio;
	private JPanel pnArticulos;
	private JPanel pnPuntosRestantes;
	private JLabel lbTxPuntosRestantes;
	private JTextField txPuntosRestantes;

	private DefaultListModel<Premio> modelArticulos;
	private DefaultListModel<Premio> modelCesta;

	private Game game;
	private JPanel panelRbt;
	private JPanel panel_9;
	private JRadioButton rbTodo;
	private JRadioButton rbAlimentos;
	private JRadioButton rbJuegos;
	private JRadioButton rbViajes;
	private JRadioButton rbElectronica;
	private JRadioButton rbDeportes;
	private JScrollPane scrollPane_1;
	private JList<Premio> listArticulos;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public VentanaPremios(PanelDeJuego panelDeJuego) {
		this.game = panelDeJuego.getInicioSesion().getVentanaInicial().getGame();
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
//				rescalarImagen();
			}

		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPremios.class.getResource("/img/arbolnavidad.jpg")));
		setTitle("Christmas sales");
		setMinimumSize(new Dimension(950, 600));
		this.panelDeJuego = panelDeJuego;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1091, 615);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel(), BorderLayout.CENTER);

		cargaInicial();
		new EventoClickRbt(this);
		new EventoClickArticulo(this);
	}

	private void cargaInicial() {
		getTxPuntosRestantes()
				.setText(String.valueOf(panelDeJuego.getInicioSesion().getVentanaInicial().getGame().getPuntos()));
		getTxValorCesta().setText("0");

		// CARGAR ARTICULOS A LAS LISTAS
		cargarListas();

		// carga inicial del primer premio
		cargaPremio(getLbImagen(), "A01", 0);
	}

	/**
	 * Carga los valores del premio a los elementos visuales
	 * 
	 * @param lbImagen2
	 * @param string
	 * @param i
	 */
	private void cargaPremio(JLabel lbdestino, String nombreImagen, int i) {
		List<Premio> premios = panelDeJuego.getInicioSesion().getVentanaInicial().getGame().getPremios();
		setImagenAdaptada(lbdestino, "/img/" + nombreImagen + ".png");
		getLbCoste().setText(String.valueOf(premios.get(i).getPuntos()));
		getLbNombreArticulo().setText(premios.get(i).getDenominacion());
		getTxDescripcion().setText(premios.get(i).getDescripcion());
	}

	/**
	 * CARGAR ARTICULOS A LAS LISTAS
	 */
	private void cargarListas() {
		// eliminamos datos por si tuviesen datos inecesarios
		modelArticulos.removeAllElements();

		List<Premio> premios = getPanelDeJuego().getInicioSesion().getVentanaInicial().getGame().getPremios();
		for (Premio p : premios) {
			modelArticulos.addElement(p);
		}
	}

	/**
	 * Metodo para rescalar la imagen
	 * 
	 * @param label
	 * @param rutaImagen
	 */
	private void setImagenAdaptada(JLabel label, String rutaImagen) {
		Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
		Image imgEscalada = imgOriginal.getScaledInstance(250, 300, Image.SCALE_FAST);
		ImageIcon icon = new ImageIcon(imgEscalada);
		label.setIcon(icon);
	}

	public PanelDeJuego getPanelDeJuego() {
		return panelDeJuego;
	}

	public DefaultListModel<Premio> getModelArticulos() {
		return modelArticulos;
	}
	

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getPnSur(), BorderLayout.SOUTH);
			panel.add(getPnArticulo(), BorderLayout.CENTER);
			panel.add(getPnCesta(), BorderLayout.EAST);
			panel.add(getPnTienda(), BorderLayout.WEST);
		}
		return panel;
	}

	private JPanel getPnSur() {
		if (pnSur == null) {
			pnSur = new JPanel();
			pnSur.setBackground(Color.WHITE);
			pnSur.setLayout(new BorderLayout(0, 0));
			pnSur.add(getBtConfirmar(), BorderLayout.EAST);
		}
		return pnSur;
	}

	private JButton getBtConfirmar() {
		if (btConfirmar == null) {
			btConfirmar = new JButton("Confirmar");
			btConfirmar.setToolTipText("Confirma la cesta");
			btConfirmar.setMnemonic('f');
			btConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarConfirmacionOFecha();
				}
			});
		}
		return btConfirmar;
	}

	/**
	 * Mostrar ventana de escoger fecha para los viajes si tuviese, o mostrar
	 * ventana de confirmacion de regalos. Avisa al usuario de que quiere hacer
	 * dependiendo de los puntos que va a canjear
	 */
	private void mostrarConfirmacionOFecha() {
		if (game.getPuntos() - game.getPuntosCesta() < 0) {
			JOptionPane.showMessageDialog(null,
					"No puedes canjear tus regalos, sobrepasas los puntos conseguidos.\nElimina algun producto.", null,
					1);
		} else if (game.getPuntos() - game.getPuntosCesta() > 0) {
			int dialogButton = JOptionPane.showConfirmDialog(null,
					"Aun te sobran algunos puntos.\n¿Seguro que quieres canjear ya tus premios?", "WARNING",
					JOptionPane.YES_NO_OPTION);
			if (dialogButton == JOptionPane.YES_OPTION) {
				mostrarConfirmacion();
			}
		} else if (game.getPuntos() - game.getPuntosCesta() == 0) {
			mostrarConfirmacion();
		}
	}

	/**
	 * Muestra la ventana de confirmacion
	 */
	private void mostrarConfirmacion() {
		VentanaConfirmacion vConfirmacion = new VentanaConfirmacion(this);
		// comprobar que tenga premio de experiencias para activar el panel debido
		if (tieneExperiencias()) {
			vConfirmacion.getPanelExtra().setVisible(true);
		}

		vConfirmacion.setLocationRelativeTo(this);
		vConfirmacion.setModal(true);
		vConfirmacion.setVisible(true);
	}

	/**
	 * Comprueba que la cesta del cliente tenga viajes o experiencias
	 * 
	 * @return
	 */
	private boolean tieneExperiencias() {
		return getGame().tieneExperiencias();
	}

	private JPanel getPnArticulo() {
		if (pnArticulo == null) {
			pnArticulo = new JPanel();
			pnArticulo.setBackground(Color.WHITE);
			pnArticulo.setBorder(new TitledBorder(
					new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
					"Articulo seleccionado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnArticulo.setLayout(new BorderLayout(0, 0));
			pnArticulo.add(getPnImagen(), BorderLayout.WEST);
			pnArticulo.add(getPnDescripcion());
		}
		return pnArticulo;
	}

	private JPanel getPnCesta() {
		if (pnCesta == null) {
			pnCesta = new JPanel();
			pnCesta.setBackground(Color.WHITE);
			pnCesta.setBorder(new TitledBorder(null, "Cesta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnCesta.setLayout(new GridLayout(2, 0, 0, 0));
			pnCesta.add(getPanel_3());
			pnCesta.add(getPanel_4());
		}
		return pnCesta;
	}

	private JPanel getPnTienda() {
		if (pnTienda == null) {
			pnTienda = new JPanel();
			pnTienda.setBackground(Color.WHITE);
			pnTienda.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Premios", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnTienda.setLayout(new BorderLayout(0, 0));
			pnTienda.add(getPnOrdenarPrecio(), BorderLayout.NORTH);
			pnTienda.add(getPnArticulos(), BorderLayout.CENTER);
			pnTienda.add(getPnPuntosRestantes(), BorderLayout.SOUTH);
		}
		return pnTienda;
	}

	private JPanel getPnImagen() {
		if (pnImagen == null) {
			pnImagen = new JPanel();
			pnImagen.setLayout(new BorderLayout(0, 0));
			pnImagen.add(getPanel_1(), BorderLayout.NORTH);
			pnImagen.add(getPanel_2(), BorderLayout.CENTER);
		}
		return pnImagen;
	}

	private JPanel getPnDescripcion() {
		if (pnDescripcion == null) {
			pnDescripcion = new JPanel();
			pnDescripcion.setLayout(new BorderLayout(0, 0));
			pnDescripcion.add(getLbNombreArticulo(), BorderLayout.NORTH);
			pnDescripcion.add(getScrollPane());
		}
		return pnDescripcion;
	}

	public Game getGame() {
		return game;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new BorderLayout(0, 0));
			panel_1.add(getLbImagen(), BorderLayout.NORTH);
		}
		return panel_1;
	}

	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBackground(Color.WHITE);
			panel_2.add(getLbNombreCoste());
			panel_2.add(getLbCoste());
		}
		return panel_2;
	}

	public JLabel getLbImagen() {
		if (lbImagen == null) {
			lbImagen = new JLabel("");
			lbImagen.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		}
		return lbImagen;
	}

	private JLabel getLbNombreCoste() {
		if (lbNombreCoste == null) {
			lbNombreCoste = new JLabel("Coste:");
		}
		return lbNombreCoste;
	}

	public JLabel getLbCoste() {
		if (lbCoste == null) {
			lbCoste = new JLabel("");
			lbCoste.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbCoste;
	}

	public JLabel getLbNombreArticulo() {
		if (lbNombreArticulo == null) {
			lbNombreArticulo = new JLabel("NombreArticulo");
		}
		return lbNombreArticulo;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setViewportView(getTxDescripcion());
		}
		return scrollPane;
	}

	public JTextArea getTxDescripcion() {
		if (txDescripcion == null) {
			txDescripcion = new JTextArea();
			txDescripcion.setEditable(false);
		}
		return txDescripcion;
	}

	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setLayout(new BorderLayout(0, 0));
			panel_3.add(getPanel_6(), BorderLayout.NORTH);
			panel_3.add(getPanel_7(), BorderLayout.CENTER);
		}
		return panel_3;
	}

	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setBackground(Color.WHITE);
			panel_4.setLayout(new BorderLayout(0, 0));
			panel_4.add(getPanel_5(), BorderLayout.NORTH);
		}
		return panel_4;
	}

	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.setBackground(Color.WHITE);
			panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panel_5.add(getBtAñadir());
			panel_5.add(getBtEliminar());
		}
		return panel_5;
	}

	private JButton getBtAñadir() {
		if (btAñadir == null) {
			btAñadir = new JButton("A\u00F1adir");
			btAñadir.setMnemonic('ñ');
			btAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					añadirACesta(e);
				}

			});
		}
		return btAñadir;
	}

	private void añadirACesta(ActionEvent e) {
		getModelCesta().removeAllElements();
		Premio premio = getListArticulos().getSelectedValue();
		game.añadirACesta(premio);
		// actualizar puntos
		getTxValorCesta().setText(String.valueOf(game.getPuntosCesta()));
		getTxPuntosRestantes().setText(String.valueOf(game.getPuntos() - game.getPuntosCesta()));
		// Actualizar la lista
		for (Premio p : game.getCesta()) {
			getModelCesta().addElement(p);
		}
	}

	/**
	 * Elimina de la cesta el premio seleccionado y actualiza todos los campos
	 * 
	 * @param e
	 */
	private void eliminarDeCesta(ActionEvent e) {
		try {
			if (!game.getCesta().isEmpty()) {
				Premio premio = getListCesta().getSelectedValue();
				game.eliminarDeCesta(premio);
				// actualizar puntos
				getTxValorCesta().setText(String.valueOf(game.getPuntosCesta()));
				getTxPuntosRestantes().setText(String.valueOf(game.getPuntos() - game.getPuntosCesta()));
				getModelCesta().removeAllElements();
				for (Premio p : game.getCesta()) {
					getModelCesta().addElement(p);
				}
			}
			validate();
		} catch (NullPointerException e1) {
			System.out.println("Debes seleccionar un elemento de la cesta");
		}
	}

	private JButton getBtEliminar() {
		if (btEliminar == null) {
			btEliminar = new JButton("Eliminar");
			btEliminar.setMnemonic('L');
			btEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					eliminarDeCesta(e);
				}

			});
		}
		return btEliminar;
	}

	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			panel_6.setBackground(Color.WHITE);
			panel_6.setLayout(new GridLayout(0, 2, 0, 0));
			panel_6.add(getLbValor());
			panel_6.add(getTxValorCesta());
		}
		return panel_6;
	}

	private JLabel getLbValor() {
		if (lbValor == null) {
			lbValor = new JLabel("Valor:");
			lbValor.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lbValor;
	}

	private JTextField getTxValorCesta() {
		if (txValorCesta == null) {
			txValorCesta = new JTextField();
			txValorCesta.setHorizontalAlignment(SwingConstants.CENTER);
			txValorCesta.setText("0");
			txValorCesta.setEditable(false);
			txValorCesta.setColumns(10);
		}
		return txValorCesta;
	}

	private JPanel getPanel_7() {
		if (panel_7 == null) {
			panel_7 = new JPanel();
			panel_7.setLayout(new BorderLayout(0, 0));
			panel_7.add(getScCesta());
		}
		return panel_7;
	}

	private JScrollPane getScCesta() {
		if (scCesta == null) {
			scCesta = new JScrollPane();
			scCesta.setViewportView(getListCesta());
		}
		return scCesta;
	}

	private JList<Premio> getListCesta() {
		if (listCesta == null) {
			listCesta = new JList<Premio>();
			modelCesta = new DefaultListModel<>();
			listCesta.setModel(modelCesta);
		}
		return listCesta;
	}

	private DefaultListModel<Premio> getModelCesta() {
		return modelCesta;
	}

	private JPanel getPnOrdenarPrecio() {
		if (pnOrdenarPrecio == null) {
			pnOrdenarPrecio = new JPanel();
			pnOrdenarPrecio.setBackground(Color.WHITE);
			pnOrdenarPrecio.add(getRdbtnOrdenarPrecio());
		}
		return pnOrdenarPrecio;
	}

	private JRadioButton getRdbtnOrdenarPrecio() {
		if (rdbtnOrdenarPrecio == null) {
			rdbtnOrdenarPrecio = new JRadioButton("Ordenar por precio");
			rdbtnOrdenarPrecio.setMnemonic('O');
			rdbtnOrdenarPrecio.setBackground(Color.WHITE);
			rdbtnOrdenarPrecio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ordenarListas();
					rdbtnOrdenarPrecio.setEnabled(false);
				}

			});
		}
		return rdbtnOrdenarPrecio;
	}

	/**
	 * Ordenamos las listas por precio
	 */
	private void ordenarListas() {
		modelArticulos = ordenaListaPorPrecio(modelArticulos);
		validate();
		repaint();
	}

	/**
	 * Ordena de menor a mayor precio aplicando un algoritmo O^2
	 * 
	 * @param model
	 * @return
	 */
	private DefaultListModel<Premio> ordenaListaPorPrecio(DefaultListModel<Premio> model) {
		List<Premio> list = new ArrayList<>();
		for (int i = 0; i < model.size(); i++) {
			list.add((Premio) model.get(i));
		}
		Collections.sort(list);
		model.removeAllElements();
		for (Premio p : list) {
			model.addElement(p);
		}
		return model;
	}

	private JPanel getPnArticulos() {
		if (pnArticulos == null) {
			pnArticulos = new JPanel();
			pnArticulos.setLayout(new BorderLayout(0, 0));
			pnArticulos.add(getPanelRbt(), BorderLayout.NORTH);
			pnArticulos.add(getPanel_9(), BorderLayout.CENTER);
		}
		return pnArticulos;
	}

	private JPanel getPnPuntosRestantes() {
		if (pnPuntosRestantes == null) {
			pnPuntosRestantes = new JPanel();
			pnPuntosRestantes.setBackground(Color.WHITE);
			pnPuntosRestantes.add(getLbTxPuntosRestantes());
			pnPuntosRestantes.add(getTxPuntosRestantes());
		}
		return pnPuntosRestantes;
	}

	private JLabel getLbTxPuntosRestantes() {
		if (lbTxPuntosRestantes == null) {
			lbTxPuntosRestantes = new JLabel("Puntos restantes:");
		}
		return lbTxPuntosRestantes;
	}

	private JTextField getTxPuntosRestantes() {
		if (txPuntosRestantes == null) {
			txPuntosRestantes = new JTextField();
			txPuntosRestantes.setHorizontalAlignment(SwingConstants.CENTER);
			txPuntosRestantes.setText("0");
			txPuntosRestantes.setEditable(false);
			txPuntosRestantes.setColumns(10);
		}
		return txPuntosRestantes;
	}

	public JPanel getPanelRbt() {
		if (panelRbt == null) {
			panelRbt = new JPanel();
			panelRbt.setBorder(new LineBorder(new Color(0, 0, 0)));
			panelRbt.setBackground(Color.WHITE);
			panelRbt.setLayout(new GridLayout(0, 2, 0, 0));
			panelRbt.add(getRbTodo());
			panelRbt.add(getRbAlimentos());
			panelRbt.add(getRbJuegos());
			panelRbt.add(getRbViajes());
			panelRbt.add(getRbElectronica());
			panelRbt.add(getRbDeportes());
		}
		return panelRbt;
	}

	private JPanel getPanel_9() {
		if (panel_9 == null) {
			panel_9 = new JPanel();
			panel_9.setLayout(new BorderLayout(0, 0));
			panel_9.add(getScrollPane_1(), BorderLayout.CENTER);
		}
		return panel_9;
	}

	private JRadioButton getRbTodo() {
		if (rbTodo == null) {
			rbTodo = new JRadioButton("Todo");
			rbTodo.setMnemonic('T');
			rbTodo.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					rdbtnOrdenarPrecio.setSelected(false);
					rdbtnOrdenarPrecio.setEnabled(true);
					List<Premio> premios = getPanelDeJuego().getInicioSesion().getVentanaInicial().getGame()
							.getPremios();
					for (Premio p : premios) {
						modelArticulos.addElement(p);
					}
				}
			});
			rbTodo.setSelected(true);
			rbTodo.setActionCommand("T");
			buttonGroup.add(rbTodo);
			rbTodo.setBackground(Color.WHITE);

		}
		return rbTodo;
	}

	private JRadioButton getRbAlimentos() {
		if (rbAlimentos == null) {
			rbAlimentos = new JRadioButton("Alimentos");
			rbAlimentos.setMnemonic('A');
			rbAlimentos.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					rdbtnOrdenarPrecio.setSelected(false);
					rdbtnOrdenarPrecio.setEnabled(true);
					cargaArticulosRbt("A");
				}
			});
			rbAlimentos.setActionCommand("A");
			buttonGroup.add(rbAlimentos);
			rbAlimentos.setBackground(Color.WHITE);

		}
		return rbAlimentos;
	}

	private JRadioButton getRbJuegos() {
		if (rbJuegos == null) {
			rbJuegos = new JRadioButton("Juegos");
			rbJuegos.setMnemonic('J');
			rbJuegos.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					rdbtnOrdenarPrecio.setSelected(false);
					rdbtnOrdenarPrecio.setEnabled(true);
					cargaArticulosRbt("J");
				}
			});
			rbJuegos.setActionCommand("J");
			buttonGroup.add(rbJuegos);
			rbJuegos.setBackground(Color.WHITE);

		}
		return rbJuegos;
	}

	private JRadioButton getRbViajes() {
		if (rbViajes == null) {
			rbViajes = new JRadioButton("Viajes");
			rbViajes.setMnemonic('V');
			rbViajes.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					rdbtnOrdenarPrecio.setSelected(false);
					rdbtnOrdenarPrecio.setEnabled(true);
					cargaArticulosRbt("V");
				}
			});
			rbViajes.setActionCommand("V");
			buttonGroup.add(rbViajes);
			rbViajes.setBackground(Color.WHITE);

		}
		return rbViajes;
	}

	private JRadioButton getRbElectronica() {
		if (rbElectronica == null) {
			rbElectronica = new JRadioButton("Electronica");
			rbElectronica.setMnemonic('E');
			rbElectronica.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					rdbtnOrdenarPrecio.setSelected(false);
					rdbtnOrdenarPrecio.setEnabled(true);
					cargaArticulosRbt("E");
				}
			});
			rbElectronica.setActionCommand("E");
			buttonGroup.add(rbElectronica);
			rbElectronica.setBackground(Color.WHITE);

		}
		return rbElectronica;
	}

	private JRadioButton getRbDeportes() {
		if (rbDeportes == null) {
			rbDeportes = new JRadioButton("Deportes");
			rbDeportes.setMnemonic('D');
			rbDeportes.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					rdbtnOrdenarPrecio.setSelected(false);
					rdbtnOrdenarPrecio.setEnabled(true);
					cargaArticulosRbt("D");
				}
			});
			rbDeportes.setActionCommand("D");
			buttonGroup.add(rbDeportes);
			rbDeportes.setBackground(Color.WHITE);

		}
		return rbDeportes;
	}

	/**
	 * Actualiza la lista de articulos dependiendo del comando que se le pasa por
	 * parametro que coincida con la seccion del regalo
	 * 
	 * @param command
	 */
	private void cargaArticulosRbt(String command) {
		getModelArticulos().removeAllElements();
		List<Premio> premios = getPanelDeJuego().getInicioSesion().getVentanaInicial().getGame().getPremios();
		for (Premio p : premios) {
			if (p.getSeccion().equals(command)) {
				getModelArticulos().addElement(p);
			}
		}
	}

	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getListArticulos());
		}
		return scrollPane_1;
	}

	public JList<Premio> getListArticulos() {
		if (listArticulos == null) {
			listArticulos = new JList<Premio>();
			listArticulos.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Premios disponibles", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			listArticulos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Premio p = getListArticulos().getSelectedValue();
					setImagenAdaptada2(lbImagen, "/img/" + p.getCodigo() + ".png");
					getLbNombreArticulo().setText(p.getDenominacion());
					getTxDescripcion().setText(p.getDescripcion());
					getLbCoste().setText(String.valueOf(p.getPuntos()));
					validate();
					repaint();
				}
			});
			modelArticulos = new DefaultListModel<>();
			listArticulos.setModel(modelArticulos);
		}
		return listArticulos;
	}

	/**
	 * Metodo para rescalar la imagen
	 * 
	 * @param label
	 * @param rutaImagen
	 */
	private void setImagenAdaptada2(JLabel label, String rutaImagen) {
		Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
		Image imgEscalada = imgOriginal.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_FAST);
		ImageIcon icon = new ImageIcon(imgEscalada);
		label.setIcon(icon);
	}

}
