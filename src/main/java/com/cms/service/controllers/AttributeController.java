package com.cms.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.cms.service.entities.AttributeDto;
import com.cms.service.services.AttributeService;

/**
 * Controller class for handling Attribute-related HTTP requests.
 */

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/attributes")
public class AttributeController {

    // Autowiring AttributeService to enable dependency injection.

    private AttributeService attributeService;

    /**
     * Constructor-based dependency injection.
     *
     * @param attributeService The service providing business logic for Attribute operations.
     */
    @Autowired
    public AttributeController(AttributeService attributeService){
        this.attributeService = attributeService;
    }

    /**
     * Endpoint for creating a new Attribute.
     *
     * @param attributeDto The Attribute data to be created.
     * @return ResponseEntity containing the created AttributeDto and HTTP status.
     */

    //http://localhost:8080/api/attributes
    @PostMapping
    public ResponseEntity<AttributeDto> createAttribute(@RequestBody AttributeDto attributeDto){
        return new ResponseEntity<>(attributeService.createAttribute(attributeDto), HttpStatus.CREATED);
    }

    /**
     * Endpoint for retrieving all Attributes.
     *
     * @return List of AttributeDto representing all Attributes.
     */

    //http://localhost:8080/api/attributes
    @GetMapping
    public List<AttributeDto> getAllAttributes(){
        return attributeService.getAllAttributes();
    }

    /**
     * Endpoint for retrieving an Attribute by its ID.
     *
     * @param id The ID of the Attribute to be retrieved.
     * @return ResponseEntity containing the retrieved AttributeDto and HTTP status.
     */

    //http://localhost:8080/api/attributes/1
    @GetMapping("/{id}")
    public ResponseEntity<AttributeDto> getAttributeId(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(attributeService.getAttributeById(id));

    }

    /**
     * Endpoint for updating an Attribute by its ID.
     *
     * @param attributeDto The updated Attribute data.
     * @param id The ID of the Attribute to be updated.
     * @return ResponseEntity containing the updated AttributeDto and HTTP status.
     */

    //http://localhost:8080/api/attributes/1
    @PutMapping("/{id}")
    public ResponseEntity<AttributeDto> updateAttribute(@RequestBody AttributeDto attributeDto,@PathVariable(name = "id") Long id){
        AttributeDto attributeResponse = attributeService.updateAttribute(attributeDto, id);
        return new ResponseEntity<>(attributeResponse,HttpStatus.OK);
    }

    /**
     * Endpoint for deleting an Attribute by its ID.
     *
     * @param id The ID of the Attribute to be deleted.
     * @return ResponseEntity with a success message and HTTP status.
     */
    
    //http://localhost:8080/api/attributes/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAttributeById(@PathVariable Long id){
        attributeService.deleteAttributeById(id);
        return new ResponseEntity<>("Attribute deleted Successfully", HttpStatus.OK);
    }
}
