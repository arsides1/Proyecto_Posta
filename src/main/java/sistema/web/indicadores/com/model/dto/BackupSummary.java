// BackupSummary.java
package sistema.web.indicadores.com.model.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Date;
import java.util.Map;
import java.util.List;

@Data
@Builder
public class BackupSummary {
    private Date fechaProcesamiento;
    private String nombreArchivo;
    private String tipoBaseDatos;

    // Estadísticas generales
    private int totalRegistrosProcesados;
    private int totalTablasVacias;
    private int totalTablasConError;

    // Detalle por tabla
    private Map<String, Integer> registrosPorTabla;
    private List<String> tablasVacias;
    private Map<String, String> erroresPorTabla;

    // Tiempos de procesamiento
    private long tiempoProcesamientoMs;
    private Date horaInicio;
    private Date horaFin;

    // Estado del proceso
    private boolean procesoCompleto;
    private String mensajeResumen;

    public void agregarEstadisticaTabla(String tabla, int cantidadRegistros) {
        registrosPorTabla.put(tabla, cantidadRegistros);
        totalRegistrosProcesados += cantidadRegistros;

        if (cantidadRegistros == 0) {
            tablasVacias.add(tabla);
            totalTablasVacias++;
        }
    }

    public void agregarError(String tabla, String error) {
        erroresPorTabla.put(tabla, error);
        totalTablasConError++;
    }

    public void finalizarProcesamiento() {
        horaFin = new Date();
        tiempoProcesamientoMs = horaFin.getTime() - horaInicio.getTime();
        procesoCompleto = true;

        generarMensajeResumen();
    }

    private void generarMensajeResumen() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Proceso completado en %d ms. ", tiempoProcesamientoMs));
        sb.append(String.format("Total registros: %d. ", totalRegistrosProcesados));

        if (totalTablasVacias > 0) {
            sb.append(String.format("Tablas vacías: %d. ", totalTablasVacias));
        }

        if (totalTablasConError > 0) {
            sb.append(String.format("Tablas con error: %d. ", totalTablasConError));
        }

        mensajeResumen = sb.toString();
    }
}