package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import java.util.Objects;

/**
 * Represents a mov instruction extends the abstract class Instruction.
 * Store an integer into a specialized register.
 *
 * @author Haomeng Wang
 */
public class MovInstruction extends Instruction {

    /** The immutable register name. Compulsory existing.
     * Integer will be set into this register.
     * */
    private final RegisterName saveInto;

    /** The immutable Integer value. Compulsory existing.
     * To be set into the specialized register.
     * */
    private final Integer val;

    /** The immutable contents of the operation code of instruction. Set to be "mov". */
    public static final String OP_CODE = "mov";

    /**
     * Constructor: a mov instruction with an optional label which can be null, one register and an integer
     * (saveInto must be an enum Register value implements RegisterName, see Registers.java)
     *
     * @param label optional label (can be null)
     * @param saveInto name of the register to store integer
     * @param val integer value
     */
    public MovInstruction(String label, RegisterName saveInto, Integer val) {
        super(label, OP_CODE);
        this.saveInto = saveInto;
        this.val = val;
    }

    /**
     * Execute this instruction in the machine.
     * Setting integer value into the register.
     *
     * @param m machine name
     * @return int value represents the current instruction location in the program
     */
    @Override
    public int execute(Machine m) {
        m.getRegisters().set(saveInto, this.val);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    /**
     * Enable to print out this instruction contents.
     *
     * @return a String represents the current instruction contents composed of label,
     * operation code, a register name and an integer.
     * (label might be empty)
     */
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + saveInto + " " + val;
    }

    /**
     * Compare this Mov instruction's contents with another one's.
     *
     * @param o should be an instruction object in the class form of Object
     * @return a boolean result whether this instruction equals to another Mov instruction
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof MovInstruction other) {
            return Objects.equals(this.label, other.label)
                    && Objects.equals(this.saveInto, other.saveInto)
                    && Objects.equals(this.val, other.val);
        }
        return false;
    }

    /**
     * Returns an integer hash value of the instruction object.
     * For the use of comparing objects reference.
     *
     * @return an int value represents hash value of the Mov instruction object.
     */
    @Override
    public int hashCode() { return Objects.hash(label, saveInto, val, OP_CODE); }
}
