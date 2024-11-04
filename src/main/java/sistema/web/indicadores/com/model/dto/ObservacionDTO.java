package sistema.web.indicadores.com.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ObservacionDTO {

    @JsonProperty("observacion")
    private String observacion;

    @JsonProperty("programa")
    private String programa;

    @JsonProperty("anio")
    private String anio;

    @JsonProperty("mes")
    private String mes;

    @JsonProperty("dia")
    private String dia;

    @JsonProperty("fechaAtencion")
    private String fechaAtencion;

    @JsonProperty("lote")
    private String lote;

    @JsonProperty("numPag")
    private Integer numPag;

    @JsonProperty("numReg")
    private Integer numReg;

    @JsonProperty("idCita")
    private String idCita;

    @JsonProperty("idUps")
    private String idUps;

    @JsonProperty("idCondicionEstablecimiento")
    private String idCondicionEstablecimiento;

    @JsonProperty("idCondicionServicio")
    private String idCondicionServicio;

    @JsonProperty("idEtnia")
    private String idEtnia;

    @JsonProperty("idPaciente")
    private String idPaciente;

    @JsonProperty("edadReg")
    private Integer edadReg;

    @JsonProperty("tipoEdad")
    private String tipoEdad;

    @JsonProperty("codigoItem")
    private String codigoItem;

    @JsonProperty("descripcionItem")
    private String descripcionItem;

    @JsonProperty("fgTipo")
    private String fgTipo;

    @JsonProperty("tipoDiagnostico")
    private String tipoDiagnostico;

    @JsonProperty("valorLab")
    private String valorLab;

    @JsonProperty("hemoglobina")
    private BigDecimal hemoglobina;

    @JsonProperty("idCorrelativoItem")
    private Integer idCorrelativoItem;

    @JsonProperty("idCorrelativoLab")
    private Integer idCorrelativoLab;

    @JsonProperty("idGenero")
    private String idGenero;

    @JsonProperty("historiaClinica")
    private String historiaClinica;

    @JsonProperty("docPaciente")
    private String docPaciente;

    @JsonProperty("paciente")
    private String paciente;

    @JsonProperty("docPersonal")
    private String docPersonal;

    @JsonProperty("atendio")
    private String atendio;

    @JsonProperty("idColegio")
    private String idColegio;

    @JsonProperty("docRegistrador")
    private String docRegistrador;

    @JsonProperty("registrador")
    private String registrador;

    @JsonProperty("idProfesion")
    private String idProfesion;
}
