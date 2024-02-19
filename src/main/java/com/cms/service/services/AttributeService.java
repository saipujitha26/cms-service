package com.cms.service.services;



import java.util.List;

import com.cms.service.entities.AttributeDto;



public interface AttributeService {

    public AttributeDto createAttribute(AttributeDto attributeDto);

    public List<AttributeDto> getAllAttributes();

    public AttributeDto getAttributeById(long id);

    public AttributeDto updateAttribute(AttributeDto attributeDto, long id);

    public void deleteAttributeById(long id);


}
