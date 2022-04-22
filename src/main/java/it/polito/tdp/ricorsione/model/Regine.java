package it.polito.tdp.ricorsione.model;

import java.util.ArrayList;
import java.util.List;

public class Regine {
	
	private List<List<Integer>> tutte ;

	//questa procedura serve per avviare la ricorsione (per la prima iterazione)
	public List<List<Integer>> cercaRegine(int N) {   
		this.tutte = new ArrayList<List<Integer>>();
		List<Integer> parziale = new ArrayList<Integer>() ;
		regine_ricorsiva(parziale, 0, N);	
		return this.tutte ;
		
	}
	
	//questa procedura serve per il caso normale e per quello terminale
	private void regine_ricorsiva(List<Integer> parziale, int livello, int N ) {  //(soluzione parziale, livello a cui sono arrivato, dimensione scacchiera)
		
		if(livello==N) { 
		
	    // CASO TERMINALE: ho trovato una soluzione parziale che sarà ora anche quella finale
   
		//System.out.println(parziale);	
		this.tutte.add(new ArrayList<Integer>(parziale));
		
		} else {
			
			//CASO NORMALE
			// da parziale[0] fino a parziale[livello-1] le soluzioni parziali sono già decise
			// devo decidere parziale[livello] tra tutti i valori possibili che vanno
			// da 0 a N-1 (che sono le colonne colonne), purché compatibili
			// la compatibilità per riga è data da costruzione (ne sto mettendo una per riga)
			// mentre la compatibilità per diagonali e colonne ladovrò verificare.
			
			for(int col=0; col<N; col++) {   // vado a provare tutte le posizioni: in pratica mi chiedo
				                             // ciclicamente, scorrendo le varie posizioni disponibili, 
				                             // se posso in quel punto piazzare una regina in base a quello 
				                             // che so già e indipendetemente da ciò che verrà.
				if(compatibile(livello, col, parziale)) {
					parziale.add(col);                      
					regine_ricorsiva(parziale, livello+1, N);   // se la colonna che sto tentando è compatibile con le posizioni 
					                                            // delle regine gia sistemate, allora faccio ricorsione 
					                                            // (= passo a una procedura ricorsiva, sempre lei stessa, una versione 
					                                            // del problema dove ho fatto questa scelta, passo una soluzione 
					                                            // parziale con le regine di prima più quella aggiunta ora, e aggiunta 
					                                            // la regina chiamo il metodo regina ricorsiva chiedendo di risolvere 
																// il problema al livello sucessivo) >> GENERO IL SOTTO-PROBLEMA. 
					
					parziale.remove(parziale.size()-1); // BACKTRACKING (ritorno sui miei passi: quando il ciclo for passa all'iterazione 
					                                    // successiva devo rimettere le cose a posto e togliere ciò che ho aggiunto).
				}
			}
		}
	}
	
	private boolean compatibile(int livello, Integer col, List<Integer> parziale) {
		
		// incompatibilità = quando cerco di mettere una regina in una colonna nella quale c'era già una regina nelle righe precedenti,
		// se nalla lista parziale esiste già il valore non andrà bene
		
		if (parziale.indexOf(col) != -1)  // se entrò qui vorrà dire che nella soluzione parziale esiste già una regina nella posizione col, quindi non andrà bene
			return false;
		
		// ho verificato le colonne ora devo verificare le diagonali, quindi se la regina che voglio mettere risulti essere sulla diagonale crescente
		// o decrescente di una regina già sistemata in precedenza, in tal caso non andrà bene. 
		// La somma delle cordinate degli elementi posizionati su una qualsiasi diagonale decrescente è costante, mentre la sottrazione tra la prima cordinata(riga)
		// e la seconda(colonna) ha anch'essa valore sempre costante, quindi verifico (sommando e sottraendo) se le mie cordinate dove voglio posizionare le regine 
		// matchano con i valori di somma e sottrazione di cordinate di regine già inserite, dove la coordinata che indica la riga è data "riga" e la coordinata
		// che indica la colonna è data dalla posizione contenuta nell'array delle soluzioni parziali ovvero "parziale.get(riga)".
		
		
		for(int riga = 0; riga < parziale.size(); riga++ )  {  // vado a fare il confronto analizzando tutte le righe sulle quali sono già state posizionate delle regine
			
			// regina alle coordinate (R,C)=( riga, parziale.get(riga) )
			// confrontare con (R,C)=(livello, col)
			
			if(riga + parziale.get(riga) == livello+col)
				return false;
			if(riga - parziale.get(riga) == livello-col)
				return false;
		}
		return true ;
	}
	
}
