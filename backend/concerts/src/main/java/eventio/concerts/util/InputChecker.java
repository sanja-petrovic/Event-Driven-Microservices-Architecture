package eventio.concerts.util;

public class InputChecker {
    public static boolean isBlankNullOrEmpty(String input) {
        return input == null || input.equals("") || input.trim().equals("");
    }
}
