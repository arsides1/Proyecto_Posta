package sistema.web.indicadores.com.repositorie.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sistema.web.indicadores.com.model.dto.TipoSexoDTO;
import sistema.web.indicadores.com.model.dto.ValorDTO;
import sistema.web.indicadores.com.repositorie.ValorRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ValorRepositoryImpl implements ValorRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<ValorDTO> rowMapper = (rs, rowNum) -> {
        ValorDTO dto = new ValorDTO();
        dto.setIdValor(rs.getInt("Id_Valor"));
        dto.setCodigoItem(rs.getString("Codigo_Item"));
        dto.setMetaFisicaAnual(rs.getFloat("Meta_Fisica_Anual"));
        dto.setCobPorcMetaAnual(rs.getFloat("Cob_Por_Meta_Anual"));
        dto.setMes(rs.getString("Mes"));
        dto.setAvanceMetaFisicaMensual(rs.getFloat("AvanceMetaFisicaMensual"));
        dto.setAvanceMetaFisicaTotal(rs.getFloat("AvanceMetaFisicaTotal"));
        return dto;
    };

    @Override
    public ValorDTO findByIdValor(String idValor) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM tbl_valores WHERE Id_Valor = ?",
                    rowMapper,
                    idValor
            );
        } catch (Exception e) {
            log.error("Error al buscar por Id_Valor {}: {}", idValor, e.getMessage());
            throw new RuntimeException("Error al buscar en Valores", e);
        }
    }

    @Override
    public List<ValorDTO> findAll() {
        String sql = "SELECT Id_Valor, Codigo_Item, Meta_Fisica_Anual, " +
                "Cob_Por_Meta_Anual, Mes, AvanceMetaFisicaMensual, AvanceMetaFisicaTotal " +
                "FROM tbl_valores";

        try {
            List<ValorDTO> result = jdbcTemplate.query(sql, rowMapper);
            log.info("Se recuperaron {} registros de valores", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error al recuperar registros de valores: {}", e.getMessage());
            throw new RuntimeException("Error al recuperar registros de Valores", e);
        }
    }

    @Override
    public Long countRegistros() {
        try {
            Long count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM tbl_valores",
                    Long.class
            );
            log.info("Total de registros en valores: {}", count);
            return count;
        } catch (Exception e) {
            log.error("Error al contar registros de valores: {}", e.getMessage());
            throw new RuntimeException("Error al contar registros de Valores", e);
        }
    }

    @Override
    public void saveAll(List<ValorDTO> registros) {
        String sql = "INSERT INTO tbl_valores (Id_Valor, Codigo_Item, Meta_Fisica_Anual, " +
                "Cob_Por_Meta_Anual, Mes, AvanceMetaFisicaMensual, AvanceMetaFisicaTotal) " +
                "VALUES (?,?,?,?,?,?,?)";

        try {
            jdbcTemplate.batchUpdate(sql, registros, registros.size(),
                    (ps, registro) -> {
                        ps.setInt(1, registro.getIdValor());
                        ps.setString(2, registro.getCodigoItem());
                        ps.setFloat(3, registro.getMetaFisicaAnual());
                        ps.setFloat(4, registro.getCobPorcMetaAnual());
                        ps.setString(5, registro.getMes());
                        ps.setFloat(6, registro.getAvanceMetaFisicaMensual());
                        ps.setFloat(7, registro.getAvanceMetaFisicaTotal());
                    }
            );
            log.info("Se guardaron {} registros en valores", registros.size());
        } catch (Exception e) {
            log.error("Error al guardar registros en valores: {}", e.getMessage());
            throw new RuntimeException("Error al guardar registros en Valores", e);
        }
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update("DELETE FROM tbl_valores");
            log.info("Se eliminaron todos los registros de valores");
        } catch (Exception e) {
            log.error("Error al eliminar registros de valores: {}", e.getMessage());
            throw new RuntimeException("Error al eliminar registros de Valores", e);
        }
    }
}
