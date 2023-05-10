package igu;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import com.toedter.calendar.JDateChooser;

import logic.Game;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class VentanaConfirmacion extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lbCanjeo;
	private JPanel panel_3;
	private JButton btConfirmar;
	private JPanel pnExtra;
	private JPanel panel_5;
	private JPanel panel_6;
	private JDateChooser date;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JLabel lbFecha;
	private VentanaPremios vPremios;
	private Game game;


	/**
	 * Create the dialog.
	 */
	public VentanaConfirmacion(VentanaPremios vPremios) {
		this.vPremios = vPremios;
		this.game = vPremios.getGame();
	
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaConfirmacion.class.getResource("/img/arbolnavidad.jpg")));
		setTitle("Christmas sales");
		setBounds(100, 100, 719, 417);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getPanel(), BorderLayout.CENTER);
	}

	public VentanaPremios getvPremios() {
		return vPremios;
	}

	public Game getGame() {
		return game;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setLayout(new GridLayout(0, 1, 0, 0));
			panel.add(getPanel_1_1());
			panel.add(getPanel_2_1());
		}
		return panel;
	}
	private JPanel getPanel_1_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(Color.WHITE);
			panel_1.setLayout(new BorderLayout(0, 0));
			panel_1.add(getLbCanjeo(), BorderLayout.CENTER);
		}
		return panel_1;
	}
	private JPanel getPanel_2_1() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBackground(Color.WHITE);
			panel_2.setLayout(new BorderLayout(0, 0));
			panel_2.add(getPanel_3_1(), BorderLayout.SOUTH);
			panel_2.add(getPanelExtra(), BorderLayout.CENTER);
		}
		return panel_2;
	}
	private JLabel getLbCanjeo() {
		if (lbCanjeo == null) {
			lbCanjeo = new JLabel("Se esta llevando a cabo su canjeo...");
			lbCanjeo.setFont(new Font("Papyrus", Font.BOLD, 25));
			lbCanjeo.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbCanjeo;
	}
	private JPanel getPanel_3_1() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setBackground(Color.WHITE);
			panel_3.setLayout(new BorderLayout(0, 0));
			panel_3.add(getBtConfirmar(), BorderLayout.EAST);
		}
		return panel_3;
	}
	private JButton getBtConfirmar() {
		if (btConfirmar == null) {
			btConfirmar = new JButton("Confirmar");
			btConfirmar.setToolTipText("Confirma tus premios");
			btConfirmar.setMnemonic('C');
			btConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					guardarEntrega();
				}

				
			});
		}
		return btConfirmar;
	}
	
	/**
	 * guarda la entrega de los premios
	 */
	private void guardarEntrega() {
		if(getPanelExtra().isVisible()) {
			if(getDateChoser().getDate().after(java.sql.Date.valueOf(LocalDate.now()))) {
				guardar();
			}
		}else {
			guardar();
		}
	}
	
	/**
	 * Guarda y cierra la aplicacion
	 */
	private void guardar() {
		getGame().saveToFile(getDateChoser().getDate(), getTxArea().getText());
		reiniciarJuego();
	}

	/**
	 * Reinicia la aplicacion y la deja preparada para el siguiente usuario
	 */
	private void reiniciarJuego() {
		VentanaInicial v = new VentanaInicial(new Game());
		v.setVisible(true);
		v.setLocationRelativeTo(null);
		
		this.dispose();
		vPremios.dispose();
		vPremios.getPanelDeJuego().dispose();
		vPremios.getPanelDeJuego().getInicioSesion().dispose();
		vPremios.getPanelDeJuego().getInicioSesion().getVentanaInicial().dispose();

	}

	public JPanel getPanelExtra() {
		if (pnExtra == null) {
			pnExtra = new JPanel();
			pnExtra.setBackground(Color.WHITE);
			pnExtra.setLayout(new GridLayout(0, 2, 0, 0));
			pnExtra.add(getPanel_5());
			pnExtra.add(getPanel_6());
			pnExtra.setVisible(false);
		}
		return pnExtra;
	}
	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.setBackground(Color.WHITE);
			panel_5.setLayout(new BorderLayout(0, 0));
			panel_5.add(getScrollPane());
		}
		return panel_5;
	}
	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			panel_6.setBackground(Color.WHITE);
			panel_6.setLayout(new GridLayout(0, 1, 0, 0));
			panel_6.add(getLbFecha());
			panel_6.add(getDateChoser());
		}
		return panel_6;
	}
	
	/**
	 * 
	 * @return
	 */
	private JDateChooser getDateChoser() {
		if(date == null) {
			date = new JDateChooser();
		}
		return date;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setToolTipText("D");
			scrollPane.setBorder(new TitledBorder(null, "Dejanos aqui tu comentario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			scrollPane.setViewportView(getTxArea());
		}
		return scrollPane;
	}
	private JTextArea getTxArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		}
		return textArea;
	}
	private JLabel getLbFecha() {
		if (lbFecha == null) {
			lbFecha = new JLabel("Introduce la fecha:");
			lbFecha.setDisplayedMnemonic('I');
			lbFecha.setLabelFor(getDateChoser());
			lbFecha.setVerticalAlignment(SwingConstants.BOTTOM);
		}
		return lbFecha;
	}
}
