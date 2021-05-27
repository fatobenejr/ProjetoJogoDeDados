
public class FacesAleatoria extends Faces {

	public int procuraFace(int a, int b) {
		int face = (int) (Math.random() * (b - a) + a);
		return face;
	}
}
