package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import java.util.Objects;

/**
 * Represents a multiplication instruction extends the abstract class Instruction.
 * Multiplied values from two registers, divide value in the former register by the from the former one,
 *  * and store the result into the former register.
 *
 * @author Haomeng Wang
 */
public class MulInstruction extends Instruction {

    /** The immutable register name. Compulsory existing.
     * Product will be set into this register.
     * */
    private final RegisterName result;

    /** The immutable register name. Compulsory existing.
     * The second parameter of multiplication comes from this register.
     * */
    private final RegisterName source;

    /** The immutable contents of the operation code of instruction. Set to be "mul". */
    public static final String OP_CODE = "mul";

    /**
     * Constructor: a multiplication instruction with an optional label which can be null and two registers
     * (result and source must be enum Register value implements RegisterName, see Registers.java)
     *
     * @param label optional label (can be null)
     * @param result register name
     * @param source register name
     */
    public MulInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    /**
     * Execute this instruction in the machine.
     * Multiplication is operated here.
     *
     * @param m machine name
     * @return int value represents the current instruction location in the program
     */
    @Override
    public int execute(Machine m) {
        int value1 = m.getRegisters().get(result);
        int value2 = m.getRegisters().get(source);
        m.getRegisters().set(result, value1 * value2);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    /**
     * Enable to print out this instruction contents.
     *
     * @return a String represents the current instruction contents composed of label,
     * operation code and two register names.
     * (label might be empty)
     */
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + source;
    }

    /**
     * Compare this Mul instruction's contents with another one's.
     *
     * @param o should be an instruction object in the class form of Object
     * @return a boolean result whether this instruction equals to another Mul instruction
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof MulInstruction other) {
            return Objects.equals(this.label, other.label)
                    && Objects.equals(this.result, other.result)
                    && Objects.equals(this.source, other.source);
        }
        return false;
    }

    /**
     * Returns an integer hash value of the instruction object.
     * For the use of comparing objects reference.
     *
     * @return an int value represents hash value of the Mul instruction object.
     */
    @Override
    public int hashCode() { return Objects.hash(label, result, source, OP_CODE); }
}
