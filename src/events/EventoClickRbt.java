package events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JRadioButton;

import igu.VentanaPremios;
import logic.Premio;

public class EventoClickRbt implements ActionListener{

	private VentanaPremios vpremios;
	
	public EventoClickRbt(VentanaPremios vpremios) {
		this.vpremios=vpremios;
	}
	
		
	@Override
	public void actionPerformed(ActionEvent e) {
		//Obtenemos que rbtn fue llamado
		JRadioButton radioBt = (JRadioButton)e.getSource();
		
		//Eliminamos el anterior contenido
		vpremios.getModelArticulos().removeAllElements();

		List<Premio> premios = vpremios.getPanelDeJuego().getInicioSesion().getVentanaInicial().getGame().getPremios();
		for (Premio p : premios) {
			if(radioBt.getActionCommand().equals("T") && p.getSeccion().equals("T")) {
				vpremios.getModelArticulos().addElement(p);
			}else if(radioBt.getActionCommand().equals("A") && p.getSeccion().equals("A")) {
				vpremios.getModelArticulos().addElement(p);
			}else if(radioBt.getActionCommand().equals("D") && p.getSeccion().equals("D")) {
				vpremios.getModelArticulos().addElement(p);
			}else if(radioBt.getActionCommand().equals("J") && p.getSeccion().equals("J")) {
				vpremios.getModelArticulos().addElement(p);
			}else if(radioBt.getActionCommand().equals("V") && p.getSeccion().equals("V")) {
				vpremios.getModelArticulos().addElement(p);
			}else if(radioBt.getActionCommand().equals("E") && p.getSeccion().equals("E")) {
				vpremios.getModelArticulos().addElement(p);
			}
		}
		
	}

}
