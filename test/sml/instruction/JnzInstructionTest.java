package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Machine;
import sml.Registers;
import sml.Translator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sml.Registers.Register.*;

public class JnzInstructionTest {

    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = Machine.getInstance();
        registers = machine.getRegisters();
        //...
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void executeValid() throws IOException {
        Translator t = Translator.getInstance("./src/test2.sml");
        t.readAndTranslate(machine.getLabels(), machine.getProgram());
        machine.execute();
        int actual_EBX = machine.getRegisters().get(EBX);
        int expected_EBX = 720;
        assertEquals(expected_EBX, actual_EBX);
    }
}
