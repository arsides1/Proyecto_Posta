// BackupServiceImpl.java
package sistema.web.indicadores.com.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sistema.web.indicadores.com.repositorie.*;
import sistema.web.indicadores.com.service.BackupService;
import sistema.web.indicadores.com.util.parser.BackupParserFactory;
import sistema.web.indicadores.com.util.enums.DatabaseType;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class BackupServiceImpl implements BackupService {
    private final CnvRepository cnvRepository;
    private final HisMinsaRepository hisMinsaRepository;
    private final IndicadorRepository indicadorRepository;
    private final ObservacionRepository observacionRepository;
    private final PadronNominalRepository padronNominalRepository;
    private final PeriodoAtencionRepository periodoAtencionRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final TipoEstadoRepository tipoEstadoRepository;
    private final TipoSexoRepository tipoSexoRepository;
    private final ValorRepository valorRepository;
    private final BackupParserFactory parserFactory;

    @Override
    @Transactional
    public Map<String, Object> procesarBackup(MultipartFile file, DatabaseType databaseType, boolean clearExisting) {
        Map<String, Object> resultado = new HashMap<>();
        Map<String, Integer> estadisticas = new HashMap<>();
        List<String> tablasVacias = new ArrayList<>();

        try {
            var parser = parserFactory.getParser(databaseType);
            var datos = parser.parsearArchivo(file);

            if (clearExisting) {
                limpiarTablas();
            }

            // Procesar CNV
            try {
                if (datos.getCnvData() != null && !datos.getCnvData().isEmpty()) {
                    cnvRepository.saveAll(datos.getCnvData());
                    estadisticas.put("CNV", datos.getCnvData().size());
                } else {
                    tablasVacias.add("CNV");
                }
            } catch (Exception e) {
                log.error("Error al procesar CNV", e);
                resultado.put("errores_CNV", e.getMessage());
            }

            // Procesar HisMinsa
            try {
                if (datos.getHisMinsaData() != null && !datos.getHisMinsaData().isEmpty()) {
                    hisMinsaRepository.saveAll(datos.getHisMinsaData());
                    estadisticas.put("HisMinsa", datos.getHisMinsaData().size());
                } else {
                    tablasVacias.add("HisMinsa");
                }
            } catch (Exception e) {
                log.error("Error al procesar HisMinsa", e);
                resultado.put("errores_HisMinsa", e.getMessage());
            }

            // Continuar con el resto de las tablas...
            procesarIndicadores(datos, estadisticas, tablasVacias, resultado);
            procesarObservaciones(datos, estadisticas, tablasVacias, resultado);
            procesarPadronNominal(datos, estadisticas, tablasVacias, resultado);
            procesarPeriodoAtencion(datos, estadisticas, tablasVacias, resultado);
            procesarTipoDocumento(datos, estadisticas, tablasVacias, resultado);
            procesarTipoEstado(datos, estadisticas, tablasVacias, resultado);
            procesarTipoSexo(datos, estadisticas, tablasVacias, resultado);
            procesarValores(datos, estadisticas, tablasVacias, resultado);

        } catch (Exception e) {
            log.error("Error general al procesar el backup", e);
            resultado.put("error_general", e.getMessage());
        }

        resultado.put("registros_procesados", estadisticas);
        resultado.put("tablas_vacias", tablasVacias);
        resultado.put("fecha_proceso", new Date());

        return resultado;
    }

    @Override
    public Map<String, Object> obtenerEstadisticas() {
        return Map.of();
    }

    @Transactional
    protected void limpiarTablas() {
        cnvRepository.deleteAll();
        hisMinsaRepository.deleteAll();
        indicadorRepository.deleteAll();
        observacionRepository.deleteAll();
        padronNominalRepository.deleteAll();
        periodoAtencionRepository.deleteAll();
        // No limpiar las tablas maestras si no vienen en el backup
        // tipoDocumentoRepository.deleteAll();
        // tipoEstadoRepository.deleteAll();
        // tipoSexoRepository.deleteAll();
        valorRepository.deleteAll();
    }

    // ... implementación de los métodos de procesamiento para cada tabla ...
}