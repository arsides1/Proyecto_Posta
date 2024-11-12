package sistema.web.indicadores.com.repositorie;

import sistema.web.indicadores.com.model.dto.ObservacionDTO;
import sistema.web.indicadores.com.model.dto.PadronNominalDTO;

import java.util.List;

public interface PadronNominalRepository {
    public PadronNominalDTO findByTipoDoc(String tipoDoc);
    List<PadronNominalDTO> findAll();
    public Long countRegistros();
    public void saveAll(List<PadronNominalDTO> registros);
    void deleteAll();
}
