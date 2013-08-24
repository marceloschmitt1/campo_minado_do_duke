package br.sc.marcelo.view;

import javax.swing.JButton;
import javax.swing.JPanel;

import br.sc.marcelo.controller.Adaptador;
import br.sc.marcelo.controller.BotaoCampoListener;

@SuppressWarnings("serial")
public class BotaoCampo extends JButton {

	/**
	 * Número da linha do botão dentro do campo minado.
	 */
	private int linha;
	/**
	 * Número coluna do botão dentro do campo minado.
	 */
	private int coluna;
	/**
	 * Escutador de ações do botão.
	 */
	private BotaoCampoListener botaoCampoListener;
	/**
	 * Adaptador para os eventos dos botões do mouse.
	 */
	private Adaptador adaptador;
	
	/**
	 * 
	 * @param linha
	 * Linha do botão dentro do campo minado.
	 * @param coluna
	 * Coluna do botão dentro do campo minado.
	 */
	public BotaoCampo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
	/**
	 * Define o escutador de ações deste botão do campo minado.
	 * @param actionListener
	 * O escutador de ações para este botão.
	 */
	public void setBotaoActionListener(BotaoCampoListener actionListener) {
		this.botaoCampoListener = actionListener;
		addActionListener(this.botaoCampoListener);
	}
	
	/**
	 * Remove o escutador de ações deste botão do campo minado.
	 * Para que o botão não responda mais quando for pressionado.
	 */
	public void removeBotaoActionListener() {
		removeActionListener(this.botaoCampoListener);
		this.botaoCampoListener = null;
	}
	
	/**
	 * Define o escutador de botões do mouse para este botão do campo minado.
	 * @param adaptador
	 * Escutador de botões do mouse para este botão do campo minado.
	 */
	public void setAdaptador(Adaptador adaptador) {
		this.adaptador = adaptador;
		addMouseListener(this.adaptador);
	}
	
	/**
	 * Remove os escutador de botões do mouse deste botão do campo minado.
	 * Assim este botão não responderá quando pressionarmos os botões do mouse. 
	 */
	public void removeAdaptador() {
		removeMouseListener(this.adaptador);
		this.adaptador = null;
	}

	/**
	 * Retorna o painel que contêm os botões do campo minado.
	 * @return
	 * Painel do campo minado.
	 */
	public JPanel getPainelCampoMinado() {
		return (JPanel) getParent();
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	
}
