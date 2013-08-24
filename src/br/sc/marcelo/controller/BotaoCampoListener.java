package br.sc.marcelo.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import br.sc.marcelo.model.CampoMinado;
import br.sc.marcelo.model.EstadoJogo;
import br.sc.marcelo.view.BotaoCampo;

/**
 * 
 * @author Marcelo Schmitt
 *
 */
public class BotaoCampoListener implements ActionListener {
	
	private boolean botaoOriginal = true;
	private ControladorJogo controladorJogo;
	
	public BotaoCampoListener(ControladorJogo controladorJogo) {
		this.controladorJogo = controladorJogo;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (!(ControladorJogo.ESTADO == EstadoJogo.INICIADO)) {
			controladorJogo.iniciarJogo();
		}
		BotaoCampo botaoCampo = (BotaoCampo) ae.getSource();
		processaClique(botaoCampo);
		if (vitoria((JPanel)botaoCampo.getParent())) {
			exibeMinas(botaoCampo.getPainelCampoMinado(), 
					"src/br/sc/marcelo/images/bandeira.png");
			fimDeJogo(botaoCampo, true);
		}
	}

	private void processaClique(BotaoCampo botaoCampo) {
		if (botaoCampo.isEnabled()
				&& (botaoCampo.getIcon() == null || !botaoOriginal)) {
			switch (controladorJogo.getCoordenada(
					botaoCampo.getLinha(), botaoCampo.getColuna())) {
			case 1:
				definirIcone(botaoCampo, "src/br/sc/marcelo/images/colored1.png");
				break;
			case 2:
				definirIcone(botaoCampo, "src/br/sc/marcelo/images/colored2.png");
				break;
			case 3:
				definirIcone(botaoCampo, "src/br/sc/marcelo/images/colored3.png");
				break;
			case 4:
				definirIcone(botaoCampo, "src/br/sc/marcelo/images/colored4.png");
				break;
			case 5:
				definirIcone(botaoCampo, "src/br/sc/marcelo/images/colored5.png");
				break;
			case 6:
				definirIcone(botaoCampo, "src/br/sc/marcelo/images/colored6.png");
				break;
			case 7:
				definirIcone(botaoCampo, "src/br/sc/marcelo/images/colored7.png");
				break;
			case 8:
				definirIcone(botaoCampo, "src/br/sc/marcelo/images/colored8.png");
				break;
			case CampoMinado.MINA:
				fimDeJogo(botaoCampo, false);
				exibeMinas(botaoCampo.getPainelCampoMinado(), 
						"src/br/sc/marcelo/images/mina_botao.png");
				definirIcone(botaoCampo, "src/br/sc/marcelo/images/boom.jpeg");
				break;
			case 0:
				botaoCampo.setEnabled(false);
				botaoCampo.setIcon(null);
				botaoOriginal = false;
				processaRevelacao(botaoCampo);
				break;
			}
		}
	}

	private void processaRevelacao(BotaoCampo botaoCampo) {
		ArrayList<BotaoCampo> vizinhos;
		vizinhos = getVizinhos(botaoCampo);
		for(BotaoCampo vizinho : vizinhos) {
			processaClique(vizinho);
		}
	}

