package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import java.util.Objects;

/**
 * Represents a jnz instruction extends the abstract class Instruction.
 * If the contents of register s is not zero, then make the statement
 * labelled L the next statement to execute.
 *
 * @author Haomeng Wang
 */
public class JnzInstruction extends Instruction {

    /** The immutable register name. Compulsory existing.
     * The contents of this register will be checked.
     * */
    private final RegisterName s;

    /** The immutable String value represents a label. Compulsory existing.
     * To be used for locating the next instruction address if the contents of register s is not zero.
     * */
    private final String L;

    /** The immutable contents of the operation code of instruction. Set to be "jnz". */
    public static final String OP_CODE = "jnz";

    /**
     * Constructor: a jnz instruction with an optional label which can be null, a register and a label name.
     * (s must be an enum Register value implements RegisterName, see Registers.java)
     *
     * @param label optional label (can be null)
     * @param s register name to check contents of
     * @param L label name that represents the next instruction to be executed if value in s is not zero
     */
    public JnzInstruction(String label, RegisterName s, String L) {
        super(label, OP_CODE);
        this.s = s;
        this.L = L;
    }

    /**
     * Execute this instruction in the machine.
     * If the contents of register s is not zero, then make the statement
     * labelled L the next statement to execute.
     *
     * @param m machine name
     * @return int value represents the current instruction location in the program
     */
    @Override
    public int execute(Machine m) {

        // Check whether the contents of register s is zero
        if (m.getRegisters().get(s) != 0) {
            return m.getLabels().getAddress(L);
        } else return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    /**
     * Enable to print out this instruction contents.
     *
     * @return a String represents the current instruction contents composed of label,
     * operation code, a register name and a specialized label name.
     * (label of this current instruction might be empty)
     */
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + s + " " + L;
    }

    /**
     * Compare this Jnz instruction's contents with another one's.
     *
     * @param o should be an instruction object in the class form of Object
     * @return a boolean result whether this instruction equals to another Jnz instruction
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof JnzInstruction other) {
            return Objects.equals(this.label, other.label)
                    && Objects.equals(this.s, other.s)
                    && Objects.equals(this.L, other.L);
        }
        return false;
    }

    /**
     * Returns an integer hash value of the instruction object.
     * For the use of comparing objects reference.
     *
     * @return an int value represents hash value of the Jnz instruction object.
     */
    @Override
    public int hashCode() { return Objects.hash(label, s, L, OP_CODE); }
}
