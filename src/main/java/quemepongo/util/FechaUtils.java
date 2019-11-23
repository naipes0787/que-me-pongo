package quemepongo.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FechaUtils {

    /**
     * @param fecha en formato yyyy-MM-dd
     * @param horario en formato HH:mm
     * @return LocalDateTime correspondiente
     */
    public static LocalDateTime parsear(String fecha, String horario) {
        int hora = Integer.parseInt(horario.split(":")[0]);
        int minutos = Integer.parseInt(horario.split(":")[1]);
        return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                .atTime(hora, minutos);
    }
}
