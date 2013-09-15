package br.sc.marcelo.controller;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * 
 * @author Marcelo Schmitt
 *
 */
public class Adaptador extends MouseAdapter {

	private List<AdaptadorObserver> observadores = new ArrayList<AdaptadorObserver>();
	
	@Override
	public void mouseClicked(MouseEvent me) {
		if (me.getButton() == MouseEvent.BUTTON3) {
			Component componente = me.getComponent();
			if (componente instanceof JButton) {
				JButton botao = (JButton) componente;
				if (botao.isEnabled()) {
					if (botao.getIcon() == null) {
						botao.setIcon(new ImageIcon(
								getClass().getResource("/br/sc/marcelo/images/bandeira.png")));
					} else {
						botao.setIcon(null);
					}
					notificarObservadores();
				}
			}
		}
	}
	
	private void notificarObservadores() {
		for(AdaptadorObserver observer : observadores) {
			observer.adaptadorChanged(this);
		}
	}
	
	public void addObserver(AdaptadorObserver observer) {
		observadores.add(observer);
	}
}
