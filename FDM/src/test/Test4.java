package test;
import algorithm.ForwardDawgMatchig;
import suffixAutomaton.SuffixAutomaton;

public class Test4 {
	
	public static void main(String[] args) {
			
			String pattern= "TCCAAT";
			String testo= "CCTAGGTTGTAGGGCCAAC";
			
			int n = 19;
			int m = 6;
			
			System.out.println("Stampa del testo: "+ testo+"\nLunghezza del testo: "+n+"\n");
			System.out.println("Stampa del pattern: "+ pattern+"\nLunghezza del patern: "+m+"\n");
	
			ForwardDawgMatchig.FDM(pattern, m, testo, n);
			
			SuffixAutomaton.printSuffixAutomaton(pattern, m);
		}


}
