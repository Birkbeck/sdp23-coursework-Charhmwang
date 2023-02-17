package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.*;
import sml.Machine;
import sml.Registers;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CheckLabelDuplicates {
    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }
    @Test
    void checkLabelDup() throws IOException {
        Translator t = new Translator("./src/test3.sml");
        //t.readAndTranslate(machine.getLabels(), machine.getProgram());
        Exception exception = assertThrows(RuntimeException.class,
                () -> { t.readAndTranslate(machine.getLabels(), machine.getProgram()); });
        String expectedMessage = "Oops, there are labels duplicates!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}