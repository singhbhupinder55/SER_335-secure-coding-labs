// SER335 LAB6 TASK1
package edu.asu.ser335.jfm;

public class NullValidateUserStrategy implements IValidateUserStrategy {

    @Override
    public boolean validateUser(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }

        for (char c : password.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                // Special character found
                return false;
            }
        }

        return true;
    }
}
