package sistema.web.indicadores.com.repositorie;


import sistema.web.indicadores.com.model.dto.TipoDocumentoDTO;

import java.util.List;

public interface TipoDocumentoRepository {
    public TipoDocumentoDTO findByIdTipoDocumento(String idTipoDocumento);
    List<TipoDocumentoDTO> findAll();
    public Long countRegistros();
    public void saveAll(List<TipoDocumentoDTO> registros);
    void deleteAll();
}
