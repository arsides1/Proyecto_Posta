package sistema.web.indicadores.com.util.parser.service;

import org.springframework.web.multipart.MultipartFile;
import sistema.web.indicadores.com.model.dto.BackupData;
public interface BackupParser {
    BackupData parsearArchivo(MultipartFile file) throws Exception;
}
