package no.oslomet.cs.algdat.Eksamen;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {

	public static void main(String[] args){
		/* Oppgave 0
		EksamenSBinTre<Character> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
		System.out.println(tre.antall());
		*/

		/* Oppgave 1
		Integer[] a = {4,7,2,9,5,10,8,1,3,6};
		EksamenSBinTre<Integer> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
		for (int verdi : a) tre.leggInn(verdi);
		System.out.println(tre.antall()); // Utskrift: 10
		 */

		// Oppgave 2

		Integer[] a = {4,7,2,9,4,10,8,7,4,6};
		EksamenSBinTre<Integer> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
		for (int verdi : a) tre.leggInn(verdi);
		System.out.println(tre.antall()); // Utskrift: 10
		System.out.println(tre.antall(5)); // Utskrift: 0
		System.out.println(tre.antall(4)); // Utskrift: 3
		System.out.println(tre.antall(7)); // Utskrift: 2
		System.out.println(tre.antall(10)); // Utskrift: 1

		System.out.println();

		Integer[] b = {5,5,5,5,5,5,5,5,6};
		EksamenSBinTre<Integer> tre2 = new EksamenSBinTre<>(Comparator.naturalOrder());
		for (int verdi : b) tre2.leggInn(verdi);
		System.out.println(tre2.antall());

		//Oppgave 5
		/*
		Integer[] a = {1,3,5,7,9,2,4,6,8};
		EksamenSBinTre<Integer> tre = new EksamenSBinTre<>(Comparator.naturalOrder());
		for (int verdi : a) tre.leggInn(verdi);

		ArrayList<Integer> out = tre.serialize();

		System.out.println();

		System.out.println();
		tre.toStringPostOrder();

		System.out.println();

		for (Integer i : out){
			System.out.print(i + ", ");
		}

		 */


		//

	}

}
