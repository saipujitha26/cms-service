package com.cms.service.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing the "Attributes" table in the database.
 */

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder

@Entity
@Table(name = "Attributes")
public class Attributes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="Type" ,nullable=false)
    private String type;

    @Column(name="Name" ,nullable=false)
    private String name;

    @Column(name="Description" ,nullable=false)
    private String description;


    /**
     * Constructor with parameters to initialize the Attributes entity.
     *
     * @param type        The type of the attribute.
     * @param name        The name of the attribute.
     * @param description The description of the attribute.
     */

    public Attributes(String type, String name, String description) {
        this.type = type;
        this.name = name;
        this.description = description;
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Attributes [id=" + id + ", type=" + type + ", name=" + name + ", description=" + description + "]";
	}


	public Attributes(Long id, String type, String name, String description) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.description = description;
	}


	public Attributes() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
