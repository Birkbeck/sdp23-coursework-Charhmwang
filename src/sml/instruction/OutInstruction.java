package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

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
}