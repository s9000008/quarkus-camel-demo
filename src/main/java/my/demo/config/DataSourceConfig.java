package my.demo.config;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import io.agroal.api.AgroalDataSource;
import io.agroal.api.configuration.supplier.AgroalPropertiesReader;
import io.quarkus.agroal.DataSource;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DataSourceConfig {

	String connectionUrl="jdbc:postgresql://localhost:5432/panache-school";
	
	String username="postgres";
	
	String password="0000";
	
	public AgroalDataSource datasource() throws SQLException {
		Map<String, String> prop = new HashMap<>();
		prop.put(AgroalPropertiesReader.MAX_SIZE, "10");
		prop.put(AgroalPropertiesReader.MIN_SIZE, "10");
		prop.put(AgroalPropertiesReader.INITIAL_SIZE, "10");
		prop.put(AgroalPropertiesReader.MAX_LIFETIME_S, "300");
		prop.put(AgroalPropertiesReader.ACQUISITION_TIMEOUT_S, "30");
		prop.put(AgroalPropertiesReader.JDBC_URL, connectionUrl);
		prop.put(AgroalPropertiesReader.PRINCIPAL, username);
		prop.put(AgroalPropertiesReader.CREDENTIAL, password);
		return AgroalDataSource.from(new AgroalPropertiesReader().readProperties(prop).get());
	}
	
}
