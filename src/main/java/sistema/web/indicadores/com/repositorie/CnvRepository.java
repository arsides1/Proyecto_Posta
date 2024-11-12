package sistema.web.indicadores.com.repositorie;

import sistema.web.indicadores.com.model.dto.CnvDTO;

import java.util.List;

public interface CnvRepository{
    List<CnvDTO> findAll();
    Long countRegistros();
    void saveAll(List<CnvDTO> registros);
    void deleteAll();
}
