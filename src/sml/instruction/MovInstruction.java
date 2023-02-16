package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (o instanceof MovInstruction other) {
            return Objects.equals(this.label, other.label);
        }
        return false;
    }

    @Override
    public int hashCode() { return Objects.hash(label, result, val, OP_CODE); }
}
