package co.edu.upb.trenes.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateTimeUtils {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private DateTimeUtils() {
    }

    public static String format(LocalDateTime value) {
        return value == null ? "" : FORMATTER.format(value);
    }
}
