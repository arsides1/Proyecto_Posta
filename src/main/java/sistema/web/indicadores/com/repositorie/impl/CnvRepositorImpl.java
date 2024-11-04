package sistema.web.indicadores.com.repositorie.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sistema.web.indicadores.com.model.dto.CnvDTO;
import sistema.web.indicadores.com.repositorie.CnvRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CnvRepositorImpl implements CnvRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<CnvDTO> rowMapper = (rs, rowNum) -> {
        CnvDTO dto = new CnvDTO();
        dto.setNuCNV(rs.getString("NU_CNV"));
        dto.setPesoNacido(rs.getString("PESO_NACIDO"));
        dto.setDurEmbParto(rs.getString("DUR_EMB_PARTO"));
        return dto;
    };

    public List<CnvDTO> findAll() {
        return jdbcTemplate.query("SELECT * FROM tbl_cnv", rowMapper);
    }

    public CnvDTO findByNuCNV(String nuCNV) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM tbl_cnv WHERE NU_CNV = ?",
                rowMapper,
                nuCNV
        );
    }

    public Long countRegistros() {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM tbl_cnv",
                Long.class
        );
    }

    public void saveAll(List<CnvDTO> registros) {
        jdbcTemplate.batchUpdate(
                "INSERT INTO tbl_cnv (NU_CNV, PESO_NACIDO, DUR_EMB_PARTO) VALUES (?, ?, ?)",
                registros,
                registros.size(),
                (ps, registro) -> {
                    ps.setString(1, registro.getNuCNV());
                    ps.setString(2, registro.getPesoNacido());
                    ps.setString(3, registro.getDurEmbParto());
                }
        );
    }
}
