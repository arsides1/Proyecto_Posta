package sistema.web.indicadores.com.repositorie;



import sistema.web.indicadores.com.model.dto.TipoEstado;

import java.util.List;

public interface TipoEstadoRepository {
    public TipoEstado findByIdEstado(String idEstado);
    List<TipoEstado> findAll();
    public Long countRegistros();
    public void saveAll(List<TipoEstado> registros);
    void deleteAll();
}
