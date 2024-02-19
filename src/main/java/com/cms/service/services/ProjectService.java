package com.cms.service.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cms.service.entities.Project;



/**
* The service interface for managing Project entities.
* It defines methods for adding, updating, retrieving, and deleting Project entities.
* Additionally, it provides methods for listing projects in paginated form.
* 
* @param <Project> The type of the entity managed by this service, in this case, Project.
* @author Jashwanth
*/
 

public interface ProjectService {
	
    /**
     * Adds a new project to the system.
     * @param project The project to add.
     * @return The added project.
     */
    public Project addProject(Project project);
 
    /**
     * Updates an existing project in the system.
     * @param project The project to update.
     * @return The updated project.
     */
    public Project updateProject(Project project);
 
    /**
     * Retrieves all projects from the system.
     * @return A list of all projects.
     */
    public List<Project> getAllProject();
 
    /**
     * Retrieves a project by its ID.
     * @param id The ID of the project to retrieve.
     * @return The retrieved project.
     */
    public Project getProject(int id);
 
    /**
     * Deletes a project from the system by its ID.
     * @param id The ID of the project to delete.
     */
    public void deleteProject(int id);
 
	/**
	 * Retrieves a paginated list of all projects.
	 * 
	 * @param offset   The offset of the page.
	 * @param pagesize The size of each page.
	 * @return A paginated list of projects.
	 */
    public Page<Project> listAllPage(int offset, int pagesize);

}
