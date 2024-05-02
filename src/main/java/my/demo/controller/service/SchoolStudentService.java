package my.demo.controller.service;

import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import my.demo.controller.repo.SchoolStudentRepo;
import my.demo.domain.SchoolStudent;

@ApplicationScoped
public class SchoolStudentService {

	@Inject
	SchoolStudentRepo repo;
	
	public SchoolStudent getStudentInfo(String code) {
		Optional<SchoolStudent> resultRowOpt = repo.findByCode(code);
		if(resultRowOpt.isPresent()) {
			return repo.findByCode(code).get();
		}
		return null;
	}
	
}
