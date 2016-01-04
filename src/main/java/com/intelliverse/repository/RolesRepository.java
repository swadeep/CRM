package com.intelliverse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.intelliverse.model.Roles;

public interface RolesRepository extends MongoRepository<Roles, String>{

}
