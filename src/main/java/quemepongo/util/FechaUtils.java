package quemepongo.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FechaUtils {

    public static LocalDateTime parsear(String fecha, String hora, String minutos) {
        return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                .atTime(Integer.parseInt(hora), Integer.parseInt(minutos));
    }
}
