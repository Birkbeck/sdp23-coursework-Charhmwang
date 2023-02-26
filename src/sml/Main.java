package sml;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;

public class Main {
	/**
	 * Initialises the system and executes the program.
	 *
	 * @param args name of the file containing the program text.
	 */
	public static void main(String... args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments - Machine <file> - required");
			System.exit(-1);
		}

		try {
			// get the bean factory
			var factory = getBeanFactory();
			Translator t = Translator.getInstance(args[0]);

			Registers r = (Registers) factory.getBean("Registers");
			Labels l = (Labels) factory.getBean("Labels");
			Machine m = (Machine) factory.getBean("Machine");

			// No need to set the Machine properties
			// - Spring does that instead based on:
			// Machine.registers(ref)=Registers
			// Machine.labels(ref)=Labels
			// in the beans file in the folder resources.

			t.readAndTranslate(m.getLabels(), m.getProgram());

			System.out.println("Here is the program; it has " + m.getProgram().size() + " instructions.");
			System.out.println(m);

			System.out.println("Beginning program execution.");
			m.execute();
			System.out.println("Ending program execution.");

			System.out.println("Values of registers at program termination:" + m.getRegisters() + ".");
		}
		catch (IOException e) {
			System.out.println("Error reading the program from " + args[0]);
		}
	}

	private static BeanFactory getBeanFactory() throws IOException {
		// get the bean factory
		var factory = new DefaultListableBeanFactory();
		// create a definition reader
		var pdr = new PropertiesBeanDefinitionReader(factory);

		// load the configuration options
		var props = new Properties();
		try (var fis = Main.class.getResourceAsStream("/beans")) {
			props.load(fis);
		}

		pdr.registerBeanDefinitions(props);
		return factory;
	}
}
