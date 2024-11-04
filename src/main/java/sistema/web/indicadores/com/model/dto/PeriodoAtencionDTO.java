package sistema.web.indicadores.com.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PeriodoAtencionDTO {

    @JsonProperty("periodoKey")
    private Integer periodoKey;

    @JsonProperty("periodo")
    private String periodo;

    @JsonProperty("anio")
    private Integer anio;

    @JsonProperty("semestre")
    private String semestre;

    @JsonProperty("trimestre")
    private String trimestre;

    @JsonProperty("mes")
    private String mes;

    @JsonProperty("nroMes")
    private Integer nroMes;
}
