package test;
import algorithm.ForwardDawgMatchig;
import suffixAutomaton.SuffixAutomaton;

public class Test2 {
	
	public static void main(String[] args) {
		
		String pattern= "GCAGAGAG";
		String testo= "GCATCGCAGAGAGTATACAGTACG";
		
		int n = 24;
		int m = 8;
		
		System.out.println("Stampa del testo: "+ testo+"\nLunghezza del testo: "+n+"\n");
		System.out.println("Stampa del pattern: "+ pattern+"\nLunghezza del patern: "+m+"\n");

		ForwardDawgMatchig.FDM(pattern, m, testo, n);
		
		SuffixAutomaton.printSuffixAutomaton(pattern, m);
	}
}
