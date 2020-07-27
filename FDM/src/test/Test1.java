package test;
import algorithm.ForwardDawgMatchig;
import suffixAutomaton.SuffixAutomaton;

public class Test1 {

	public static void main(String[] args) {
		
		String pattern= "CCTCTCTTT";
		String testo= "AAAAAGGGGGGGGGGGGGGGAAAAA";
		
		int n = 25;
		int m = 9;
		
		System.out.println("Stampa del testo: "+ testo+"\nLunghezza del testo: "+n+"\n");
		System.out.println("Stampa del pattern: "+ pattern+"\nLunghezza del patern: "+m+"\n");

		ForwardDawgMatchig.FDM(pattern, m, testo, n);
		
		SuffixAutomaton.printSuffixAutomaton(pattern, m);
	}

}
