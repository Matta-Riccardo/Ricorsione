package it.polito.tdp.ricorsione.model;

import java.util.ArrayList;
import java.util.List;

public class QuadratoMagico {

	private int N;
	private int N2;
	private int sommaCorretta;

	public void risolviQuadrato(int N) {

		this.N = N;
		this.N2 = N * N;
		this.sommaCorretta = N * (N2 + 1) / 2;

		List<Integer> parziale = new ArrayList<>();

		cerca(parziale, 0);

	}

	private void cerca(List<Integer> parziale, int livello) {
		if (livello == this.N2) {
			// caso terminale: già messo N^2 numeri
			if (controllaSomme(parziale)) {
				System.out.println(parziale);
			}
		} else {
			if (livello % this.N == 0 && livello!=0) {
				if(!controllaRiga(parziale, livello/this.N-1)) 
					return ;
			}
			for (int i = 1; i <= this.N2; i++) {
				if (!parziale.contains(i)) {
					// provare ad aggiungere 'i' alla cella 'livello'
					parziale.add(i);
					cerca(parziale, livello + 1);
					parziale.remove(parziale.size() - 1);
				}
			}
		}

	}

	private boolean controllaRiga(List<Integer> parziale, int riga) {
		int s = 0 ;
		for (int col=0; col<this.N; col++) {
			s = s + parziale.get(riga * this.N + col);
		}
		if (s != this.sommaCorretta)
			return false;
		else
			return true;
	}

	private boolean controllaSomme(List<Integer> parziale) {
		// controlla la somma per righe
		for (int riga = 0; riga < this.N; riga++) {
			int s = 0;
			for (int col = 0; col < this.N; col++)
				s = s + parziale.get(riga * this.N + col);
			if (s != this.sommaCorretta)
				return false;
		}

		// controlla la somma per colonna
		for (int col = 0; col < this.N; col++) {
			int s = 0;
			for (int riga = 0; riga < this.N; riga++)
				s = s + parziale.get(riga * this.N + col);
			if (s != this.sommaCorretta)
				return false;
		}

		// diagonale 1
		int s = 0;
		for (int riga = 0; riga < this.N; riga++)
			s = s + parziale.get(riga * this.N + riga);
		if (s != this.sommaCorretta)
			return false;

		// diagonale 2
		s = 0;
		for (int riga = 0; riga < this.N; riga++)
			s = s + parziale.get(riga * this.N + (this.N - 1 - riga));
		if (s != this.sommaCorretta)
			return false;

		return true;
	}
}
