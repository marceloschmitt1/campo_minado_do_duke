package br.sc.marcelo.controller;

import br.sc.marcelo.model.Cronometro;

public interface CronometroObserver {
	public void cronometroChanged(Cronometro cronometro);
}
