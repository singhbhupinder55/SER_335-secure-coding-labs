// SER335 LAB6 TASK1
package edu.asu.ser335.jfm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ValidateUserFactory {

    private static final String PROPERTIES_FILE = "/resources/jfm335.properties";

    // SER335 LAB6 TASK1
    public static IValidateUserStrategy getStrategy() {
        Properties props = new Properties();
        try (InputStream input = ValidateUserFactory.class.getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                System.out.println("DEBUG: Properties file not found. Using StrictValidateUserStrategy as default.");
                return new StrictValidateUserStrategy(); // Default fallback
            }
            props.load(input);
            String classname = props.getProperty("validateuserstrategy.property");

            if (classname == null || classname.trim().isEmpty()) {
                System.out.println("DEBUG: Property missing. Using StrictValidateUserStrategy as default.");
                return new StrictValidateUserStrategy();
            }

            System.out.println("DEBUG: Loaded strategy class name from properties = " + classname);

            Class<?> strategyClass = Class.forName(classname.trim());
            Object instance = strategyClass.getDeclaredConstructor().newInstance();

            if (instance instanceof IValidateUserStrategy) {
                System.out.println("DEBUG: Successfully created instance of " + classname);
                return (IValidateUserStrategy) instance;
            } else {
                System.out.println("DEBUG: Loaded class is not an instance of IValidateUserStrategy. Using StrictValidateUserStrategy.");
                return new StrictValidateUserStrategy();
            }
        } catch (Exception e) {
            System.out.println("DEBUG: Exception occurred while loading strategy. Using StrictValidateUserStrategy.");
            e.printStackTrace();
            return new StrictValidateUserStrategy();
        }
    }
}
