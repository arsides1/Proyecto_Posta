package sistema.web.indicadores.com.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IndicadorDTO {

    @JsonProperty("codigoItem")
    private String codigoItem;

    @JsonProperty("descripcionItem")
    private String descripcionItem;

    @JsonProperty("nombreIndicador")
    private String nombreIndicador;

    @JsonProperty("idUps")
    private String idUps;

    @JsonProperty("descripcionUps")
    private String descripcionUps;
}
