package entry;

public interface Entry<K, V> {
	/** Returns the key stored in this entry. */
	public K getKey();
	/** Returns the value stored in this entry. */
	public V getValue();
}
