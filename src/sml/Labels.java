package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

// [Done] TODO: write a JavaDoc for the class

/**
 * Represents labels of the whole set of labeled instructions in a program
 * and the corresponding addresses.
 *
 * @author Haomeng Wang
 */
public final class Labels {

	/** The immutable reference of a hashmap storing labels that each one is in form of String,
	 * and the corresponding address in form of Integer. Compulsory existing.
	 * Each label which is not null will be set into the map as key with value of integer.
	 * */
	private final Map<String, Integer> labels = new HashMap<>();

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label the label
	 * @param address the address the label refers to
	 */
	public void addLabel(String label, int address) {
		Objects.requireNonNull(label);
		// [Done] TODO: Add a check that there are no label duplicates.
		// Check if this label is already existed, throw a runtime exception
			try {
				if (labels.containsKey(label))
					throw new RuntimeException();
				else
					labels.put(label, address);
			} catch (RuntimeException e) {
				System.out.println("Oops, there are labels duplicates!");
				System.exit(1);
			}
	}

	/**
	 * Returns the address associated with the label.
	 *
	 * @param label the label
	 * @return the address the label refers to
	 */
	public int getAddress(String label) {
		// [Done]TODO: Where can NullPointerException be thrown here?
		//       (Write an explanation.)
		//       Add code to deal with non-existent labels.
		// NullPointerException is thrown when there is a parameter label found
		// in a jnz instruction, but not found as a key in the labels map.
		try {
			if (!labels.containsKey(label))
				throw new NullPointerException();
		} catch (NullPointerException e){
			System.out.println("Non-exited label!");
			System.exit(1);
		}
		return labels.get(label);
	}

	/**
	 * representation of this instance,
	 * in the form "[label -> address, label -> address, ..., label -> address]"
	 *
	 * @return the string representation of the labels map
	 */
	@Override
	public String toString() {
		// [Done] TODO: Implement the method using the Stream API (see also class Registers).
		return labels.entrySet().stream()
				.sorted(Map.Entry.comparingByValue())
				.map(e -> e.getKey() + " -> " + e.getValue())
				.collect(Collectors.joining(", ", "[", "]")) ;
	}

	// [Done] TODO: Implement equals and hashCode (needed in class Machine).
	/**
	 * Compares to another Labels object whether equal based on the hashmap labels contents.
	 *
	 * @param o should be a Labels object in the class form of Object
	 * @return a boolean result whether this labels collection equals to another one
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Labels other) {
			return this.labels.toString().equals(other.labels.toString());
		}
		return false;
	}

	/**
	 * Returns an integer hash value of the Labels object.
	 * For the use of comparing objects reference.
	 *
	 * @return an int value represents hash value of the Labels object.
	 */
	@Override
	public int hashCode() { return Objects.hash(labels); }

	/**
	 * Removes the labels
	 */
	public void reset() {
		labels.clear();
	}
}
