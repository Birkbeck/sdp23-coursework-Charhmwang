package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    @Test
    void executeLabels_equals() throws IOException {
        Translator t = new Translator("./src/test2.sml");
        Machine m = new Machine(new Registers());
        t.readAndTranslate(m.getLabels(), m.getProgram());

        Labels testLabels = new Labels();
        testLabels.addLabel("f1", 0);
        testLabels.addLabel("f2", 2);
        testLabels.addLabel("f3", 3);

        Boolean expected = true;
        Boolean actual = m.getLabels().equals(testLabels);

        assertEquals(expected, actual);
    }
}
