package br.sc.marcelo.model;

/**
 * 
 * @author Marcelo Schmitt
 *
 */
public class CampoMinado {

	//Constantes
	private final int[][] campo;
	private final int linhas;
	private final int colunas;
	private final int quantidadeMinas;
	private int[][] minas;
	public static final int MINA = 9;
	
	public CampoMinado(int linhas, int colunas, int quantidadeMinas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.quantidadeMinas = quantidadeMinas;
		this.campo = new int[this.linhas][this.colunas];
		this.minas = new int[quantidadeMinas][2];
		colocarMinas();
		definirNumeros();
		//imprimirCampo();
	}
	
	private void colocarMinas() {
		int linha;
		int coluna;
		for (int i = 0; i < quantidadeMinas; i++) {
			linha = (int)(Math.random()*linhas);
			coluna = (int)(Math.random()*colunas);
			if(!(campo[linha][coluna] == MINA)) {
				campo[linha][coluna] = MINA;
				minas[i][0] = linha;
				minas[i][1] = coluna;
			} else {
				i--;
			}
		}
	}
	
	private void definirNumeros() {
		for (int linha = 0; linha < linhas; linha++) {
			for (int coluna = 0; coluna < colunas; coluna++) {
				if (campo[linha][coluna] == MINA) {
					circundarMina(linha, coluna);
				}
			}
		}
	}
	
	private void circundarMina(int linha, int coluna) {
		incrementarNumero(linha, coluna - 1);
		incrementarNumero(linha - 1, coluna - 1);
		incrementarNumero(linha - 1, coluna);
		incrementarNumero(linha - 1, coluna + 1);
		incrementarNumero(linha, coluna + 1);
		incrementarNumero(linha + 1, coluna + 1);
		incrementarNumero(linha + 1, coluna);
		incrementarNumero(linha + 1, coluna - 1);
	}
	
	private void incrementarNumero(int linha, int coluna) {
		if (estaDentroCampo(linha, coluna) && !(campo[linha][coluna] == MINA)) {
			campo[linha][coluna] += 1;
		}
	}
	
	public boolean estaDentroCampo(int linha, int coluna) {
		return ((linha >= 0 && linha < getLinhas()) && 
				(coluna >= 0 && coluna < getColunas()));
	}
	/*
	private void imprimirCampo() {
		for (int i = 0; i < linhas; i++) {
			System.out.print("[");
			for (int j = 0; j < colunas; j++) {
				System.out.print(campo[i][j] + ", ");
			}
			System.out.println("]");
		}
	}
	*/
	public int getCoordenada(int linha, int coluna) {
		return campo[linha][coluna];
	}
	
	public int[][] getMinas() {
		return minas;
	}
	
	public int getLinhas() {
		return this.linhas;
	}
	
	public int getColunas() {
		return this.colunas;
	}
	
	public int getQuantidadeMinas() {
		return quantidadeMinas;
	}
}
