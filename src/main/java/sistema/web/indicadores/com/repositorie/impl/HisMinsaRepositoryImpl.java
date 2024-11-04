package sistema.web.indicadores.com.repositorie.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sistema.web.indicadores.com.model.dto.HisMinsaDTO;
import sistema.web.indicadores.com.repositorie.HisMinsaRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HisMinsaRepositoryImpl implements HisMinsaRepository {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<HisMinsaDTO> rowMapper = (rs, rowNum) -> {
        HisMinsaDTO dto = new HisMinsaDTO();
        dto.setIdCita(rs.getString("id_cita"));
        dto.setAnio(rs.getString("anio"));
        dto.setFechaAtencion(rs.getString("Fecha_Atencion"));
        dto.setLote(rs.getString("Lote"));
        dto.setNumPag(rs.getInt("Num_Pag"));
        dto.setNumReg(rs.getInt("Num_Reg"));
        // ... continuar con todos los campos
        return dto;
    };

    public HisMinsaDTO findByIdCita(String idCita) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM tbl_hisminsa WHERE id_cita = ?",
                rowMapper,
                idCita
        );
    }

    public List<HisMinsaDTO> findByAnio(String anio) {
        return jdbcTemplate.query(
                "SELECT * FROM tbl_hisminsa WHERE anio = ?",
                rowMapper,
                anio
        );
    }

    public Long countRegistros() {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM tbl_hisminsa",
                Long.class
        );
    }

    public void saveAll(List<HisMinsaDTO> registros) {
        String sql = "INSERT INTO tbl_hisminsa (id_cita, anio, Fecha_Atencion, Lote, Num_Pag, " +
                "Num_Reg /* continuar con todas las columnas */) VALUES (?,?,?,?,?,? /* ... */)";

        jdbcTemplate.batchUpdate(sql, registros, registros.size(),
                (ps, registro) -> {
                    ps.setString(1, registro.getIdCita());
                    ps.setString(2, registro.getAnio());
                    ps.setString(3, registro.getFechaAtencion());
                    ps.setString(4, registro.getLote());
                    ps.setObject(5, registro.getNumPag());
                    ps.setObject(6, registro.getNumReg());
                    // ... continuar con todos los campos
                }
        );
    }
}
