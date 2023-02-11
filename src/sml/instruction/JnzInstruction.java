package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

public class JnzInstruction extends Instruction {
    // jnz s L - If the contents of register s is not zero, then make the statement labelled
    //           L the next statement to execute
    private final RegisterName s;
    private final String L;

    public static final String OP_CODE = "jnz";

    public JnzInstruction(String label, RegisterName s, String L) {
        super(label, OP_CODE);
        this.s = s;
        this.L = L;
    }

    @Override
    public int execute(Machine m) {

        // Check whether the contents of register s is zero
        if (m.getRegisters().get(s) != 0) {
            return m.getLabels().getAddress(L);
        } else return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + s + " " + L;
    }
}
