// BackupService.java
package sistema.web.indicadores.com.service;

import org.springframework.web.multipart.MultipartFile;
import sistema.web.indicadores.com.util.enums.DatabaseType;
import java.util.Map;

public interface BackupService {
    Map<String, Object> procesarBackup(MultipartFile file, DatabaseType databaseType, boolean clearExisting);
    Map<String, Object> obtenerEstadisticas();
}