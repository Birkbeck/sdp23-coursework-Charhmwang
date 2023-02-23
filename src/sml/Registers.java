package sml;

import java.util.*;
import java.util.stream.Collectors;

// [Done] TODO: write a JavaDoc for the class

/**
 * Represents registers collection in a machine.
 * There are 8 registers in the machine, and each one stores a mutable integer value.
 *
 * @author Haomeng Wang
 */
public final class Registers {

    /** The immutable reference of a hashmap storing Register that each one is an enum variable,
     * and the storing value in form of Integer. Compulsory existing.
     * */
    private final Map<Register, Integer> registers = new HashMap<>();

    /** The enum collection implements interface RegisterName in form of string, composed of 8 register names.
     */
    public enum Register implements RegisterName {
        EAX, EBX, ECX, EDX, ESP, EBP, ESI, EDI;
    }

    /**
     * Constructor: set contents of registers in the machine to original status
     */
    public Registers() {
        clear(); // the class is final
    }

    /**
     * Add each register enum name as key and zero as value into the map registers.
     * Initialization of the machine registers before a program being executed.
     */
    public void clear() {
        for (Register register : Register.values())
            registers.put(register, 0);
    }

    /**
     * Sets the given register to the value.
     *
     * @param register register name
     * @param value new value
     */
    public void set(RegisterName register, int value) {
        registers.put((Register)register, value);
    }

    /**
     * Returns the value stored in the register.
     *
     * @param register register name
     * @return value
     */
    public int get(RegisterName register) {
        return registers.get((Register)register);
    }

    // [Done] TODO: use pattern matching for instanceof
    // https://docs.oracle.com/en/java/javase/14/language/pattern-matching-instanceof-operator.html
    /**
     * Compares to another Registers object whether equal based on the hashmap registers contents.
     *
     * @param o should be a Registers object in the class form of Object
     * @return a boolean result whether this registers collection equals to another one
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Registers other) {
            return registers.equals(other.registers);
        }
        return false;
    }

    /**
     * Returns an integer hash value of the Registers object.
     * For the use of comparing objects reference.
     *
     * @return an int value represents hash value of the Registers object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(registers);
    }

    /**
     * representation of this instance,
     * in the form "[register = int value, register = int value, ..., register = int value]"
     *
     * @return the string representation of the registers map
     */
    @Override
    public String toString() {
        return registers.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> e.getKey() + " = " + e.getValue())
                .collect(Collectors.joining(", ", "[", "]")) ;
    }
}
