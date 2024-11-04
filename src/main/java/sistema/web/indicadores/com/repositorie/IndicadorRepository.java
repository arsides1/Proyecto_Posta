package sistema.web.indicadores.com.repositorie;

import sistema.web.indicadores.com.model.dto.IndicadorDTO;

import java.util.List;

public interface IndicadorRepository {

    public IndicadorDTO findByCodigoItem(String codigoItem);
    public Long countRegistros();
    public void saveAll(List<IndicadorDTO> registros);

}
