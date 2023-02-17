package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 *
 * @author ...
 */
public final class Labels {
	private final Map<String, Integer> labels = new HashMap<>();

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label the label
	 * @param address the address the label refers to
	 */
	public boolean addLabel(String label, int address) {
		Objects.requireNonNull(label);
		// [Done]TODO: Add a check that there are no label duplicates.
		// Check if this label is already existed, return false to Translator
		if (labels.containsKey(label))
			return false;
		else
			labels.put(label, address);
		return true;
	}

	/**
	 * Returns the address associated with the label.
	 *
	 * @param label the label
	 * @return the address the label refers to
	 */
	public int getAddress(String label) {
		// TODO: Where can NullPointerException be thrown here?
		//       (Write an explanation.)
		//       Add code to deal with non-existent labels.
		if (!labels.containsKey(label)) {
			throw new NullPointerException("Oops, cannot find the specialized label in a Jnz instruction!");
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
		// TODO: Implement the method using the Stream API (see also class Registers).
		return "";
	}

	// TODO: Implement equals and hashCode (needed in class Machine).
	@Override
	public boolean equals(Object o) {
		if (o instanceof Labels other) {
			return Objects.equals(this.labels, other.labels);
		}
		return false;
	}
	@Override
	public int hashCode() { return Objects.hash(labels); }
	/**
	 * Removes the labels
	 */
	public void reset() {
		labels.clear();
	}
}
