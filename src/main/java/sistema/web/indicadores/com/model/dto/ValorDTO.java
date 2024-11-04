package sistema.web.indicadores.com.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ValorDTO {

    @JsonProperty("idValor")
    private Integer idValor;

    @JsonProperty("codigoItem")
    private String codigoItem;

    @JsonProperty("metaFisicaAnual")
    private Float metaFisicaAnual;

    @JsonProperty("cobPorcMetaAnual")
    private Float cobPorcMetaAnual;

    @JsonProperty("mes")
    private String mes;

    @JsonProperty("avanceMetaFisicaMensual")
    private Float avanceMetaFisicaMensual;

    @JsonProperty("avanceMetaFisicaTotal")
    private Float avanceMetaFisicaTotal;
}
