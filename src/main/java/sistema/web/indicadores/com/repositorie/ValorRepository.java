package sistema.web.indicadores.com.repositorie;

import sistema.web.indicadores.com.model.dto.ValorDTO;

import java.util.List;

public interface ValorRepository {
    public ValorDTO findByIdValor(String idValor);
    List<ValorDTO> findAll();
    public Long countRegistros();
    public void saveAll(List<ValorDTO> registros);
    void deleteAll();
}
