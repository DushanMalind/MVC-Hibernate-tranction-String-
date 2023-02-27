package lk.ijse.computerShop.generator;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.boot.model.relational.SqlStringGenerationContext;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class CustomerIDGenerator implements IdentifierGenerator {

    private final String DEFAULT_SEQUENCE_NAME = "customer_hib_sequence";


    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        IdentifierGenerator.super.configure(type, params, serviceRegistry);
    }

    @Override
    public void registerExportables(Database database) {
        IdentifierGenerator.super.registerExportables(database);
    }

    @Override
    public void initialize(SqlStringGenerationContext context) {
        IdentifierGenerator.super.initialize(context);
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object object) throws HibernateException {
        Serializable result = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String prefix="C";

        try {
            connection=sharedSessionContractImplementor.connection();
            statement=connection.createStatement();

            try {
                statement.executeUpdate("UPDATE " + DEFAULT_SEQUENCE_NAME + " SET next_val=LAST_INSERT_ID(next_val+1)");
                resultSet = statement.executeQuery("SELECT next_val FROM  " + DEFAULT_SEQUENCE_NAME);

            } catch (Exception e) {

                System.out.println("NO TABLE");

                statement.execute("CREATE table " + DEFAULT_SEQUENCE_NAME + " (next_val INT NOT NULL)");
                statement.executeUpdate("INSERT INTO " + DEFAULT_SEQUENCE_NAME + " VALUES(0)");
                statement.executeUpdate("UPDATE " + DEFAULT_SEQUENCE_NAME + " SET next_val=LAST_INSERT_ID(next_val+1)");
                resultSet = statement.executeQuery("SELECT next_val FROM  " + DEFAULT_SEQUENCE_NAME);

            }
            if (resultSet.next()) {

                int nextValue = resultSet.getInt(1);
                String suffix = String.format("%03d", nextValue);
                result = prefix.concat(suffix);
                System.out.println("Custom generated sequence is : "+result);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean supportsJdbcBatchInserts() {
        return IdentifierGenerator.super.supportsJdbcBatchInserts();
    }
}
