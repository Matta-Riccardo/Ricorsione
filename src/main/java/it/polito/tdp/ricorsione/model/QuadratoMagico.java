package it.polito.tdp.ricorsione.model;

import java.util.ArrayList;
import java.util.List;

public class QuadratoMagico {

	private int N;
	private int N2;
	private int sommaCorretta;

	public void risolviQuadrato(int N) {  // metodo che riceve come parametro il valore N del lato 

		this.N = N;
		this.N2 = N * N;
		this.sommaCorretta = N * (N2 + 1) / 2;

		List<Integer> parziale = new ArrayList<>();

		cerca(parziale, 0);

	}

	private void cerca(List<Integer> parziale, int livello) { // procedura ricorsiva che riceve una saluzione parziale e il livello
		if (livello == this.N2) {
			// caso terminale: già messo N^2 numeri
			
			if (controllaSomme(parziale)) { // se lo somme sono giuste stampo la soluzione parziale, altrimenti provo ad andare avanti con la ricorsione
				                            // provando ad aggiungere ogni volta un numero in più tra quelli non ancora utilizzati
				System.out.println(parziale);
			}
		} else {
			if (livello % this.N == 0 && livello!=0) { // terminata una riga io posso subito controllare se ha senso scendere nei successivi livelli
				                                       // o se non avendo la somma giusta fermarmi subito ed evitare di perdere tempo.
				                                       // controllo che livello/this.N da resto zero, ovvero (livello = N)
				
				if(!controllaRiga(parziale, livello/this.N-1)) 
					return ;
			}
			
			for (int i = 1; i <= this.N2; i++) { // devo controllare che la soluzione non contenga gia il numero i
				if (!parziale.contains(i)) {
					// provare ad aggiungere 'i' alla cella 'livello' (itero sui valori possibili e provo una alla volta a aggiungerli all'array controllando non esistano duplicati)
					parziale.add(i);
					cerca(parziale, livello + 1);  // procedo con la ricorsione e faccio il backtraking
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
		// dovrò fare N-controlli per riga, N-controlli per colonna, e due controlli in più per fare le verifiche sulle due diagonali
		
		// controlla la somma per righe
		for (int riga = 0; riga < this.N; riga++) {
			int s = 0;
			for (int col = 0; col < this.N; col++)
				s = s + parziale.get(riga * this.N + col); // per passare da una rappresentazione di quadrato a un semplice array
			                                               // [es. quadrato 3x3]
			                                               // riga 0 = get di (0 per colonna 0, 1, per colonna 1, 2 per colonna 2);
			                                               // riga 1 = get di (3 per colonna 0, 4 per colonna 1, 5 per colonna 2);
			                                               // riga 2 = get di (6 per colonna 0, 7 per colonna 2, 8 per colonna 2). 
			if (s != this.sommaCorretta)
				return false;
		}

		// controlla la somma per colonna (stessa procedura della precedente, scambio riga con col)
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