	/**
	 * 
	 * @param botaoCampo
	 * @return
	 * Retorna um ArraysList de botões vizinhos ao botão
	 * passado como argumento.
	 */
	private ArrayList<BotaoCampo> getVizinhos(BotaoCampo botaoCampo) {
		ArrayList<BotaoCampo> vizinhos = new ArrayList<BotaoCampo>();
		JPanel painelBotoes = botaoCampo.getPainelCampoMinado();
		//Variáveis para evitar enganos.
		int linhaVizinho;
		int colunaVizinho;
		
		linhaVizinho = botaoCampo.getLinha();
		colunaVizinho = botaoCampo.getColuna() - 1;
		if (controladorJogo.estaDentroCampo(linhaVizinho, colunaVizinho)) {
			vizinhos.add((BotaoCampo) painelBotoes.getComponent(
					controladorJogo.converteCoordenadaLista(linhaVizinho, colunaVizinho)));
		}
		linhaVizinho = botaoCampo.getLinha() - 1;
		colunaVizinho = botaoCampo.getColuna() - 1;
		if (controladorJogo.estaDentroCampo(linhaVizinho, colunaVizinho)) {
			vizinhos.add((BotaoCampo) painelBotoes.getComponent(
					controladorJogo.converteCoordenadaLista(linhaVizinho, colunaVizinho)));
		}
		linhaVizinho = botaoCampo.getLinha() - 1;
		colunaVizinho = botaoCampo.getColuna();
		if (controladorJogo.estaDentroCampo(linhaVizinho, colunaVizinho)) {
			vizinhos.add((BotaoCampo) painelBotoes.getComponent(
					controladorJogo.converteCoordenadaLista(linhaVizinho, colunaVizinho)));
		}
		linhaVizinho = botaoCampo.getLinha() - 1;
		colunaVizinho = botaoCampo.getColuna() + 1;
		if (controladorJogo.estaDentroCampo(linhaVizinho, colunaVizinho)) {
			vizinhos.add((BotaoCampo) painelBotoes.getComponent(
					controladorJogo.converteCoordenadaLista(linhaVizinho, colunaVizinho)));
		}
		linhaVizinho = botaoCampo.getLinha();
		colunaVizinho = botaoCampo.getColuna() + 1;
		if (controladorJogo.estaDentroCampo(linhaVizinho, colunaVizinho)) {
			vizinhos.add((BotaoCampo) painelBotoes.getComponent(
					controladorJogo.converteCoordenadaLista(linhaVizinho, colunaVizinho)));
		}
		linhaVizinho = botaoCampo.getLinha() + 1;
		colunaVizinho = botaoCampo.getColuna() + 1;
		if (controladorJogo.estaDentroCampo(linhaVizinho, colunaVizinho)) {
			vizinhos.add((BotaoCampo) painelBotoes.getComponent(
					controladorJogo.converteCoordenadaLista(linhaVizinho, colunaVizinho)));
		}
		linhaVizinho = botaoCampo.getLinha() + 1;
		colunaVizinho = botaoCampo.getColuna();
		if (controladorJogo.estaDentroCampo(linhaVizinho, colunaVizinho)) {
			vizinhos.add((BotaoCampo) painelBotoes.getComponent(
					controladorJogo.converteCoordenadaLista(linhaVizinho, colunaVizinho)));
		}
		linhaVizinho = botaoCampo.getLinha() + 1;
		colunaVizinho = botaoCampo.getColuna() - 1;
		if (controladorJogo.estaDentroCampo(linhaVizinho, colunaVizinho)) {
			vizinhos.add((BotaoCampo) painelBotoes.getComponent(
					controladorJogo.converteCoordenadaLista(linhaVizinho, colunaVizinho)));
		}
		return vizinhos;
	}

	/*
	/*
	 * @author marcelo
	 * @param indice
	 * @return Um array contendo dois elementos [x, y]. O elemento x (de índice
	 *         0) representa a coordenada X. O elemento y (de índice 1)
	 *         representa a coordenada Y.
	 /
	private int[] converteListaCoordenada(int indice) {
		int[] coordenadas = new int[2];
		// linha
		coordenadas[0] = (indice / campoMinado.getLinhas());
		// coluna
		coordenadas[1] = (indice % campoMinado.getLinhas());
		return coordenadas;
	}
	*/

	private void definirIcone(JButton botao, String imagem) {
		botao.setEnabled(false);
		botao.setIcon(new ImageIcon(imagem));
		botao.setDisabledIcon(new ImageIcon(imagem));
	}
	
	private boolean vitoria(JPanel painelCampoMinado) {
		boolean vitoria = true;
		Component[] componentes = painelCampoMinado.getComponents();
		for (int i = 0; i < componentes.length; i++) {
			BotaoCampo botao = (BotaoCampo) componentes[i];
			vitoria &= (
					(!botao.isEnabled() && 
					controladorJogo.getCoordenada(botao.getLinha(), botao.getColuna()) != 9))
					|| controladorJogo.getCoordenada(botao.getLinha(), botao.getColuna()) == 9;
		}
		return vitoria;
	}

	private void fimDeJogo(BotaoCampo botaoCampo, boolean vitoria) {
		JPanel painelCampo = botaoCampo.getPainelCampoMinado();
		Component[] componentes = painelCampo.getComponents();
		for(int i = 0; i < componentes.length; i++) {
			BotaoCampo botao = (BotaoCampo) componentes[i];
			botao.removeBotaoActionListener();
			botao.removeAdaptador();
		}
		controladorJogo.terminarJogo(vitoria);
	}
	
	/**
	 * Exibe as minhas no campo minado com a imagem passada como argumento.
	 * @param botaoCampo
	 * @param imagem
	 * String da imagem que será usada para exibir as minas no campo minado.
	 */
	private void exibeMinas(JPanel painelCampoMinado, String imagem) {
		//Exibir onde estavam as minas no campo
		int[][] minas = controladorJogo.getMinas();
		BotaoCampo botaoCampo;
		int linhaMina;
		int colunaMina;
		for (int i = 0; i < minas.length; i++) {
			linhaMina = minas[i][0];
			colunaMina = minas[i][1];
			botaoCampo = (BotaoCampo) painelCampoMinado.getComponent(
					controladorJogo.converteCoordenadaLista(linhaMina, colunaMina));
			definirIcone(botaoCampo, imagem);
			//TODO Marcar bandeiras erradas
		}
	}
}