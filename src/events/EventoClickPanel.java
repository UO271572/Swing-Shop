/**
 * 
 */
package events;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import igu.PanelDeJuego;
import logic.Game;

/**
 * @author PC
 *
 */
public class EventoClickPanel implements ActionListener {

	private Game game;
	private PanelDeJuego panel;

	/**
	 * Constructor
	 * 
	 * @param game
	 */
	public EventoClickPanel(Game game,PanelDeJuego panel) {
		setGame(game);
		setPanel(panel);
	}

	private void setGame(Game game) {
		this.game = game;
	}

	private void setPanel(PanelDeJuego panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!game.isFinalizado()) {
			((JButton) e.getSource()).setEnabled(false);
			((JButton) e.getSource()).setBackground(Color.gray);
			game.destapar(Integer.parseInt(((JButton) e.getSource()).getActionCommand()));
			panel.getTxPuntos().setText(String.valueOf(game.getPuntos()));
			cambiarImagen(e);
		}else {
			JOptionPane.showMessageDialog(null, "Has agotado tus opciones, ¡canjea tus puntos!", null, 1);
		}
		System.out.println(((JButton) e.getSource()).getActionCommand());
	}
	
	/**
	 * Modifica la imagen dependiendo del premio que le haya tocado
	 * @param e
	 */
	private void cambiarImagen(ActionEvent e) {
		JButton bt = ((JButton) e.getSource());
		int actionCommand = Integer.parseInt(bt.getActionCommand());
		bt.setIcon(null);
		if(!game.getTablero().getCasillas()[actionCommand].isEsBonus() && !game.getTablero().getCasillas()[actionCommand].isEsMultiplicador()) {
			setImagenAdaptada(bt,"/img/"+game.getTablero().getCasillas()[actionCommand].getPuntos()+".png");
		}else if (game.getTablero().getCasillas()[actionCommand].isEsBonus()) {
			setImagenAdaptada(bt,"/img/bonus.png");
		}else if(game.getTablero().getCasillas()[actionCommand].isEsMultiplicador()) {
			setImagenAdaptada(bt,"/img/multiplicador.png");
		}
	}
	
	private void setImagenAdaptada(JButton boton, String rutaImagen){
		 Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage(); 
		 Image imgEscalada = imgOriginal.getScaledInstance(boton.getWidth(),boton.getHeight(), Image.SCALE_FAST);
		 ImageIcon icon = new ImageIcon(imgEscalada);
		 boton.setIcon(icon);
	}

}
