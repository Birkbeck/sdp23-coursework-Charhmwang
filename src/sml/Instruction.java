package sml;

// [Done] TODO: write a JavaDoc for the class

/**
 * Represents an abstract instruction.
 * An instruction has an optional label in form of String which can be null,
 * and an operation code in form of String.
 * There will be 7 subclasses concrete a specialized operation instruction.
 *
 * @author Haomeng Wang
 */
public abstract class Instruction {

	/** The immutable contents of the label of instruction. Optional existing. */
	protected final String label;

	/** The immutable contents of the operation code of instruction. Must have a value. */
	protected final String opcode;

	/**
	 * Constructor: an instruction with a label and an opcode
	 * (opcode must be an operation of the language)
	 *
	 * @param label optional label (can be null)
	 * @param opcode operation name
	 */
	public Instruction(String label, String opcode) {
		this.label = label;
		this.opcode = opcode;
	}


	/**
	 * Returns the content of label in this Instruction.
	 *
	 * @return the content of label in this Instruction.
	 */
	public String getLabel() {
		return label;
	}


	/**
	 * Returns the content of opcode in this Instruction.
	 *
	 * @return the content of opcode in this Instruction.
	 */
	public String getOpcode() {
		return opcode;
	}


	/**
	 * Address of the operation code of instruction.
	 *
	 * Increase 1 each time a new instruction is initialized.
	 **/
	public static int NORMAL_PROGRAM_COUNTER_UPDATE = -1;


	/**
	 * Executes the instruction in the given machine.
	 *
	 * @param machine the machine the instruction runs on
	 * @return the new program counter (for jump instructions)
	 *          or NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
	 *          the instruction with the next address is to be executed
	 */
	public abstract int execute(Machine machine);


	/**
	 * Executes the instruction in the given machine.
	 * Only subclasses object can access to the method.
	 * @return the label in form of String.
	 */
	protected String getLabelString() {
		return (getLabel() == null) ? "" : getLabel() + ": ";
	}


	// [Done] TODO: What does abstract in the declaration below mean?
	//       (Write a short explanation.)
	// It is a common method that all the subclasses of this class must have (implement),
	// in abstract class, abstract method does not have its implementation, because subclasses implement
	// in their own ways, it is the feature of polymorphism.
	/**
	 * Convert instruction object into a String.
	 * Only subclasses object can access to the method.
	 * @return a String describing this instruction.
	 */
	@Override
	public abstract String toString();


	// [Done] TODO: Make sure that subclasses also implement equals and hashCode (needed in class Machine).
	/**
	 * Compare this instruction's contents with another one's.
	 * Only subclasses object can access to the method.
	 * @param o should be an instruction object in the class form of Object
	 * @return a boolean result whether this instruction equals to another instruction
	 */
	@Override
	public abstract boolean equals(Object o);

	/**
	 * Returns an integer hash value of the instruction object.
	 * For the use of comparing objects reference.
	 * Only subclasses object can access to the method.
	 * @return an int value represents hash value of the instruction object.
	 */
	@Override
	public abstract int hashCode();
}
