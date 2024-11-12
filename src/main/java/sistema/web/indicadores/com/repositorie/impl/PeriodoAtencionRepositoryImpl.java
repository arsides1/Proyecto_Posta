package sistema.web.indicadores.com.repositorie.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sistema.web.indicadores.com.model.dto.PadronNominalDTO;
import sistema.web.indicadores.com.model.dto.PeriodoAtencionDTO;
import sistema.web.indicadores.com.repositorie.PeriodoAtencionRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PeriodoAtencionRepositoryImpl implements PeriodoAtencionRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<PeriodoAtencionDTO> rowMapper = (rs, rowNum) -> {
        PeriodoAtencionDTO dto = new PeriodoAtencionDTO();
        dto.setPeriodoKey(rs.getInt("PeriodoKey"));
        dto.setPeriodo(rs.getString("Periodo"));
        dto.setAnio(rs.getInt("Anio"));
        dto.setSemestre(rs.getString("Semestre"));
        dto.setTrimestre(rs.getString("Trimestre"));
        dto.setMes(rs.getString("Mes"));
        dto.setNroMes(rs.getInt("NroMes"));
        return dto;
    };

    @Override
    public PeriodoAtencionDTO findByPeriodoKey(String periodoKey) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM dimperiodoaten WHERE PeriodoKey = ?",
                    rowMapper,
                    periodoKey
            );
        } catch (Exception e) {
            log.error("Error al buscar por PeriodoKey {}: {}", periodoKey, e.getMessage());
            throw new RuntimeException("Error al buscar en Periodo Atención", e);
        }
    }

    @Override
    public List<PeriodoAtencionDTO> findAll() {
        String sql = "SELECT PeriodoKey, Periodo, Anio, Semestre, Trimestre, " +
                "Mes, NroMes FROM dimperiodoaten";

        try {
            List<PeriodoAtencionDTO> result = jdbcTemplate.query(sql, rowMapper);
            log.info("Se recuperaron {} registros de periodos de atención", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error al recuperar registros de periodos de atención: {}", e.getMessage());
            throw new RuntimeException("Error al recuperar registros de Periodo Atención", e);
        }
    }

    @Override
    public Long countRegistros() {
        try {
            Long count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM dimperiodoaten",
                    Long.class
            );
            log.info("Total de registros en periodos de atención: {}", count);
            return count;
        } catch (Exception e) {
            log.error("Error al contar registros de periodos de atención: {}", e.getMessage());
            throw new RuntimeException("Error al contar registros de Periodo Atención", e);
        }
    }

    @Override
    public void saveAll(List<PeriodoAtencionDTO> registros) {
        String sql = "INSERT INTO dimperiodoaten (PeriodoKey, Periodo, Anio, " +
                "Semestre, Trimestre, Mes, NroMes) VALUES (?,?,?,?,?,?,?)";

        try {
            jdbcTemplate.batchUpdate(sql, registros, registros.size(),
                    (ps, registro) -> {
                        ps.setInt(1, registro.getPeriodoKey());
                        ps.setString(2, registro.getPeriodo());
                        ps.setInt(3, registro.getAnio());
                        ps.setString(4, registro.getSemestre());
                        ps.setString(5, registro.getTrimestre());
                        ps.setString(6, registro.getMes());
                        ps.setInt(7, registro.getNroMes());
                    }
            );
            log.info("Se guardaron {} registros en periodos de atención", registros.size());
        } catch (Exception e) {
            log.error("Error al guardar registros en periodos de atención: {}", e.getMessage());
            throw new RuntimeException("Error al guardar registros en Periodo Atención", e);
        }
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update("DELETE FROM dimperiodoaten");
            log.info("Se eliminaron todos los registros de periodos de atención");
        } catch (Exception e) {
            log.error("Error al eliminar registros de periodos de atención: {}", e.getMessage());
            throw new RuntimeException("Error al eliminar registros de Periodo Atención", e);
        }
    }
}
