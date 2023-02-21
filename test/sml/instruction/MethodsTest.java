package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;
import sml.Translator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static sml.Registers.Register.EAX;

public class MethodsTest {
    private Machine machine;
    private Registers registers;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream out = System.out;


    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
        System.setOut(out);
    }

    @Test
    void executeLabelsToString() throws IOException {
        Translator t = new Translator("./src/test2.sml");
        Machine m = new Machine(new Registers());
        t.readAndTranslate(m.getLabels(), m.getProgram());
        System.out.print(m.getLabels());
        String actual = outputStream.toString();
        String expected = "[f1 -> 0, f2 -> 2, f3 -> 3]";

        assertEquals(expected, actual);
    }
}
