package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import java.util.Objects;

/**
 * Represents an out instruction extends the abstract class Instruction.
 * Print the contents of register s on the console.
 *
 * @author Haomeng Wang
 */
public class OutInstruction extends Instruction {

    /** The immutable register name. Compulsory existing.
     * The contents of this register will be printed out.
     * */
    private final RegisterName s;

    /** The immutable contents of the operation code of instruction. Set to be "out". */
    public static final String OP_CODE = "out";

    /**
     * Constructor: an out instruction with an optional label which can be null and a register
     * (s must be an enum Register value implements RegisterName, see Registers.java)
     *
     * @param label optional label (can be null)
     * @param s register name to print contents of
     */
    public OutInstruction(String label, RegisterName s) {
        super(label, OP_CODE);
        this.s = s;
    }

    /**
     * Execute this instruction in the machine.
     * Print the contents of the register s on the console.
     *
     * @param m machine name
     * @return int value represents the current instruction location in the program
     */
    @Override
    public int execute(Machine m) {
        System.out.println("Contents of register " + s + ": " + m.getRegisters().get(s));
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    /**
     * Enable to print out this instruction contents.
     *
     * @return a String represents the current instruction contents composed of label,
     * operation code and a register name.
     * (label might be empty)
     */
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + s;
    }

    /**
     * Compare this Out instruction's contents with another one's.
     *
     * @param o should be an instruction object in the class form of Object
     * @return a boolean result whether this instruction equals to another Out instruction
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof OutInstruction other) {
            return Objects.equals(this.label, other.label)
                    && Objects.equals(this.s, other.s);
        }
        return false;
    }

    /**
     * Returns an integer hash value of the instruction object.
     * For the use of comparing objects reference.
     *
     * @return an int value represents hash value of the Out instruction object.
     */
    @Override
    public int hashCode() { return Objects.hash(label, s, OP_CODE); }
}