package nodelist;


import java.util.Iterator;
import java.util.NoSuchElementException;

import position.Position;

public class ElementIterator<E> implements Iterator<E>{
	  protected PositionList<E> list; // the underlying list
	  protected Position<E> cursor; // the next position
	  /** Creates an element iterator over the given list. */
	  public ElementIterator(PositionList<E> L) {
	    list = L;
	    cursor = (list.isEmpty())? null : list.first();
	  }
//	end#fragment Iterator
	  /** Returns whether the iterator has a next object. */
//	begin#fragment Iterator
	  public boolean hasNext() { return (cursor != null);  }
//	end#fragment Iterator
	  /** Returns the next object in the iterator. */
//	begin#fragment Iterator
	  public E next() throws NoSuchElementException {
	    if (cursor == null)
	      throw new NoSuchElementException("No next element");
	    E toReturn = cursor.element();
	    cursor = (cursor == list.last())? null : list.next(cursor);
	    return toReturn;
	  }
	public void remove() {
		// TODO Auto-generated method stub
		
	}
}
