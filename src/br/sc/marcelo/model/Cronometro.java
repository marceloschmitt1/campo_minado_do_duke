package br.sc.marcelo.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import br.sc.marcelo.controller.CronometroObserver;

/**
 * 
 * @author Marcelo Schmitt
 *
 */
public class Cronometro extends Timer {

	private Calendar inicioJogo = Calendar.getInstance();
	private Calendar tempoDeJogo;
	private List<CronometroObserver> observadores = new ArrayList<CronometroObserver>();

	public void contaSegundo() {
		tempoDeJogo.add(Calendar.SECOND, 1);
		notificaObservadores();
	}

	public void iniciarCronometro() {
		zerarCronometro();
	}

	public void zerarCronometro() {
		inicioJogo.set(Calendar.HOUR_OF_DAY, 0);
		inicioJogo.set(Calendar.MINUTE, 0);
		inicioJogo.set(Calendar.SECOND, 0);
		tempoDeJogo = Calendar.getInstance();
		tempoDeJogo.setTime(inicioJogo.getTime());
	}

	public Date getTempoDeJogo() {
		return tempoDeJogo.getTime();
	}
	
	public void addObeserver(CronometroObserver observer) {
		observadores.add(observer);
	}
	
	public void removeObserver(CronometroObserver observer) {
		this.observadores.remove(observer);
	}
	
	private void notificaObservadores() {
		for (CronometroObserver observer : observadores) {
			observer.cronometroChanged(this);
		}
	}
}
