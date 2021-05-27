
public class Faces  {
	private String[] facesDado = {"face1.jpg", "face2.jpg", "face3.jpg",
								  "face4.jpg", "face5.jpg", "face6.jpg"};
	
	public String gerarArquivo(int valor) {
		return facesDado[valor];
	}
}
