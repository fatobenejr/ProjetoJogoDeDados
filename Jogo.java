import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Jogo extends JFrame {

	/**
	 * Antonio
	 */
	private static final long serialVersionUID = 1L;
	private Color corFundo;
	private JLabel lblTitulo;
	private JLabel lblRanking;
	private JLabel lblEstatistica;
	private ImageIcon imagem;
	private JLabel lblNome;
	private JTextField txtNomeJogador;
	private JLabel lbldado1;
	private JLabel lbldado2;
	private JLabel lbldado3;
	private JComboBox<Integer> combo1;
	private JComboBox<Integer> combo2;
	private JComboBox<Integer> combo3;
	private JButton btnJogar;
	private String textoRanking;
	private String textoEstatisticas;
	private int muda = 0;
	private int rodada = 0;
	private int pontuacaoTotal = 0;
	private Ranking ranking;
	private Estatisticas estatisticas;
	private FacesAleatoria facesAleatoria;
	private Jogador jogador;

	public Jogo() {
		// Criação do container para vinculo de objetos
		Container tela = getContentPane();
		// Tipo de formulario nulo devido a posição
		// ser colocada pelo programador
		tela.setLayout(null);
		corFundo = new Color(0, 174, 87);
		tela.setBackground(corFundo);

		lblTitulo = new JLabel("<html><font size= 18 color=white> Jogo de Dados </font></html>");
		lblTitulo.setBounds(200, 0, 300, 200);
		tela.add(lblTitulo);

		ranking = new Ranking();
		textoRanking = ranking.rankingInicial();
		lblRanking = new JLabel(textoRanking);
		lblRanking.setBounds(650, -100, 300, 500);
		tela.add(lblRanking);

		estatisticas = new Estatisticas();
		textoEstatisticas = estatisticas.painelEstatisticFaces();
		lblEstatistica = new JLabel(textoEstatisticas);
		lblEstatistica.setBounds(650, 150, 300, 500);
		tela.add(lblEstatistica);

		lblNome = new JLabel("<html><font size=5 color=white>Jogador:</font></html>");
		lblNome.setBounds(149, 400, 104, 25);
		tela.add(lblNome);

		txtNomeJogador = new JTextField("", 3);
		txtNomeJogador.setBounds(235, 400, 104, 25);
		txtNomeJogador.setFocusable(true);
		tela.add(txtNomeJogador);

		facesAleatoria = new FacesAleatoria();

		imagem = new ImageIcon(getClass().getResource("face6.jpg"));

		lbldado1 = new JLabel(imagem);
		lbldado1.setBounds(0, 0, 400, 500);
		tela.add(lbldado1);

		lbldado2 = new JLabel(imagem);
		lbldado2.setBounds(0, 0, 650, 500);
		tela.add(lbldado2);

		lbldado3 = new JLabel(imagem);
		lbldado3.setBounds(0, 0, 900, 500);
		tela.add(lbldado3);

		combo1 = new JComboBox<>();
		combo1.setBounds(399, 330, 104, 20);
		addItensCombo(combo1);
		tela.add(combo1);

		combo2 = new JComboBox<>();
		combo2.setBounds(274, 330, 104, 20);
		addItensCombo(combo2);
		tela.add(combo2);

		combo3 = new JComboBox<>();
		combo3.setBounds(149, 330, 104, 20);
		addItensCombo(combo3);
		tela.add(combo3);

		btnJogar = new JButton("<html><font size=5>Jogar</font></html>");
		btnJogar.setBounds(400, 400, 104, 40);
		tela.add(btnJogar);

		setSize(800, 550); // Tamanho do Formulario
		setLocationRelativeTo(null); // Ajuste do formulario no meio da tela

		TrataEv trat = new TrataEv(); // Cria uma instância do
		btnJogar.addActionListener(trat);

		setVisible(true);
	}

	class TrataEv implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Object botao = e.getSource();
			if (botao == btnJogar) {
				if (muda < 5) {
					jogaParte1();
					jogaParte2();
					muda++;
				} else if (muda == 5){
					imagem = new ImageIcon(getClass().getResource("face6.jpg"));
					lbldado1.setIcon(imagem);
					lbldado2.setIcon(imagem);
					lbldado3.setIcon(imagem);
					rodada = 0;
					muda++;
					pontuacaoTotal = 0;
				} else {
					muda = 0;
					mensagemContinuar();
				}

			}
		}
	}

	private void jogaParte1() {

		if (rodada == 0) {
			String nomeAuxiliar = txtNomeJogador.getText();
			if (nomeAuxiliar.equalsIgnoreCase("")) {
				txtNomeJogador.setText("???");
				jogador = new Jogador(txtNomeJogador.getText());
			} else if (nomeAuxiliar.length() < 3) {
				int diferenca = 3 - nomeAuxiliar.length();
				for (int i = 0; i < diferenca; ++i)
					nomeAuxiliar += " ";
				jogador = new Jogador(nomeAuxiliar);
			} else
				jogador = new Jogador(nomeAuxiliar);
		}

	}

	private void jogaParte2() {
		int acertos = 0;
		int[] dados = new int[3];
		String[] vetorFaces = new String[dados.length];

		if (rodada < 5) {

			for (int i = 0; i < dados.length; ++i) {
				dados[i] = facesAleatoria.procuraFace(0, 6);
				vetorFaces[i] = facesAleatoria.gerarArquivo(dados[i]);
				if (i == 0) {
					ImageIcon imagem = new ImageIcon(getClass().getResource(vetorFaces[i]));
					lbldado1.setIcon(imagem);
					if (combo1.getSelectedItem().equals(dados[i] + 1))
						acertos++;
				}
				if (i == 1) {
					ImageIcon imagem = new ImageIcon(getClass().getResource(vetorFaces[i]));
					lbldado2.setIcon(imagem);
					if (combo2.getSelectedItem().equals(dados[i] + 1))
						acertos++;
				}
				if (i == 2) {
					ImageIcon imagem = new ImageIcon(getClass().getResource(vetorFaces[i]));
					lbldado3.setIcon(imagem);
					if (combo3.getSelectedItem().equals(dados[i] + 1))
						acertos++;
				}
			}
			pontuacaoTotal += pontuacao(acertos);
			if (rodada >= 4) {
				jogador.setPontos(pontuacaoTotal);
				ranking.addJogadorRanking(jogador);
				textoRanking = ranking.painelRankingJogador();
				lblRanking.setText(textoRanking);
			}
			
		}
		++rodada;
		estatisticas.atualizaEstatistica(dados);
		textoEstatisticas = estatisticas.painelEstatisticFaces();
		lblEstatistica.setText(textoEstatisticas);
	}

	private void mensagemContinuar() {
		int mensagem = JOptionPane.showConfirmDialog(null, "Deseja Continuar?", "Aviso", 0, 1);
		if (mensagem == 1)
			System.exit(1);
	}

	private void addItensCombo(JComboBox<Integer> combo) {
		for (int i = 1; i <= 6; i++)
			combo.addItem(i);
	}

	private int pontuacao(int acertos) {
		int pontos = 0;
		if (acertos == 1)
			pontos = 25;
		if (acertos == 2)
			pontos = 60;
		if (acertos == 3)
			pontos = 100;
		return pontos;
	}

	public static void main(String args[]) {
		Jogo app = new Jogo();
		// Habilita funcionalidade do botão fechar do formulario.
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
