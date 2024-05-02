package my.demo.controller.service;

import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import my.demo.controller.repo.SchoolRepo;
import my.demo.domain.School;
import my.demo.dto.SchoolDto;

@ApplicationScoped
public class SchoolService {

	@Inject
	SchoolRepo schoolRepo;
	
	public School get(String code) {
		Optional<School> optRow = schoolRepo.findByCode(code);
		return optRow.isPresent() ? optRow.get() : null;
	}
	
	@Transactional
	public void add(SchoolDto dto) {
		School school = new School();
		school.setCode(dto.getCode());
		school.setName(dto.getName());
		System.out.println(school);
		schoolRepo.persist(school);
	}
	
	@Transactional
	public void update(SchoolDto dto) {
		Optional<School> optSchool = schoolRepo.findByCode(dto.getCode()); // 假设有一个根据 ID 查询实体的方法
		
        if (optSchool.isPresent()) {
        	School school = optSchool.get();
            school.setCode(dto.getCode());
            school.setName(dto.getName());
            schoolRepo.persist(school); // 使用 persist 方法更新实体
        } else {
        	
        }
	}
	
	@Transactional
	public void delete(SchoolDto dto) {
		School school = new School();
		school.setCode(dto.getCode());
		school.setName(dto.getName());
		schoolRepo.delete(school);
	}
}
