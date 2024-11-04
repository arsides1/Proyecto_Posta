package sistema.web.indicadores.com.util.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sistema.web.indicadores.com.util.enums.DatabaseType;
import sistema.web.indicadores.com.util.parser.service.BackupParser;
import sistema.web.indicadores.com.util.parser.service.impl.MySqlBackupParser;
import sistema.web.indicadores.com.util.parser.service.impl.PostgresBackupParser;
import sistema.web.indicadores.com.util.parser.service.impl.SqlServerBackupParser;

@Component
public class BackupParserFactory {

    @Autowired
    private MySqlBackupParser mySqlBackupParser;

    @Autowired
    private SqlServerBackupParser sqlServerBackupParser;

    @Autowired
    private PostgresBackupParser postgresBackupParser;


    public BackupParser getParser(DatabaseType type) {
        return switch (type) {
            case MYSQL -> new MySqlBackupParser();
            case SQLSERVER -> new SqlServerBackupParser();
            case POSTGRESQL -> new PostgresBackupParser();
        };
    }
}