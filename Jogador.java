
public class Jogador implements Comparable<Jogador> {

	private String nome;
	private Integer pontos;

	Jogador() {
		this.nome = "---";
		this.pontos = 0;
	}

	Jogador(String nome) {
		this.nome = nome;
		this.pontos = 0;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}
	
	@Override
	public int compareTo(Jogador o) {
		int valor = pontos.compareTo(o.pontos);
		return (valor != 0 ? valor : 1);
	}
}
