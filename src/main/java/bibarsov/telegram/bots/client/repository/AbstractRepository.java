package bibarsov.telegram.bots.client.repository;

import javax.annotation.ParametersAreNonnullByDefault;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@ParametersAreNonnullByDefault
public abstract class AbstractRepository {

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("SQLite db driver is not on classpath");
        }
    }

    private Connection c;

    protected abstract String getTableDDL();

    protected abstract String getDbConnectionString();

    protected final Connection getConnection() throws SQLException {
        if (c == null || c.isClosed()) {
            synchronized (this) {
                if (c == null || c.isClosed()) {
                    c = DriverManager.getConnection(getDbConnectionString());
                    c.setAutoCommit(true);
                }
            }
        }
        return c;
    }

    public final void ensureTableExists() {
        try (Statement statement = this.getConnection().createStatement()) {
            statement.executeUpdate(getTableDDL());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
