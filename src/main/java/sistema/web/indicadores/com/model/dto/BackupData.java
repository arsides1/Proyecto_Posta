// BackupData.java
package sistema.web.indicadores.com.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BackupData {
    private List<CnvDTO> cnvData;
    private List<HisMinsaDTO> hisMinsaData;
    private List<IndicadorDTO> indicadorData;
    private List<ObservacionDTO> observacionData;
    private List<PadronNominalDTO> padronNominalData;
    private List<PeriodoAtencionDTO> periodoAtencionData;
    private List<TipoDocumentoDTO> tipoDocumentoData;
    private List<TipoEstado> tipoEstadoData;
    private List<TipoSexoDTO> tipoSexoData;
    private List<ValorDTO> valorData;

    // Metadatos del backup
    private Date fechaProcesamiento;
    private String nombreArchivo;
    private String tipoBaseDatos;

    // Estadísticas de procesamiento
    private int totalRegistrosProcesados;
    private int totalTablasVacias;
    private int totalTablasConError;

    // Métodos de utilidad
    public boolean tieneDatos(String tabla) {
        return switch (tabla.toLowerCase()) {
            case "cnv" -> cnvData != null && !cnvData.isEmpty();
            case "hisminsa" -> hisMinsaData != null && !hisMinsaData.isEmpty();
            case "indicador" -> indicadorData != null && !indicadorData.isEmpty();
            case "observacion" -> observacionData != null && !observacionData.isEmpty();
            case "padronnominal" -> padronNominalData != null && !padronNominalData.isEmpty();
            case "periodoatencion" -> periodoAtencionData != null && !periodoAtencionData.isEmpty();
            case "tipodocumento" -> tipoDocumentoData != null && !tipoDocumentoData.isEmpty();
            case "tipoestado" -> tipoEstadoData != null && !tipoEstadoData.isEmpty();
            case "tiposexo" -> tipoSexoData != null && !tipoSexoData.isEmpty();
            case "valor" -> valorData != null && !valorData.isEmpty();
            default -> false;
        };
    }

    public int getCantidadRegistros(String tabla) {
        return switch (tabla.toLowerCase()) {
            case "cnv" -> cnvData != null ? cnvData.size() : 0;
            case "hisminsa" -> hisMinsaData != null ? hisMinsaData.size() : 0;
            case "indicador" -> indicadorData != null ? indicadorData.size() : 0;
            case "observacion" -> observacionData != null ? observacionData.size() : 0;
            case "padronnominal" -> padronNominalData != null ? padronNominalData.size() : 0;
            case "periodoatencion" -> periodoAtencionData != null ? periodoAtencionData.size() : 0;
            case "tipodocumento" -> tipoDocumentoData != null ? tipoDocumentoData.size() : 0;
            case "tipoestado" -> tipoEstadoData != null ? tipoEstadoData.size() : 0;
            case "tiposexo" -> tipoSexoData != null ? tipoSexoData.size() : 0;
            case "valor" -> valorData != null ? valorData.size() : 0;
            default -> 0;
        };
    }

    public void calcularEstadisticas() {
        totalRegistrosProcesados = 0;
        totalTablasVacias = 0;

        String[] tablas = {"cnv", "hisminsa", "indicador", "observacion",
                "padronnominal", "periodoatencion", "tipodocumento",
                "tipoestado", "tiposexo", "valor"};

        for (String tabla : tablas) {
            int cantidadRegistros = getCantidadRegistros(tabla);
            totalRegistrosProcesados += cantidadRegistros;
            if (cantidadRegistros == 0) {
                totalTablasVacias++;
            }
        }
    }

    public void generarResumen() {
        calcularEstadisticas();
        fechaProcesamiento = new Date();
    }
}