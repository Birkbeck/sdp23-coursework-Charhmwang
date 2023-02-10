package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

public class MovInstruction extends Instruction {
    private final RegisterName result;
    private final int val;
    public static final String OP_CODE = "mov";

    public MovInstruction(String label, RegisterName result, int val) {
        super(label, OP_CODE);
        this.result = result;
        this.val = val;
    }

    @Override
    public int execute(Machine m) {
        m.getRegisters().set(result, this.val);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + val;
    }
}
