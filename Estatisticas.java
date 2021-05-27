
public class Estatisticas extends Relatorios{

	private int[] estatisticasFaces;

	public Estatisticas() {
		this.estatisticasFaces = new int[6];
	}

	public void addUnidEstatisticFaces(int posicao) {
		this.estatisticasFaces[posicao] = estatisticasFaces[posicao] + 1;
	}

	public void atualizaEstatistica(int[] faces) {
		for (int i = 0; i < faces.length; ++i) {
		if (faces[i] == 0)
			this.addUnidEstatisticFaces(0);
		if (faces[i] == 1)
			this.addUnidEstatisticFaces(1);
		if (faces[i] == 2)
			this.addUnidEstatisticFaces(2);
		if (faces[i] == 3)
			this.addUnidEstatisticFaces(3);
		if (faces[i] == 4)
			this.addUnidEstatisticFaces(4);
		if (faces[i] == 5)
			this.addUnidEstatisticFaces(5);
		}
	}

	public String painelEstatisticFaces() {
		String facesEstatisca = "<html><font size= 5 color=white> Estatística </font>\n<br><font size= 4 color=white>";
		int range = this.estatisticasFaces.length - 1;
		for (int i = 0; i < range; ++i)
			facesEstatisca += "face " + (i + 1) + ":&emsp;&emsp;" + this.estatisticasFaces[i] + "\n<br>";
		facesEstatisca += "face " + (range + 1) + ":&emsp;&emsp;" + this.estatisticasFaces[range];

		return facesEstatisca + "</font></html>";
	}

	public int[] getEstatisticasFaces() {
		return estatisticasFaces;
	}

	public void setEstatisticasFaces(int[] estatisticasFaces) {
		this.estatisticasFaces = estatisticasFaces;
	}

}
