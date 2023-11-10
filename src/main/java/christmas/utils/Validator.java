package christmas.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validator {

    public static void validateIsNotBlank(String input, String message) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void validateIsNumber(String input, String message) {
        if (!input.matches("^[1-9]\\d*$")) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void validateIsNumber(String[] input, String message) {
        for (String s : input) {
            if (!s.matches("^[1-9]\\d*$")) {
                throw new IllegalArgumentException(message);
            }
        }
    }

    public static void validateIsMultipleOf(String input, int target, String message) {
        if (Integer.parseInt(input) % target != 0) {
            throw new IllegalArgumentException(String.format(message, target));
        }
    }

    public static <T> void validateListLength(List<T> list, int targetLength, String message) {
        if (list.size() != targetLength) {
            throw new IllegalArgumentException(String.format(message, targetLength));
        }
    }

    public static void validateNumberInRange(int input, int min, int max, String message) {
        if (input > max || input < min) {
            throw new IllegalArgumentException(String.format(message, min, max));
        }
    }

    public static void validateNumberInRange(List<Integer> input, int min, int max, String message) {
        for (Integer number : input) {
            if (number > max || number < min) {
                throw new IllegalArgumentException(String.format(message, min, max));
            }
        }
    }

    public static void validateNumberInRange(String input, int min, int max, String message) {
        int number = Integer.parseInt(input);
        if (number > max || number < min) {
            throw new IllegalArgumentException(message);
        }
    }

    public static <T> void validateIsElementUnique(List<T> list, String message) {
        Set<T> set = new HashSet<>(list);
        if (set.size() < list.size()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void validateCorrectFormat(String input, String format, String message) {
        if (!input.matches(format)) {
            throw new IllegalArgumentException(message);
        }
    }
}
