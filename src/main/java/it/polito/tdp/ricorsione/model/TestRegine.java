package it.polito.tdp.ricorsione.model;

import java.util.List;

public class TestRegine {

	public static void main(String[] args) {
//		ReginePrimaSoluzione r = new ReginePrimaSoluzione() ;
//		List<Integer> soluzione = r.cercaRegine(8);
//		System.out.println(soluzione) ;
		
//		System.out.printl(soluzione.size());
		
		Regine r = new Regine();
		List<List<Integer>> soluzione = r.cercaRegine(8);
		for(List<Integer> l : soluzione) {	
			System.out.println(l);
		}
	}
}
