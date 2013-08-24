package br.sc.marcelo.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import br.sc.marcelo.controller.Adaptador;
import br.sc.marcelo.controller.AdaptadorObserver;
import br.sc.marcelo.controller.BotaoCampoListener;
import br.sc.marcelo.controller.ControladorJogo;
import br.sc.marcelo.controller.CronometroObserver;
import br.sc.marcelo.controller.ObservadorJogo;
import br.sc.marcelo.model.Cronometro;
import br.sc.marcelo.model.EstadoJogo;

/**
 * 
 * @author Marcelo Schmitt
 *
 */
@SuppressWarnings("serial")
public class JanelaCampoMinado extends JFrame 
										implements CronometroObserver, 
										AdaptadorObserver,
										ObservadorJogo {

	//TODO adicionar trilha de fundo
	//TODO histórico de pontuação Banco
	//Aumento sucessivo de complexidade
	//Matriz de modelo do campo e matriz boolean de clicados coordenadas clicadas
	//TODO utilizar resources para as imagens
	/** Componentes do menu **/
	private JMenuBar barraDeMenu;
	private JMenu menu;
	private JMenuItem novo;
	private JMenuItem sobre;
	/** Componentes da barra de ferramentas **/
	private JToolBar barraDeFerramentas;
	private JButton botaoNovo;
	private JLabel marcadorBandeiras;
	private JLabel marcadorBandeirasAtivas;
	private JLabel marcadorCronometro;
	private JLabel marcadorTempo;
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	/** Painel de opções **/
	private JPanel painelOpcoes;
	/** Painel do campo minado **/
	private JPanel painelCampoMinado;
	/** Campo minado atual **/
	//private CampoMinado campoMinado;
	/** Controlador do jogo **/
	private ControladorJogo controladorJogo;
	
	public JanelaCampoMinado() {
		controladorJogo = new ControladorJogo();
		controladorJogo.addObservadorJogo(this);
		controladorJogo.getCronometro().addObeserver(this);
		//campoMinado = new CampoMinado(16, 16, 40);
		//campoMinado = new CampoMinado(16, 30, 99);
		init();
	}
	
	public void init() {
		//Definindo o título da janela.
		setTitle("Campo Minado do Duke");
		//Obtendo o Toolkit o as dimensões da tela.
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screensize = kit.getScreenSize();
		int height = screensize.height;
		int width = screensize.width;
		//Definindo o tamanho da janela como metade do tamanho da tela.
		setSize(width / 2, height / 2);
		setLocationByPlatform(true);
		Image icon = kit.getImage(
				"src/br/sc/marcelo/images/mina.jpeg");
		setIconImage(icon);
		
		//Definido barra de menu e menu da aplicação
		construirBarraDeMenu();
		setJMenuBar(barraDeMenu);
		
		//Definindo a barra de ferramentas da aplicação
		construirBarraDeFerramentas();
		getContentPane().add(barraDeFerramentas, BorderLayout.NORTH);
		
		//Definindo a janela de escolha de tamanho do campo minado.
		construirPainelOpcoes();
		getContentPane().add(painelOpcoes, BorderLayout.CENTER);
		
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		pack();
		//TODO definir tamanho mínimo
		//setMinimumSize(this.getSize());
		//TODO definir tamanho máximo?
		//setMaximumSize(this.getSize());
	}
	
	private void construirPainelOpcoes() {
		painelOpcoes = new JPanel(new BorderLayout());
		painelOpcoes.add(
				new JLabel("Tamanho do campo", SwingConstants.HORIZONTAL), 
				BorderLayout.NORTH);
		JPanel campos = new JPanel(new GridLayout(2, 2, 2, 2));
		Icon icone = new ImageIcon(
				"src/br/sc/marcelo/../marcelo/images/Duke-coolapps-30pct.gif");
		System.out.println(getClass().getClassLoader().getResource("duke_wave2.jpg"));
		//File f = new File(getClass().getResource("images/new_duke.jpg").getFile());
		//System.out.println("existe? " + f.exists());
		System.out.println(getClass().getResource("images/duke_wave2.jpg"));
		
		try {
			Enumeration<URL> resources = getClass().getClassLoader().getResources("src");
			while(resources.hasMoreElements()) {
				System.out.println(resources.nextElement());
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		JButton campo8x8 = new JButton("8x8", icone);
		//campo8x8.addActionListener(new BotaoOpcaoListener(8 , 8, 10));
		campo8x8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controladorJogo.novoJogo(8, 8, 10);
				construirPainelCampoMinado(8, 8);
				remove(painelOpcoes);
				getContentPane().add(painelCampoMinado, BorderLayout.CENTER);
				validate();
				pack();
			}
		});
		campos.add(campo8x8);
		JButton campo16x16 = new JButton("16x16", icone);
		//campo16x16.addActionListener(new BotaoOpcaoListener(16, 16, 40));
		campo16x16.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controladorJogo.novoJogo(16, 16, 40);
				construirPainelCampoMinado(16, 16);
				remove(painelOpcoes);
				getContentPane().add(painelCampoMinado, BorderLayout.CENTER);
				validate();
				pack();
			}
		});
		campos.add(campo16x16);
		JButton campo16x30 = new JButton("16x30", icone);
		//campo16x30.addActionListener(new BotaoOpcaoListener(16, 30, 99));
		campo16x30.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controladorJogo.novoJogo(16, 30, 99);
				construirPainelCampoMinado(16, 30);
				remove(painelOpcoes);
				getContentPane().add(painelCampoMinado, BorderLayout.CENTER);
				validate();
				pack();
			}
		});
		campos.add(campo16x30);
		campos.add(new JButton("Personalizado (nas próximas versões)"));
		painelOpcoes.add(campos, BorderLayout.CENTER);
	}
	
	private BotaoCampoListener construirListener() {
		//solução 1 - nova implementação de ActionListener <-- BotaoCampoListener
		//solução 2 - iterar entre botões
		//solução 3 - reflection
		//solução 4 - extender JButton <-- BotaoCampo
		//solução 5 - extender JPanel
		//solução 6 - utilizar JTable no lugar do JPanel <-- Fail
		return new BotaoCampoListener(controladorJogo);
	}
	
	private void construirBarraDeMenu() {
		barraDeMenu = new JMenuBar();
		menu = new JMenu("Campo Minado");
		novo = new JMenuItem("Novo Jogo");
		novo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controladorJogo.pararJogo();
				//TODO NullPointerExpetion se clicar em um botão de novo ou 
				//no menu novo antes de ter iniciado um jogo.
				remove(painelCampoMinado);
				getContentPane().add(painelOpcoes, BorderLayout.CENTER);
				//TODO procurar um método melhor para atualizar o cronometro
				atualizaMarcadorCronometro(controladorJogo.getCronometro());
				validate();
				pack();
			}
		});
		sobre = new JMenuItem("Sobre");
		sobre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				JOptionPane.showMessageDialog((Component) ae.getSource(), 
						"Desenvolvido por Marcelo Schmitt\n" +
						"marcelo.schmitt1@gmail.com", 
						"Sobre", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		JMenuItem sair = new JMenuItem("Sair");
		sair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menu.add(novo);
		menu.add(sobre);
		menu.addSeparator();
		menu.add(sair);
		barraDeMenu.add(menu);
	}
	
	private void construirPainelCampoMinado(int linhas, int colunas) {
		painelCampoMinado = new JPanel(
				new GridLayout(linhas, colunas, 2, 2));
		Dimension tamanhoDosBotoes = new Dimension(35, 35);
		BotaoCampo botaoDoCampo;
		Adaptador adaptador;
		for (int linha = 0; linha < linhas; linha++) {
			for (int coluna = 0; coluna < colunas; coluna++) {
				botaoDoCampo = new BotaoCampo(linha, coluna);
				botaoDoCampo.setPreferredSize(tamanhoDosBotoes);
				botaoDoCampo.setBotaoActionListener(construirListener());
				adaptador = new Adaptador();
				adaptador.addObserver(this);
				//TODO adaptadores perdidos em memória quando o jogo termina.
				botaoDoCampo.setAdaptador(adaptador);
				painelCampoMinado.add(botaoDoCampo);
			}
		}
	}
	
	private void construirBarraDeFerramentas() {
		barraDeFerramentas = new JToolBar();
		botaoNovo = new JButton(new ImageIcon("" +
				"src/br/sc/marcelo/images/new_duke.jpg"));
		botaoNovo.setToolTipText("Iniciar um novo jogo");
		botaoNovo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controladorJogo.pararJogo();
				remove(painelCampoMinado);
				getContentPane().add(painelOpcoes, BorderLayout.CENTER);
				//TODO procurar um método melhor para atualizar o cronometro
				atualizaMarcadorCronometro(controladorJogo.getCronometro());
				validate();
				pack();
			}
		});
		marcadorBandeiras = new JLabel("Bandeiras: ");
		marcadorBandeirasAtivas = new JLabel();
		//marcadorBandeiras.setVisible(false);
		//atualizaMarcadorBandeiras(0);
		marcadorTempo = new JLabel("Tempo: ");
		marcadorCronometro = new JLabel();
		atualizaMarcadorCronometro(controladorJogo.getCronometro());
		
		//Adicionando os componentes à barra de ferramentas.
		barraDeFerramentas.add(botaoNovo);
		barraDeFerramentas.add(marcadorBandeiras);
		barraDeFerramentas.add(marcadorBandeirasAtivas);
		barraDeFerramentas.add(marcadorTempo);
		barraDeFerramentas.add(marcadorCronometro);
	}

	private void atualizarMarcadorBandeiras(int bandeirasAtivas) {
		//TODO atualizarMarcacorBandeiras
		/*
		marcadorBandeirasAtivas.setText(bandeirasAtivas + "/" 
					+ campoMinado.getQuantidadeMinas());
		*/
	}

	private void atualizaMarcadorCronometro(Cronometro cronometro) {
		marcadorCronometro.setText(sdf.format(cronometro.getTempoDeJogo()));
		marcadorCronometro.repaint();
	}

	@Override
	public void cronometroChanged(Cronometro cronometro) {
		atualizaMarcadorCronometro(cronometro);
	}
	
	@Override
	public void adaptadorChanged(Adaptador adaptador) {
		atualizarMarcadorBandeiras(0);
	}

	public void estadoJogoMudou(final EstadoJogo ESTADO) {
		switch (ESTADO) {
		case NOVO:
			botaoNovo.setIcon(new ImageIcon("src/br/sc/marcelo/images/new_duke.jpg"));
			break;
		case PARADO:
			botaoNovo.setIcon(new ImageIcon("src/br/sc/marcelo/images/new_duke.jpg"));
			break;
		case INICIADO:
			botaoNovo.setIcon(new ImageIcon("src/br/sc/marcelo/images/gaming_duke.png"));
			break;
		case PAUSADO:
			//TODO implementar sistema de pausa
			break;
		case VITORIA:
			botaoNovo.setIcon(new ImageIcon("src/br/sc/marcelo/images/happy_duke.jpg"));
			break;
		case DERROTA:
			botaoNovo.setIcon(new ImageIcon("src/br/sc/marcelo/images/toasted_duke.jpg"));
			break;
		}
	}
}
