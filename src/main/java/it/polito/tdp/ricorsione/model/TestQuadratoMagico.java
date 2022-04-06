package it.polito.tdp.ricorsione.model;

public class TestQuadratoMagico {

	public static void main(String[] args) {
		QuadratoMagico q = new QuadratoMagico();
		
		System.out.println("Lato 2");
		q.risolviQuadrato(2);
		
		System.out.println("Lato 3");
		q.risolviQuadrato(3);

		System.out.println("Lato 4");
		q.risolviQuadrato(4);


	}

}
