package in.codeblog.ppmapi.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.codeblog.ppmapi.domain.*;
import in.codeblog.ppmapi.exception.ProjectIdException;
import in.codeblog.ppmapi.repository.*;


@Service
public class ProjectService {
	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	private BacklogRepository backlogRepository;
	public Project saveOrUpdate(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			if(project.getId()==null)
			{
				Backlog backlog=new Backlog();
				project.setBacklog(backlog);
				backlog.setProject(project);
				backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			}
			if(project.getId()!=null)
			{
				project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
			}return projectRepository.save(project);
			}
		catch(Exception e)
		{
			throw new ProjectIdException("Project Id '"+project.getProjectIdentifier().toUpperCase()+"' already exists !");
		}
		
	}
	public Project findByProjectIdentifier(String projectId)
	{
		Project project=projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if(project==null)
		{
			throw new ProjectIdException("Project Id '"+projectId.toUpperCase()+"' does not exists !");
		}
		return project;
		
	}
	public Iterable<Project> findAllProjects()
	{
		return projectRepository.findAll();
	}
	public Project deleteProjectByIdentifier(String projectIdentifier)
	{
		Project project=projectRepository.findByProjectIdentifier(projectIdentifier);
		if(project==null)
		{
			throw new ProjectIdException("Project with Id '"+projectIdentifier+"' does not exists !");
		}
		projectRepository.delete(project);
		return project;
	}
	
}
