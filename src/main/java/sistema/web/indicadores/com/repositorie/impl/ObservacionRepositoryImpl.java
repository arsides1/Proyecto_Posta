package sistema.web.indicadores.com.repositorie.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sistema.web.indicadores.com.model.dto.HisMinsaDTO;
import sistema.web.indicadores.com.model.dto.ObservacionDTO;
import sistema.web.indicadores.com.repositorie.ObservacionRepository;

import java.util.List;
@Repository
@RequiredArgsConstructor
@Slf4j
public class ObservacionRepositoryImpl implements ObservacionRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<ObservacionDTO> rowMapper = (rs, rowNum) -> {
        ObservacionDTO dto = new ObservacionDTO(); // Cambiado de HisMinsaDTO a ObservacionDTO
        dto.setObservacion(rs.getString("Observacion"));
        dto.setPrograma(rs.getString("PROGRAMA"));
        dto.setAnio(rs.getString("ANIO"));
        dto.setMes(rs.getString("MES"));
        dto.setDia(rs.getString("DIA"));
        dto.setFechaAtencion(rs.getString("Fecha_Atencion"));
        dto.setLote(rs.getString("Lote"));
        dto.setNumPag(rs.getInt("Num_Pag"));
        dto.setNumReg(rs.getInt("Num_Reg"));
        dto.setIdCita(rs.getString("Id_Cita"));
        dto.setIdUps(rs.getString("Id_Ups"));
        dto.setIdCondicionEstablecimiento(rs.getString("Id_Condicion_Establecimiento"));
        dto.setIdCondicionServicio(rs.getString("Id_Condicion_Servicio"));
        dto.setIdEtnia(rs.getString("Id_Etnia"));
        dto.setIdPaciente(rs.getString("Id_Paciente"));
        dto.setEdadReg(rs.getInt("Edad_Reg"));
        dto.setTipoEdad(rs.getString("Tipo_Edad"));
        dto.setCodigoItem(rs.getString("Codigo_Item"));
        dto.setDescripcionItem(rs.getString("Descripcion_Item"));
        dto.setFgTipo(rs.getString("Fg_Tipo"));
        dto.setTipoDiagnostico(rs.getString("Tipo_Diagnostico"));
        dto.setValorLab(rs.getString("Valor_Lab"));
        dto.setHemoglobina(rs.getBigDecimal("Hemoglobina"));
        dto.setIdCorrelativoItem(rs.getInt("Id_Correlativo_Item"));
        dto.setIdCorrelativoLab(rs.getInt("Id_Correlativo_Lab"));
        dto.setIdGenero(rs.getString("id_genero"));
        dto.setHistoriaClinica(rs.getString("Historia_Clinica"));
        dto.setDocPaciente(rs.getString("Doc_Paciente"));
        dto.setPaciente(rs.getString("PACIENTE"));
        dto.setDocPersonal(rs.getString("Doc_Personal"));
        dto.setAtendio(rs.getString("ATENDIO"));
        dto.setIdColegio(rs.getString("Id_Colegio"));
        dto.setDocRegistrador(rs.getString("Doc_Registrador"));
        dto.setRegistrador(rs.getString("REGISTRADOR"));
        dto.setIdProfesion(rs.getString("Id_Profesion"));
        return dto;
    };
    @Override
    public ObservacionDTO findByIdCita(String idCita) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM tbl_observaciones WHERE id_cita = ?",
                rowMapper,
                idCita
        );
    }

    @Override
    public List<ObservacionDTO> findAll() {
        String sql = "SELECT Observacion, PROGRAMA, ANIO, MES, DIA, Fecha_Atencion, Lote, " +
                "Num_Pag, Num_Reg, Id_Cita, Id_Ups, Id_Condicion_Establecimiento, " +
                "Id_Condicion_Servicio, Id_Etnia, Id_Paciente, Edad_Reg, Tipo_Edad, " +
                "Codigo_Item, Descripcion_Item, Fg_Tipo, Tipo_Diagnostico, Valor_Lab, " +
                "Hemoglobina, Id_Correlativo_Item, Id_Correlativo_Lab, id_genero, " +
                "Historia_Clinica, Doc_Paciente, PACIENTE, Doc_Personal, ATENDIO, " +
                "Id_Colegio, Doc_Registrador, REGISTRADOR, Id_Profesion " +
                "FROM tbl_observaciones";

        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Long countRegistros() {
        String sql = "SELECT COUNT(*) FROM tbl_observaciones";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public void saveAll(List<ObservacionDTO> registros) {
        String sql = "INSERT INTO tbl_observaciones (Observacion, PROGRAMA, ANIO, MES, DIA, " +
                "Fecha_Atencion, Lote, Num_Pag, Num_Reg, Id_Cita, Id_Ups, " +
                "Id_Condicion_Establecimiento, Id_Condicion_Servicio, Id_Etnia, Id_Paciente, " +
                "Edad_Reg, Tipo_Edad, Codigo_Item, Descripcion_Item, Fg_Tipo, Tipo_Diagnostico, " +
                "Valor_Lab, Hemoglobina, Id_Correlativo_Item, Id_Correlativo_Lab, id_genero, " +
                "Historia_Clinica, Doc_Paciente, PACIENTE, Doc_Personal, ATENDIO, Id_Colegio, " +
                "Doc_Registrador, REGISTRADOR, Id_Profesion) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        jdbcTemplate.batchUpdate(sql, registros, registros.size(),
                (ps, registro) -> {
                    ps.setString(1, registro.getObservacion());
                    ps.setString(2, registro.getPrograma());
                    ps.setString(3, registro.getAnio());
                    ps.setString(4, registro.getMes());
                    ps.setString(5, registro.getDia());
                    ps.setString(6, registro.getFechaAtencion());
                    ps.setString(7, registro.getLote());
                    ps.setInt(8, registro.getNumPag());
                    ps.setInt(9, registro.getNumReg());
                    ps.setString(10, registro.getIdCita());
                    ps.setString(11, registro.getIdUps());
                    ps.setString(12, registro.getIdCondicionEstablecimiento());
                    ps.setString(13, registro.getIdCondicionServicio());
                    ps.setString(14, registro.getIdEtnia());
                    ps.setString(15, registro.getIdPaciente());
                    ps.setInt(16, registro.getEdadReg());
                    ps.setString(17, registro.getTipoEdad());
                    ps.setString(18, registro.getCodigoItem());
                    ps.setString(19, registro.getDescripcionItem());
                    ps.setString(20, registro.getFgTipo());
                    ps.setString(21, registro.getTipoDiagnostico());
                    ps.setString(22, registro.getValorLab());
                    ps.setBigDecimal(23, registro.getHemoglobina());
                    ps.setInt(24, registro.getIdCorrelativoItem());
                    ps.setInt(25, registro.getIdCorrelativoLab());
                    ps.setString(26, registro.getIdGenero());
                    ps.setString(27, registro.getHistoriaClinica());
                    ps.setString(28, registro.getDocPaciente());
                    ps.setString(29, registro.getPaciente());
                    ps.setString(30, registro.getDocPersonal());
                    ps.setString(31, registro.getAtendio());
                    ps.setString(32, registro.getIdColegio());
                    ps.setString(33, registro.getDocRegistrador());
                    ps.setString(34, registro.getRegistrador());
                    ps.setString(35, registro.getIdProfesion());
                }
        );
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM tbl_observaciones";
        jdbcTemplate.update(sql);
    }
}
