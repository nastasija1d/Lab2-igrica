package paket;

public class Generator {
	private int min, max;
	
	public Generator(int mn, int mx) {
		min = mn;
		max = mx;
	}
	
	public int generisi() {
		int i = (int) (min + Math.random() * (max - min));
		return i;
	}
}
