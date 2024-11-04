package sistema.web.indicadores.com.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PadronNominalDTO {

    @JsonProperty("tipoDoc")
    private String tipoDoc;

    @JsonProperty("codigoPN")
    private String codigoPn;

    @JsonProperty("nuCnv")
    private String nuCnv;

    @JsonProperty("cui")
    private String cui;

    @JsonProperty("nuDniMenor")
    private String nuDniMenor;

    @JsonProperty("tiDocIdentidad")
    private String tiDocIdentidad;

    @JsonProperty("tipDoc")
    private String tipDoc;

    @JsonProperty("docPadron")
    private String docPadron;

    @JsonProperty("apPaternoN")
    private String apPaternoN;

    @JsonProperty("apMaternoN")
    private String apMaternoN;

    @JsonProperty("nombresN")
    private String nombresN;

    @JsonProperty("sexo")
    private String sexo;

    @JsonProperty("feNacMenor")
    private String feNacMenor;

    @JsonProperty("edadNino")
    private String edadDelNino;

    @JsonProperty("eje")
    private String eje;

    @JsonProperty("direccionPn")
    private String direccionPn;

    @JsonProperty("referencia")
    private String referencia;

    @JsonProperty("pnSubRegion")
    private String pnSubRegion;

    @JsonProperty("fed2020")
    private String fed2020;

    @JsonProperty("ubigeo")
    private String ubigeo;

    @JsonProperty("nDpto")
    private String nDpto;

    @JsonProperty("nProv")
    private String nProv;

    @JsonProperty("nDistrito")
    private String nDistrito;

    @JsonProperty("codCp")
    private String codCp;

    @JsonProperty("codigoEess")
    private String codigoEess;

    @JsonProperty("ipressPn")
    private String ipressPn;

    @JsonProperty("codEstab")
    private String codEstab;

    @JsonProperty("ipressPnC")
    private String ipressPnC;

    @JsonProperty("codigoEessNacimiento")
    private String codigoEessNacimiento;

    @JsonProperty("nomEessNacimiento")
    private String nomEessNacimiento;

    @JsonProperty("codigoEessAdscripcion")
    private String codigoEessAdscripcion;

    @JsonProperty("nEessAdscripcion")
    private String nEessAdscripcion;

    @JsonProperty("tipoSeguro")
    private String tipoSeguro;

    @JsonProperty("seguro")
    private String seguro;

    @JsonProperty("tiSeguroMenor")
    private String tiSeguroMenor;

    @JsonProperty("madDni")
    private String madDni;

    @JsonProperty("madApPaterno")
    private String madApPaterno;

    @JsonProperty("madApMaterno")
    private String madApMaterno;

    @JsonProperty("madNombre")
    private String madNombre;

    @JsonProperty("madCelu")
    private String madCelu;

    @JsonProperty("jfDni")
    private String jfDni;

    @JsonProperty("jfApPaterno")
    private String jfApPaterno;

    @JsonProperty("jfApMaterno")
    private String jfApMaterno;

    @JsonProperty("jfNombre")
    private String jfNombre;
}
