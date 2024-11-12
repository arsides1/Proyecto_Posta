package sistema.web.indicadores.com.repositorie;

import sistema.web.indicadores.com.model.dto.TipoSexoDTO;

import java.util.List;

public interface TipoSexoRepository {
    public TipoSexoDTO findByIdSexo(String idSexo);
    List<TipoSexoDTO> findAll();
    public Long countRegistros();
    public void saveAll(List<TipoSexoDTO> registros);
    void deleteAll();
}
