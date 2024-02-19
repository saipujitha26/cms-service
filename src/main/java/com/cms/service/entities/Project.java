package com.cms.service.entities;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
* The Project entity class represents a project within the CMS (Content
* Management System). It contains information about the project such as its ID,
* name, stage, and relevant dates.
* 
* @author Jashwanth
*/
@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
public class Project {
	
	/** The unique identifier for the project. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
 
	/** The name of the project. */
	private String name;
 
	/** The stage of the project (e.g., planning, development, testing, etc.). */
	private String stage;
 
	/** The date when the project was initially drafted. */
	private LocalDate draftDate;
 
	/** The date when the project planning started. */
	private LocalDate planningDate;
 
	/** The date when the project was released. */
	private LocalDate releasedDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public LocalDate getDraftDate() {
		return draftDate;
	}

	public void setDraftDate(LocalDate draftDate) {
		this.draftDate = draftDate;
	}

	public LocalDate getPlanningDate() {
		return planningDate;
	}

	public void setPlanningDate(LocalDate planningDate) {
		this.planningDate = planningDate;
	}

	public LocalDate getReleasedDate() {
		return releasedDate;
	}

	public void setReleasedDate(LocalDate releasedDate) {
		this.releasedDate = releasedDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(draftDate, id, name, planningDate, releasedDate, stage);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		return Objects.equals(draftDate, other.draftDate) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(planningDate, other.planningDate) && Objects.equals(releasedDate, other.releasedDate)
				&& Objects.equals(stage, other.stage);
	}

	public Project(int id, String name, String stage, LocalDate draftDate, LocalDate planningDate,
			LocalDate releasedDate) {
		super();
		this.id = id;
		this.name = name;
		this.stage = stage;
		this.draftDate = draftDate;
		this.planningDate = planningDate;
		this.releasedDate = releasedDate;
	}

	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
