package graph;

import position.Position;

import java.util.ArrayList;
import java.util.Iterator;

import graph.AdjacencyListGraph.MyEdge;
import graph.AdjacencyListGraph.MyVertex;
import lasdException.InvalidPositionException;
import map.*;
import nodelist.*;

public class AdjacencyListGraph<V,E> implements Graph<V,E> {

	  protected NodePositionList<Vertex<V>> VList;	// container for vertices
	  protected NodePositionList<Edge<E>> EList;	// container for edges
	 
	  /** Default constructor that creates an empty graph */
	 
	  public AdjacencyListGraph() {
	    VList = new NodePositionList<Vertex<V>>();
	    EList = new NodePositionList<Edge<E>>();
	  }
	  /** Return an iterator over the vertices of the graph */
	  public Iterable<Vertex<V>> vertices() {
	    return VList;
	  }
	  /** Return an iterator over the edges of the graph */
	  public Iterable<Edge<E>> edges() {
	    return EList;
	  }
	  
	  /** Implementation of a decorable position by means of a hash
	   * table. */
	  public static class MyPosition<T>
	    extends HashTableMap<Object,Object> implements DecorablePosition<T> {
	    /** The element stored at this position. */
	    protected T elem;
	    
	    /** Returns the element stored at this position. */
	    public T element() {
	      return elem;
	    }
	    /** Sets the element stored at this position. */
	    public void setElement(T o) {
	      elem = o;
	    }
	  }
	  
	  
	  /** Implementation of a vertex for an undirected adjacency list
	   * graph. Each vertex stores its incidence container and position
	   * in the vertex container of the graph. */
	  
	  public class MyVertex<V> extends MyPosition<V> implements Vertex<V> {
	    /** Incidence container of the vertex. */
	    protected PositionList<Edge<E>> incEdges;
	    /** Incidence container of the suffix link of the vertex. */
	    protected PositionList<Edge<E>> sufEdges;
	    /** Position of the vertex in the vertex container of the graph. */
	    protected Position<Vertex<V>> loc;
	    /** Vertex attributes. */
	    protected int length, terminal, initial;
	    
	    MyVertex(V o) {
	      elem = o;
	      terminal = 0;
	      initial= 0;
	      length=0;
	      incEdges = new NodePositionList<Edge<E>>();
	      sufEdges = new NodePositionList<Edge<E>>(); 
	    }
	    
	    
	    /** Return the degree of a given vertex */
	    public int degree() {
	      return incEdges.size();
	    }
	    /** Returns the suffix link of this vertex. */
	    public Iterable<Edge<E>> sufEdges() {
	      return sufEdges;
	    }
	    

	    /** Inserts a suffix link into the suffix container of this vertex.  */
	    public Position<Edge<E>> insertSuf(Edge<E> e) {
	      sufEdges.addLast(e);
	      return sufEdges.last();
	    }

	    /** Removes a suffix link from the suffix container of this vertex.  */
	    public void removeSuf(Position<Edge<E>> p) {
	      sufEdges.remove(p);
	    }
	   
	    /** Returns the incident edges on this vertex. */
	    public Iterable<Edge<E>> incidentEdges() {
	      return incEdges;
	    }
	    /** Inserts an edge into the incidence container of this vertex. */
	    public Position<Edge<E>> insertIncidence(Edge<E> e) {
	      incEdges.addLast(e);
	      return incEdges.last();
	    }
	    /** Removes an edge from the incidence container of this vertex. */
	    public void removeIncidence(Position<Edge<E>> p) {
	      incEdges.remove(p);
	    }
	    /** Returns the position of this vertex in the vertex container of
	     * the graph. */
	    public Position<Vertex<V>> location() {
	      return loc;
	    }
	    /** Sets the position of this vertex in the vertex container of
	     * the graph. */
	    public void setLocation(Position<Vertex<V>> p) {
	      loc = p;
	    }
	    
	    
	    /** Returns a string representation of the element stored at this
	     * vertex. */
	    public String toString() {
	      return elem.toString();
	    }
	  }
	  
