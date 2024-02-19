package com.cms.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cms.service.entities.Project;



/**
* The repository interface for managing Project entities. It provides CRUD
* (Create, Read, Update, Delete) operations for Project entities. This
* interface extends JpaRepository which provides methods for interacting with
* the underlying database.
* 
* @param <Project> The type of the entity managed by this repository, in this
*                  case, Project.
* @param <Integer> The type of the ID of the entity managed by this repository,
*                  in this case, Integer.
* @author Jashwanth
*/
 
@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{

}
