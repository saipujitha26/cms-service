package com.cms.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cms.service.entities.Project;
import com.cms.service.repositories.ProjectRepository;



/**
* Implementation of the ProjectService interface that provides methods for
* managing projects. It interacts with the ProjectRepository to perform CRUD
* operations on Project entities.
* 
* @author Jashwanth
*/
 
@Service
public class ProjectServiceImp implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
 
	@Override
	public Project addProject(Project project) {
		Project saveProject = projectRepository.save(project);
		// Save the project and return the saved project
		return saveProject;
	}
 
	@Override
	public Project updateProject(Project project) {
		// Update the project and return the updated project
		return projectRepository.save(project);
	}
 
	@Override
	public List<Project> getAllProject() {
		List<Project> allProjects = projectRepository.findAll();
		// Retrieve all projects
		return allProjects;
	}
 
	@Override
	public Project getProject(int id) {
		// Retrieve a project by its ID
		return projectRepository.findById(id).get();
	}
 
	@Override
	public void deleteProject(int id) {
		// Delete a project by its ID
		projectRepository.deleteById(id);
 
	}
 
	@Override
	public Page<Project> listAllPage(int offset, int pagesize) {
		Page<Project> Pages = projectRepository.findAll(PageRequest.of(offset, pagesize));
		// Retrieve a paginated list of projects
		return Pages;
	}
}
