// SER335 LAB6 TASK1
        package edu.asu.ser335.jfm;

public class StrictValidateUserStrategy implements IValidateUserStrategy {

    @Override
    public boolean validateUser(String password) {
        if (password == null || password.length() < 6) {
            return false;
        }
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if ("!_=@%".indexOf(c) >= 0) {
                hasSpecial = true;
            } else {
                // If invalid character found, return false immediately
                return false;
            }
        }

        return hasUpper && hasLower && hasDigit && hasSpecial;
    }
}
