package App.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
 
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
 
public final class DbTestUtil {
 
    private DbTestUtil() {}
 
    public static void resetAutoIncrementColumns(ApplicationContext applicationContext,
                                                 String... tableNames) throws SQLException {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        String resetSqlTemplate = getResetSqlTemplate(applicationContext);
        try (Connection dbConnection = dataSource.getConnection()) {
        	Statement st = dbConnection.createStatement();
        	st.execute("SET FOREIGN_KEY_CHECKS = 0;");
            for (String resetSqlArgument: tableNames) {
            	try (Statement statement = dbConnection.createStatement()) {
                    String resetSql = String.format(resetSqlTemplate, resetSqlArgument);
                    statement.execute(resetSql);
                } catch(Exception e) {
                	System.out.println(e);
                }
            }
            st.execute("SET FOREIGN_KEY_CHECKS = 1;");
        }
    }
 
    private static String getResetSqlTemplate(ApplicationContext applicationContext) {
        Environment environment = applicationContext.getBean(Environment.class);
        return environment.getRequiredProperty("test.reset.sql.template");
    }
}
