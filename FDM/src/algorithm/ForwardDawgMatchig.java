package algorithm;

import graph.AdjacencyListGraph;
import graph.Vertex;
import suffixAutomaton.SuffixAutomaton;

public class ForwardDawgMatchig {

	public static void FDM(String x, int m, String y, int n){
		
		int j, ell=0, fact=0;
		int[] temp = new int[n+1];
		Vertex init, state;
		
		AdjacencyListGraph grafo = new AdjacencyListGraph();
		
		SuffixAutomaton.builSuffixAutomaton(x, m, grafo);
		
		init = grafo.getInitial();
		state = init;
		
		for(j = 0; j < n; ++j){
			if(grafo.getTarget(state, y.charAt(j)) != null){
				++ell; 
				state = grafo.getTarget(state, y.charAt(j));
				if(j == n-1){
					temp[n]=ell;
				}
			}
			else{
				while((state != init) && (grafo.getTarget(state, y.charAt(j)) == null)){
					state = grafo.getSuffixLink(state);
					}
					if(grafo.getTarget(state, y.charAt(j))!= null){
						
						temp[j] = ell;
						ell = grafo.getLength(state)+1;
						if(j == n-1){
							temp[n]=ell;
						}
						state = grafo.getTarget(state, y.charAt(j));
					}
					else{
						temp[j] = ell;
						if(j == n-1){
							temp[n]=ell;
						}
						ell = 0;
						state = init;
					}
				}
			if(ell == m)
				System.out.println("Posizione in cui compare un'occorrenza intera del pattern: "+ (j-m+1)+ "\n");
			}
			for(int i = 0; i < n+1; ++i){
				if(temp[i]!=0){
					fact++;
					System.out.println("Posizione del fattore del pattern nel testo: "+ (i-temp[i]));
					System.out.println("Lunghezza del fattore: "+ temp[i]+"\n");
				}
			}
			if(fact == 0 )
				System.out.println("Non ci sono fattori del pattern nel testo.");
			
		}
}
