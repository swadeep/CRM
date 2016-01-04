package com.intelliverse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.intelliverse.model.Users;

public interface UsersRepository extends MongoRepository<Users, String> {

}
