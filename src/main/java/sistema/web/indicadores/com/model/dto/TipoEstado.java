package sistema.web.indicadores.com.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class TipoEstado  {
    @JsonProperty("idEstado")
    private Integer idEstado;

    @JsonProperty("descripcion")
    private String descripcion;
}
