package sistema.web.indicadores.com.repositorie;

import sistema.web.indicadores.com.model.dto.CnvDTO;

import java.util.List;

public interface CnvRepository {
    public List<CnvDTO> findAll();
    public Long countRegistros();
    public void saveAll(List<CnvDTO> registros);
}
