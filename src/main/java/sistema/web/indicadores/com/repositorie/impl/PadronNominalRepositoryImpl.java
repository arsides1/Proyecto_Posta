package sistema.web.indicadores.com.repositorie.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sistema.web.indicadores.com.model.dto.IndicadorDTO;
import sistema.web.indicadores.com.model.dto.PadronNominalDTO;
import sistema.web.indicadores.com.repositorie.PadronNominalRepository;

import java.util.List;
@Repository
@RequiredArgsConstructor
@Slf4j
public class PadronNominalRepositoryImpl implements PadronNominalRepository {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<PadronNominalDTO> rowMapper = (rs, rowNum) -> {
        PadronNominalDTO dto = new PadronNominalDTO();
        dto.setTipoDoc(rs.getString("TIPO_DOC"));
        dto.setCodigoPn(rs.getString("CODIGO_PN"));
        dto.setNuCnv(rs.getString("nu_cnv"));
        dto.setCui(rs.getString("CUI"));
        dto.setNuDniMenor(rs.getString("nu_dni_menor"));
        dto.setTiDocIdentidad(rs.getString("ti_doc_identidad"));
        dto.setTipDoc(rs.getString("TIP_DOC"));
        dto.setDocPadron(rs.getString("DOC_PADRON"));
        dto.setApPaternoN(rs.getString("AP_PATERNO_N"));
        dto.setApMaternoN(rs.getString("AP_MATERNO_N"));
        dto.setNombresN(rs.getString("NOMBRES_N"));
        dto.setSexo(rs.getString("SEXO"));
        dto.setFeNacMenor(rs.getString("fe_nac_menor"));
        dto.setEdadDelNino(rs.getString("EDAD_DEL_NINO"));
        dto.setEje(rs.getString("EJE"));
        dto.setDireccionPn(rs.getString("DIRECCION_PN"));
        dto.setReferencia(rs.getString("REFERENCIA"));
        dto.setPnSubRegion(rs.getString("PN_SUB_REGION"));
        dto.setFed2020(rs.getString("FED2020"));
        dto.setUbigeo(rs.getString("UBIGEO"));
        dto.setNDpto(rs.getString("N_DPTO"));
        dto.setNProv(rs.getString("N_PROV"));
        dto.setNDistrito(rs.getString("N_DISTRITO"));
        dto.setCodCp(rs.getString("COD_CP"));
        dto.setCodigoEess(rs.getString("CODIGO_EESS"));
        dto.setIpressPn(rs.getString("IPRESS_PN"));
        dto.setCodEstab(rs.getString("COD_ESTAB"));
        dto.setIpressPnC(rs.getString("IPRESS_PN_C"));
        dto.setCodigoEessNacimiento(rs.getString("CODIGO_EESS_NACIMIENTO"));
        dto.setNomEessNacimiento(rs.getString("NOM_EESS_NACIMIENTO"));
        dto.setCodigoEessAdscripcion(rs.getString("CODIGO_EESS_ADSCRIPCION"));
        dto.setNEessAdscripcion(rs.getString("N_EESS_ADSCRIPCION"));
        dto.setTipoSeguro(rs.getString("TIPO_SEGURO"));
        dto.setSeguro(rs.getString("SEGURO"));
        dto.setTiSeguroMenor(rs.getString("ti_seguro_menor"));
        dto.setMadDni(rs.getString("MAD_DNI"));
        dto.setMadApPaterno(rs.getString("MAD_AP_PATERNO"));
        dto.setMadApMaterno(rs.getString("MAD_AP_MATERNO"));
        dto.setMadNombre(rs.getString("MAD_NOMBRE"));
        dto.setMadCelu(rs.getString("MAD_CELU"));
        dto.setJfDni(rs.getString("JF_DNI"));
        dto.setJfApPaterno(rs.getString("JF_AP_PATERNO"));
        dto.setJfApMaterno(rs.getString("JF_AP_MATERNO"));
        dto.setJfNombre(rs.getString("JF_NOMBRE"));
        return dto;
    };

