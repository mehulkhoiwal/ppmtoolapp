package com.yash.ppmtoolapi.exception;

public class ProjectIdExceptionResponse {
	String projectIdentifier;
	public ProjectIdExceptionResponse(String projectIdentifier)
	{
		super();
		this.projectIdentifier=projectIdentifier;
	}
	public String getProjectIdentifier() {
		return projectIdentifier;
	}
	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}
	
}