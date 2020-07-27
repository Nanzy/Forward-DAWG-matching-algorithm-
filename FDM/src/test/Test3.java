package test;
import algorithm.ForwardDawgMatchig;
import suffixAutomaton.SuffixAutomaton;

public class Test3 {
		
	public static void main(String[] args) {
			
			String pattern= "AGAGGA";
			String testo= "TCCCAGAGGATCCT";
			
			int n = 14;
			int m = 6;
			
			System.out.println("Stampa del testo: "+ testo+"\nLunghezza del testo: "+n+"\n");
			System.out.println("Stampa del pattern: "+ pattern+"\nLunghezza del pattern: "+m+"\n");
	
			ForwardDawgMatchig.FDM(pattern, m, testo, n);
			
			SuffixAutomaton.printSuffixAutomaton(pattern, m);
		}

}