    @Override
    public PadronNominalDTO findByTipoDoc(String tipoDoc) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM tbl_padronnominal WHERE TIPO_DOC = ?",
                    rowMapper,
                    tipoDoc
            );
        } catch (Exception e) {
            log.error("Error al buscar por TIPO_DOC {}: {}", tipoDoc, e.getMessage());
            throw new RuntimeException("Error al buscar en Padron Nominal", e);
        }
    }

    @Override
    public List<PadronNominalDTO> findAll() {
        String sql = "SELECT TIPO_DOC, CODIGO_PN, nu_cnv, CUI, nu_dni_menor, ti_doc_identidad, " +
                "TIP_DOC, DOC_PADRON, AP_PATERNO_N, AP_MATERNO_N, NOMBRES_N, SEXO, fe_nac_menor, " +
                "EDAD_DEL_NINO, EJE, DIRECCION_PN, REFERENCIA, PN_SUB_REGION, FED2020, UBIGEO, " +
                "N_DPTO, N_PROV, N_DISTRITO, COD_CP, CODIGO_EESS, IPRESS_PN, COD_ESTAB, " +
                "IPRESS_PN_C, CODIGO_EESS_NACIMIENTO, NOM_EESS_NACIMIENTO, " +
                "CODIGO_EESS_ADSCRIPCION, N_EESS_ADSCRIPCION, TIPO_SEGURO, SEGURO, " +
                "ti_seguro_menor, MAD_DNI, MAD_AP_PATERNO, MAD_AP_MATERNO, MAD_NOMBRE, " +
                "MAD_CELU, JF_DNI, JF_AP_PATERNO, JF_AP_MATERNO, JF_NOMBRE " +
                "FROM tbl_padronnominal";

        try {
            List<PadronNominalDTO> result = jdbcTemplate.query(sql, rowMapper);
            log.info("Se recuperaron {} registros del padrón nominal", result.size());
            return result;
        } catch (Exception e) {
            log.error("Error al recuperar registros del padrón nominal: {}", e.getMessage());
            throw new RuntimeException("Error al recuperar registros del Padrón Nominal", e);
        }
    }

    @Override
    public Long countRegistros() {
        try {
            Long count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM tbl_padronnominal",
                    Long.class
            );
            log.info("Total de registros en el padrón nominal: {}", count);
            return count;
        } catch (Exception e) {
            log.error("Error al contar registros del padrón nominal: {}", e.getMessage());
            throw new RuntimeException("Error al contar registros del Padrón Nominal", e);
        }
    }

    @Override
    public void saveAll(List<PadronNominalDTO> registros) {
        String sql = "INSERT INTO tbl_padronnominal (TIPO_DOC, CODIGO_PN, nu_cnv, CUI, " +
                "nu_dni_menor, ti_doc_identidad, TIP_DOC, DOC_PADRON, AP_PATERNO_N, " +
                "AP_MATERNO_N, NOMBRES_N, SEXO, fe_nac_menor, 'EDAD DEL NIÑO', EJE, " +
                "DIRECCION_PN, REFERENCIA, PN_SUB_REGION, FED2020, UBIGEO, N_DPTO, N_PROV, " +
                "N_DISTRITO, COD_CP, CODIGO_EESS, IPRESS_PN, COD_ESTAB, IPRESS_PN_C, " +
                "CODIGO_EESS_NACIMIENTO, NOM_EESS_NACIMIENTO, CODIGO_EESS_ADSCRIPCION, " +
                "N_EESS_ADSCRIPCION, TIPO_SEGURO, SEGURO, ti_seguro_menor, MAD_DNI, " +
                "MAD_AP_PATERNO, MAD_AP_MATERNO, MAD_NOMBRE, MAD_CELU, JF_DNI, JF_AP_PATERNO, " +
                "JF_AP_MATERNO, JF_NOMBRE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" +
                ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            jdbcTemplate.batchUpdate(sql, registros, registros.size(),
                    (ps, registro) -> {
                        ps.setString(1, registro.getTipoDoc());
                        ps.setString(2, registro.getCodigoPn());
                        ps.setString(3, registro.getNuCnv());
                        ps.setString(4, registro.getCui());
                        ps.setString(5, registro.getNuDniMenor());
                        ps.setString(6, registro.getTiDocIdentidad());
                        ps.setString(7, registro.getTipDoc());
                        ps.setString(8, registro.getDocPadron());
                        ps.setString(9, registro.getApPaternoN());
                        ps.setString(10, registro.getApMaternoN());
                        ps.setString(11, registro.getNombresN());
                        ps.setString(12, registro.getSexo());
                        ps.setString(13, registro.getFeNacMenor());
                        ps.setString(14, registro.getEdadDelNino());
                        ps.setString(15, registro.getEje());
                        ps.setString(16, registro.getDireccionPn());
                        ps.setString(17, registro.getReferencia());
                        ps.setString(18, registro.getPnSubRegion());
                        ps.setString(19, registro.getFed2020());
                        ps.setString(20, registro.getUbigeo());
                        ps.setString(21, registro.getNDpto());
                        ps.setString(22, registro.getNProv());
                        ps.setString(23, registro.getNDistrito());
                        ps.setString(24, registro.getCodCp());
                        ps.setString(25, registro.getCodigoEess());
                        ps.setString(26, registro.getIpressPn());
                        ps.setString(27, registro.getCodEstab());
                        ps.setString(28, registro.getIpressPnC());
                        ps.setString(29, registro.getCodigoEessNacimiento());
                        ps.setString(30, registro.getNomEessNacimiento());
                        ps.setString(31, registro.getCodigoEessAdscripcion());
                        ps.setString(32, registro.getNEessAdscripcion());
                        ps.setString(33, registro.getTipoSeguro());
                        ps.setString(34, registro.getSeguro());
                        ps.setString(35, registro.getTiSeguroMenor());
                        ps.setString(36, registro.getMadDni());
                        ps.setString(37, registro.getMadApPaterno());
                        ps.setString(38, registro.getMadApMaterno());
                        ps.setString(39, registro.getMadNombre());
                        ps.setString(40, registro.getMadCelu());
                        ps.setString(41, registro.getJfDni());
                        ps.setString(42, registro.getJfApPaterno());
                        ps.setString(43, registro.getJfApMaterno());
                        ps.setString(44, registro.getJfNombre());
                    }
            );
            log.info("Se guardaron {} registros en el padrón nominal", registros.size());
        } catch (Exception e) {
            log.error("Error al guardar registros en el padrón nominal: {}", e.getMessage());
            throw new RuntimeException("Error al guardar registros en el Padrón Nominal", e);
        }
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update("DELETE FROM tbl_padronnominal");
            log.info("Se eliminaron todos los registros del padrón nominal");
        } catch (Exception e) {
            log.error("Error al eliminar registros del padrón nominal: {}", e.getMessage());
            throw new RuntimeException("Error al eliminar registros del Padrón Nominal", e);
        }
    }
}