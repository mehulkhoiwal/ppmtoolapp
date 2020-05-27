package in.codeblog.ppmapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import in.codeblog.ppmapi.domain.ProjectTask;


public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {
	List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);
	ProjectTask findByProjectSequence(String sequence);
}
