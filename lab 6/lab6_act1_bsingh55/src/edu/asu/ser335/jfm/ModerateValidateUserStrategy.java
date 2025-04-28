// SER335 LAB6 TASK1
package edu.asu.ser335.jfm;

public class ModerateValidateUserStrategy implements IValidateUserStrategy {

    @Override
    public boolean validateUser(String password) {
        if (password == null || password.length() < 3) {
            return false;
        }

        boolean hasLetter = false;
        boolean hasDigit = false;

        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                return false; // Invalid character
            }
        }

        return hasLetter && hasDigit;
    }
}
