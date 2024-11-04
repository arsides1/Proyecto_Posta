package sistema.web.indicadores.com.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TipoSexoDTO {

    @JsonProperty("idSexo")
    private Integer idSexo;

    @JsonProperty("descripcion")
    private String descripcion;
}
