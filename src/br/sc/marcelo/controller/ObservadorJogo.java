package br.sc.marcelo.controller;

import br.sc.marcelo.model.EstadoJogo;

public interface ObservadorJogo {
	public void estadoJogoMudou(EstadoJogo estadoJogo);
}
