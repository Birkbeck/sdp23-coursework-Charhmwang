package sml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * This class reads a sml file adding each line instruction into the machine's program.
 * Singleton class.
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author Haomeng Wang
 */
public final class Translator {

    private final String fileName; // source file of SML code

    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    private Translator(String fileName) { this.fileName = fileName; }

    private static Translator translatorInstance = null;

    public synchronized static Translator getInstance(String fileName) {
        if (translatorInstance == null) {
            translatorInstance = new Translator(fileName);
        }
        return translatorInstance; }


    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"

    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size());
                    program.add(instruction);
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */
    private Instruction getInstruction(String label) throws IOException, ClassNotFoundException {
        if (line.isEmpty())
            return null;
        String opcode = scan(); // get "add" or "sub", "mov" ...
        //e.g., to match "AddInstruction" class form in beans.properties
        String insClassName = opcode.substring(0, 1).toUpperCase()
                + opcode.substring(1) + "Instruction";

        Instruction ins = builder(label, insClassName);
        if (ins == null)
            System.out.println("Unknown instruction: " + opcode);

        return ins;
    }


    // [Done] TODO: Then, replace the switch by using the Reflection API
    // [Done] TODO: Next, use dependency injection to allow this machine class to work with different sets of opcodes (different CPUs)
    /**
     * Creates an instance of the Instruction subclass described in param className.
     * This class should exist in the beans.properties file.
     * Each subclass of Instruction has only one constructor, with the first argument is always a label string.
     * The rest parameters of constructor assigned from the scanned contents and based on the parameter's type.
     *
     * @param label optional label (can be null)
     * @param insClassName instruction subclass name
     * @return instantiated Instruction object of class type insClassName or null if construction is
     * not possible.
     * @throws IOException FileReader cannot find or read the given file
     * @throws ClassNotFoundException insClassName does not refer to a known Class
     */
    private Instruction builder(String label, String insClassName) throws IOException, ClassNotFoundException {

        // Get all the subclasses and classpath from a properties file in directory resources
        Properties props = new Properties();
        props.load( new FileReader("../resources/beans.properties") );
        String classPath = props.getProperty(insClassName);
        Class<?> cls = Class.forName(classPath);
        // Each Instruction subclass is designed having only one constructor
        Constructor<?> constructor = cls.getConstructors()[0];
        int paramsNum = constructor.getParameterCount();

        try {
            Object[] paramObjects = new Object[paramsNum];
            // get the constructor parameters
            // a collection of params class - of the constructor
            Class<?>[] consParamTypes = constructor.getParameterTypes();
            paramObjects[0] = label; // First param is always label

            // Assign the other params from the second one
            for (int i = 1; i < consParamTypes.length; i++) {
                Class<?> c = consParamTypes[i];
                // Can be RegisterName, or Integer, or String(for Jnz target label)
                if (c.getName().equals("sml.RegisterName")) {
                    paramObjects[i] = Registers.Register.valueOf(scan());
                } else if (c.getName().equals("java.lang.Integer")) {
                    paramObjects[i] = Integer.parseInt(scan());
                } else if (c.getName().equals("java.lang.String")) {
                    paramObjects[i] = scan();
                }
            }
            return (Instruction) constructor.newInstance(paramObjects);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException ignore) {
        }
        return null;
    }


    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1); // Has label

        // undo scanning the word
        line = word + " " + line; // Has no label, line recovered to be the trimmed original line
        return null;
    }

    /*
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
    private String scan() {
        line = line.trim();

        // If there is a label, then line need to be changed into substring
        // If no label, line return with no change
        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i); // case if there is label
                line = line.substring(i);  // opcode
                return word;
            }
        return line; // case if no label
    }
}