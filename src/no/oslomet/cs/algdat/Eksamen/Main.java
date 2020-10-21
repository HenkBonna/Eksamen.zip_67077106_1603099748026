package no.oslomet.cs.algdat.Eksamen;

import java.util.Comparator;

public class Main {

	public static void main(String[] args){
		// Oppgave 0
		//EksamenSBinTre<Character> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
		//System.out.println(tre.antall());
		//


		// Oppgave 1
		Integer[] a = {4,7,2,9,5,10,8,1,3,6};
		EksamenSBinTre<Integer> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
		for (int verdi : a) tre.leggInn(verdi);
		System.out.println(tre.antall()); // Utskrift: 10
		//

	}

}
