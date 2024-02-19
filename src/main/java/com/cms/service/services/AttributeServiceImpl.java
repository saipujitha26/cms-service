package com.cms.service.services;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.service.entities.AttributeDto;
import com.cms.service.entities.Attributes;
import com.cms.service.exceptions.AttributeResourceNotFoundException;
import com.cms.service.repositories.AttributesRepository;

/**
 * Implementation of the AttributeService interface.
 */

@Service
public class AttributeServiceImpl implements AttributeService {


    // Autowiring AttributeRepository to enable dependency injection.

    private AttributesRepository attributesRepository;

    /**
     * Constructor-based dependency injection.
     *
     * @param attributesRepository Repository for managing Attribute entities.
     */
    @Autowired
    public AttributeServiceImpl(AttributesRepository attributesRepository){
        this.attributesRepository = attributesRepository;
    }

    /**
     * Convert Attribute Entity to DTO.
     *
     * @param attributes The Attributes entity to be converted.
     * @return AttributeDto representing the converted DTO.
     */

    //convert Attribute Entity to DTO
    private AttributeDto mapToDto(Attributes attributes){
        AttributeDto attributeDto = new AttributeDto();
        attributeDto.setId(attributes.getId());
        attributeDto.setType(attributes.getType());
        attributeDto.setName(attributes.getName());
        attributeDto.setDescription(attributes.getDescription());
        return attributeDto;
    }

    /**
     * Convert DTO to Entity.
     *
     * @param attributeDto The AttributeDto to be converted.
     * @return Attributes representing the converted entity.
     */

    //convert DTO to Entity
    private Attributes mapToEntity(AttributeDto attributeDto){
        Attributes attributes = new Attributes();
        attributes.setType(attributeDto.getType());
        attributes.setName(attributeDto.getName());
        attributes.setDescription(attributeDto.getDescription());
        return attributes;
    }
    @Override
    public AttributeDto createAttribute(AttributeDto attributeDto) {

        Attributes attributes = mapToEntity(attributeDto);
        Attributes newAttribute = attributesRepository.save(attributes);

        AttributeDto attributeResponse = mapToDto(attributes);

        // Return the converted DTO for the created attribute
        return attributeResponse;
    }

    @Override
    public List<AttributeDto> getAllAttributes() {
        List<Attributes> attributesList = attributesRepository.findAll();

        // Convert the list of entities to a list of DTOs using stream and map
        return attributesList.stream().map(attributes -> mapToDto(attributes)).collect(Collectors.toList());
    }

    @Override
    public AttributeDto getAttributeById(long id) {
        Attributes attributes = attributesRepository.findById(id).orElseThrow(()-> new AttributeResourceNotFoundException("Attribute",id));

        // Return the converted DTO for the found attribute
        return mapToDto(attributes);
    }

    @Override
    public AttributeDto updateAttribute(AttributeDto attributeDto, long id) {
        Attributes attributes = attributesRepository.findById(id).orElseThrow(()-> new AttributeResourceNotFoundException("Attribute",id));

        // Update the entity with data from the DTO
        attributes.setType(attributeDto.getType());
        attributes.setName(attributeDto.getName());
        attributes.setDescription(attributeDto.getDescription());

        // Save the updated entity and return the converted DTO
        Attributes updateAttribute = attributesRepository.save(attributes);
        return mapToDto(updateAttribute);
    }

    @Override
    public void deleteAttributeById(long id) {

        Attributes attributes = attributesRepository.findById(id).orElseThrow(()-> new AttributeResourceNotFoundException("Attribute",id));

        // Delete the attribute by calling delete method on the repository
        attributesRepository.delete(attributes);
    }
}
