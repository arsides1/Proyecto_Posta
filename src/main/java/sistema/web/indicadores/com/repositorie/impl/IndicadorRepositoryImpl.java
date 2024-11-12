package sistema.web.indicadores.com.repositorie.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sistema.web.indicadores.com.model.dto.CnvDTO;
import sistema.web.indicadores.com.model.dto.IndicadorDTO;
import sistema.web.indicadores.com.repositorie.IndicadorRepository;

import java.util.List;
@Repository
@RequiredArgsConstructor
@Slf4j
public class IndicadorRepositoryImpl implements IndicadorRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<IndicadorDTO> rowMapper = (rs, rowNum) -> {
        IndicadorDTO dto = new IndicadorDTO();
        dto.setCodigoItem(rs.getString("Codigo_Item"));
        dto.setDescripcionItem(rs.getString("Descripcion_Item"));
        dto.setNombreIndicador(rs.getString("Nombre_Indicador"));
        dto.setIdUps(rs.getString("Id_Ups"));
        dto.setDescripcionUps(rs.getString("Descripcion_Ups"));
        return dto;
    };
    @Override
    public IndicadorDTO findByCodigoItem(String codigoItem) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM tbl_indicadores WHERE Codigo_Item = ?",
                rowMapper,
                codigoItem
        );
    }

    @Override
    public List<IndicadorDTO> findAll() {
        return jdbcTemplate.query("SELECT * FROM tbl_indicadores", rowMapper);
    }

    @Override
    public Long countRegistros() {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM tbl_indicadores",
                Long.class
        );
    }

    @Override
    public void saveAll(List<IndicadorDTO> registros) {
        jdbcTemplate.batchUpdate(
                "INSERT INTO tbl_indicadores (Codigo_Item, Descripcion_Item, Nombre_Indicador, Id_Ups, Descripcion_Ups) VALUES (?, ?, ?, ?, ?)",
                registros,
                registros.size(),
                (ps, registro) -> {
                    ps.setString(1, registro.getCodigoItem());
                    ps.setString(2, registro.getDescripcionItem());
                    ps.setString(3, registro.getNombreIndicador());
                    ps.setString(4, registro.getIdUps());
                    ps.setString(5, registro.getDescripcionUps());
                }
        );
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update("DELETE FROM tbl_indicadores");
            log.info("Se eliminaron todos los registros de tbl_indicadores");
        } catch (Exception e) {
            log.error("Error al eliminar registros de tbl_indicadores: {}", e.getMessage());
            throw new RuntimeException("Error al eliminar registros de Indicadores", e);
        }
    }
}
