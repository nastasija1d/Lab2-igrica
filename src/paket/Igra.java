package paket;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Igra extends Frame {
	
	Mreza mreza;
	private double balans = 0;
	private double kvota;
	private double dobitak;
	private int ulog = 100;
	private TextField ulogTF = new TextField("100");
	private Label balansL, kvotaL, dobitakL;
	private Generator gen;

	private Panel south;
	private Label broj = new Label("");
	private Button igraj = new Button("Igraj!");
	
	public Igra() {
		setBounds(700, 200, 400, 400);

		setTitle("Igra");
		
		populate();
		pack();
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) { dispose();}
		});
	}

	
	private void populate() {
		mreza = new Mreza(this, 5, 4);
		gen = new Generator(0, mreza.brojPolja());
		add(mreza,BorderLayout.CENTER);
		
		Panel east = new Panel();		
		east.setLayout(new GridLayout(0, 1));
		balans = 0;
		balansL = new Label("Balans: " + balans);
		east.add(balansL);

		Panel p = new Panel();
		p.add(new Label("Ulog: "));
		p.add(ulogTF);
		east.add(p);
		
		ulogTF.addTextListener(new TextListener() {
			@Override
			public void textValueChanged(TextEvent e) {
				if (ulogTF.getText().equals("")) return;
				ulog = Integer.parseInt(ulogTF.getText());
				azuriraj();
			}
		});
		
		kvotaL = new Label(" Kvota: " + kvota);
		east.add(kvotaL);
		dobitakL = new Label(" Dobitak: " + dobitak);
		east.add(dobitakL);
		
		p = new Panel();
		p.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p.add(igraj);
		east.add(p);
		
		igraj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				igraj();				
			}
		});
		
		east.setBackground(Color.LIGHT_GRAY);
		add(east,BorderLayout.EAST);
		
		south = new Panel();
		south.add(broj);
		south.setBackground(Color.GRAY);
		add(south,BorderLayout.SOUTH);
		
	}


	public void azuriraj() {
		int i = mreza.getIzabrana().size();
		if (i == 0) {
			kvota = 0;
			igraj.setEnabled(false);
		}else {
			kvota = (double) mreza.brojPolja() / i;
			igraj.setEnabled(true);
		}
		dobitak = (double) ulog * kvota;
		dobitakL.setText("Dobitak: " + String.format("%.2f", dobitak));
		kvotaL.setText("Kvota: " + String.format("%.2f", kvota));
		balansL.setText("Balans: " + String.format("%.2f", balans));
	}
	
	public void igraj() {
		int i = gen.generisi();
		broj.setText("Broj: " + i);
		broj.revalidate();
		if (mreza.getSkup().contains(i)) {
			south.setBackground(Color.GREEN);
			broj.setBackground(Color.GREEN);
			balans += dobitak;
		}else {
			south.setBackground(Color.RED);
			broj.setBackground(Color.RED);
			balans -= ulog;
		}
		azuriraj();
	}


	public static void main(String[]arg) {
		new Igra();
	}
}
