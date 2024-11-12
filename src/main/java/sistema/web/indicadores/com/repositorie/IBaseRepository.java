package sistema.web.indicadores.com.repositorie;

import java.util.List;

public interface IBaseRepository<T> {
    List<T> findAll();
    void saveAll(List<T> registros);
    void deleteAll();
    Long countRegistros();

}