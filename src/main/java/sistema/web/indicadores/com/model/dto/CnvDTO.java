package sistema.web.indicadores.com.model.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CnvDTO {

    @JsonProperty("nuCNV")
    private String nuCNV;

    @JsonProperty("pesoNacido")
    private String pesoNacido;

    @JsonProperty("durEmbParto")
    private String durEmbParto;
}
