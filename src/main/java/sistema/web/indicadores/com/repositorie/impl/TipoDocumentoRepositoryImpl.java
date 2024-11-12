package sistema.web.indicadores.com.repositorie.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sistema.web.indicadores.com.model.dto.PeriodoAtencionDTO;
import sistema.web.indicadores.com.model.dto.TipoDocumentoDTO;
import sistema.web.indicadores.com.repositorie.TipoDocumentoRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TipoDocumentoRepositoryImpl implements TipoDocumentoRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<TipoDocumentoDTO> rowMapper = (rs, rowNum) -> {
        TipoDocumentoDTO dto = new TipoDocumentoDTO();
        dto.setIdTipoDocumento(rs.getInt("IdTipoDocumento"));
        dto.setTipoDocumento(rs.getString("TipoDocumento"));
        dto.setDescripcionDocumento(rs.getString("Descripcion_Documento"));
        return dto;
    };

    @Override
    public TipoDocumentoDTO findByIdTipoDocumento(String idTipoDocumento) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM tbl_tiposdocumento WHERE IdTipoDocumento = ?",
                    rowMapper,
                    idTipoDocumento
            );
        } catch (Exception e) {
            log.error("Error al buscar por IdTipoDocumento {}: {}", idTipoDocumento, e.getMessage());
            throw new RuntimeException("Error al buscar en Tipos de Documento", e);
        }
    }

    @Override
    public List<TipoDocumentoDTO> findAll() {
        String sql = "SELECT IdTipoDocumento, TipoDocumento, Descripcion_Documento " +
                "FROM tbl_tiposdocumento";

        try {
            List<TipoDocumentoDTO> result = jdbcTemplate.query(sql, rowMapper);
            log.info("Se recuperaron {} registros de tipos de documento", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error al recuperar registros de tipos de documento: {}", e.getMessage());
            throw new RuntimeException("Error al recuperar registros de Tipos de Documento", e);
        }
    }

    @Override
    public Long countRegistros() {
        try {
            Long count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM tbl_tiposdocumento",
                    Long.class
            );
            log.info("Total de registros en tipos de documento: {}", count);
            return count;
        } catch (Exception e) {
            log.error("Error al contar registros de tipos de documento: {}", e.getMessage());
            throw new RuntimeException("Error al contar registros de Tipos de Documento", e);
        }
    }

    @Override
    public void saveAll(List<TipoDocumentoDTO> registros) {
        String sql = "INSERT INTO tbl_tiposdocumento (IdTipoDocumento, TipoDocumento, " +
                "Descripcion_Documento) VALUES (?,?,?)";

        try {
            jdbcTemplate.batchUpdate(sql, registros, registros.size(),
                    (ps, registro) -> {
                        ps.setInt(1, registro.getIdTipoDocumento());
                        ps.setString(2, registro.getTipoDocumento());
                        ps.setString(3, registro.getDescripcionDocumento());
                    }
            );
            log.info("Se guardaron {} registros en tipos de documento", registros.size());
        } catch (Exception e) {
            log.error("Error al guardar registros en tipos de documento: {}", e.getMessage());
            throw new RuntimeException("Error al guardar registros en Tipos de Documento", e);
        }
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update("DELETE FROM tbl_tiposdocumento");
            log.info("Se eliminaron todos los registros de tipos de documento");
        } catch (Exception e) {
            log.error("Error al eliminar registros de tipos de documento: {}", e.getMessage());
            throw new RuntimeException("Error al eliminar registros de Tipos de Documento", e);
        }
    }
}