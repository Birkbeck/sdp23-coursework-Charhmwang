package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodsTest {
    private Machine machine;
    private Registers registers;
    private Labels labels;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream out = System.out;


    @BeforeEach
    void setUp() {
        machine =  Machine.getInstance();
        registers = Registers.getInstance();
        labels = new Labels();

        machine.setRegisters(registers);
        machine.setLabels(labels);
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
        Translator t = Translator.getInstance("test2.sml");
        Machine m = Machine.getInstance();
        t.readAndTranslate(m.getLabels(), m.getProgram());
        System.out.print(m.getLabels());
        String actual = outputStream.toString();
        String expected = "[f1 -> 0, f2 -> 2, f3 -> 3]";

        assertEquals(expected, actual);
    }

    @Test
    void executeLabels_equals() throws IOException {
        Translator t = Translator.getInstance("test2.sml");

        Labels testLabels = new Labels();
        testLabels.addLabel("f1", 0);
        testLabels.addLabel("f2", 2);
        testLabels.addLabel("f3", 3);
        t.readAndTranslate(machine.getLabels(), machine.getProgram());

        boolean expected = true;
        boolean actual = testLabels.equals(machine.getLabels());

        Assertions.assertEquals(expected, actual);
    }

}
