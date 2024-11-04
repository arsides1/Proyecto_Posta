package sistema.web.indicadores.com.repositorie;

import sistema.web.indicadores.com.model.dto.HisMinsaDTO;

import java.util.List;

public interface HisMinsaRepository {
    public HisMinsaDTO findByIdCita(String idCita);
    public List<HisMinsaDTO> findByAnio(String anio);
    public Long countRegistros();
    public void saveAll(List<HisMinsaDTO> registros);
}
