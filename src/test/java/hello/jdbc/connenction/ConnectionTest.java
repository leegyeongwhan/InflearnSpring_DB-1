package hello.jdbc.connenction;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.DataInput;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connenction.ConnectionConst.*;

@Slf4j
public class ConnectionTest {

    @Test
    void diverManager() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection connection1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        log.info("connection={} ,class{}", connection, connection.getClass());
        log.info("connection1={} ,class{}", connection1, connection1.getClass());
    }

    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(URL);
        hikariDataSource.setUsername(USERNAME);
        hikariDataSource.setPassword(PASSWORD);
        hikariDataSource.setMaximumPoolSize(10);
        hikariDataSource.setPoolName("MyPool");

        userDatasource(hikariDataSource);
        Thread.sleep(1000);
    }

    @Test
    void dataSourceDiverManager() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        userDatasource(dataSource);
    }

    private void userDatasource(DataSource dataSource) throws SQLException {
        Connection connection = dataSource.getConnection();
        Connection connection1 = dataSource.getConnection();
        log.info("connection={} ,class{}", connection, connection.getClass());
        log.info("connection1={} ,class{}", connection1, connection1.getClass());
    }
}
