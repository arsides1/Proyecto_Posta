package sistema.web.indicadores.com.repositorie;

import sistema.web.indicadores.com.model.dto.HisMinsaDTO;
import sistema.web.indicadores.com.model.dto.ObservacionDTO;

import java.util.List;

public interface HisMinsaRepository {
    public HisMinsaDTO findByIdCita(String idCita);
    List<HisMinsaDTO> findAll();
    public List<HisMinsaDTO> findByAnio(String anio);
    public Long countRegistros();
    public void saveAll(List<HisMinsaDTO> registros);
    void deleteAll();
}
