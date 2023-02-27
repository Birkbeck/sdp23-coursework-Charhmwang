package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static sml.Registers.Register.EAX;

public class OutInstructionTest {
    public static final String OP_CODE = "out";
    private Machine machine;
    private Registers registers;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream out = System.out;


    @BeforeEach
    void setUp() {
        machine =  Machine.getInstance();
        registers = Registers.getInstance();
        machine.setRegisters(registers);
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
        System.setOut(out);
    }

    @Test
    void executeValid() {
        registers.set(EAX, 5);
        Instruction instruction = new OutInstruction(null, EAX);
        instruction.execute(machine);
        String actual = outputStream.toString();

        assertEquals("Contents of register EAX: 5\n", actual);
    }
}

