package br.sc.marcelo.controller;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.swing.JFrame;

import br.sc.marcelo.model.CampoMinado;
import br.sc.marcelo.model.Cronometro;
import br.sc.marcelo.model.EstadoJogo;
import br.sc.marcelo.view.JanelaCampoMinado;

/**
 * 
 * @author Marcelo Schmitt
 *
 */
public class ControladorJogo {
	/** Estados do jogo **/
	public static EstadoJogo ESTADO;
	public CampoMinado campoMinado;
	private Cronometro cronometro;
	private TimerTask contagem;
	private List<ObservadorJogo> observadoresDoJogo;
	
	public ControladorJogo() {
		ControladorJogo.ESTADO = EstadoJogo.NOVO;
		observadoresDoJogo = new ArrayList<ObservadorJogo>();
		cronometro = new Cronometro();
		cronometro.zerarCronometro();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JanelaCampoMinado janelaCampoMinado = new JanelaCampoMinado();
				janelaCampoMinado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				janelaCampoMinado.setVisible(true);
				//TODO Definir botão em para tela cheia?
				/*
				//janelaCampoMinado.setResizable(false);
				//janelaCampoMinado.setUndecorated(true);
				GraphicsDevice monitor = GraphicsEnvironment.
						getLocalGraphicsEnvironment().getDefaultScreenDevice();
				if (monitor.isFullScreenSupported()) {
					try {
						monitor.setFullScreenWindow(janelaCampoMinado);
					} finally {
						
					}
				}
				*/
				//cronometro.addObeserver(janelaCampoMinado);
			}
		});
	}
	
	public void novoJogo(int linhas, int colunas, int quantidadeMinas) {
		campoMinado = new CampoMinado(linhas, colunas, quantidadeMinas);
		atualizaEstadoJogo(EstadoJogo.NOVO);
	}

	public void iniciarJogo() {
		cronometro.iniciarCronometro();
		contagem = new TimerTask() {
			@Override
			public void run() {
				cronometro.contaSegundo();
			}
		};
		cronometro.schedule(contagem, 1000, 1000);
		atualizaEstadoJogo(EstadoJogo.INICIADO);
	}

	public void pararJogo() {
		contagem.cancel();
		cronometro.zerarCronometro();
		atualizaEstadoJogo(EstadoJogo.PARADO);
	}
	
	public void terminarJogo(boolean vitoria) {
		contagem.cancel();
		if (vitoria) {
			atualizaEstadoJogo(EstadoJogo.VITORIA);
		} else {
			atualizaEstadoJogo(EstadoJogo.DERROTA);
		}
	}

	public int getCoordenada(int linha, int coluna) {
		return campoMinado.getCoordenada(linha, coluna);
	}

	public int[][] getMinas() {
		return campoMinado.getMinas();
	}

	public int converteCoordenadaLista(int linha, int coluna) {
		return ((campoMinado.getColunas() * linha) + coluna);
	}

	public boolean estaDentroCampo(int linha, int coluna) {
		return campoMinado.estaDentroCampo(linha, coluna);
	}

	public void addObservadorJogo(ObservadorJogo observadorJogo) {
		observadoresDoJogo.add(observadorJogo);
	}

	private void atualizaEstadoJogo(final EstadoJogo ESTADO) {
		ControladorJogo.ESTADO = ESTADO;
		for (ObservadorJogo observador : observadoresDoJogo) {
			observador.estadoJogoMudou(ESTADO);
		}
	}

	public Cronometro getCronometro() {
		return cronometro;
	}

}
