package utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;

/**
 * Convenience class to encapsulate several common checks, and throw appropriate exceptions if conditions are
 * not met.
 * <p/>
 * This class can be used to validate method parameters.
 */
public class Checks {

    /**
     * @param ob         object to check for not-null-ness
     * @param objectName name of the object, will be used in case of check failure
     * @throws NullPointerException if object is null
     */
    public static void checkNotNull(final Object ob, final String objectName) {
        if (ob == null) {
            throw new NullPointerException(objectName + " should not be null");
        }
    }

    /**
     * @param i     value to check for not-negative-ness
     * @param iName name of the value that will be used in case of check failure
     * @throws IllegalArgumentException if value is negative
     */
    public static void checkNotNegative(final int i, final String iName) {
        if (i < 0) {
            throw new IllegalArgumentException(iName + " should be non-negative: " + i);
        }
    }

    /**
     * @param i     value to check for not-negative-ness
     * @param iName name of the value that will be used in case of check failure
     * @throws IllegalArgumentException if value is negative
     */
    public static void checkNotNegative(final double i, final String iName) {
        if (i < 0) {
            throw new IllegalArgumentException(iName + " should be non-negative: " + i);
        }
    }

    /**
     * @param i     value to check for not-positve-ness
     * @param iName name of the value that will be used in case of check failure
     * @throws IllegalArgumentException if value is positive
     */
    public static void checkNotPositive(final double i, final String iName) {
        if (i > 0) {
            throw new IllegalArgumentException(iName + " should be non-positive: " + i);
        }
    }

    /**
     * @param i     value to check for positive-ness
     * @param iName name of the value that will be used in case of check failure
     * @throws IllegalArgumentException if value is not positive
     */
    public static void checkPositive(final int i, final String iName) {
        if (i <= 0) {
            throw new IllegalArgumentException(iName + " should be positive: " + i);
        }
    }

    /**
     * @param l     value to check for positive-ness
     * @param iName name of the value that will be used in case of check failure
     * @throws IllegalArgumentException if value is not positive
     */
    public static void checkPositive(final long l, final String iName) {
        if (l <= 0) {
            throw new IllegalArgumentException(iName + " should be positive: " + l);
        }
    }

    /**
     * @param collection     value to check for not-emptyness
     * @param collectionName name of the value that will be used in case of check failure
     * @throws NullPointerException     if collection is <code>null</code>
     * @throws IllegalArgumentException if value is negative
     */
    public static void checkNotEmpty(final Collection collection, final String collectionName) {
        checkNotNull(collection, collectionName);
        if (collection.isEmpty()) {
            throw new IllegalArgumentException(collectionName + " should not be empty.");
        }
    }

    /**
     * <p>Checks if the given string is null or empty</p>
     *
     * @param str
     * @param stringName
     * @throws NullPointerException     if str is <code>null</code>
     * @throws IllegalArgumentException if str is empty
     */
    public static void checkNotEmpty(final String str, final String stringName) {
        checkNotNull(str, stringName);
        if (StringUtils.isEmpty(str)) {
            throw new IllegalArgumentException(stringName + " should not be empty.");
        }
    }

    /**
     * <p>Checks if the given string is null, empty or not a valid number (must not be a floating point number)</p>
     *
     * @param str
     * @param stringName
     * @throws NullPointerException     if str is <code>null</code>
     * @throws IllegalArgumentException if str is empty or is not a valid non-floating point number
     */
    public static void checkValidNumber(final String str, final String stringName) {
        checkNotEmpty(str, stringName);
        if (!StringUtils.isNumeric(str)) {
            throw new IllegalArgumentException(stringName + " should be numeric.");
        }
    }

    /**
     * <p>Checks if the given string is null, empty or not a valid Calendar weekday</p>
     *
     * @param str
     * @param stringName
     * @throws NullPointerException     if str is <code>null</code>
     * @throws IllegalArgumentException if str is empty or is not a valid Calendar weekday
     */
    public static void checkValidWeekDay(final String str, final String stringName) {
        checkValidNumber(str, stringName);
        int value = Integer.valueOf(str);
        if (value < Calendar.SUNDAY || value > Calendar.SATURDAY) {
            throw new IllegalArgumentException(stringName + " should be a valid WeekDay.");
        }
    }

    /**
     * <p>Checks if the given string is shorter or equal than the given length</p>
     *
     * @param str
     * @param length
     * @param stringName
     * @throws IllegalArgumentException if str larger than length
     */
    public static void checkValidLength(final String str, final int length, final String stringName) {
        if (StringUtils.length(str) > length) {
            throw new IllegalArgumentException(stringName + " should be shorter.");
        }
    }

