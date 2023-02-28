package paket;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.HashSet;


public class Mreza extends Panel {
	
	private Polje[][] tabla;
	private int x, y;
	private ArrayList<Polje> izabrana = new ArrayList<>();
	HashSet<Integer> skup = new HashSet<Integer>();
	Igra igra;
	
	public Mreza(Igra ig, int x, int y) {
		igra = ig;
		this.x = x;
		this.y = y;
		tabla = new Polje [x][y];
		this.setLayout(new GridLayout(x, y, 3, 3));
		this.setBackground(Color.BLACK);
		for (int i = 0; i < x * y; i++) {
			Polje p = new Polje(this, i);
			tabla[(int)i/y][i%y] = p;
			this.add(p);
		}
	}
	public Mreza(Igra i) {
		this(i, 4, 5);
	}

	public void selektuj(Polje p) {
		izabrana.add(p);
		skup.add(p.getBroj());
		igra.azuriraj();
	}
	public void odselektuj(Polje p) {
		izabrana.remove(p);
		skup.remove(p.getBroj());
		igra.azuriraj();
	}
	
	public HashSet<Integer> getSkup(){
		return skup;
	}
	
	public int brojPolja() {
		return this.x * this.y;
	}
	
	public ArrayList<Polje> getIzabrana() {
		return izabrana;
	}
	
}
