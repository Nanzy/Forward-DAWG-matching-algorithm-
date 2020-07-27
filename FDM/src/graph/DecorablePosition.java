package graph;

import position.Position;
import map.Map;

public interface DecorablePosition<T> extends Position<T>, Map<Object,Object> {
} // no new methods needed -- this is a mixture of Position and Map.
