package in.codeblog.ppmapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.codeblog.ppmapi.domain.Backlog;
import in.codeblog.ppmapi.domain.ProjectTask;
import in.codeblog.ppmapi.repository.BacklogRepository;
import in.codeblog.ppmapi.repository.ProjectTaskRepository;

@Service
public class ProjectTaskService {
	@Autowired
	private BacklogRepository backlogRepository;
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	public ProjectTask addProjectTask(String projectIdentifier,ProjectTask projectTask)
	{
		
		//ProjectTask should be added to specific project.project!=null,backlog exist
		Backlog backlog=backlogRepository.findByProjectIdentifier(projectIdentifier);
		
		//Set Backlog to projectTask
		projectTask.setBacklog(backlog);
		
		//ProjectTask Sequence should be like projId-1
		Integer backlogSequence=backlog.getPTSequence();
		
		//update projectTask sequence no.
		backlogSequence++;
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

}