	  /** Implementation of an edge for a directed adjacency list
	   * graph.  Each edge stores its endpoints (end vertices), its
	   * position within the incidence container of its endpoint, and
	   * position in the edge container of the graph. */
	  public class MyEdge<E> extends MyPosition<E> implements Edge<E> {

	    /** The end vertices of the edge. */
	    protected MyVertex<V>[] endVertices;
	    /** The position of the entries for the edge in the incidence
	     * container of the end vertice. */
	    protected Position<Edge<E>>[] Inc; 
	    /** The position of the edge in the edge container of the
	     * graph. */
	    protected Position<Edge<E>> loc;
		
	    protected boolean type; 

	    /** Constructs an edge with the given endpoints and elements. */
	    MyEdge (Vertex<V> v, Vertex<V> w, E o) {
	      elem = o;
	      type = false;
	      endVertices = (MyVertex<V>[]) new MyVertex[2];
	      endVertices[0] = (MyVertex<V>)v;
	      endVertices[1] = (MyVertex<V>)w;
	      Inc = (Position<Edge<E>>[]) new Position[1]; 
	    }
	    /** Checks if an edge is from the suffix link function or it is an edge of 
	     * the delta transition function of the automaton 
	     */
	    public boolean isSuffix(){
	    	if(type)
	    		return true;
	    	else
	    		return false;
	    }
	    
	    public E getElement(){
	    	return elem;
	    }
	    /**
	     * Sets to true the attribute type of the edge
	     */
	    public void setSuffix(boolean isSuffix){
	    	type= true;
	    }
	    
	    /** Returns the end vertices of the edge. */
	    public MyVertex<V>[] endVertices() {
	      return endVertices;
	    }
	    /** Returns the positions of the edge in the incidence containers
	     * of its end vertices.*/
	    public Position<Edge<E>>[] incidences() {
	      return Inc;
	    }
	    /** Sets the position of the edge in the incidence container of
	     * its end vertice. */
	    public void setIncidences(Position<Edge<E>> pv) {
	      Inc[0] = pv; 
	      //Inc[1] = pw; 
	    }
	    /** Returns the position of the edge in the edge container of the
	     * graph. */
	    public Position<Edge<E>> location() {
	      return loc;
	    }
	    /** Sets the position of the edge in the edge container of the
	     * graph. */
	    public void setLocation(Position<Edge<E>> p) {
	      loc = p;
	    }
	    /** Returns a string representation of the edge via a tuple of
	     * vertices. */
	    public String toString() {
	      return "(" + endVertices[0].toString() + ")";
	    }
	  }
	  
	public boolean areAdjacent(Vertex<V> u, Vertex<V> v) throws InvalidPositionException {
		Iterable<Edge<E>> iterToSearch;
		iterToSearch = incidentEdges(u);
		
		for (Edge<E> e: iterToSearch){
			Vertex<V>[] endV = endVertices(e);
			if (((endV[0] == u) && (endV[1] == v))){
				return true;
			}
		
		}
		return false;
	}
	
	public Vertex<V>[] endVertices(Edge<E> e) throws InvalidPositionException {
		MyEdge<E> ee = checkEdge(e);
		return ee.endVertices;
	}
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidPositionException {
		MyVertex<V> vv = checkVertex(v);
		return vv.incidentEdges();
	}
	
	public Iterable<Edge<E>> suffEdges(Vertex<V> v) throws InvalidPositionException {
		MyVertex<V> vv = checkVertex(v);
		return vv.sufEdges();
	}
	
