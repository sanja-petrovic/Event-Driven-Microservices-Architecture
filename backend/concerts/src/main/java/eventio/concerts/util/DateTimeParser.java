package eventio.concerts.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {
    public static LocalDateTime parse(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(input, formatter);
    }
}
