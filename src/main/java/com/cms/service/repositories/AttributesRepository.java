package com.cms.service.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.cms.service.entities.Attributes;

@Repository
public interface AttributesRepository extends JpaRepository<Attributes,Long> {
}



















//The JPA module provides SimpleJpaRepository class which internally implements JPARepository
//This SimpleJparespository was annotated with @repository.
//All the emthods defined in simple JPA Repository are all transactional
//So we need to annoatte the service class with @Transactional.