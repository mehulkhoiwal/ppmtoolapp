package com.yash.ppmtoolapi.repository;
import com.yash.ppmtoolapi.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
	@Override
	Iterable<Project> findAllById(Iterable<Long> ids);
	
	Project findByProjectIdentifier(String projectId);
	
	@Override
	Iterable<Project> findAll();
}
