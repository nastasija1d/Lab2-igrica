package paket;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Polje extends Canvas {

	public enum statusi{SLOBODNO, IZABRANO};
	
	private Mreza mreza;
	private int broj;
	private statusi status;
	
	public Polje(Mreza m, int i) {
		this.setPreferredSize(new Dimension(75, 75));
		mreza = m;
		broj = i;
		status = statusi.SLOBODNO;
		this.setBackground(Color.ORANGE);
		repaint();
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (status == statusi.IZABRANO) {
					status = statusi.SLOBODNO;
					mreza.odselektuj(Polje.this);
				}else {
					status = statusi.IZABRANO;
					mreza.selektuj(Polje.this);
				}
				repaint();
				revalidate();
			}
		});
	}
	
	@Override
	public void paint(Graphics g) {
		int dim = Math.min(this.getWidth(), this.getHeight());
		int w = dim/2 - g.getFontMetrics().stringWidth(((Integer)broj).toString())/2;
		int h = dim/2 + g.getFontMetrics().getHeight()/2;
		g.setColor(Color.BLACK);
		if (status == statusi.IZABRANO) {
			g.setColor(Color.BLUE);
			g.fillOval(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.white);
		}
		
		g.setFont(new Font("Arial", Font.BOLD,dim / 3));
		g.drawString(""+broj, w, h);
	}
	
	public statusi getStatus() {
		return status;
	}
	public int getBroj() {
		return broj;
	}
	
	
	
}
