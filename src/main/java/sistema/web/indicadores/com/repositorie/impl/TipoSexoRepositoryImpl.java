package sistema.web.indicadores.com.repositorie.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sistema.web.indicadores.com.model.dto.TipoEstado;
import sistema.web.indicadores.com.model.dto.TipoSexoDTO;
import sistema.web.indicadores.com.repositorie.TipoSexoRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TipoSexoRepositoryImpl implements TipoSexoRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<TipoSexoDTO> rowMapper = (rs, rowNum) -> {
        TipoSexoDTO dto = new TipoSexoDTO();
        dto.setIdSexo(rs.getInt("IdSexo"));
        dto.setDescripcion(rs.getString("Descripcion"));
        return dto;
    };

    @Override
    public TipoSexoDTO findByIdSexo(String idSexo) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM tbl_tipossexo WHERE IdSexo = ?",
                    rowMapper,
                    idSexo
            );
        } catch (Exception e) {
            log.error("Error al buscar por IdSexo {}: {}", idSexo, e.getMessage());
            throw new RuntimeException("Error al buscar en Tipos de Sexo", e);
        }
    }

    @Override
    public List<TipoSexoDTO> findAll() {
        String sql = "SELECT IdSexo, Descripcion FROM tbl_tipossexo";

        try {
            List<TipoSexoDTO> result = jdbcTemplate.query(sql, rowMapper);
            log.info("Se recuperaron {} registros de tipos de sexo", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error al recuperar registros de tipos de sexo: {}", e.getMessage());
            throw new RuntimeException("Error al recuperar registros de Tipos de Sexo", e);
        }
    }

    @Override
    public Long countRegistros() {
        try {
            Long count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM tbl_tipossexo",
                    Long.class
            );
            log.info("Total de registros en tipos de sexo: {}", count);
            return count;
        } catch (Exception e) {
            log.error("Error al contar registros de tipos de sexo: {}", e.getMessage());
            throw new RuntimeException("Error al contar registros de Tipos de Sexo", e);
        }
    }

    @Override
    public void saveAll(List<TipoSexoDTO> registros) {
        String sql = "INSERT INTO tbl_tipossexo (IdSexo, Descripcion) VALUES (?,?)";

        try {
            jdbcTemplate.batchUpdate(sql, registros, registros.size(),
                    (ps, registro) -> {
                        ps.setInt(1, registro.getIdSexo());
                        ps.setString(2, registro.getDescripcion());
                    }
            );
            log.info("Se guardaron {} registros en tipos de sexo", registros.size());
        } catch (Exception e) {
            log.error("Error al guardar registros en tipos de sexo: {}", e.getMessage());
            throw new RuntimeException("Error al guardar registros en Tipos de Sexo", e);
        }
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update("DELETE FROM tbl_tipossexo");
            log.info("Se eliminaron todos los registros de tipos de sexo");
        } catch (Exception e) {
            log.error("Error al eliminar registros de tipos de sexo: {}", e.getMessage());
            throw new RuntimeException("Error al eliminar registros de Tipos de Sexo", e);
        }
    }
}
