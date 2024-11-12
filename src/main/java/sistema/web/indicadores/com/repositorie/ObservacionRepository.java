package sistema.web.indicadores.com.repositorie;

import sistema.web.indicadores.com.model.dto.IndicadorDTO;
import sistema.web.indicadores.com.model.dto.ObservacionDTO;

import java.util.List;

public interface ObservacionRepository {
    public ObservacionDTO findByIdCita(String idCita);
    List<ObservacionDTO> findAll();
    public Long countRegistros();
    public void saveAll(List<ObservacionDTO> registros);
    void deleteAll();

}
