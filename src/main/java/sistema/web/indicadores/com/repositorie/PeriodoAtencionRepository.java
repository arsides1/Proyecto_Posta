package sistema.web.indicadores.com.repositorie;

import sistema.web.indicadores.com.model.dto.PadronNominalDTO;
import sistema.web.indicadores.com.model.dto.PeriodoAtencionDTO;

import java.util.List;

public interface PeriodoAtencionRepository {
    public PeriodoAtencionDTO findByPeriodoKey(String periodoKey);
    List<PeriodoAtencionDTO> findAll();
    public Long countRegistros();
    public void saveAll(List<PeriodoAtencionDTO> registros);
    void deleteAll();
}