	public int degree(Vertex<V> v) throws InvalidPositionException {
		MyVertex<V> vv = checkVertex(v);
		return vv.degree();
	}
	public Vertex<V> insertVertex(V o) {
	    MyVertex<V> vv =  new MyVertex<V>(o);
	    VList.addLast(vv);
	    Position<Vertex<V>> p = VList.last();
	    vv.setLocation(p);
	    return vv;
	}
	public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E o) throws InvalidPositionException {
	    MyVertex<V> uu = checkVertex(u);
	    MyVertex<V> vv = checkVertex(v); 
	    MyEdge<E> ee = new MyEdge<E>(u, v, o);
	    Position<Edge<E>> pu = uu.insertIncidence(ee);
	    ee.setIncidences(pu);
	    EList.addLast(ee);
	    Position<Edge<E>> pe = EList.last();
	    ee.setLocation(pe);
	    return ee;
	}
	

	public Edge<E> insertSuff(Vertex<V> u, Vertex<V> v, Character c) throws InvalidPositionException {
	    MyVertex<V> uu = checkVertex(u);
	    
	    MyVertex<V> vv = checkVertex(v);
	    MyEdge<E> ee = new MyEdge<E>(u, v, (E)c);
	    ee.setSuffix(true);
	    Position<Edge<E>> pu = uu.insertSuf(ee);
	    ee.setIncidences(pu);
	    EList.addLast(ee);   
	    Position<Edge<E>> pe = EList.last();
	    ee.setLocation(pe); 
	    return ee;
	}
	
	public E removeSuff(Edge<E> e) throws InvalidPositionException {
	    MyEdge<E> ee = checkEdge(e);
	    MyVertex<V>[] endv = ee.endVertices(); 
	    Position<Edge<E>>[] inc = ee.incidences();
	    endv[0].removeSuf(inc[0]);
	    EList.remove(ee.location()); 
	    ee.setLocation(null);	// invalidating this edge
	    return e.element();
	}
	
	
	public Vertex<V> getSuffixLink(Vertex<V> v){
		MyVertex<V> vv = checkVertex(v);
		MyVertex<V> uu= null;
		Iterable<Edge<E>> list = vv.sufEdges();
		Iterator<Edge<E>> iterator = list.iterator();
		while(iterator.hasNext()){
		    MyEdge<E> e = (MyEdge<E>)iterator.next();
		    if(opposite(vv, e) != null) 
		    	uu = (MyVertex<V>) opposite(vv, e);
		  }
		   if(uu != null)
		    return uu;
		   else return null;
	}
	
	 public Edge<E> getSufEdge(Vertex<V> v) throws InvalidPositionException{
		    MyVertex<V> vv =  checkVertex(v);
		    MyEdge<E> ee = new MyEdge<E>(null, null, null);
		    Iterable<Edge<E>> list = vv.sufEdges();
		    Iterator<Edge<E>> iterator = list.iterator();
		    while(iterator.hasNext()){
		    	MyEdge<E> e = (MyEdge<E>)iterator.next();
		    	ee=e;
				  
		    }
		    return ee;
		}
	
	
	public int numEdges() {
		return EList.size();
	}
	
	public int numVertices() {
		return VList.size();
	}
	

	
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidPositionException {
	    checkVertex(v);
	    MyEdge<E> ee = checkEdge(e);
	    Vertex<V>[] endv = ee.endVertices();
	    if (v == endv[0])
	        return endv[1];
	    else if (v == endv[0] && v == endv[1])
	    	return endv[0];
	      else
	    	  return null;
	}
	
	private MyVertex<V> checkVertex(Vertex<V> v) {
	    if (v == null || !(v instanceof MyVertex))
	        throw new InvalidPositionException("Vertex is invalid");
	    return (MyVertex<V>) v;
	}
	
	private MyEdge<E> checkEdge(Edge<E> e) {
	    if (e== null || !(e instanceof MyEdge))
	        throw new InvalidPositionException("Edge is invalid");
		return (MyEdge<E>) e;
	}
	
	public E removeEdge(Edge<E> e) throws InvalidPositionException {
	    MyEdge<E> ee = checkEdge(e);
	    MyVertex<V>[] endv = ee.endVertices();
	    Position<Edge<E>>[] inc = ee.incidences();
	    endv[0].removeIncidence(inc[0]);
	    EList.remove(ee.location());
	    ee.setLocation(null);	// invalidating this edge
	    return e.element();
	}
	
	public V removeVertex(Vertex<V> v) throws InvalidPositionException {
	    MyVertex<V> vv = checkVertex(v);
	    Iterable<Edge<E>> iterToSearch = incidentEdges(v); 
	    for (Edge<E> e : iterToSearch)
	    	if (((MyEdge<E>)e).location() != null) // if the edge has not been marked invalid
		        removeEdge(e);
	    VList.remove(vv.location());
	    return v.element();
	}
	
	public V replace(Vertex<V> p, V o) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public E replace(Edge<E> p, E o) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getLength(Vertex<V> v){
		MyVertex<V> vv = checkVertex(v);
		return vv.length;
	}

	public void setLength(Vertex<V> v, int el){
		MyVertex<V> vv = checkVertex(v);
		vv.length = el;
	}
	
	/** Returns the opposite vertex of the given vertex v, 
	* if the edge thath links them is labelled by c */
	public Vertex<V> getTarget(Vertex<V> v, Character c){
		MyVertex<V> vv =  checkVertex(v);
		MyEdge<E> ee = new MyEdge<E>(null, null, null);
		Iterable<Edge<E>> list = vv.incidentEdges();
		Iterator<Edge<E>> iterator = list.iterator();
		while(iterator.hasNext()){
			MyEdge<E> e = (MyEdge<E>)iterator.next();
		    if(e.getElement() == c)
		    	ee = e;
		}
		if(ee != null)
			return opposite(vv, ee);
		else return null;
		   
	}
	
	/** Returns the edge labelled by c if 
	 * there is a vertex opposite to the given vertex v */
	public Edge<E> getTargetEdge(Vertex<V> v, Character c) throws InvalidPositionException{
		MyVertex<V> vv =  checkVertex(v);
		MyEdge<E> ee = new MyEdge<E>(null, null, null);
		Iterable<Edge<E>> list = vv.incidentEdges();
		Iterator<Edge<E>> iterator = list.iterator();
		while(iterator.hasNext()){
			MyEdge<E> e = (MyEdge<E>)iterator.next();
		    if(e.getElement() == c)
		    	ee = e;
		}
		return ee;    
	}
	    
	/** Creates an edge labelled by c that links vertx v and vertex u */
	public Edge<E> setTarget(Vertex<V> v, Character c, Vertex<V> u) throws InvalidPositionException{
		MyVertex<V> vv =  checkVertex(v);
		MyVertex<V> uu =  checkVertex(u);    
		return insertEdge(vv, uu, (E) c);    
	}
	  
	public void copyVertex(Vertex<V> target, Vertex<V> source){
		MyVertex ss = checkVertex(source);
		MyVertex tt = checkVertex(target);
		tt.setLocation(ss.location()); 
		Iterable<Edge<E>> list = ss.incidentEdges();
		Iterator<Edge<E>> iterator = list.iterator();
		while(iterator.hasNext()){
			MyEdge<E> e = (MyEdge<E>)iterator.next();
			MyVertex<V> vv = (MyVertex<V>) opposite(ss, e);
			setTarget(tt,  (Character) e.getElement(), vv);
		}
		Iterable<Edge<E>> sufList = ss.sufEdges();
		Iterator<Edge<E>> sufIterator = sufList.iterator();
		while(sufIterator.hasNext()){
			MyEdge<E> e = (MyEdge<E>)sufIterator.next();
			MyVertex<V> vv = (MyVertex<V>) opposite(ss, e);
			insertSuff(tt, vv, 's');
		 }	 
	}

	/** Returns true if v is a terminal vertex */
	public boolean isTerminal(Vertex<V> v){
		MyVertex<V> vv= checkVertex(v);
		if(vv.terminal == 0)return false;
	    	else return true;
	}
	  
	public void setTerminal(Vertex<V> v){
		MyVertex<V> vv= checkVertex(v);
		vv.terminal = 1;
	}
	  
	public Vertex<V> getTerminal(){
		Iterable<Vertex<V>> vertici = vertices();
		Vertex<V> vv = new MyVertex(null);
		for(Vertex v : vertici){
			if (isTerminal(v))
				vv = v;
		}
		return vv;
	}
	  
	public boolean isInitial(Vertex<V> v){
		MyVertex<V> vv= checkVertex(v);
		if(vv.initial == 0)return false;
	    	else return true;
	}
	  
	public void setInitial(Vertex<V> v){
		MyVertex<V> vv= checkVertex(v);
		vv.initial = 1;
	}
	
	public Vertex<V> getInitial(){
		Iterable<Vertex<V>> vertici = vertices();
		Vertex<V> vv = new MyVertex(null);
		for(Vertex v : vertici){
			if (isInitial(v))
				vv = v;
		}
		return vv;
	}	  
}
