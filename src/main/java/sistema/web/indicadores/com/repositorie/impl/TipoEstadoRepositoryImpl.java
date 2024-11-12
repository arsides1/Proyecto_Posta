package sistema.web.indicadores.com.repositorie.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sistema.web.indicadores.com.model.dto.TipoDocumentoDTO;
import sistema.web.indicadores.com.model.dto.TipoEstado;
import sistema.web.indicadores.com.repositorie.TipoEstadoRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TipoEstadoRepositoryImpl implements TipoEstadoRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<TipoEstado> rowMapper = (rs, rowNum) -> {
        TipoEstado dto = new TipoEstado();
        dto.setIdEstado(rs.getInt("IdEstado"));
        dto.setDescripcion(rs.getString("Descripcion"));
        return dto;
    };

    @Override
    public TipoEstado findByIdEstado(String idEstado) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM tbl_tiposestados WHERE IdEstado = ?",
                    rowMapper,
                    idEstado
            );
        } catch (Exception e) {
            log.error("Error al buscar por IdEstado {}: {}", idEstado, e.getMessage());
            throw new RuntimeException("Error al buscar en Tipos de Estado", e);
        }
    }

    @Override
    public List<TipoEstado> findAll() {
        String sql = "SELECT IdEstado, Descripcion FROM tbl_tiposestados";

        try {
            List<TipoEstado> result = jdbcTemplate.query(sql, rowMapper);
            log.info("Se recuperaron {} registros de tipos de estado", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error al recuperar registros de tipos de estado: {}", e.getMessage());
            throw new RuntimeException("Error al recuperar registros de Tipos de Estado", e);
        }
    }

    @Override
    public Long countRegistros() {
        try {
            Long count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM tbl_tiposestados",
                    Long.class
            );
            log.info("Total de registros en tipos de estado: {}", count);
            return count;
        } catch (Exception e) {
            log.error("Error al contar registros de tipos de estado: {}", e.getMessage());
            throw new RuntimeException("Error al contar registros de Tipos de Estado", e);
        }
    }

    @Override
    public void saveAll(List<TipoEstado> registros) {
        String sql = "INSERT INTO tbl_tiposestados (IdEstado, Descripcion) VALUES (?,?)";

        try {
            jdbcTemplate.batchUpdate(sql, registros, registros.size(),
                    (ps, registro) -> {
                        ps.setInt(1, registro.getIdEstado());
                        ps.setString(2, registro.getDescripcion());
                    }
            );
            log.info("Se guardaron {} registros en tipos de estado", registros.size());
        } catch (Exception e) {
            log.error("Error al guardar registros en tipos de estado: {}", e.getMessage());
            throw new RuntimeException("Error al guardar registros en Tipos de Estado", e);
        }
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update("DELETE FROM tbl_tiposestados");
            log.info("Se eliminaron todos los registros de tipos de estado");
        } catch (Exception e) {
            log.error("Error al eliminar registros de tipos de estado: {}", e.getMessage());
            throw new RuntimeException("Error al eliminar registros de Tipos de Estado", e);
        }
    }
}