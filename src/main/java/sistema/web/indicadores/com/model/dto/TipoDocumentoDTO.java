package sistema.web.indicadores.com.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TipoDocumentoDTO {

    @JsonProperty("idTipoDocumento")
    private Integer idTipoDocumento;

    @JsonProperty("tipoDocumento")
    private String tipoDocumento;

    @JsonProperty("descripcionDocumento")
    private String descripcionDocumento;
}
