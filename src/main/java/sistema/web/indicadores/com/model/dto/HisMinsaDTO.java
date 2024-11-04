package sistema.web.indicadores.com.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.math.BigDecimal;


@Data
public class HisMinsaDTO {

    @JsonProperty("idCita")
    private String idCita;

    @JsonProperty("anio")
    private String anio;

    @JsonProperty("fechaAtencion")
    private String fechaAtencion;

    @JsonProperty("lote")
    private String lote;

    @JsonProperty("numPag")
    private Integer numPag;

    @JsonProperty("numReg")
    private Integer numReg;

    @JsonProperty("idUps")
    private String idUps;

    @JsonProperty("descripcionUps")
    private String descripcionUps;

    @JsonProperty("idEstablecimiento")
    private Integer idEstablecimiento;

    @JsonProperty("tipoDocPac")
    private Integer tipoDocPac;

    @JsonProperty("idPaciente")
    private String idPaciente;

    @JsonProperty("paciente")
    private String paciente;

    @JsonProperty("docPaciente")
    private String docPaciente;

    @JsonProperty("fichaFamiliar")
    private String fichaFamiliar;

    @JsonProperty("tipoDocPer")
    private Integer tipoDocPer;

    @JsonProperty("idPersonal")
    private String idPersonal;

    @JsonProperty("personal")
    private String personal;

    @JsonProperty("docPersonal")
    private String docPersonal;

    @JsonProperty("idProfesion")
    private String idProfesion;

    @JsonProperty("tipoDocReg")
    private Integer tipoDocReg;

    @JsonProperty("idRegistrador")
    private String idRegistrador;

    @JsonProperty("registrador")
    private String registrador;

    @JsonProperty("docRegistrador")
    private String docRegistrador;

    @JsonProperty("idFinanciador")
    private String idFinanciador;

    @JsonProperty("idCondicionEstablecimiento")
    private String idCondicionEstablecimiento;

    @JsonProperty("idCondicionServicio")
    private String idCondicionServicio;

    @JsonProperty("anioActualPaciente")
    private Integer anioActualPaciente;

    @JsonProperty("mesActualPaciente")
    private Integer mesActualPaciente;

    @JsonProperty("diaActualPaciente")
    private Integer diaActualPaciente;

    @JsonProperty("idTurno")
    private String idTurno;

    @JsonProperty("codigoItem")
    private String codigoItem;

    @JsonProperty("descripcionItem")
    private String descripcionItem;

    @JsonProperty("fgTipo")
    private String fgTipo;

    @JsonProperty("valorLab")
    private String valorLab;

    @JsonProperty("tipoDiagnostico")
    private String tipoDiagnostico;

    @JsonProperty("idCorrelativoItem")
    private Integer idCorrelativoItem;

    @JsonProperty("idCorrelativoLab")
    private Integer idCorrelativoLab;

    @JsonProperty("hemoglobina")
    private BigDecimal hemoglobina;

}
