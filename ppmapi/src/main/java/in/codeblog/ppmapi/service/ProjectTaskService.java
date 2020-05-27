package in.codeblog.ppmapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.codeblog.ppmapi.domain.Backlog;
import in.codeblog.ppmapi.domain.Project;
import in.codeblog.ppmapi.domain.ProjectTask;
import in.codeblog.ppmapi.exception.ProjectNotFoundException;
import in.codeblog.ppmapi.repository.BacklogRepository;
import in.codeblog.ppmapi.repository.ProjectRepository;
import in.codeblog.ppmapi.repository.ProjectTaskRepository;

@Service
public class ProjectTaskService {
	@Autowired
	private BacklogRepository backlogRepository;
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	@Autowired
	private ProjectRepository projectRepository;
	public ProjectTask addProjectTask(String projectIdentifier,ProjectTask projectTask)
	{
		try 
		{
			//ProjectTask should be added to specific project.project!=null,backlog exist
			Backlog backlog=backlogRepository.findByProjectIdentifier(projectIdentifier);
			
			//Set Backlog to projectTask
			projectTask.setBacklog(backlog);
			
			//ProjectTask Sequence should be like projId-1
			Integer backlogSequence=backlog.getPTSequence();
			
			//update projectTask sequence no.
			backlogSequence++;
			backlog.setPTSequence(backlogSequence);
			projectTask.setProjectSequence(projectIdentifier+"-"+backlogSequence);
			projectTask.setProjectIdentifier(projectIdentifier);
			
			//setting the default priority and status
			//if project task is created first time, then set the  default priority as 3
			if(projectTask.getPriority()==null)
			{
				projectTask.setPriority(3);
				
			}
			if(projectTask.getStatus()==""||projectTask.getStatus()==null)
			{
				projectTask.setStatus("TODO");
			}
			return projectTaskRepository.save(projectTask);
		}
		catch(Exception ex) 
		{
			throw new ProjectNotFoundException("Project Not Found !");
		}
		
						
	}
	public Iterable<ProjectTask> findBacklogById(String id)
	{
		Project project=projectRepository.findByProjectIdentifier(id);
		if(project==null)
		{
			throw new ProjectNotFoundException("Project with id : '"+id+"' does not exists !");
		}
		return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
	}
	public ProjectTask findPTByProjectSequence(String backlog_id,String pt_id)
	{
		return projectTaskRepository.findByProjectSequence(pt_id);
	}

}
