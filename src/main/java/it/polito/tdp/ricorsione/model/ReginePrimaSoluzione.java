/*
package it.polito.tdp.ricorsione.model;

import java.util.ArrayList;
import java.util.List;

public class ReginePrimaSoluzione {
	
	List<Integer> soluzione;

	public List<Integer> cercaRegine(int N) {
		this.soluzione = null ;
		List<Integer> parziale = new ArrayList<Integer>() ;
		regine_ricorsiva(parziale, 0, N);	
		return this.soluzione;
	}
	
	private boolean regine_ricorsiva(List<Integer> parziale, int livello, int N ) {
		if(livello==N) { // caso terminale
//			System.out.println(parziale);
			this.soluzione = new ArrayList<Integer>(parziale);
			return false ; // non continuare!
		} else {
			// ho già parziale[0] fino a parziale[livello-1] già decise
			// devo decidere parziale[livello] tra tutti i valori possibili
			// da 0 a N-1 (colonne), purché compatibili
			for(int col=0; col<N; col++) {
				if(compatibile(livello, col, parziale)) {
					parziale.add(col);
					boolean continua = regine_ricorsiva(parziale, livello+1, N);
					if(!continua)
						return false;
					parziale.remove(parziale.size()-1); // backtracking
				}
			}
			return true ;
		}
	}
	
	private boolean compatibile(int livello, Integer col, List<Integer> parziale) {
		if (parziale.indexOf(col) != -1)
			return false;
		for(int riga = 0; riga < parziale.size(); riga++ )  {
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

*/


package it.polito.tdp.ricorsione.model;

import java.util.ArrayList;
import java.util.List;

public class ReginePrimaSoluzione {
	
	private List<Integer> soluzione;

	//questa procedura serve per avviare la ricorsione (per la prima iterazione)
    public List<Integer> cercaRegine(int N) {
		this.soluzione = null ;
		List<Integer> parziale = new ArrayList<Integer>() ;
		regine_ricorsiva(parziale, 0, N);	
		return this.soluzione;
	}
	
	// questa procedura è boolean perchè oltre a passare la soluzione parziale alla chiamata ricorsiva passo anche un booleano, e appena il boolean ritorna false
    // vorrà dire che ho trovato la mia soluzione e quindi non avrà senso continuare e posso finire li la ricerca ricorsiva delle soluzioni.
	private boolean regine_ricorsiva(List<Integer> parziale, int livello, int N ) {  //(soluzione parziale, livello a cui sono arrivato, dimensione scacchiera,
																					  // il livello corrisponde al numero della riga cui sono arrivato)
		
		if(livello==N) { 
		
	    // CASO TERMINALE: ho trovato una soluzione parziale che sarà ora anche quella finale
   
		// System.out.println(parziale);	
		this.soluzione = new ArrayList<Integer>(parziale);
		return false; // non continuare!
		
		} else {
			
			//CASO NORMALE
			// da parziale[0] fino a parziale[livello-1] le soluzioni parziali sono già decise
			// devo decidere parziale[livello] tra tutti i valori possibili che vanno
			// da 0 a N-1 (che sono le colonne), purché compatibili
			// la compatibilità per riga è data da costruzione (ne sto mettendo una per riga)
			// mentre la compatibilità per diagonali e colonne la dovrò verificare.
			
			for(int col=0; col<N; col++) {   // vado a provare tutte le posizioni: in pratica mi chiedo
				                             // ciclicamente, scorrendo le varie posizioni disponibili, 
				                             // se posso in quel punto piazzare una regina in base a quello 
				                             // che so già e indipendetemente da ciò che verrà.
				if(compatibile(livello, col, parziale)) {
					parziale.add(col);                      
					boolean continua = regine_ricorsiva(parziale, livello+1, N);   // se la colonna che sto tentando è compatibile con le posizioni 
					                                            // delle regine gia sistemate, allora faccio ricorsione 
					                                            // (= passo a una procedura ricorsiva, sempre lei stessa, una versione 
					                                            // del problema dove ho fatto questa scelta, passo una soluzione 
					                                            // parziale con le regine di prima più quella aggiunta ora, e aggiunta 
					                                            // la regina chiamo il metodo regina ricorsiva chiedendo di risolvere 
																// il problema al livello sucessivo) >> GENERO IL SOTTO-PROBLEMA. 
					if(!continua) {
						return false;
					}
					
					parziale.remove(parziale.size()-1); // BACKTRACKING (ritorno sui miei passi: quando il ciclo for passa all'iterazione 
					                                    // successiva devo rimettere le cose a posto e togliere ciò che ho aggiunto).
				}
			}
			return true;
		}
	}
	
	private boolean compatibile(int livello, Integer col, List<Integer> parziale) { // int livello = valore corrispondente la riga alla quale si trova il valore
																					// che voglio aggiungere alla soluzione parziale.
		
		// incompatibilità = quando cerco di mettere una regina in una colonna nella quale c'era già una regina nelle righe precedenti,
		// se nalla lista parziale esiste già il valore non andrà bene.
		
		if (parziale.indexOf(col) != -1)  // se entrò qui vorrà dire che nella soluzione parziale esiste già una regina nella posizione col, quindi non andrà bene.
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
			
			if(riga + parziale.get(riga) == livello+col)  // la somma delle coordinate (x+y) dei punti che formano la diagonale crescente è costante.
				return false;
			if(riga - parziale.get(riga) == livello-col)  // la differenza delle coordinate (x-y) dei punti che formano la diagonale derescente è costante.
				return false;
		}
		return true ;
	}
	
}
 

