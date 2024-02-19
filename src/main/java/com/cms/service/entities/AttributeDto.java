package com.cms.service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class AttributeDto {

    private Long id;
    private String type;
    private String name;
    private String description;
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
		return "AttributeDto [id=" + id + ", type=" + type + ", name=" + name + ", description=" + description + "]";
	}
	public AttributeDto(Long id, String type, String name, String description) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.description = description;
	}
	public AttributeDto() {
		super();
		// TODO Auto-generated constructor stub
	}

//    public AttributeDto(Long id, String type, String name, String description) {
//        this.id = id;
//        this.type = type;
//        this.name = name;
//        this.description = description;
//    }
    
    
}
