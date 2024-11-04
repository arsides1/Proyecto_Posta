package sistema.web.indicadores.com.util.parser.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sistema.web.indicadores.com.model.dto.*;
import lombok.extern.slf4j.Slf4j;
import sistema.web.indicadores.com.util.parser.ParserUtils;
import sistema.web.indicadores.com.util.parser.service.BackupParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class MySqlBackupParser implements BackupParser {
    private static final Pattern VALUES_PATTERN = Pattern.compile("\\((.*?)\\)");

    @Override
    public BackupData parsearArchivo(MultipartFile file) throws Exception {
        BackupData backupData = new BackupData();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream()))) {

            String linea;
            StringBuilder contenidoActual = new StringBuilder();
            String tablaActual = null;

            while ((linea = reader.readLine()) != null) {
                if (linea.contains("INSERT INTO")) {
                    if (tablaActual != null) {
                        procesarContenidoTabla(tablaActual, contenidoActual.toString(), backupData);
                        contenidoActual = new StringBuilder();
                    }
                    tablaActual = extraerNombreTabla(linea);
                }
                if (!linea.trim().startsWith("--") && !linea.trim().isEmpty()) {
                    contenidoActual.append(linea).append("\n");
                }
            }

            if (tablaActual != null) {
                procesarContenidoTabla(tablaActual, contenidoActual.toString(), backupData);
            }

        } catch (Exception e) {
            log.error("Error procesando backup MySQL", e);
            throw new Exception("Error al procesar backup MySQL: " + e.getMessage());
        }

        return backupData;
    }

    private String extraerNombreTabla(String linea) {
        int inicioNombre = linea.indexOf("INTO") + 4;
        int finNombre = linea.indexOf("(");
        if (finNombre == -1) {
            finNombre = linea.indexOf(" VALUES");
        }
        return linea.substring(inicioNombre, finNombre).trim();
    }

    private void procesarContenidoTabla(String tabla, String contenido, BackupData backupData) {
        List<String[]> valores = extraerValores(contenido);

        switch (tabla.toLowerCase().replace("`", "").trim()) {
            case "tbl_cnv" -> backupData.setCnvData(parsearDatosCnv(valores));
            case "tbl_hisminsa" -> backupData.setHisMinsaData(parsearDatosHisMinsa(valores));
            case "tbl_indicadores" -> backupData.setIndicadorData(parsearDatosIndicador(valores));
            case "tbl_observaciones" -> backupData.setObservacionData(parsearDatosObservacion(valores));
            case "tbl_padronnominal" -> backupData.setPadronNominalData(parsearDatosPadronNominal(valores));
            case "dimperiodoaten" -> backupData.setPeriodoAtencionData(parsearDatosPeriodoAtencion(valores));
            case "tbl_tiposdocumento" -> backupData.setTipoDocumentoData(parsearDatosTipoDocumento(valores));
            case "tbl_tiposestados" -> backupData.setTipoEstadoData(parsearDatosTipoEstado(valores));
            case "tbl_tipossexo" -> backupData.setTipoSexoData(parsearDatosTipoSexo(valores));
            case "tbl_valores" -> backupData.setValorData(parsearDatosValor(valores));
            default -> log.warn("Tabla no reconocida: {}", tabla);
        }
    }

    private List<String[]> extraerValores(String contenido) {
        List<String[]> valores = new ArrayList<>();
        Matcher matcher = VALUES_PATTERN.matcher(contenido);
        while (matcher.find()) {
            String[] fila = matcher.group(1).split(",(?=(?:[^']*'[^']*')*[^']*$)", -1);
            for (int i = 0; i < fila.length; i++) {
                fila[i] = fila[i].trim().replaceAll("^'|'$", "");
            }
            valores.add(fila);
        }
        return valores;
    }

    private List<CnvDTO> parsearDatosCnv(List<String[]> valores) {
        List<CnvDTO> resultados = new ArrayList<>();
        for (String[] fila : valores) {
            try {
                CnvDTO dto = new CnvDTO();
                dto.setNuCNV(ParserUtils.getString(fila, 0));
                dto.setPesoNacido(ParserUtils.getString(fila, 1));
                dto.setDurEmbParto(ParserUtils.getString(fila, 2));
                resultados.add(dto);
            } catch (Exception e) {
                ParserUtils.logParsingError("CNV", fila, e);
            }
        }
        return resultados;
    }

    private List<HisMinsaDTO> parsearDatosHisMinsa(List<String[]> valores) {
        List<HisMinsaDTO> resultados = new ArrayList<>();
        for (String[] fila : valores) {
            try {
                HisMinsaDTO dto = new HisMinsaDTO();
                dto.setIdCita(ParserUtils.getString(fila, 0));
                dto.setAnio(ParserUtils.getString(fila, 1));
                dto.setFechaAtencion(ParserUtils.getString(fila, 2));
                dto.setLote(ParserUtils.getString(fila, 3));
                dto.setNumPag(ParserUtils.getInteger(fila, 4));
                dto.setNumReg(ParserUtils.getInteger(fila, 5));
                dto.setIdUps(ParserUtils.getString(fila, 6));
                dto.setDescripcionUps(ParserUtils.getString(fila, 7));
                dto.setIdEstablecimiento(ParserUtils.getInteger(fila, 8));
                dto.setTipoDocPac(ParserUtils.getInteger(fila, 9));
                dto.setIdPaciente(ParserUtils.getString(fila, 10));
                dto.setPaciente(ParserUtils.getString(fila, 11));
                dto.setDocPaciente(ParserUtils.getString(fila, 12));
                dto.setFichaFamiliar(ParserUtils.getString(fila, 13));
                dto.setTipoDocPer(ParserUtils.getInteger(fila, 14));
                dto.setIdPersonal(ParserUtils.getString(fila, 15));
                dto.setPersonal(ParserUtils.getString(fila, 16));
                dto.setDocPersonal(ParserUtils.getString(fila, 17));
                dto.setIdProfesion(ParserUtils.getString(fila, 18));
                dto.setTipoDocReg(ParserUtils.getInteger(fila, 19));
                dto.setIdRegistrador(ParserUtils.getString(fila, 20));
                dto.setRegistrador(ParserUtils.getString(fila, 21));
                dto.setDocRegistrador(ParserUtils.getString(fila, 22));
                dto.setIdFinanciador(ParserUtils.getString(fila, 23));
                dto.setIdCondicionEstablecimiento(ParserUtils.getString(fila, 24));
                dto.setIdCondicionServicio(ParserUtils.getString(fila, 25));
                dto.setAnioActualPaciente(ParserUtils.getInteger(fila, 26));
                dto.setMesActualPaciente(ParserUtils.getInteger(fila, 27));
                dto.setDiaActualPaciente(ParserUtils.getInteger(fila, 28));
                dto.setIdTurno(ParserUtils.getString(fila, 29));
                dto.setCodigoItem(ParserUtils.getString(fila, 30));
                dto.setDescripcionItem(ParserUtils.getString(fila, 31));
                dto.setFgTipo(ParserUtils.getString(fila, 32));
                dto.setValorLab(ParserUtils.getString(fila, 33));
                dto.setTipoDiagnostico(ParserUtils.getString(fila, 34));
                dto.setIdCorrelativoItem(ParserUtils.getInteger(fila, 35));
                dto.setIdCorrelativoLab(ParserUtils.getInteger(fila, 36));
                dto.setHemoglobina(ParserUtils.getBigDecimal(fila, 37));
                resultados.add(dto);
            } catch (Exception e) {
                ParserUtils.logParsingError("HisMinsa", fila, e);
            }
        }
        return resultados;
    }

    private List<IndicadorDTO> parsearDatosIndicador(List<String[]> valores) {
        List<IndicadorDTO> resultados = new ArrayList<>();
        for (String[] fila : valores) {
            try {
                IndicadorDTO dto = new IndicadorDTO();
                dto.setCodigoItem(ParserUtils.getString(fila, 0));
                dto.setDescripcionItem(ParserUtils.getString(fila, 1));
                dto.setNombreIndicador(ParserUtils.getString(fila, 2));
                dto.setIdUps(ParserUtils.getString(fila, 3));
                dto.setDescripcionUps(ParserUtils.getString(fila, 4));
                resultados.add(dto);
            } catch (Exception e) {
                ParserUtils.logParsingError("Indicador", fila, e);
            }
        }
        return resultados;
    }

    private List<ObservacionDTO> parsearDatosObservacion(List<String[]> valores) {
        List<ObservacionDTO> resultados = new ArrayList<>();
        for (String[] fila : valores) {
            try {
                ObservacionDTO dto = new ObservacionDTO();
                dto.setObservacion(ParserUtils.getString(fila, 0));
                dto.setPrograma(ParserUtils.getString(fila, 1));
                dto.setAnio(ParserUtils.getString(fila, 2));
                dto.setMes(ParserUtils.getString(fila, 3));
                dto.setDia(ParserUtils.getString(fila, 4));
                dto.setFechaAtencion(ParserUtils.getString(fila, 5));
                dto.setLote(ParserUtils.getString(fila, 6));
                dto.setNumPag(ParserUtils.getInteger(fila, 7));
                dto.setNumReg(ParserUtils.getInteger(fila, 8));
                dto.setIdCita(ParserUtils.getString(fila, 9));
                dto.setIdUps(ParserUtils.getString(fila, 10));
                dto.setIdUps(ParserUtils.getString(fila, 11));
                dto.setIdCondicionEstablecimiento(ParserUtils.getString(fila, 12));
                dto.setIdCondicionServicio(ParserUtils.getString(fila, 13));
                dto.setIdEtnia(ParserUtils.getString(fila, 14));
                dto.setIdPaciente(ParserUtils.getString(fila, 15));
                dto.setIdPaciente(ParserUtils.getString(fila, 16));
                dto.setEdadReg(ParserUtils.getInteger(fila, 17));
                dto.setTipoEdad(ParserUtils.getString(fila, 18));
                dto.setCodigoItem(ParserUtils.getString(fila, 19));
                dto.setDescripcionItem(ParserUtils.getString(fila, 20));
                dto.setFgTipo(ParserUtils.getString(fila, 21));
                dto.setTipoDiagnostico(ParserUtils.getString(fila, 22));
                dto.setValorLab(ParserUtils.getString(fila, 23));
                dto.setHemoglobina(ParserUtils.getBigDecimal(fila, 24));
                dto.setIdCorrelativoItem(ParserUtils.getInteger(fila, 25));
                dto.setIdCorrelativoLab(ParserUtils.getInteger(fila, 26));
                dto.setIdGenero(ParserUtils.getString(fila, 27));
                dto.setHistoriaClinica(ParserUtils.getString(fila, 28));
                dto.setDocPaciente(ParserUtils.getString(fila, 29));
                dto.setPaciente(ParserUtils.getString(fila, 30));
                dto.setDocPersonal(ParserUtils.getString(fila, 31));
                dto.setAtendio(ParserUtils.getString(fila, 32));
                dto.setIdColegio(ParserUtils.getString(fila, 33));
                dto.setDocRegistrador(ParserUtils.getString(fila, 34));
                dto.setRegistrador(ParserUtils.getString(fila, 35));
                dto.setIdProfesion(ParserUtils.getString(fila, 36));
                resultados.add(dto);
            } catch (Exception e) {
                ParserUtils.logParsingError("Observacion", fila, e);
            }
        }
        return resultados;
    }

    private List<PadronNominalDTO> parsearDatosPadronNominal(List<String[]> valores) {
        List<PadronNominalDTO> resultados = new ArrayList<>();
        for (String[] fila : valores) {
            try {
                PadronNominalDTO dto = new PadronNominalDTO();
                dto.setTipoDoc(ParserUtils.getString(fila, 0));
                dto.setCodigoPn(ParserUtils.getString(fila, 1));
                dto.setNuCnv(ParserUtils.getString(fila, 2));
                dto.setCui(ParserUtils.getString(fila, 3));
                dto.setNuDniMenor(ParserUtils.getString(fila, 4));
                dto.setTiDocIdentidad(ParserUtils.getString(fila, 5));
                dto.setDocPadron(ParserUtils.getString(fila, 6));
                dto.setApPaternoN(ParserUtils.getString(fila, 7));
                dto.setApMaternoN(ParserUtils.getString(fila, 8));
                dto.setNombresN(ParserUtils.getString(fila, 9));
                dto.setSexo(ParserUtils.getString(fila, 10));
                dto.setFeNacMenor(ParserUtils.getString(fila, 11));
                dto.setEdadDelNino(ParserUtils.getString(fila, 12));
                dto.setEje(ParserUtils.getString(fila, 13));
                dto.setDireccionPn(ParserUtils.getString(fila, 14));
                dto.setReferencia(ParserUtils.getString(fila, 15));
                dto.setPnSubRegion(ParserUtils.getString(fila, 16));
                dto.setFed2020(ParserUtils.getString(fila, 17));
                dto.setUbigeo(ParserUtils.getString(fila, 18));
                dto.setNDpto(ParserUtils.getString(fila, 19));
                dto.setNProv(ParserUtils.getString(fila, 20));
                dto.setNDistrito(ParserUtils.getString(fila, 21));
                dto.setCodCp(ParserUtils.getString(fila, 22));
                dto.setCodigoEess(ParserUtils.getString(fila, 23));
                dto.setIpressPn(ParserUtils.getString(fila, 24));
                dto.setCodEstab(ParserUtils.getString(fila, 25));
                dto.setIpressPnC(ParserUtils.getString(fila, 26));
                dto.setCodigoEessNacimiento(ParserUtils.getString(fila, 27));
                dto.setNomEessNacimiento(ParserUtils.getString(fila, 28));
                dto.setCodigoEessNacimiento(ParserUtils.getString(fila, 29));
                dto.setCodigoEessAdscripcion(ParserUtils.getString(fila, 30));
                dto.setNEessAdscripcion(ParserUtils.getString(fila, 31));
                dto.setTipoSeguro(ParserUtils.getString(fila, 32));
                dto.setSeguro(ParserUtils.getString(fila, 33));
                dto.setTiSeguroMenor(ParserUtils.getString(fila, 34));
                dto.setMadDni(ParserUtils.getString(fila, 35));
                dto.setMadApPaterno(ParserUtils.getString(fila, 36));
                dto.setMadApMaterno(ParserUtils.getString(fila, 36));
                dto.setMadNombre(ParserUtils.getString(fila, 37));
                dto.setMadCelu(ParserUtils.getString(fila, 38));
                dto.setJfDni(ParserUtils.getString(fila, 39));
                dto.setJfApPaterno(ParserUtils.getString(fila, 40));
                dto.setJfApMaterno(ParserUtils.getString(fila, 40));
                dto.setJfNombre(ParserUtils.getString(fila, 41));
                resultados.add(dto);
            } catch (Exception e) {
                ParserUtils.logParsingError("PadronNominal", fila, e);
            }
        }
        return resultados;
    }

    private List<PeriodoAtencionDTO> parsearDatosPeriodoAtencion(List<String[]> valores) {
        List<PeriodoAtencionDTO> resultados = new ArrayList<>();
        for (String[] fila : valores) {
            try {
                PeriodoAtencionDTO dto = new PeriodoAtencionDTO();
                dto.setPeriodoKey(ParserUtils.getInteger(fila, 0));
                dto.setPeriodo(ParserUtils.getString(fila, 1));
                dto.setAnio(ParserUtils.getInteger(fila, 2));
                dto.setSemestre(ParserUtils.getString(fila, 3));
                dto.setTrimestre(ParserUtils.getString(fila, 4));
                dto.setMes(ParserUtils.getString(fila, 5));
                dto.setNroMes(ParserUtils.getInteger(fila, 6));
                resultados.add(dto);
            } catch (Exception e) {
                ParserUtils.logParsingError("PeriodoAtencion", fila, e);
            }
        }
        return resultados;
    }

    private List<TipoDocumentoDTO> parsearDatosTipoDocumento(List<String[]> valores) {
        List<TipoDocumentoDTO> resultados = new ArrayList<>();
        for (String[] fila : valores) {
            try {
                TipoDocumentoDTO dto = new TipoDocumentoDTO();
                dto.setIdTipoDocumento(ParserUtils.getInteger(fila, 0));
                dto.setTipoDocumento(ParserUtils.getString(fila, 1));
                dto.setDescripcionDocumento(ParserUtils.getString(fila, 2));
                resultados.add(dto);
            } catch (Exception e) {
                ParserUtils.logParsingError("TipoDocumento", fila, e);
            }
        }
        return resultados;
    }

    private List<TipoEstado> parsearDatosTipoEstado(List<String[]> valores) {
        List<TipoEstado> resultados = new ArrayList<>();
        for (String[] fila : valores) {
            try {
                TipoEstado dto = new TipoEstado();
                dto.setIdEstado(ParserUtils.getInteger(fila, 0));
                dto.setDescripcion(ParserUtils.getString(fila, 1));
                resultados.add(dto);
            } catch (Exception e) {
                ParserUtils.logParsingError("TipoEstado", fila, e);
            }
        }
        return resultados;
    }

    private List<TipoSexoDTO> parsearDatosTipoSexo(List<String[]> valores) {
        List<TipoSexoDTO> resultados = new ArrayList<>();
        for (String[] fila : valores) {
            try {
                TipoSexoDTO dto = new TipoSexoDTO();
                dto.setIdSexo(ParserUtils.getInteger(fila, 0));
                dto.setDescripcion(ParserUtils.getString(fila, 1));
                resultados.add(dto);
            } catch (Exception e) {
                ParserUtils.logParsingError("TipoSexo", fila, e);
            }
        }
        return resultados;
    }

    private List<ValorDTO> parsearDatosValor(List<String[]> valores) {
        List<ValorDTO> resultados = new ArrayList<>();
        for (String[] fila : valores) {
            try {
                ValorDTO dto = new ValorDTO();
                dto.setIdValor(ParserUtils.getInteger(fila, 0));
                dto.setCodigoItem(ParserUtils.getString(fila, 1));
                dto.setMetaFisicaAnual(ParserUtils.getFloat(fila, 2));
                dto.setCobPorcMetaAnual(ParserUtils.getFloat(fila, 3));
                dto.setMes(ParserUtils.getString(fila, 4));
                dto.setAvanceMetaFisicaMensual(ParserUtils.getFloat(fila, 5));
                dto.setAvanceMetaFisicaTotal(ParserUtils.getFloat(fila, 6));
                resultados.add(dto);
            } catch (Exception e) {
                ParserUtils.logParsingError("Valor", fila, e);
            }
        }
        return resultados;
    }
}