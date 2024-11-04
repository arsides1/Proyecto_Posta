// ParserUtils.java
package sistema.web.indicadores.com.util.parser;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParserUtils {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String getString(String[] valores, int index) {
        try {
            return index < valores.length ? valores[index].trim() : null;
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer getInteger(String[] valores, int index) {
        try {
            String valor = getString(valores, index);
            return valor != null && !valor.isEmpty() ? Integer.parseInt(valor) : null;
        } catch (Exception e) {
            return null;
        }
    }

    public static BigDecimal getBigDecimal(String[] valores, int index) {
        try {
            String valor = getString(valores, index);
            return valor != null && !valor.isEmpty() ? new BigDecimal(valor) : null;
        } catch (Exception e) {
            return null;
        }
    }

    public static Float getFloat(String[] valores, int index) {
        try {
            String valor = getString(valores, index);
            return valor != null && !valor.isEmpty() ? Float.parseFloat(valor) : null;
        } catch (Exception e) {
            return null;
        }
    }

    public static LocalDate getLocalDate(String[] valores, int index) {
        try {
            String valor = getString(valores, index);
            return valor != null && !valor.isEmpty() ? LocalDate.parse(valor, DATE_FORMATTER) : null;
        } catch (Exception e) {
            return null;
        }
    }

    public static LocalDateTime getLocalDateTime(String[] valores, int index) {
        try {
            String valor = getString(valores, index);
            return valor != null && !valor.isEmpty() ? LocalDateTime.parse(valor, DATETIME_FORMATTER) : null;
        } catch (Exception e) {
            return null;
        }
    }

    public static Boolean getBoolean(String[] valores, int index) {
        try {
            String valor = getString(valores, index);
            if (valor == null || valor.isEmpty()) return null;
            return "1".equals(valor) || "true".equalsIgnoreCase(valor);
        } catch (Exception e) {
            return null;
        }
    }

    public static void logParsingError(String tabla, String[] valores, Exception e) {
        log.error("Error parseando datos para tabla {}: valores = {}, error = {}",
                tabla, Arrays.toString(valores), e.getMessage());
    }
}