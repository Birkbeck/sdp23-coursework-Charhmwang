package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

import static sml.Instruction.NORMAL_PROGRAM_COUNTER_UPDATE;

public class OutInstruction extends Instruction {

    private final RegisterName r;
    public static final String OP_CODE = "out";

    public OutInstruction(String label, RegisterName r) {
        super(label, OP_CODE);
        this.r = r;
    }

    @Override
    public int execute(Machine m) {
        System.out.println("Contents of register " + r + ": " + m.getRegisters().get(r));
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + r;
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof OutInstruction other) {
            return Objects.equals(this.label, other.label);
        }
        return false;
    }

    @Override
    public int hashCode() { return Objects.hash(label, r, OP_CODE); }
}