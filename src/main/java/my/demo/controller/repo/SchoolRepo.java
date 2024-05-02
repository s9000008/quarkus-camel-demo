package my.demo.controller.repo;

import java.util.Optional;

import io.agroal.api.AgroalDataSource;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import my.demo.config.DataSourceConfig;
import my.demo.domain.School;

@ApplicationScoped
public class SchoolRepo implements PanacheRepository<School>{

	@Inject
    AgroalDataSource datasource;
	
	public Optional<School> findByCode(String code){
		return this.find("code", code).firstResultOptional();
	}
	
}
