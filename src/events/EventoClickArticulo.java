/**
 * 
 */
package events;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import igu.VentanaPremios;
import logic.Premio;

/**
 * @author PC
 *
 */
public class EventoClickArticulo implements ListSelectionListener{

	private VentanaPremios vPremios;
//	private Game game;
	
	public EventoClickArticulo(VentanaPremios vPremios) {
		this.vPremios = vPremios;
//		this.game = vPremios.getPanelDeJuego().getInicioSesion().getVentanaInicial().getGame();
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



	@Override
	public void valueChanged(ListSelectionEvent e) {
		@SuppressWarnings("unchecked")
		Premio seleccionado = ((JList<Premio>)e.getSource()).getSelectedValue();
		setImagenAdaptada(vPremios.getLbImagen(), "img/"+seleccionado.getCodigo()+".png");
		vPremios.getLbNombreArticulo().setText(seleccionado.getDenominacion());
		vPremios.getTxDescripcion().setText(seleccionado.getDescripcion());
		vPremios.getLbCoste().setText(String.valueOf(seleccionado.getPuntos()));
		vPremios.validate();
		vPremios.repaint();
	}
	
	

}
