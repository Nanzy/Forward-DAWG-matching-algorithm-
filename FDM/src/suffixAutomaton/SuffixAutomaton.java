package suffixAutomaton;

import java.util.Iterator;

import graph.AdjacencyListGraph;
import graph.AdjacencyListGraph.MyEdge;
import graph.AdjacencyListGraph.MyVertex;

public class SuffixAutomaton{
	
	public static void builSuffixAutomaton(String x, int m, AdjacencyListGraph graph){
		char c;
		int vertexC= 0;
		MyVertex init = (MyVertex) graph.insertVertex(0);
		graph.setInitial(init);
		vertexC++;
		
		graph.setLength(init, 0); 
		
		MyVertex art = (MyVertex) graph.insertVertex(vertexC);
		
		vertexC++;
		graph.setLength(art, 0);
	
		MyEdge suf1 = (MyEdge) graph.insertSuff(init, art, 's');
		
		MyVertex last = init;
		for(int i = 0; i < m; ++i){
			c = x.charAt(i);
			MyVertex p = last;
			MyVertex q = (MyVertex) graph.insertVertex(vertexC);
			vertexC++;
			graph.setLength(q, graph.getLength(p)+1);
		
			while(p!= init && (graph.getTarget(p, c)== null)){
				graph.setTarget(p, c, q);
				p = (MyVertex) graph.getSuffixLink(p);
			}
			if(graph.getTarget(p, c) == null){
				graph.setTarget(init, c, q);
				graph.insertSuff(q, init, 's'); 
			}
			else{
				if(((graph.getLength(p))+1) == (graph.getLength(graph.getTarget(p, c)))){
					graph.insertSuff(q, graph.getTarget(p, c), 's');	
				}
				else{
					MyVertex r = (MyVertex) graph.insertVertex(vertexC);
					vertexC++;
					graph.copyVertex(r, graph.getTarget(p, c));
					graph.setLength(r, graph.getLength(p)+1); 
					
					graph.removeSuff(graph.getSufEdge(graph.getTarget(p, c)));
					
					graph.insertSuff(graph.getTarget(p, c), r, 's');
					if(graph.getSufEdge(q).element() != null){
						graph.removeSuff(graph.getSufEdge(q));
					}
					graph.insertSuff(q, r, 's');
					
					while(p!= art && graph.getLength(graph.getTarget(p, c)) >= graph.getLength(r)){
						graph.removeEdge(graph.getTargetEdge(p, c));
						graph.setTarget(p, c, r);
						p = (MyVertex) graph.getSuffixLink(p);
					}
					
				}
			
				
			}
			last = q;
		}
		graph.setTerminal(last);
	}
	
	public static void printSuffixAutomaton(String pattern, int m){
		AdjacencyListGraph graph = new AdjacencyListGraph();
		builSuffixAutomaton(pattern, m, graph);
		Iterable vertex = graph.vertices();
		System.out.println("\nGli stati dell'automa sono: ");
		Iterator vIterator = vertex.iterator();
		System.out.print("[ ");
		
		while(vIterator.hasNext())
			System.out.print(vIterator.next()+" ");
		
		System.out.print("]\n");
		Iterator vIterator2 = vertex.iterator();
		while(vIterator2.hasNext()){
			MyVertex v = (MyVertex)vIterator2.next();
			Iterable edges= v.incidentEdges();
			Iterator eIterator = edges.iterator();
			System.out.println("\nStato "+ v+ ", numero di archi uscenti = "+ v.degree());
			while(eIterator.hasNext()){
				MyEdge e = (MyEdge)eIterator.next();
				System.out.println("Etichetta dell'arco = "+ e.element() + ", verso lo stato --> "+ graph.opposite(v, e));
			}
				
		}
		Iterator vIterator3 = vertex.iterator();
		while(vIterator3.hasNext()){
			MyVertex v = (MyVertex)vIterator3.next();
			Iterable suffixLink= v.sufEdges();
			Iterator sIterator = suffixLink.iterator();
			while(sIterator.hasNext()){
				MyEdge s = (MyEdge)sIterator.next();
				System.out.println("\nIl suffix link dello stato "+ v+ " è lo stato "+ graph.opposite(v, s));
			}
		}
		
	}
}
