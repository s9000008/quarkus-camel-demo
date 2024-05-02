package my.demo.controller.repo;

import java.util.Optional;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import my.demo.config.DataSourceConfig;
import my.demo.domain.SchoolStudent;
@ApplicationScoped
public class SchoolStudentRepo implements PanacheRepository<SchoolStudent>{

	@Inject
    DataSourceConfig dataSourceConfig;	
	
	public Optional<SchoolStudent> findByCode(String code){
		return this.find("code", code).firstResultOptional();
	}
	
}
