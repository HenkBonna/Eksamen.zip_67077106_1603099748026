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
		/*
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



		 */

		//Oppgave 5

		/*

		Integer[] c = {1,3,5,7,9,2,4,6,8};
		EksamenSBinTre<Integer> tre5 = new EksamenSBinTre<>(Comparator.naturalOrder());
		for (int verdi : c) tre5.leggInn(verdi);

		ArrayList<Integer> out = tre5.serialize();


		System.out.println("Serialized, toStringPostOrder:");
		System.out.println(tre5.toStringPostOrder());

		 */

		/*
		System.out.println("Serialized:");

		for (Integer i : out){
			System.out.print(i + ", ");
		}

		 */

		/*
		System.out.println();
		EksamenSBinTre<Integer> tre5Deserialized = new EksamenSBinTre<>(Comparator.naturalOrder());

		System.out.println();
		System.out.println("Deserialized, postORder:");
		System.out.println(EksamenSBinTre.deserialize(out, Comparator.naturalOrder()).toStringPostOrder());
		System.out.println();
*/

		///




		int[] v = {4,7,2,9,4,10,8,7,4,6,1};
		EksamenSBinTre<Integer> tre3 = new EksamenSBinTre<>(Comparator.naturalOrder());
		for (int verdi : v) tre3.leggInn(verdi);
		System.out.println(tre3.fjernAlle(4)); // 3
		tre3.fjernAlle(7); tre3.fjern(8);
		System.out.println(tre3.antall()); // 5
		System.out.println(tre3.toStringPostOrder() + " " + tre3.toString());
// [1, 2, 6, 9, 10] [10, 9, 6, 2, 1]
// OBS: Hvis du ikke har gjort oppgave 4 kan du her bruke toString()
		//

	}

}
