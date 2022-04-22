package it.polito.tdp.ricorsione.model;

import java.util.List;

public class TestRegine {

	public static void main(String[] args) {
		ReginePrimaSoluzione r = new ReginePrimaSoluzione() ;
		List<Integer> soluzione = r.cercaRegine(15);
		System.out.println(soluzione) ;
		//System.out.printl(soluzione.size());
	}

}
