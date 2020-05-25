package in.codeblog.ppmapi.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.codeblog.ppmapi.domain.ProjectTask;
import in.codeblog.ppmapi.service.MapValidationErrorService;
import in.codeblog.ppmapi.service.ProjectTaskService;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {
	@Autowired
	private ProjectTaskService projectTaskService;
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("/{backlog_id}")
	public ResponseEntity<?>addPTtoBacklog(@Valid @RequestBody ProjectTask projectTask,BindingResult result,@PathVariable String backlog_id)
	{
		ResponseEntity<?>errorMap=mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null)
			return errorMap;
		ProjectTask projectTask2=projectTaskService.addProjectTask(backlog_id, projectTask);
		return new ResponseEntity<ProjectTask>(projectTask2,HttpStatus.CREATED);
	}

}
