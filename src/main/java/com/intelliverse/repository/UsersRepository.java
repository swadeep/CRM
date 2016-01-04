package com.intelliverse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.intelliverse.model.Users;

public interface UsersRepository extends MongoRepository<Users, String> {

	public Users findByEmail(String email);
    public Users findByResetPasswordToken(String token);
}
