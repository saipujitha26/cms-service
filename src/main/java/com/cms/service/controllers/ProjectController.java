package com.cms.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.service.entities.Project;
import com.cms.service.services.ProjectService;


/**
* The controller class for handling HTTP requests related to projects in the
* CMS. It defines endpoints for adding, updating, retrieving, and deleting
* projects. Additionally, it provides an endpoint for retrieving projects in
* paginated form.
* 
* @author Jashwanth
*/
 
@RestController
@RequestMapping("/cms")
@CrossOrigin("http://localhost:3000")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
 
	/**
	 * Endpoint for adding a new project.
	 * 
	 * @param project The project to add.
	 * @return ResponseEntity with the added project and HTTP status CREATED.
	 */
	@PostMapping
	public ResponseEntity<Project> addProject(@RequestBody Project project) {
		Project savedProject = projectService.addProject(project);
		return new ResponseEntity<Project>(savedProject, HttpStatus.CREATED);
 
	}
 
	/**
	 * Endpoint for updating an existing project.
	 * 
	 * @param project The project to update.
	 * @return ResponseEntity with the updated project and HTTP status OK.
	 */
	@PutMapping("/update")
	public ResponseEntity<Project> updateProject(@RequestBody Project project) {
		Project updatedProject = projectService.updateProject(project);
		return new ResponseEntity<Project>(updatedProject, HttpStatus.OK);
	}
 
	/**
	 * Endpoint for retrieving all projects.
	 * 
	 * @return ResponseEntity with a list of projects and HTTP status OK.
	 */
	@GetMapping
	public ResponseEntity<List<Project>> getAllProject() {
		List<Project> projects = projectService.getAllProject();
		return new ResponseEntity<List<Project>>(projects, HttpStatus.OK);
	}
 
	/**
	 * Endpoint for retrieving a project by its ID.
	 * 
	 * @param id The ID of the project to retrieve.
	 * @return ResponseEntity with the retrieved project and HTTP status OK.
	 */
	@GetMapping("/get/{id}")
	public ResponseEntity<Project> getProjectById(@PathVariable int id) {
		Project getProject = projectService.getProject(id);
		return new ResponseEntity<Project>(getProject, HttpStatus.OK);
	}
 
	/**
	 * Endpoint for deleting a project by its ID.
	 * 
	 * @param id The ID of the project to delete.
	 * @return ResponseEntity with HTTP status NO_CONTENT.
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProject(@PathVariable int id) {
		projectService.deleteProject(id);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
 
	/**
	 * Endpoint for retrieving projects in paginated form.
	 * 
	 * @param offset   The offset of the page.
	 * @param pagesize The size of each page.
	 * @return A paginated list of projects.
	 */
	@GetMapping("/page/{offset}/{pagesize}")
	public Page<Project> getbypage(@PathVariable int offset, @PathVariable int pagesize) {
		return projectService.listAllPage(offset, pagesize);
	}

}
