package sistema.web.indicadores.com.repositorie.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sistema.web.indicadores.com.model.dto.HisMinsaDTO;
import sistema.web.indicadores.com.repositorie.HisMinsaRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
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
        dto.setIdUps(rs.getString("Id_Ups"));
        dto.setDescripcionUps(rs.getString("Descripcion_Ups"));
        dto.setIdEstablecimiento(rs.getInt("Id_Establecimiento"));
        dto.setTipoDocPac(rs.getInt("tipo_doc_pac"));
        dto.setIdPaciente(rs.getString("Id_Paciente"));
        dto.setPaciente(rs.getString("Paciente"));
        dto.setDocPaciente(rs.getString("Doc_Paciente"));
        dto.setFichaFamiliar(rs.getString("Ficha_familiar"));
        dto.setTipoDocPer(rs.getInt("tipo_doc_per"));
        dto.setIdPersonal(rs.getString("Id_Personal"));
        dto.setPersonal(rs.getString("Personal"));
        dto.setDocPersonal(rs.getString("Doc_Personal"));
        dto.setIdProfesion(rs.getString("Id_Profesion"));
        dto.setTipoDocReg(rs.getInt("tipo_doc_reg"));
        dto.setIdRegistrador(rs.getString("Id_Registrador"));
        dto.setRegistrador(rs.getString("Registrador"));
        dto.setDocRegistrador(rs.getString("Doc_Registrador"));
        dto.setIdFinanciador(rs.getString("Id_Financiador"));
        dto.setIdCondicionEstablecimiento(rs.getString("Id_Condicion_Establecimiento"));
        dto.setIdCondicionServicio(rs.getString("Id_Condicion_Servicio"));
        dto.setAnioActualPaciente(rs.getInt("Anio_Actual_Paciente"));
        dto.setMesActualPaciente(rs.getInt("Mes_Actual_Paciente"));
        dto.setDiaActualPaciente(rs.getInt("Dia_Actual_Paciente"));
        dto.setIdTurno(rs.getString("Id_Turno"));
        dto.setCodigoItem(rs.getString("Codigo_item"));
        dto.setDescripcionItem(rs.getString("Descripcion_item"));
        dto.setFgTipo(rs.getString("Fg_Tipo"));
        dto.setValorLab(rs.getString("Valor_Lab"));
        dto.setTipoDiagnostico(rs.getString("Tipo_diagnostico"));
        dto.setIdCorrelativoItem(rs.getInt("Id_Correlativo_item"));
        dto.setIdCorrelativoLab(rs.getInt("Id_Correlativo_Lab"));
        dto.setHemoglobina(rs.getBigDecimal("Hemoglobina"));
        return dto;
    };

    public HisMinsaDTO findByIdCita(String idCita) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM tbl_hisminsa WHERE id_cita = ?",
                rowMapper,
                idCita
        );
    }

    @Override
    public List<HisMinsaDTO> findAll() {
        String sql = "SELECT id_cita, anio, Fecha_Atencion, Lote, Num_Pag, " +
                "Num_Reg, Id_Ups, Descripcion_Ups, Id_Establecimiento, tipo_doc_pac, Id_Paciente, " +
                "Paciente, Doc_Paciente, Ficha_Familiar, tipo_doc_per, Id_Personal, Personal, " +
                "Doc_Personal, Id_Profesion, tipo_doc_reg, Id_Registrador, Registrador, " +
                "Doc_Registrador, Id_Financiador, Id_Condicion_Establecimiento, " +
                "Id_Condicion_Servicio, Anio_Actual_Paciente, Mes_Actual_Paciente, " +
                "Dia_Actual_Paciente, Id_Turno, Codigo_Item, Descripcion_Item, Fg_Tipo, " +
                "Valor_Lab, Tipo_Diagnostico, Id_Correlativo_Item, Id_Correlativo_Lab, " +
                "Hemoglobina FROM tbl_hisminsa";

        try {
            List<HisMinsaDTO> result = jdbcTemplate.query(sql, rowMapper);
            log.info("Se recuperaron {} registros de tbl_hisminsa", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error al recuperar registros de tbl_hisminsa: {}", e.getMessage());
            throw new RuntimeException("Error al recuperar registros de HisMinsa", e);
        }
    }

    public List<HisMinsaDTO> findByAnio(String anio) {
        try {
            List<HisMinsaDTO> result = jdbcTemplate.query(
                    "SELECT * FROM tbl_hisminsa WHERE anio = ?",
                    rowMapper,
                    anio
            );
            log.info("Se recuperaron {} registros del año {} de tbl_hisminsa", result.size(), anio);
            return result;
        } catch (Exception e) {
            log.error("Error al recuperar registros por año {} de tbl_hisminsa: {}", anio, e.getMessage());
            throw new RuntimeException("Error al recuperar registros por año de HisMinsa", e);
        }
    }

    public Long countRegistros() {
        try {
            Long count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM tbl_hisminsa",
                    Long.class
            );
            log.info("Total de registros en tbl_hisminsa: {}", count);
            return count;
        } catch (Exception e) {
            log.error("Error al contar registros de tbl_hisminsa: {}", e.getMessage());
            throw new RuntimeException("Error al contar registros de HisMinsa", e);
        }
    }

    public void saveAll(List<HisMinsaDTO> registros) {
        String sql = "INSERT INTO tbl_hisminsa (id_cita, anio, Fecha_Atencion, Lote, Num_Pag, " +
                "Num_Reg, Id_Ups, Descripcion_Ups, Id_Establecimiento, tipo_doc_pac, Id_Paciente, " +
                "Paciente, Doc_Paciente, Ficha_Familiar, tipo_doc_per, Id_Personal, Personal, " +
                "Doc_Personal, Id_Profesion, tipo_doc_reg, Id_Registrador, Registrador, " +
                "Id_Financiador, Id_Condicion_Establecimiento, Id_Condicion_Servicio, " +
                "Anio_Actual_Paciente, Mes_Actual_Paciente, Dia_Actual_Paciente, " +
                "Id_Turno, Codigo_Item, Descripcion_Item, Fg_Tipo, Valor_Lab, " +
                "Tipo_Diagnostico, Id_Correlativo_Item, Id_Correlativo_Lab, Hemoglobina) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        jdbcTemplate.batchUpdate(sql, registros, registros.size(),
                (ps, registro) -> {
                    ps.setString(1, registro.getIdCita());
                    ps.setString(2, registro.getAnio());
                    ps.setString(3, registro.getFechaAtencion());
                    ps.setString(4, registro.getLote());
                    ps.setObject(5, registro.getNumPag());
                    ps.setObject(6, registro.getNumReg());
                    ps.setString(7, registro.getIdUps());
                    ps.setString(8, registro.getDescripcionUps());
                    ps.setInt(9, registro.getIdEstablecimiento());
                    ps.setInt(10, registro.getTipoDocPac());
                    ps.setString(11, registro.getIdPaciente());
                    ps.setString(12, registro.getPaciente());
                    ps.setString(13, registro.getDocPaciente());
                    ps.setString(14, registro.getFichaFamiliar());
                    ps.setInt(15, registro.getTipoDocPer());
                    ps.setString(16, registro.getIdPersonal());
                    ps.setString(17, registro.getPersonal());
                    ps.setString(18, registro.getDocPersonal());
                    ps.setString(19, registro.getIdProfesion());
                    ps.setInt(20, registro.getTipoDocReg());
                    ps.setString(21, registro.getIdRegistrador());
                    ps.setString(22, registro.getRegistrador());
                    ps.setString(23, registro.getIdFinanciador());
                    ps.setString(24, registro.getIdCondicionEstablecimiento());
                    ps.setString(25, registro.getIdCondicionServicio());
                    ps.setInt(26, registro.getAnioActualPaciente());
                    ps.setInt(27, registro.getMesActualPaciente());
                    ps.setInt(28, registro.getDiaActualPaciente());
                    ps.setString(29, registro.getIdTurno());
                    ps.setString(30, registro.getCodigoItem());
                    ps.setString(31, registro.getDescripcionItem());
                    ps.setString(32, registro.getFgTipo());
                    ps.setString(33, registro.getValorLab());
                    ps.setString(34, registro.getTipoDiagnostico());
                    ps.setObject(35, registro.getIdCorrelativoItem());
                    ps.setObject(36, registro.getIdCorrelativoLab());
                    ps.setBigDecimal(37, registro.getHemoglobina());
                }
        );
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update("DELETE FROM tbl_hisminsa");
            log.info("Se eliminaron todos los registros de tbl_hisminsa");
        } catch (Exception e) {
            log.error("Error al eliminar registros de tbl_hisminsa: {}", e.getMessage());
            throw new RuntimeException("Error al eliminar registros de HisMinsa", e);
        }
    }
}
