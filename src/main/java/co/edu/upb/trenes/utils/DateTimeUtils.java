package co.edu.upb.trenes.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public final class DateTimeUtils {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private DateTimeUtils() {
    }

    public static String format(LocalDateTime value) {
        return value == null ? "" : FORMATTER.format(value);
    }

    public static List<String> generarHorasOperacion() {
        List<String> horas = new ArrayList<>();
        for (int hora = 5; hora <= 22; hora++) {
            horas.add(String.format("%02d:00", hora));
            if (hora < 22) {
                horas.add(String.format("%02d:30", hora));
            }
        }
        return horas;
    }
}
