import java.util.ArrayList;
import java.util.Collections;

public class Ranking extends Relatorios{

	private ArrayList<Jogador> jogadores;
	private final int COLOCADOS = 5;

	Ranking() {
		this.jogadores = new ArrayList<>();
		for (int i = 0; i < COLOCADOS; i++) {
			Jogador j = new Jogador();
			jogadores.add(j);
		}
	}
	
	public String rankingInicial() {
		String rankingInicio = "<html><font size= 5 color=white> Ranking </font>\n<br><font size= 4 color=white>";
		for (int i = 0; i < COLOCADOS; i++) {
			rankingInicio += jogadores.get(i).getNome().toString() + "&emsp;&emsp;" + jogadores.get(i).getPontos().toString() + " pts\n<br>";
		}
		return rankingInicio + "</font></html>";
	}
	
	public void ordenaJogadores() {
		Collections.sort(this.jogadores);
		Collections.reverse(this.jogadores);
	}
	
	public void addJogadorRanking(Jogador jogador) {
			this.jogadores.add(jogador);
			ordenaJogadores();
	}
	
	public String painelRankingJogador() {
		String infoJogador = "<html><font size= 5 color=white> Ranking </font>\n<br><font size= 4 color=white>";
		int range = (this.jogadores.size() > COLOCADOS)? COLOCADOS : this.jogadores.size();
		
		for (int i = 0; i < range; ++i)
			infoJogador += jogadores.get(i).getNome().substring(0, 3).toUpperCase().trim().toString() + "&emsp;&emsp;" + jogadores.get(i).getPontos().toString() + " pts\n<br>";
		return infoJogador + "</font></html>";
	}
	
}