    /**
     * <p>Checks if the given string is longer or equal than the given length</p>
     *
     * @param str
     * @param length
     * @param stringName
     * @throws IllegalArgumentException if str larger than length
     */
    public static void checkValidMinimumLength(final String str, final int length, final String stringName) {
        if (StringUtils.length(str) < length) {
            throw new IllegalArgumentException(stringName + " should be longer.");
        }
    }

    /**
     * <p>Checks if the given number is in given range</p>
     *
     * @param value      Value to check.
     * @param minimum    Lower boundary (inclusive). Leave to null to indicate open range.
     * @param maximum    Upper boundary (inclusive). Leave to null to indicate open range.
     * @param numberName name of the number, will be used in case of check failure
     * @throws IllegalArgumentException if the number is outside specified range.
     */
    public static void checkValidRange(final Number value, final Number minimum, final Number maximum, final String numberName) {
        checkNotNull(value, numberName);
        if (minimum != null) {
            if (value.doubleValue() < minimum.doubleValue()) {
                throw new IllegalArgumentException(numberName + " should be at least " + minimum);
            }
        }
        if (maximum != null) {
            if (value.doubleValue() > maximum.doubleValue()) {
                throw new IllegalArgumentException(numberName + " should be at most " + maximum);
            }
        }
    }

    public static void checkNIF(String id) {
        String regexp = "[0-9]{1,8}[A-Za-z]";
        if (!id.matches(regexp)) {
            throw new IllegalArgumentException("NIF " + id + " must match " + regexp);
        }
        String nif;
        String nifLetter;
        if (id.indexOf('-') == -1) {
            nif = id.substring(0, id.length() - 1);
            nifLetter = id.substring(id.length() - 1, id.length());
        } else {
            nif = id.substring(0, id.length() - 2);
            nifLetter = id.substring(id.length() - 1, id.length());
        }

        if (!getNIFChar(nif).equalsIgnoreCase(nifLetter)) {
            throw new IllegalArgumentException("Invalid NIF number " + id);
        }
    }

    public static void checkNIE(String id) {
        String regexp = "[XYZxyz][0-9]{1,8}[A-Za-z]";
        if (!id.matches(regexp)) {
            throw new IllegalArgumentException("NIE " + id + " must match " + regexp);
        }
        String letter = id.substring(id.length() - 1, id.length());
        StringBuilder num = new StringBuilder().append(id.substring(1, id.length() - 1));
        String initialLetter = id.substring(0, 1);
        if (initialLetter.equalsIgnoreCase("y")) {
            num.insert(0, '1');
        } else if (initialLetter.equalsIgnoreCase("z")) {
            num.insert(0, '2');
        }
        String letterNIE = getNIFChar(num.toString());
        if (!letter.equalsIgnoreCase(letterNIE)) {
            throw new IllegalArgumentException("Invalid NIE number " + id);
        }
    }

    protected static String getNIFChar(String nif) {
        Long lNif = new BigInteger(nif).longValue();
        int resto = (int) (lNif % 23);
        String correctLetters = "TRWAGMYFPDXBNJZSQVHLCKE";
        return String.valueOf(correctLetters.charAt(resto));
    }

    public static void checkValidPersonName(String name, String fieldName) {
        String allowed = ".- '";
        for (int i = 0; i < name.length(); i++) {
            if (Character.isLetter(name.charAt(i))) {
                continue;
            }
            if (allowed.indexOf(name.charAt(i)) < 0) {
                throw new IllegalArgumentException("Illegal char in " + fieldName + " '" + name + "': " + name.charAt(i));
            }
        }
    }

    public static void checkIp(String ip) {
        String regexp = "\\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b";
        if (!ip.matches(regexp)) {
            throw new IllegalArgumentException("IP " + ip + " must match " + regexp);
        }
    }

    public static void checkMail(String id) {
        String regexp = "^^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+([\\-]*[A-Za-z0-9])*(\\.[A-Za-z0-9]+([\\-]*[A-Za-z0-9])*)*(\\.[A-Za-z]{2,})$";
        if (!id.matches(regexp)) {
            throw new IllegalArgumentException("Mail " + id + " has to match  " + regexp);
        }
    }

    public static void checkCurrency(String currencyCode, String fieldName) {
        try {
            Currency.getInstance(currencyCode);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(fieldName + " is not a valid Currency: " + currencyCode, e);
        }

    }

}
